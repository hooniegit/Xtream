package com.hooniegit.Xtream.Modules.Xtream.Configuration;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

import com.hooniegit.Xtream.Modules.Xtream.EventOne.DataOne;
import com.hooniegit.Xtream.Modules.Xtream.EventTwo.DataTwo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * <Super> Event Class
 *  : Use HashMap<String, Object> as Reference
 */
@RequiredArgsConstructor
@Getter @Setter
public class Event {

	// ** <ADD PROPERTIES HERE> **
    private DataOne dataOne;
	private DataTwo dataTwo;

	/**
	 * Clear Properties
	 */
	public void clear() {
		this.dataOne = null;
		this.dataTwo = null;
	}

}