package com.study.nettyRpc.Server;

import com.study.enums.SerializeType;
import io.netty.channel.EventLoopGroup;

public class NettyServer {

    private static NettyServer nettyServer = new NettyServer();

    // 服务端boss线程组
    private EventLoopGroup bossGroup;
    // 服务端worker线程组
    private EventLoopGroup workerGroup;
    // 序列化类型配置信息
//    private SerializeType serializeType = PropertyConfigeHelper.getSerializeType();
}
