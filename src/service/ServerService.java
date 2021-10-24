package service;

import Socket_RIP.ConfigRouter;
import Socket_RIP.Socket_RIP_Server;
import model.Neighbor;
import model.RouterModel;
import model.RoutingTableModel;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class ServerService {


    public void OpenServer(String fileName, String routerName, Integer routerPort, String[] myNeighbors){
            RoutingTable routingTable = new RoutingTable();
            RouterService routerService = new RouterService();

            List<Neighbor> neighbors = new ArrayList<Neighbor>();

            List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
            routingTable.createdRoutingTable(router, fileName); //"Router_A.txt"
            RouterModel routerModel = new RouterModel(router,routerName, routerPort);

            NeighborService neighborService = new NeighborService();
//            String[] myNeighbors = {"B", "C", "F"};
            neighbors = neighborService.addNeighborToRouter(myNeighbors);

            routerService.addNeighbor(routerModel, neighbors);
//            routingTable.printRouterModel(routerModelA);

            Socket_RIP_Server socketServer = new Socket_RIP_Server();
            socketServer.waitingForClient(routerModel);
        }
}
