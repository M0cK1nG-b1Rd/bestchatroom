package com.bestchatroom.testcient;

import com.bestchatroom.ssl.SslTwoWayContextFactory;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.ssl.SslHandler;

import javax.net.ssl.SSLEngine;

public class SslTwoWayClientInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		String cChatPath =  System.getProperty("user.dir")+"/src/main/java/com/zhaozhou/netty/demo/ssl/conf/twoway/clientStore.jks";
		
		SSLEngine engine = SslTwoWayContextFactory.getClientContext(cChatPath,cChatPath).createSSLEngine();
		engine.setUseClientMode(true);
		pipeline.addLast("ssl", new SslHandler(engine));

		// On top of the SSL handler, add the text line codec.
		pipeline.addLast("framer", new LineBasedFrameDecoder(1024, false, false));
		pipeline.addLast("decoder", new StringDecoder());
		pipeline.addLast("encoder", new StringEncoder());
		// and then business logic.
		pipeline.addLast("handler", new SslTwoWayClientHandler());
	}

}
