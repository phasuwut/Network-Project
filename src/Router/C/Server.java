package Router.C;


import Router.B.ConfigRouter;
import Router.B.SocketData;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import Socket.SocketServer;

public class Server {
    public static void main(String[] args) {
        Router.B.ConfigRouter configRouter =new ConfigRouter();
        SocketServer socketServer=new SocketServer();
        socketServer.Response(configRouter);
    }
}
