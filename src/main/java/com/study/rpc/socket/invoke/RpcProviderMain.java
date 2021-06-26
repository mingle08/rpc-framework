package com.study.rpc.socket.invoke;

import com.study.rpc.socket.framework.ProviderReflect;
import com.study.rpc.socket.service.HelloService;
import com.study.rpc.socket.service.HelloServiceImpl;

public class RpcProviderMain {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        ProviderReflect.provider(service, 8083);
    }
}
