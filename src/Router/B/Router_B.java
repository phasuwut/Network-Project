package Router.B;

import Socket_RIP.Socket_RIP_Client;
import Socket_RIP.Socket_RIP_Server;
import model.Count;
import model.Neighbor;
import model.RouterModel;
import model.RoutingTableModel;
import service.NeighborService;
import service.RouterService;
import service.RoutingTable;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Router_B {
    public static void main(String[] args) {
        RoutingTable routingTable = new RoutingTable();
        RouterService routerService = new RouterService();

        List<Neighbor> neighbors = new ArrayList<Neighbor>();

        List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router, "Router_B.txt");
        RouterModel routerModel = new RouterModel(router,"Router B", 9092);

        NeighborService neighborService = new NeighborService();
        String[] myNeighbors = {"A", "E", "F"};
        neighbors = neighborService.addNeighborToRouter(myNeighbors);

        routerService.addNeighbor(routerModel, neighbors);
//            routingTable.printRouterModel(routerModelA);



        List<Count> countList = new ArrayList<Count>();
        for(int i = 0; i < routerModel.getNeighbors().size(); i++){
            countList.add(new Count(routerModel.getNeighbors().get(i).getName(),0,0,false));

        }


        Socket_RIP_Client socketClient = new Socket_RIP_Client();

        Timer myTimer = new Timer();

        myTimer.schedule(new TimerTask() {

            public void run() {
                for (int i = 0; i < routerModel.getNeighbors().size(); i++) {
                    socketClient.sendUpdateMessageToServer(routerModel.getNeighbors().get(i), routerModel);
                }

            }

        }, 0, 3000);

        // check neighbor every 180 sec
//        myTimer.schedule(new TimerTask() {
//
//            public void run() {
//                System.out.println(countList.toString());
//
//            }
//
//        }, 0, 18000);


        Socket_RIP_Server socketServer = new Socket_RIP_Server();
        socketServer.waitingForClient(routerModel,countList);
    }
}
