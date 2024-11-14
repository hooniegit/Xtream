package com.hooniegit.Xtream;

import java.util.ArrayList;
import java.util.List;

public class MilliRecorder {
    private List<Long> stamp = new ArrayList<>();

    public void put() {
        stamp.add(System.nanoTime());
    }

    public Long measure(int start, int end, int derive) {
        return (stamp.get(end) - stamp.get(start)) / derive;
    }

    public void clear() {
        stamp.clear();
    }

}
