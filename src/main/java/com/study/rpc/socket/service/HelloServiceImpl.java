package com.study.rpc.socket.service;

/**
 * 远程接口实现类，对应ServiceImpl角色
 */
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String content) {
        return "hello, " + content;
    }
}
