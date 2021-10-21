package Router.B;

import Router.A.Data;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static void main(String[] args) {
        try {
            ConfigRouter configRouter =new ConfigRouter();

            DatagramSocket socket = null;
            socket = new DatagramSocket(configRouter.getPort());
            byte[] incomingData = new byte[1024];
            while (true){
                DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
                socket.receive(incomingPacket);
                byte[] data = incomingPacket.getData();
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                try {

                    System.out.println(0);
                    SocketData socketData= (SocketData) is.readObject();
                    System.out.println(1);
                    System.out.println(socketData.toString());
                    System.out.println(2);


                } catch (Exception e) {
                    System.out.println("error");
                    System.out.println(e);
                    //e.printStackTrace();
                }
                InetAddress IPAddress = incomingPacket.getAddress();
                int port = incomingPacket.getPort();
                String reply = "Thank you for the message";
                byte[] replyBytea = reply.getBytes();
                DatagramPacket replyPacket =
                        new DatagramPacket(replyBytea, replyBytea.length, IPAddress, port);
                socket.send(replyPacket);
                //Thread.sleep(2000);
                // System.exit(0);
            }
        }
        catch (Exception e) {
            System.out.println("error");
            System.out.println(e);
            //e.printStackTrace();
        }
    }
}

