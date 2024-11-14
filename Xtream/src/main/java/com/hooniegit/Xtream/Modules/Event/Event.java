package com.hooniegit.Xtream.Modules.Event;
import com.hooniegit.Xtream.Modules.Data.Data;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Event {
    private Data data;

    public Event(Data data) {
        this.data = data;
    }
}

