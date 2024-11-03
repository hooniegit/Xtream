package com.hooniegit.Xtream.Stream.Handlers;

import com.hooniegit.Xtream.Stream.Event;
import com.hooniegit.Xtream.Stream.Handler;

import lombok.Getter;

/**
 * <Sample> Custom Event Handler
 * - Run Process With Event
 */
@Getter
public class Event02Handler extends Handler {
	
	/**
	 * On Event Process
	 */
	@Override
    protected void process(Event event) {
		System.out.println(">> Event 02 Started");
		
		// Task Zone
        System.out.println("[Received] new.id : " + (String) event.getData().get("id"));
        System.out.println("[Received] old.email : " + (String) event.getData().get("email"));
        
        // No After Events To Share Data
    }

}
