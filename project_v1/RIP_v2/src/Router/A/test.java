package Router.A;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;
import java.util.*;


import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.*;

import org.json.simple.JSONObject;
import controller.*;
import model.*;
import service.*;

public class test {
    Socket socket = null;

    public static void main(String[] args) {

        RoutingTable routingTable = new RoutingTable();
        RouterService routerService = new RouterService();
        List<RoutingTableModel> router_A = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router_A, "Router_A.txt");
        List<RouterModel> routerList = new ArrayList<RouterModel>();
        RouterModel routerModelA = new RouterModel(router_A,"Router A", "9091");
        List<Neighbor> neighbors_A = new ArrayList<Neighbor>();
        // neighbors_A.add(new Neighbor(routerModelB.getName(), routerModelB.getPort(),routerModelB.getRoutingTableModels()));
        //neighbors_A.add(new Neighbor(routerModelC.getName(), routerModelC.getPort(), routerModelC.getRoutingTableModels()));
        // neighbors_A.add(new Neighbor(routerModelF.getName(), routerModelF.getPort(), routerModelF.getRoutingTableModels()));
        System.out.println(routerModelA.getRoutingTableModels());

       /// DatagramPacket sendPacket = new DatagramPacket(data, data.length, "127.0.0.1", 9876);
        //socket = new Socket ("127.0.0.1",12345);

        String name="1" ;
        String imgStr="12345667786";
        int opNum=1234;

        Map <String, RouterModel> map = new HashMap <String, RouterModel> ();
        map.put ("routing", routerModelA);
        //Convert json to String type
        JSONObject json = new JSONObject(map);
        System.out.println(json);
        System.out.println(map);
        String jsonString = "";
        jsonString = json.toString ();

        byte [] jsonByte = jsonString.getBytes ();
        DataOutputStream outputStream = null;
        ///  outputStream = new DataOutputStream (socket.getOutputStream ());
        System.out.println ("The length of the data sent is:" + jsonByte.length);
        System.out.println(jsonByte);
        outputStream.write (jsonByte);
    }
}
/*

https://titanwolf.org/Network/Articles/Article?AID=5b1c9a7c-2584-47eb-950c-7f2315f540df
https://www.javatpoint.com/java-json-example
import model.Neighbor;
import model.RouterModel;
import model.RoutingTableModel;
import service.RouterService;
import service.RoutingTable;
import model.RoutingTableModel;

import service.RouterService;
import service.RoutingTable;


        RoutingTable routingTable = new RoutingTable();
        RouterService routerService = new RouterService();
        List<RoutingTableModel> router_A = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router_A, "Router_A.txt");
        List<RouterModel> routerList = new ArrayList<RouterModel>();
        RouterModel routerModelA = new RouterModel(router_A,"Router A", "9091");
        List<Neighbor> neighbors_A = new ArrayList<Neighbor>();
        // neighbors_A.add(new Neighbor(routerModelB.getName(), routerModelB.getPort(),routerModelB.getRoutingTableModels()));
        //neighbors_A.add(new Neighbor(routerModelC.getName(), routerModelC.getPort(), routerModelC.getRoutingTableModels()));
        // neighbors_A.add(new Neighbor(routerModelF.getName(), routerModelF.getPort(), routerModelF.getRoutingTableModels()));
        System.out.println(routerModelA.getRoutingTableModels());

*/
