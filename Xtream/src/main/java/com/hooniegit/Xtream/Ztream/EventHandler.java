package com.hooniegit.Xtream.Ztream;

import org.springframework.stereotype.Component;

@Component
public class EventHandler {
    /**
     * Handle Published Event
     * @param event
     */
    public void handleEvent(byte[] data) {
        System.out.println("Handling event");
        
        // Need To Add TasksHere..

    }
}
