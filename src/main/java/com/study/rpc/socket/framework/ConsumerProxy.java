package com.study.rpc.socket.framework;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.net.Socket;

/**
 * Service API接口的代理类，内部逻辑通过Socket与服务的提供方进行通信，包括写入调用参数与获取调用返回的结果对象，
 * 通过代理使通信及获取返回结果等复杂逻辑，对接口调用方透明。
 *
 * 通过实现服务接口的动态代理对象获得服务接口的动态代理实例Proxy.newProxyInstance，通过实现InvocationHandler接口中的方法：
 * public Object invoke(Object proxy, Method method, Object[] arguments) 来完成远程RPC调用。
 * 具体通过Java对象输出流ObjectOutputStream将调用接口的方法及参数写入Socket，发起远程调用，之后通过Java对象输入流ObjectInputStream
 * 从Socket中获得返回结果
 */
public class ConsumerProxy {
    /**
     * 服务消费代理接口
     * @param interfaceClass
     * @param host
     * @param port
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> T consume(final Class<T> interfaceClass, final String host, final int port) throws Exception {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Socket socket = new Socket(host, port);
                try {
                    ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                    try {
                        output.writeUTF(method.getName());
                        output.writeObject(args);
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            Object obj = input.readObject();
                            if (obj instanceof Throwable) {
                                throw (Throwable) obj;
                            }
                            return obj;
                        } finally {
                            input.close();
                        }
                    } finally {
                        output.close();
                    }
                } finally {
                    socket.close();
                }
            }
        });
    }
}
