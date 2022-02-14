package com.weng.myrpc0.client;



import com.weng.myrpc0.common.User;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.UUID;

/**
 * @Author Hua
 * @Date: 2022/1/24 10:24
 */
public class RPCClient {
    public static void main(String[] args) {
        try {
            // 建立Socket连接
            Socket socket = new Socket("127.0.0.1", 0000);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            // 传给服务器id
            objectOutputStream.writeInt(new Random().nextInt());
            objectOutputStream.flush();
            // 服务器查询数据，返回对应的对象
            User user = (User)objectInputStream.readObject();
            System.out.println("服务端返回的User"+user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("客户端启动失败");
        }
    }
}
