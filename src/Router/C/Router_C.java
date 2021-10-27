package Router.C;

import Socket_RIP.Socket_RIP_Client;
import Socket_RIP.Socket_RIP_Server;
import model.Count;
import model.Neighbor;
import model.RouterModel;
import model.RoutingTableModel;
import service.NeighborService;
import service.RouterService;
import service.RoutingTable;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static java.lang.Integer.parseInt;

public class Router_C {
//    public static void main(String[] args) {
//        RoutingTable routingTable = new RoutingTable();
//        RouterService routerService = new RouterService();
//
//        List<Neighbor> neighbors = new ArrayList<Neighbor>();
//
//        List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
//        routingTable.createdRoutingTable(router, "Router_C.txt");
//        RouterModel routerModel = new RouterModel(router,"Router C", "9093");
//
//        NeighborService neighborService = new NeighborService();
//        String[] myNeighbors = {"A", "D"};
//        neighbors = neighborService.addNeighborToRouter(myNeighbors);
//
//        routerService.addNeighbor(routerModel, neighbors);
////            routingTable.printRouterModel(routerModelA);
//
//        Socket_RIP_Server socketServer = new Socket_RIP_Server();
//        socketServer.waitingForClient(routerModel);
//
//    }
//
public static void main(String[] args) {
    RoutingTable routingTable = new RoutingTable();
    RouterService routerService = new RouterService();

    List<Neighbor> neighbors = new ArrayList<Neighbor>();

    List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
    routingTable.createdRoutingTable(router, "Router_C.txt");
    RouterModel routerModel = new RouterModel(router,"Router C", 9093);

    NeighborService neighborService = new NeighborService();
    String[] myNeighbors = {"A", "D", "G"};
    neighbors = neighborService.addNeighborToRouter(myNeighbors);

    routerService.addNeighbor(routerModel, neighbors);
//            routingTable.printRouterModel(routerModelA);

    List<Count> countList = new ArrayList<Count>();
    for(int i = 0; i < routerModel.getNeighbors().size(); i++){
        countList.add(new Count(routerModel.getNeighbors().get(i).getName(),null));

    }


    Socket_RIP_Client socketClient = new Socket_RIP_Client();

    Timer myTimer = new Timer();

    myTimer.schedule(new TimerTask() {

        public void run() {
            for (int i = 0; i < routerModel.getNeighbors().size(); i++) {
                socketClient.sendUpdateMessageToServer(routerModel.getNeighbors().get(i), routerModel, routerModel.getNeighbors());
            }

        }

    }, 0, 3000);

    // check neighbor every 180 sec
//    myTimer.schedule(new TimerTask() {
//
//        public void run() {
//            System.out.println(countList.toString());
//
//        }
//
//    }, 0, 18000);


    Socket_RIP_Server socketServer = new Socket_RIP_Server();
    socketServer.waitingForClient(routerModel,countList);

}

}
