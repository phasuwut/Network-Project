package Router.A;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;

//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
import model.RoutingTableModel;

import controller.*;
import service.RouterService;
import service.RoutingTable;
import model.*;
import org.json.simple.JSONObject;

import java.io.Serializable;

public class Client {
    DatagramSocket Socket;

    public Client() {

    }
    public static List<RoutingTableModel> dataRouting = new ArrayList<RoutingTableModel>();

    public void createAndListenSocket() {
        try {

/*            FileReader fileReader =new FileReader();
            fileReader.createdRoutingTable(dataRouting );
            System.out.println("dataRouting");
            System.out.println(dataRouting);  //  ArrayList*/

            RoutingTable routingTable = new RoutingTable();
            RouterService routerService = new RouterService();
            List<RoutingTableModel> router_A = new ArrayList<RoutingTableModel>();
            // System.out.println(router_A ); // array
            routingTable.createdRoutingTable(router_A, "Router_A.txt");

            /*
                for(int i=0;i<router_A.size();i++){
                    System.out.println(router_A.get(i).getNext_router() );
                    System.out.println(router_A.get(i).getDest_sub()) ;
                    System.out.println(router_A.get(i).getHops_to_dest()) ;
                }

                System.out.println(router_A.get(0) );
                System.out.println(router_A.get(0).getNext_router() );
                System.out.println(router_A.size());
            */

            List<RouterModel> routerList = new ArrayList<RouterModel>();
            RouterModel routerModelA = new RouterModel(router_A,"Router A", "9091");
            List<Neighbor> neighbors_A = new ArrayList<Neighbor>();
            // neighbors_A.add(new Neighbor(routerModelB.getName(), routerModelB.getPort(),routerModelB.getRoutingTableModels()));
            //neighbors_A.add(new Neighbor(routerModelC.getName(), routerModelC.getPort(), routerModelC.getRoutingTableModels()));
            // neighbors_A.add(new Neighbor(routerModelF.getName(), routerModelF.getPort(), routerModelF.getRoutingTableModels()));



            System.out.println(0); // array
            System.out.println(router_A ); // array
            System.out.println(routerModelA ); // array
            System.out.println(routerModelA.getRoutingTableModels());
            System.out.println(neighbors_A);
            System.out.println(routerList);
            System.out.println(5);

            // Print Routing table
            System.out.println(routerModelA.getName() );
            System.out.println(routerModelA.getPort() );
            System.out.println(routerModelA.getRoutingTableModels() );
            System.out.println(routerModelA.getRoutingTableModels().size() );
            for(int i=0;i<routerModelA.getRoutingTableModels().size();i++){
                System.out.println(routerModelA.getRoutingTableModels().get(i).getHops_to_dest() );
                System.out.println(routerModelA.getRoutingTableModels().get(i).getNext_router());
                System.out.println(routerModelA.getRoutingTableModels().get(i).getDest_sub() );
            }
           // int PortRouterModelA = Integer.parseInt(routerModelA.getPort());
            Data dataSentSocket= new Data(  routerModelA.getPort(),routerModelA.getName() );
            System.out.println(55);
            System.out.println(dataSentSocket);
            System.out.println(66);


            routerService.addNeighbor(routerModelA, neighbors_A);
            routerList.add(routerModelA);
            routingTable.printRouterList((routerList));
            Object object = routerModelA.getRoutingTableModels();


     /*       System.out.println(object.getClass().getName());
            Socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] incomingData = new byte[1024];


           ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            //outputStream.write(routerModelA.getRoutingTableModels());
            ObjectOutputStream os = new ObjectOutputStream(outputStream);

            //  os.writeObject(student);
            System.out.println(1);
            System.out.println( routerModelA.getRoutingTableModels().toString());
           // os.writeObject(routerModelA.getRoutingTableModels().toString());
            // os.writeObject(object);*/

            Socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("localhost");
            byte[] incomingData = new byte[1024];


            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ObjectOutputStream os = new ObjectOutputStream(outputStream);
            os.writeObject(dataSentSocket);
            byte[] data = outputStream.toByteArray();
            //os.writeObject(dataSentSocket);
            System.out.println(2);



/*
            Map <String, RouterModel> map = new HashMap <String, RouterModel> ();
            map.put ("routing", routerModelA);
            //Convert json to String type
            JSONObject json = new JSONObject(map);
            System.out.println();
*/


/*            System.out.println(1);
            System.out.println( routerModelA.getRoutingTableModels().toString());
            JSONObject jsonObject = new JSONObject();
            System.out.println(jsonObject.escape(routerModelA.getRoutingTableModels().toString()));
            Object obj = JSONValue.parse(routerModelA.getRoutingTableModels().toString());
            JSONArray array = new JSONArray(routerModelA.getRoutingTableModels().toString());
            System.out.println(obj);
            System.out.println(array);
            JSONObject obj=new JSONObject();
            obj.put("name","routerModelA.getRoutingTableModels()");
            System.out.print(obj);*/

            //byte[] data = outputStream.toByteArray();
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