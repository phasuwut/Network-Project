package Router.A;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


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
                    System.out.println(is);
                    Student student = (Student) is.readObject();

                    Student student2 =new  Student(555,"data", "nana") ;
                    System.out.println(student2);


                    ///System.out.println(is.readObject());
                    System.out.println("Student object received = "+student);
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

