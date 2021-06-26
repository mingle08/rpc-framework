package com.study.rpc.netty.provider;

import com.study.rpc.netty.api.IRpcHelloService;

public class RpcHelloServiceImpl implements IRpcHelloService {

    @Override
    public String hello(String name) {
        return "Hello, " + name + " !";
    }
}
