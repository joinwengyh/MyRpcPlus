package com.weng.myrpc0.server;



import com.weng.myrpc0.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author Hua
 * @Date: 2022/1/24 10:21
 */
public class RPCServer {
    public static void main(String[] args) {
        UserServiceImpl userService = new UserServiceImpl();
        try {
            ServerSocket serverSocket  = new ServerSocket(0000);
            System.out.println("服务端启动了");
            // BIO的方式监听Socket
            while (true) {
                Socket socket  = serverSocket.accept();
                // 开启一个线程去处理
                new Thread(()->{
                  try {
                      ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
                      ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                      // 读取客户端传过来的id
                      int id = ois.readInt();
                      User userByUserId = userService.getUserByUserId(id);
                      // 写入User对象给客户端
                      oos.writeObject(userByUserId);
                      oos.flush();
                  } catch (IOException e) {
                      e.printStackTrace();
                      System.out.println("从IO中读取数据错误");
                  }
                }).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("服务器启动失败");
        }

    }
}
