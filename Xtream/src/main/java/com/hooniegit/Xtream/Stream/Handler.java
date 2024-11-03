package com.hooniegit.Xtream.Stream;

import com.lmax.disruptor.EventHandler;

import lombok.Getter;

/**
 * Event Handler Based On EventHandler<T>
 * 
 */
@Getter
public class Handler implements EventHandler<Event> {

    /**
     * On Event Task
     */
    @Override
    public void onEvent(Event event, long sequence, boolean endOfBatch) {
        process(event);    
    }

    /**
     * On Event Process : NEED TO OVERRIDE
     * @param event
     */
    protected void process(Event event) {
    	// ** Override This Method **
        // Need to Define Tasks & Sharing (At Extend Class)
    }

}
