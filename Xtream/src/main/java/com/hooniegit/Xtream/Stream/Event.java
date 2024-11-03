package com.hooniegit.Xtream.Stream;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <Super> Event Class
 *  : Use HashMap<String, Object> as Reference
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Event {
	private HashMap<String, Object> data;

	/**
	 * Return Data Object Map (Only for Async Tasks)
	 * @return Data Object Map
	 */
	public HashMap<String, Object> getDataObject() {
		return new HashMap<>(this.data);
	}
	
	/**
	 * Update Data Object Map
	 * @param key
	 * @param value
	 */
	public void updateData(String key, Object value) {
		this.data.put(key, value);
	}
	
	/**
	 * Clear Properties
	 */
	public void clear() {
		this.data = null;
	}

}
