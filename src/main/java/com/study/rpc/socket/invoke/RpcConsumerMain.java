package com.study.rpc.socket.invoke;

import com.study.rpc.socket.service.HelloService;
import com.study.rpc.socket.framework.ConsumerProxy;

import java.util.concurrent.TimeUnit;

public class RpcConsumerMain {
    public static void main(String[] args) throws Exception {
        HelloService service = ConsumerProxy.consume(HelloService.class, "127.0.0.1", 8083);
        for (int i = 0; i < 1000; i++) {
            String hello = service.sayHello("huxm_" + i);
            System.out.println(hello);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
