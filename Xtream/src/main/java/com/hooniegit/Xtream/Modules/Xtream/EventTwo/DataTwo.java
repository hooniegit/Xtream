package com.hooniegit.Xtream.Modules.Xtream.EventTwo;

import java.util.EnumMap;
import java.util.List;

import com.hooniegit.Xtream.Modules.Xtream.EventOne.ParameterTypeOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class DataTwo {
    private EnumMap<ParameterTypeOne, Object> map = new EnumMap<>(ParameterTypeOne.class);

    /**
     * Get EnumMap Data
     * @param <T>
     * @param type
     * @return
     */
    public <T> T get(ParameterTypeOne type) {
        return (T) map.get(type);
    }
    
    /**
     * Set EnumMap Data
     * @param <T>
     * @param type
     * @param value
     */
    public <T> void set(ParameterTypeOne type, T value) {
        map.put(type, value);
    }
}
