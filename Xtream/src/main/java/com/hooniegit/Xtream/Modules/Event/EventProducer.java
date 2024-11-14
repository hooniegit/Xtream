package com.hooniegit.Xtream.Modules.Event;

import io.netty.channel.embedded.EmbeddedChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventProducer {

    private final EmbeddedChannel channel;

    @Autowired
    public EventProducer(FirstEventHandler firstEventHandler, SecondEventHandler secondEventHandler) {
        this.channel = new EmbeddedChannel(firstEventHandler, secondEventHandler);
    }

    public void produceEvent(Data data) {
        Event event = new Event(data);
        channel.writeInbound(event);
    }
}
