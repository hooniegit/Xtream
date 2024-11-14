package com.hooniegit.Xtream.Modules.Data;

import java.util.EnumMap;

public class Data {
    private EnumMap<Type, Object> map = new EnumMap<>(Type.class);

    /**
     * Get EnumMap Data
     * @param <T>
     * @param type
     * @return
     */
    public <T> T get(Type type) {
        return (T) map.get(type);
    }
    
    /**
     * Set EnumMap Data
     * @param <T>
     * @param type
     * @param value
     */
    public <T> void set(Type type, T value) {
        map.put(type, value);
    }
}
