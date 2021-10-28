package Socket_RIP;


import model.Neighbor;
import model.RouterModel;
import model.RoutingTableModel;
import service.RouterService;
import service.RoutingTable;

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
            System.out.println( routingTableModels);
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


            Time time=new Time();
            time.setInterval(() ->upStatus(configRouter,routingTableModels),30000);

/*            DatagramPacket incomingPacket = new DatagramPacket(incomingData, incomingData.length);
            Socket.receive(incomingPacket);
            String response = new String(incomingPacket.getData());
            System.out.println("Response from server:" + response);*/
        } catch (Exception e) {
            System.out.println("error");
            System.out.println(e);
            //e.printStackTrace();
        }
    }
    public void upStatus(ConfigRouter configRouter,List<RoutingTableModel> routingTableModels){
        while (true){
            List<Status> messagesUpdate = new ArrayList<>();
            messagesUpdate.add(new Status(configRouter.getPort(),configRouter.getRouterName()));
            System.out.println(routingTableModels.size());
            for(int i=0;i<routingTableModels.size();i++){
                System.out.println(routingTableModels.get(i).getDest_sub());
                System.out.println(" ");
            }
           // System.out.println(messagesUpdate);
        }
    }

    public void sendToServer(Neighbor neighbor, RouterModel routingTableModel) {

        RouterService routerService = new RouterService();

        try {
            Socket socket = new Socket("localhost", (neighbor.getPort()));


            routerService.updateRoutingTableWhenNeighborOnline(routingTableModel.getRoutingTableModels(), neighbor.getRoutingTableModel(), neighbor.getName()); // อัพเดทตัวเอง

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);


            objectOutputStream.writeObject(routingTableModel.getRoutingTableModels());
            dataOutputStream.writeUTF(routingTableModel.getName());
            objectOutputStream.flush(); // send the message


        } catch (ConnectException exception) {

        } catch (IOException ex) {
            System.out.println("Server " + neighbor.getName() + " got disconnected");
        } catch (Exception e) {
            System.out.println("error");
            System.out.println(e);
        }
    }

    public void sendUpdateMessageToServer(Neighbor neighbor, RouterModel routingTableModel, List<Neighbor> neighbors ) {
        RoutingTable routingTable = new RoutingTable();
        RouterService routerService = new RouterService();


        try {
            Socket socket = new Socket("localhost", (neighbor.getPort()));

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);


            objectOutputStream.writeObject(null);
            dataOutputStream.writeUTF("Hello "+ neighbor.getName() +" this is message from " + routingTableModel.getName());
            dataOutputStream.flush(); // send the message


        } catch (ConnectException exception) {
//            System.out.println("Server " + neighbor.getName() + " is still offline");
            for(int i = 0; i < routingTableModel.getRoutingTableModels().size(); i++){
                if(routingTableModel.getRoutingTableModels().get(i).getNext_router().equals(neighbor.getName())){
                    routingTableModel.getRoutingTableModels().get(i).setNext_router_state("disconnected");
                }
            }


//
//            routerService.updateRoutingTableWhenNeighborDisconnected(neighbor.getRoutingTableModel(), routingTableModel.getRoutingTableModels(), neighbor.getName());
//            routerService.tellNeighborToHaveUpdate(routingTableModel);
//            routingTable.printRouterModel(routingTableModel);
//            routerService.updateRoutingTableWhenNeighborDisconnect(routingTableModel.getRoutingTableModels() ,neighbor.getName(), neighbors);

//
        } catch (IOException ex) {
            System.out.println("Server " + neighbor.getName() + " got disconnected");
        } catch (Exception e) {
            System.out.println("error");
            System.out.println(e);
        }
    }

    public void myNeighborDisconnect(Neighbor neighbor, String destination_subnet, String fromRouterName) {
        try {
            Socket socket = new Socket("localhost", (neighbor.getPort()));

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);


            objectOutputStream.writeObject(null);
            dataOutputStream.writeUTF("Delete "+ destination_subnet +" from " + fromRouterName);
            dataOutputStream.flush(); // send the message


        } catch (ConnectException exception) {

        } catch (IOException ex) {
            System.out.println("Server " + neighbor.getName() + " got disconnected");
        } catch (Exception e) {
            System.out.println("error");
            System.out.println(e);
        }

    }
}
