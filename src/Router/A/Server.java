package Router.A;

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
//        configRouter.setRouterFile("Router_A.txt");
//        configRouter.setRouterName("Router_A");
//        configRouter.setPort(9090);
//
//        Socket_RIP_Server socketServer = new Socket_RIP_Server();
//        socketServer.Response(configRouter);
//    }
        public static void main(String[] args) {
            RoutingTable routingTable = new RoutingTable();
            RouterService routerService = new RouterService();

            List<Neighbor> neighbors_A = new ArrayList<Neighbor>();

            List<RoutingTableModel> router_A = new ArrayList<RoutingTableModel>();
            routingTable.createdRoutingTable(router_A, "Router_A.txt");
            RouterModel routerModelA = new RouterModel(router_A,"Router A", 9091);

            NeighborService NeighborService_A = new NeighborService();
            String[] myNeighbors = {"B", "C", "F"};
            neighbors_A = NeighborService_A.addNeighborToRouter(myNeighbors);

            routerService.addNeighbor(routerModelA, neighbors_A);
//            routingTable.printRouterModel(routerModelA);

        Socket_RIP_Server socketServer = new Socket_RIP_Server();
//        socketServer.waitingForClient(routerModelA);

        }
}
