


import RIP.model.RoutingTableModel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;



import java.util.ArrayList;
import java.util.List;

public class Server {
    DatagramSocket socket = null;
    public Server() {
    }

    public void createAndListenSocket() {
        try {
            //fig ip port
            ConfigRouter configRouter = new ConfigRouter();
            int portServer = configRouter.getPort();


            socket = new DatagramSocket(portServer);
            byte[] incomingData = new byte[1024];

            while (true) {
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                socket.receive(incomingPacket);

                byte[] data = incomingPacket.getData();

                System.out.println("RIP.data");
                System.out.println(data);
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                try {
                    System.out.println("1");

                    List<RoutingTableModel> dataRouting = new ArrayList<RoutingTableModel>();
                    //dataRouting=RIP.data;


                    System.out.println(is.readObject());


                } catch (Exception e) {
                    System.out.println(e);
                }


                // output
                InetAddress IPAddress = incomingPacket.getAddress();
                int port = incomingPacket.getPort();

                String reply = "Thank you for the message";
                byte[] replyBytea = reply.getBytes();
                DatagramPacket replyPacket = new DatagramPacket(replyBytea, replyBytea.length, IPAddress, port);
                socket.send(replyPacket);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        server.createAndListenSocket();
    }
}



/*
 import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.net.*;


 ServerSocket ss = new ServerSocket(7777);
            Socket socket = ss.accept(); // detail sender
            System.out.println("Connection from " + socket + "!");

            InputStream inputStream = socket.getInputStream();
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream); // -้อมูลที่ส่งเข้ามา
            List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();

            System.out.println(listOfMessages);
            listOfMessages.forEach((msg)-> System.out.println(msg.getText()));

            System.out.println("Closing sockets.");
            socket.close();
            ss.close();*/