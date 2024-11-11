package com.hooniegit.Xtream.Modules.Serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.Serializable;

/**
 * Data Serialization Class
 *
 * <Usage>
 * - Serialize T To Byte[]
 * - De-Serialize Byte[] To T
 */
public class ByteSerialization extends KryoSerialization {

    /**
     * Serialize T To Byte[]
     * @param <T>
     * @param object
     * @return
     * @throws Exception
     */
    public <T extends Serializable> byte[] serializeToBytes(T object, Class<T> clazz) throws Exception {
        ByteBuf buffer = serialize(object, clazz);
        try {
            int length = buffer.readableBytes();
            byte[] byteArray = new byte[length];
            buffer.readBytes(byteArray);
            return byteArray;
        } finally {
            buffer.release();
        }
    }

    /**
     * De-Serialize byte[] To T
     * @param <T>
     * @param byteArray
     * @return
     * @throws Exception
     */
    public <T> T deserializeFromBytes(byte[] byteArray, Class<T> clazz) throws Exception {
        ByteBuf buffer = Unpooled.wrappedBuffer(byteArray);
        return deserialize(buffer, clazz);
    }
}
