package Router.A;

import Socket_RIP.Socket_RIP_Client;
import Socket_RIP.Socket_RIP_Server;
import model.Neighbor;
import model.RouterModel;
import model.RoutingTableModel;
import service.NeighborService;
import service.RouterService;
import service.RoutingTable;
import service.Timer_Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Router_A {
    public static void main(String[] args) {
        RoutingTable routingTable = new RoutingTable();
        RouterService routerService = new RouterService();
        Timer_Time timer_ = new Timer_Time();

        List<Neighbor> neighbors = new ArrayList<Neighbor>();

        List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router, "Router_A.txt");
        RouterModel routerModel = new RouterModel(router, "Router A", 9091);

        NeighborService neighborService = new NeighborService();
        String[] myNeighbors = {"B", "C", "F"};
        neighbors = neighborService.addNeighborToRouter(myNeighbors);

        routerService.addNeighbor(routerModel, neighbors);
//            routingTable.printRouterModel(routerModelA);



        Socket_RIP_Client socketClient = new Socket_RIP_Client();

        Timer myTimer = new Timer();
        myTimer.schedule(new TimerTask() {

            public void run() {
                for (int i = 0; i < routerModel.getNeighbors().size(); i++) {
                    socketClient.sendUpdateMessageToServer(routerModel.getNeighbors().get(i), routerModel);
                }

            }

        }, 0, 30000);

        Socket_RIP_Server socketServer = new Socket_RIP_Server();
        socketServer.waitingForClient(routerModel);


    }
}
