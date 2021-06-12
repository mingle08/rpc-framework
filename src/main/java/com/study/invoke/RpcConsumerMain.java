package com.study.invoke;

import com.study.framework.ConsumerProxy;
import com.study.service.HelloService;

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
