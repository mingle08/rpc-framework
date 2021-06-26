package com.study.rpc.netty.consumer;

import com.study.rpc.netty.api.IRpcHelloService;
import com.study.rpc.netty.api.IRpcService;
import com.study.rpc.netty.consumer.proxy.RpcProxy;

public class RpcConsumer {

    public static void main(String[] args) {
        IRpcHelloService rpcHello = RpcProxy.create(IRpcHelloService.class);
        System.out.println(rpcHello.hello("Tom老师"));

        IRpcService service = RpcProxy.create(IRpcService.class);

        System.out.println("8 + 2 = " + service.add(8, 2));
        System.out.println("8 - 2 = " + service.sub(8, 2));
        System.out.println("8 * 2 = " + service.mul(8, 2));
        System.out.println("8 / 2 = " + service.div(8, 2));
    }
}
