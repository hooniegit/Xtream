package com.hooniegit.Xtream.Modules.Stream;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import com.hooniegit.Xtream.Modules.Stream.Sample.One;
import com.hooniegit.Xtream.Modules.Stream.Sample.Two;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;

public class Processor {
    private  final GenericObjectPool<Two> returnPool;
    private final ByteBufAllocator allocator;

    public Processor() {
        GenericObjectPoolConfig<Two> poolConfig = new GenericObjectPoolConfig<>();
        poolConfig.setMaxTotal(100000000); // Max Pool Size
        this.returnPool = new GenericObjectPool<>(new Factory(), poolConfig);
        this.allocator = ByteBufAllocator.DEFAULT;
    }

    /**
     * Process Long List Data
     * - Create List Of New Instance
     * @param oneList
     * @return
     */
    public List<Two> processList(List<One> oneList) {
        return oneList.stream()
                .map(one -> {
                    ByteBuf byteBuf1 = allocator.buffer(); // For 'name'
                    ByteBuf byteBuf2 = allocator.buffer(); // For 'age'
                    try {
                        // Write Datas To ByteBuf
                        byteBuf1.writeBytes(one.getName().getBytes());
                        byteBuf2.writeInt(one.getAge());

                        // Get Instance From Pool & Set Datas
                        Two two = this.returnPool.borrowObject();
                        byte[] nameBytes = new byte[byteBuf1.readableBytes()];
                        byteBuf1.readBytes(nameBytes);
                        two.setName(new String(nameBytes, StandardCharsets.UTF_8));
                        two.setAge(byteBuf2.readInt());
                        return two;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return null;
                    } finally {
                        // Initialize 
                        byteBuf1.release();
                        byteBuf2.release();
                    }
                })
                .collect(Collectors.toList());
    }

    public void returnObjectsToPool(List<Two> processedList) {
        processedList.forEach(this.returnPool::returnObject);
    }

    public Two getObjectFromPool() throws Exception {
        return this.returnPool.borrowObject();
    }


}
