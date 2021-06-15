package com.bestchatroom.server.handler;

import com.bestchatroom.message.AbstractResponseMessage;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;


@Slf4j
@ChannelHandler.Sharable
public class TimeCheckHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        AbstractResponseMessage message = (AbstractResponseMessage) msg;
        // 超过3秒将丢弃消息
        if (message.getCurrentDate().getTime() - new Date().getTime()> 3000) {
            log.debug("Expired Message.. Discard");
            return;
        }
        super.channelRead(ctx, msg);
    }
}
