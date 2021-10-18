package Router.A;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.RoutingTableModel;

import controller.*;

public class Client {
    DatagramSocket Socket;

    public Client() {

    }
    public static List<RoutingTableModel> dataRouting = new ArrayList<RoutingTableModel>();

    public void createAndListenSocket() {
        try {

            FileReader fileReader =new FileReader();
            fileReader.createdRoutingTable(dataRouting );
            System.out.println("dataRouting");
            System.out.println(dataRouting);  //  ArrayList





            Socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] incomingData = new byte[1024];
            Student student = new Student(1, "Bijoy", "Kerala");
            System.out.println("student");
            System.out.println(student);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(student);
            byte[] data = outputStream.toByteArray();
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, 9876);
            Socket.send(sendPacket);
            System.out.println("Message sent from client");
            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            Socket.receive(incomingPacket);
            String response = new String(incomingPacket.getData());
            System.out.println("Response from server:" + response);
            Thread.sleep(2000);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client client = new Client();
        client.createAndListenSocket();
    }
}