package com.hooniegit.Xtream.Modules.Xtream.EventTwo;

import java.util.List;

import com.hooniegit.Xtream.Modules.Xtream.Handler;
import com.hooniegit.Xtream.Modules.Xtream.Configuration.Event;

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

