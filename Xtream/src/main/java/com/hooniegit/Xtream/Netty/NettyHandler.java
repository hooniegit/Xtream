package com.hooniegit.Xtream.Netty;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Service;

// Spring Service로 등록
@Service
@io.netty.channel.ChannelHandler.Sharable // Sharable annotation 추가
public class NettyHandler extends ChannelInboundHandlerAdapter {
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof NettyEvent) {
            NettyEvent event = (NettyEvent) msg;
            System.out.println("Event received: " + event.getMessage());
            // 이벤트 처리 로직
        } else {
            super.channelRead(ctx, msg); // 다른 메시지에 대한 기본 처리
        }
    }
}

