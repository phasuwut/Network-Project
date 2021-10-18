import java.net.*;
import java.io.*;

//ip address
import java.net.InetAddress;
import java.net.UnknownHostException;

class Server{
    public static void main(String args[])throws Exception{
        ConfigRouter configRouter = new ConfigRouter();
        int port = configRouter.getPort();



        DatagramSocket serverSocket = new DatagramSocket(port);
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];


        while (true) {
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int port2 = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            System.out.println(capitalizedSentence);
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port2);
            serverSocket.send(sendPacket);
        }
    }



}