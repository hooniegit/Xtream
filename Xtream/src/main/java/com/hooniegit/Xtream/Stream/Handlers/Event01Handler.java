package com.hooniegit.Xtream.Stream.Handlers;

import java.util.HashMap;

import com.hooniegit.Xtream.Stream.Event;
import com.hooniegit.Xtream.Stream.Handler;

import lombok.Getter;

/**
 * <Sample> Custom Event Handler
 * - Run Process With Event
 * - Update Data (For Next Event)
 */
@Getter
public class Event01Handler extends Handler {
	
	/**
	 * On Event Task
	 */
	@Override
    protected void process(Event event) {
		System.out.println(">> Event 01 Started");
		
		// Task Zone
        System.out.println("[Received] " + (String) event.getData().get("id"));

        // Share Zone
        HashMap<String, Object> share = new HashMap<>();
        share.put("id", "HOONIEGIT");
        share.put("email", (String) event.getData().get("email"));
        event.setData(share);
    }

}
