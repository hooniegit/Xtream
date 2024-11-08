package com.hooniegit.Xtream.Ztream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.springframework.stereotype.Service;

@Service
public class BufferService {
    private final ByteBuf buffer;
    private final int threshold;
    private final EventHandler eventHandler;

    public BufferService(EventHandler eventHandler) {
        this.buffer = PooledByteBufAllocator.DEFAULT.buffer(1024); // Size
        this.threshold = 800; // Threshold
        this.eventHandler = eventHandler;
    }

    /**
     * Add Datas To Buffer &  Release Event If Overflowed
     * @param data
     */
    public void addData(byte[] data) {
        if (buffer.readableBytes() + data.length > threshold) {
            eventHandler.handleEvent(data);
            buffer.clear();
        }
        buffer.writeBytes(data);
    }

    /**
     * Check Current Buffer Size
     * @return
     */
    public int getBufferSize() {
        return buffer.readableBytes();
    }

    /**
     * Release Buffer
     */
    public void release() {
        buffer.release();
    }
}
