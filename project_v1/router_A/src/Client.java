import java.net.*;
import java.io.*;

import java.util.List;
import RIP.model.*;
import java.net.DatagramPacket;
import java.util.Scanner;


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



        DatagramSocket ds = new DatagramSocket();
        byte buf[] = null;
        Scanner sc = new Scanner(System.in);

        // loop while user not enters "bye"
        while (true)
        {
            String inp = sc.nextLine();

            buf = inp.getBytes();
            DatagramPacket DpSend = new DatagramPacket(buf, buf.length, ip, port);
            ds.send(DpSend);

            // break the loop if user enters "bye"
            if (inp.equals("bye"))
                break;
        }
    }
}