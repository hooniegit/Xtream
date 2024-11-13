package com.hooniegit.Xtream.Stream;

import com.hooniegit.Xtream.Stream.Configuration.Event;

/**
 * Clear Event Properties
 */
public class ClearingEventHandler extends Handler {

	/**
	 * Event Task : Clear Event Properties
	 */
	@Override
	protected void process(Event event) {
		System.out.println("EVENT CLEARED");
		event.clear();
	}


}