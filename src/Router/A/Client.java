package Router.A;

import Socket_RIP.Socket_RIP_Client;
import Socket_RIP.ConfigRouter;

import model.Neighbor;
import model.RouterModel;
import model.RoutingTableModel;
import service.RouterService;
import service.RoutingTable;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        // config
        Socket_RIP_Client  socketClient =new Socket_RIP_Client();
        ConfigRouter configRouter =new ConfigRouter();
        configRouter.setRouterFile("Router_A.txt");
        configRouter.setRouterName("Router_A");
        configRouter.setPort(9091);

        //GetDAta
        RoutingTable routingTable = new RoutingTable();
        RouterService routerService = new RouterService();
        List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router, configRouter.getFile());
        List<RouterModel> routerList = new ArrayList<RouterModel>();
        RouterModel routerModel = new RouterModel(router,configRouter.getRouterName(),  Integer.toString(configRouter.getPort()) );
        List<Neighbor> neighbors_B = new ArrayList<Neighbor>();

        // socket
        socketClient.Send(configRouter ,routerModel.getRoutingTableModels());
    }
}
