package com.hooniegit.Xtream.Stream;

import java.util.List;

/**
 * Build Stream Based On List<Handlers>
 */
public class StreamBuilder {
	
    private final List<Handler> handlers;

    public StreamBuilder(List<Handler> handlers) {
        this.handlers = handlers;
    }

    /**
     * Build Stream Based On List<Handlers>
     * @return
     */
    public Stream build() {
        return new Stream(handlers);
    }
    
}
