package com.study.socketRpc.invoke;

import com.study.socketRpc.framework.ProviderReflect;
import com.study.socketRpc.service.HelloService;
import com.study.socketRpc.service.HelloServiceImpl;

public class RpcProviderMain {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        ProviderReflect.provider(service, 8083);
    }
}
