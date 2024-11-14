package com.hooniegit.Xtream.Modules.Xtream;

import com.hooniegit.Xtream.Modules.Xtream.Configuration.Event;

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