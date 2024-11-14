package com.hooniegit.Xtream.Modules.Event;

import java.util.EnumMap;
import java.util.function.Supplier;

public class Data {
    private EnumMap<Type, Supplier<?>> map = new EnumMap<>(Type.class);

    /**
     * Get EnumMap Data
     * @param <T>
     * @param type
     * @return
     */
    public <T> T get(Type type) {
        Supplier<?> supplier = map.get(type);
        if (supplier != null) {
            Object value = supplier.get();
            try {
                return (T) value; // Not Checking Type Safety
            } catch (ClassCastException e) {
                throw new ClassCastException("Requested type does not match the actual type.");
            }
        }
        return null;
    }

    /**
     * Set EnumMap Data
     * @param <T>
     * @param type
     * @param value
     */
    public <T> void set(Type type, T value) {
        map.put(type, () -> value); // Add Supplier For Type Safety
    }

}

