package com.hooniegit.Xtream.Modules.Xtream.Configuration;

import java.util.EnumMap;

public class Event {
    private EnumMap<EventType, Object> map = new EnumMap<>(EventType.class);

    /**
     * Get EnumMap Data
     * @param <T> 
     * @param type
     * @return
     */
    public <T> T get(EventType type) {
        return (T) map.get(type);
    }
    
    /**
     * Set EnumMap Data
     * @param <T>
     * @param type
     * @param value
     */
    public <T> void set(EventType type, T value) {
        map.put(type, value);
    }

	/**
	 * Clear Data Map
	 */
	public void clear() {
		this.map = null;
	}
}
