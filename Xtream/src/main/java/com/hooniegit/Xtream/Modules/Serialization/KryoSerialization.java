package com.hooniegit.Xtream.Modules.Serialization;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Data Serialization Class
 * - Kryo: For Rapid Data Serialization
 * - Netty: For Buffering
 * - Pool2: For Instance Re-Using
 * 
 * <Usage>
 * - Serialize T To ByteBuf
 * - De-Serialize ByteBuf To T
 */
public class KryoSerialization {

    private final ObjectPool<Kryo> kryoPool;

    public KryoSerialization() {
        GenericObjectPoolConfig<Kryo> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(10);
        kryoPool = new GenericObjectPool<>(new BasePooledObjectFactory<Kryo>() {
            @Override
            public Kryo create() {
                Kryo kryo = new Kryo();
                return kryo;
            }

            @Override
            public PooledObject<Kryo> wrap(Kryo kryo) {
                return new DefaultPooledObject<>(kryo);
            }
        }, config);
    }

    public <T> ByteBuf serialize(T object, Class<T> clazz) throws Exception {
        Kryo kryo = kryoPool.borrowObject();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Output output = new Output(baos)) {
            kryo.register(clazz);
            kryo.setReferences(false);
            kryo.writeClassAndObject(output, object);
            output.close();
            byte[] bytes = baos.toByteArray();
            ByteBuf buffer = Unpooled.buffer(bytes.length);
            buffer.writeBytes(bytes);
            return buffer;
        } finally {
            kryoPool.returnObject(kryo);
        }
    }

    public <T> T deserialize(ByteBuf buffer, Class<T> clazz) throws Exception {
        byte[] bytes = new byte[buffer.readableBytes()];
        buffer.readBytes(bytes);
        try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
            Input input = new Input(bais)) {
            Kryo kryo = kryoPool.borrowObject();
            try {
                // Check Registration
                if (kryo.getRegistration(clazz) == null) {
                    kryo.register(clazz);
                } else {
                }
    
                // Deserialize the object using the provided class type
                return (T) kryo.readClassAndObject(input);
            } finally {
                kryoPool.returnObject(kryo);
            }
        }
    }


}