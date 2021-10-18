


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
public class Server {
    DatagramSocket socket = null;
    public Server() {
    }

    public void createAndListenSocket() {
        try {
            socket = new DatagramSocket(9876);
            byte[] incomingData = new byte[1024];

            while (true) {
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                socket.receive(incomingPacket);

                byte[] data = incomingPacket.getData();

                System.out.println(data);

                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                try {
                    Student student = (Student) is.readObject();
                    System.out.println("Student object received = "+student);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
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