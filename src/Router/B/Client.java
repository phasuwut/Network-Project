package Router.B;

import Router.A.Data;
import model.Neighbor;
import model.RouterModel;
import model.RoutingTableModel;
import service.RouterService;
import service.RoutingTable;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.ArrayList;
import java.util.List;
import java.net.*;
import java.util.*;

public class Client {


    public Client() { }
    public static List<RoutingTableModel> dataRouting = new ArrayList<RoutingTableModel>();

    public static void main(String[] args) {
        try {
            ConfigRouter configRouter =new ConfigRouter();


            RoutingTable routingTable = new RoutingTable();
            RouterService routerService = new RouterService();
            List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
            routingTable.createdRoutingTable(router, configRouter.getFile());
            List<RouterModel> routerList = new ArrayList<RouterModel>();
            RouterModel routerModel = new RouterModel(router,configRouter.getRouterName(),  Integer.toString(configRouter.getPort()) );
            List<Neighbor> neighbors_B = new ArrayList<Neighbor>();
            System.out.println(routerModel ); // array

            //int PortRouter = Integer.parseInt( routerModel.getPort());
            SocketData socketData=new SocketData(configRouter.getPort() , configRouter.getRouterName() );
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(socketData);
            byte[] data = outputStream.toByteArray();
            System.out.println(2);
            byte[] incomingData = new byte[1024];
            DatagramSocket Socket;
            Socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(configRouter.getIp());
            DatagramPacket sendPacket = new DatagramPacket(data, data.length, IPAddress, configRouter.getPort());
            Socket.send(sendPacket);

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
}
