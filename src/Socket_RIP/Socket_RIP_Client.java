package Socket_RIP;


import model.Neighbor;
import model.RouterModel;
import model.RoutingTableModel;
import service.RouterService;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.net.Socket;

import static java.lang.Integer.parseInt;

public class Socket_RIP_Client {
    DatagramSocket Socket;

    public void Socket_RIP_Client() {

    }

    public void Send(ConfigRouter configRouter, List<RoutingTableModel> routingTableModels) {
        try {

            // เตรียนข้อมูลที่จะส่ง
            List<SocketData> messages = new ArrayList<>();
            messages.add(new SocketData(configRouter.getPort(), configRouter.getRouterName(), routingTableModels));
            System.out.println(messages);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(messages);

            // sending
            byte[] data = outputStream.toByteArray();
            byte[] incomingData = new byte[1024];
            Socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(configRouter.getIp());
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, configRouter.getPort());
            Socket.send(sendPacket);

            //response
            System.out.println("Message sent from client");


            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            Socket.receive(incomingPacket);
            String response = new String(incomingPacket.getData());
            System.out.println("Response from server:" + response);
        } catch (Exception e) {
            System.out.println("error");
            System.out.println(e);
            //e.printStackTrace();
        }
    }

    public void sendToServer(Neighbor neighbor, RouterModel routingTableModel) {

//        System.out.println(parseInt(neighbor.getPort()));
//        System.out.println(parseInt(neighbor.getPort()));
//        System.out.println("neighbor  Name ! " + neighbor.getName());


        try {
            Socket socket = new Socket("localhost", (neighbor.getPort()));
            System.out.println("Connected! " + neighbor.getName());

            RouterService routerService = new RouterService();

            routerService.updateRoutingTableWhenNeighborOnline(routingTableModel.getRoutingTableModels(), neighbor.getRoutingTableModel(), neighbor.getName()); // อัพเดทตัวเอง

            // get the output stream from the socket.
            OutputStream outputStream = socket.getOutputStream();
            // create a data output stream from the output stream so we can send data through it
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

            System.out.println("Sending string to the ServerSocket");

            // write the message we want to send
            objectOutputStream.writeObject(routingTableModel.getRoutingTableModels());
            dataOutputStream.writeUTF(routingTableModel.getName());
            objectOutputStream.flush(); // send the message
//            dataOutputStream.close(); // close the output stream when we're done.
//
//            Socket echoSocket = new Socket(neighbor.getName(), parseInt(neighbor.getPort()));
//            DataOutputStream dOut = new DataOutputStream(echoSocket.getOutputStream());
//            dOut.writeByte(1024);
//            dOut.writeUTF(neighbor.getRoutingTableModel().toString());
//            dOut.flush(); // Send off the data
//            dOut.writeByte(-1);
//            dOut.flush();

//            List<SocketData> messages = new ArrayList<>();
//            messages.add(new SocketData((parseInt(neighbor.getPort())), neighbor.getName(), neighbor.getRoutingTableModel()));
//            System.out.println(messages);
//            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//            ObjectOutputStream os = new ObjectOutputStream(outputStream);
//            os.writeObject(messages);
//
//            // sending
//            byte[] data = outputStream.toByteArray();
//            byte[] incomingData = new byte[1024];
//            Socket = new DatagramSocket();
//            InetAddress IPAddress = InetAddress.getByName(neighbor.getRouterIp());
//            DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress,(parseInt(neighbor.getPort())));
//            Socket.send(sendPacket);
//
//            //response
//            System.out.println("Message sent from client");
//
//
//            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
//            Socket.receive(incomingPacket);
//            String response = new String(incomingPacket.getData());
//            System.out.println("Response from server:" + response);

        } catch (ConnectException exception) {
            System.out.println("Server " + neighbor.getName() + " is still offline");
        } catch (IOException ex) {
            System.out.println("Server " + neighbor.getName() + " got disconnected");
        } catch (Exception e) {
            System.out.println("error eiei");
            System.out.println(e);
            //e.printStackTrace();
        }
    }
}
