package com.hooniegit.Xtream.Netty.Modules;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

import java.io.*;

public class Serialization {

    /**
     * Pool ByteBufferClass
     */
    static class ByteBufFactory extends BasePooledObjectFactory<ByteBuf> {
        @Override
        public ByteBuf create() {
            return Unpooled.buffer(1024); 
        }

        @Override
        public PooledObject<ByteBuf> wrap(ByteBuf byteBuf) {
            return new DefaultPooledObject<>(byteBuf);
        }

        @Override
        public void passivateObject(PooledObject<ByteBuf> pooledObject) {
            pooledObject.getObject().clear(); // Initialize Buffer
        }
    }

    private final ObjectPool<ByteBuf> byteBufPool;

    public Serialization() {
        GenericObjectPoolConfig<ByteBuf> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(10); // Max Pool Size
        config.setMaxIdle(5); // Max Idle Count
        byteBufPool = new GenericObjectPool<>(new ByteBufFactory(), config);
    }

    /**
     * Serialize T To ByteBuf
     * @param <T>
     * @param object
     * @return
     * @throws Exception
     */
    public <T extends Serializable> ByteBuf serialize(T object) throws Exception {
        ByteBuf buffer = byteBufPool.borrowObject();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            buffer.writeInt(bytes.length);
            buffer.writeBytes(bytes);
            return buffer;
        } catch (Exception e) {
            byteBufPool.invalidateObject(buffer);
            throw e;
        }
    }

    /**
     * De-Serialize ByteBuf to T
     * @param <T>
     * @param buffer
     * @return
     * @throws Exception
     */
    public <T> T deserialize(ByteBuf buffer) throws Exception {
        try {
            int length = buffer.readInt();
            byte[] bytes = new byte[length];
            buffer.readBytes(bytes);
            try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
                 ObjectInputStream ois = new ObjectInputStream(bais)) {
                return (T) ois.readObject(); // Not Checking Type Safety
            }
        } finally {
            byteBufPool.returnObject(buffer);
        }
    }
}