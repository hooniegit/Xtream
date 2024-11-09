package com.hooniegit.Xtream.Netty;

public class NettyEvent {
    private String message;

    public NettyEvent(String message) {
        this.message = message;
    }

    /**
     * Return Message Data
     * @return
     */
    public String getMessage() {
        return message;
    }
}
