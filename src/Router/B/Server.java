package Router.B;

import Socket_RIP.ConfigRouter;
import Socket_RIP.Socket_RIP_Server;
import model.Neighbor;
import model.RouterModel;
import model.RoutingTableModel;
import service.NeighborService;
import service.RouterService;
import service.RoutingTable;

import java.util.ArrayList;
import java.util.List;

public class Server {
//    public static void main(String[] args) {
//        ConfigRouter configRouter =new ConfigRouter();
//        configRouter.setRouterFile("Router_B.txt");
//        configRouter.setRouterName("Router_B");
//        configRouter.setPort(9092);
//
//        Socket_RIP_Server socketServer=new Socket_RIP_Server();
//        socketServer.Response(configRouter);
//    }

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

        Socket_RIP_Server socketServer = new Socket_RIP_Server();
        socketServer.waitingForClient(routerModel);

    }
}
