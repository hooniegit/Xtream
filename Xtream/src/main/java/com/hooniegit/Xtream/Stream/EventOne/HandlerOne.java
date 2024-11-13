package com.hooniegit.Xtream.Stream.EventOne;

import java.util.HashMap;
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
public class HandlerOne extends Handler {
	
	/**
	 * On Event Task
	 */
	@Override
    protected void process(Event event) {
		System.out.println(">> Event 01 Started");

        List<String> list = event.getDataOne().getList();
    }

}
