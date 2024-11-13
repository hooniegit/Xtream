package com.hooniegit.Xtream.Stream.EventTwo;

import java.util.List;

import com.hooniegit.Xtream.Stream.Handler;
import com.hooniegit.Xtream.Stream.Configuration.Event;

import lombok.Getter;

/**
 * <Sample> Custom Event Handler
 * - Run Process With Event
 * - Update Data (For Next Event)
 */
@Getter
public class HandlerTwo extends Handler {
	
	/**
	 * On Event Task
	 */
	@Override
    protected void process(Event event) {
		System.out.println(">> Event 02 Started");

        List<String> list = event.getDataTwo().getList();
    }

}

