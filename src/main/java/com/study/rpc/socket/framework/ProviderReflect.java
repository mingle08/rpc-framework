package com.study.rpc.socket.framework;

import org.apache.commons.lang3.reflect.MethodUtils;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务的提供方，通过接收ConsumerProxy通过Socket写入的参数，定位到具体的服务实现，并通过Java反射技术实现服务的调用，
 * 然后将调用结果写入Socket，返回到ConsumerProxy
 *
 * 通过Java对象输入流ObjectInputStream从Socket中按照ConsumerProxy的写入顺序逐一获取调用方法名称及方法参数，通过
 * org.apache.commons.lang3中的工具方法MethodUtils.invokeExactMethod对服务实现庆发起反射调用，将调用结果写入
 * Socket返回给调用方
 */
public class ProviderReflect {
    private static final ExecutorService executorService = Executors.newCachedThreadPool();
    /**
     * 服务的发布
     */
    public static void provider(final Object service, int port) throws Exception {
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            final Socket socket = serverSocket.accept();
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                        try {
                            try {
                                // method name
                                String methodName = input.readUTF();
                                // method arguments
                                Object[] arguments = (Object[]) input.readObject();
                                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                                try {
                                    // 方法调用
                                    Object result = MethodUtils.invokeExactMethod(service, methodName, arguments);
                                    output.writeObject(result);
                                } catch (Throwable t) {
                                    output.writeObject(t);
                                } finally {
                                    output.close();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            } finally {
                                input.close();
                            }
                        } finally {
                            socket.close();
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
