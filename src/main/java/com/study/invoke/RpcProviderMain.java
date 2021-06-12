package com.study.invoke;

import com.study.framework.ProviderReflect;
import com.study.service.HelloService;
import com.study.service.HelloServiceImpl;

public class RpcProviderMain {
    public static void main(String[] args) throws Exception {
        HelloService service = new HelloServiceImpl();
        ProviderReflect.provider(service, 8083);
    }
}
