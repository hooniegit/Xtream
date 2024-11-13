package com.hooniegit.Xtream.Stream.Configuration;

import com.hooniegit.Xtream.Stream.ClearingEventHandler;
import com.hooniegit.Xtream.Stream.Handler;
import com.hooniegit.Xtream.Stream.EventOne.DataOne;
import com.hooniegit.Xtream.Stream.EventTwo.DataTwo;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import com.lmax.disruptor.util.DaemonThreadFactory;

import java.util.HashMap;
import java.util.List;

/**
 * LMAX Event Stream
 * - Based on Event
 * - Single Thread Handles All Of Handlers
 * - Clear Used Handler Data
 */
public class Stream {
    private final Disruptor<Event> disruptor;
    private final RingBuffer<Event> ringBuffer;

    public Stream(List<Handler> handlers) {

        disruptor = new Disruptor<>(
                Event::new,
                1024, // Buffer Size
                DaemonThreadFactory.INSTANCE,
                ProducerType.SINGLE,
                new SleepingWaitStrategy()
        );

        if (handlers.size() > 1) {
            disruptor.handleEventsWith(handlers.get(0));
            for (int i = 1; i < handlers.size(); i++) {
            	if (i != handlers.size() - 1) {
            		disruptor.after(handlers.get(i - 1)).handleEventsWith(handlers.get(i));
            	} else {
            		disruptor.after(handlers.get(i - 1)).handleEventsWith(handlers.get(i))
            			.then(new ClearingEventHandler()); // Clear Finished Event
            	}
            }
        } else {
            disruptor.handleEventsWith(handlers.get(0));
        }

        disruptor.start();
        ringBuffer = disruptor.getRingBuffer();
    }

    /**
    *
    * @throws Exception
    */
    public void stop() throws Exception {
        disruptor.shutdown();
    }

    // ** <ADD INITIALIZERS HERE> **
    /**
     * Publish Event With Initial Data Map
     * @param initialData
     */
    public void publishInitialEvent(DataOne dataOne) {
        long sequence = ringBuffer.next();
        try {
            Event event = ringBuffer.get(sequence);
            event.setDataOne(dataOne);
        } finally {
            ringBuffer.publish(sequence);
        }
    }

    /**
     * Publish Event With Initial Data Map
     * @param initialData
     */
    public void publishInitialEvent(DataTwo dataTwo) {
        long sequence = ringBuffer.next();
        try {
            Event event = ringBuffer.get(sequence);
            event.setDataTwo(dataTwo);
        } finally {
            ringBuffer.publish(sequence);
        }
    }

}
