import java.net.*;
import java.io.*;

import java.util.List;
import RIP.model.*;
import java.net.DatagramPacket;
import java.util.Scanner;
import java.util.Arrays;

import RIP.model.RoutingTableModel;

class Client{
    public static void main(String args[])throws Exception{
        //fig ip port
        ConfigRouter configRouter = new ConfigRouter();
        int port = configRouter.getPort();
        String ipAddress =  configRouter.getIp();
        InetAddress ip = InetAddress.getByName(ipAddress);
        System.out.println(ip);
        // print RoutingTable's A
        System.out.println(configRouter.getFile());


        // loop while user not enters "bye"
        while (true)
        {

            // how to https://gist.github.com/chatton/14110d2550126b12c0254501dde73616






            BufferedReader inFromUser =new BufferedReader(new InputStreamReader(System.in));
            DatagramSocket clientSocket = new DatagramSocket();

            byte[] sendData = new byte[1024]; //input
            byte[] receiveData = new byte[1024]; //output

            String sentence = inFromUser.readLine();// >> cin
            sendData = sentence.getBytes();
            sendData = configRouter.getFile().getBytes();

            // how to https://www.devzoneoriginal.com/2020/07/java-socket-example-for-sending-and.html
           // String req = Arrays.toString(configRouter.getFile());
           // System.out.println(req);

            //send
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,ip, port);
            clientSocket.send(sendPacket);

            //response
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);

            System.out.println(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("FROM SERVER:" + modifiedSentence);

            // break the loop if user enters "bye"
            if (sentence.equals("bye")){
                clientSocket.close();
                break;
            }

        }
    }
}