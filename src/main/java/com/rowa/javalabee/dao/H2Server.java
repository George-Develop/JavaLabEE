package com.rowa.javalabee.dao;

import org.h2.tools.Server;

public class H2Server {
    public static void main(String[] args) throws Exception {
        // Запуск TCP-сервера на порту 9092
        Server server = Server.createTcpServer("-tcpAllowOthers").start();
        System.out.println("H2 TCP server started at port " + server.getPort());
    }
}
