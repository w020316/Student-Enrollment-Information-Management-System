package com.stu.server;

import com.stu.util.DBConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static final int PORT = 8888;

    public static void main(String[] args) {
        DBConnection.init("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/stu_manage?useUnicode=true&characterEncoding=utf8",
                "root", "123456");

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("服务端启动，监听端口: " + PORT);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("客户端连接: " + socket.getInetAddress());
                new Thread(new RequestHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
