package Router.C;

import Router.B.ConfigRouter;
import Router.B.SocketData;
import Socket.SocketClient;
import model.Neighbor;
import model.RouterModel;
import model.RoutingTableModel;
import service.RouterService;
import service.RoutingTable;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        // config
        SocketClient  socketClient =new SocketClient();
        ConfigRouter configRouter =new ConfigRouter();

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
