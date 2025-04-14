package com.hooniegit.Xtream.Configuration.Handlers;

import com.hooniegit.Xtream.Tools.Event;
import com.hooniegit.Xtream.Tools.Handler;
import com.hooniegit.Xtream.Configuration.DataClass.Sample;

import lombok.Getter;

@Getter
public class Handler02 extends Handler<Sample> {

	@Override
    protected void process(Event<Sample> event) {
		// System.out.println(">> Event 02 Started");
        // System.out.println("[Received] : " + event.getData().getValue());
    }

}
