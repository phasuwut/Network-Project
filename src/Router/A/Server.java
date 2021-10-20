package Router.A;

import model.RouterModel;
import model.RoutingTableModel;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// how to http://www.coderpanda.com/java-socket-programming-transferring-java-object-through-socket-using-udp/
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
                ByteArrayInputStream in = new ByteArrayInputStream(data);
                ObjectInputStream is = new ObjectInputStream(in);
                try {
                    // System.out.println(is);
                    //  Student student = (Student) is.readObject();
                    //RouterModel routerModelA = new RouterModel(router_A,"Router A", "9091");
                    // RouterModel routerModelA = new RouterModel( is.readObject(),"Router A", "9091" );

                    System.out.println(is.readObject());
                    // RouterModel routerModelA = new RouterModel (setRoutingTableModels2(is.readObject()))  ;

                    //List<RoutingTableModel> router_A = new ArrayList<RoutingTableModel>();
                    //router_A.printRouterList(is.readobject());
                    System.out.println(2);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
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