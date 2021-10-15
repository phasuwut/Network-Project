package controller;

import model.RouterModel;
import model.RoutingTableModel;
import service.RoutingTable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Initial  {

    public static void main(String[] args) {
        int ascii = 65;

        RoutingTable routingTable = new RoutingTable();


        List<RoutingTableModel> router_A = new ArrayList<RoutingTableModel>();
        List<RoutingTableModel> router_B = new ArrayList<RoutingTableModel>();
        List<RoutingTableModel> router_C = new ArrayList<RoutingTableModel>();
        List<RoutingTableModel> router_D = new ArrayList<RoutingTableModel>();
        List<RoutingTableModel> router_E = new ArrayList<RoutingTableModel>();
        List<RoutingTableModel> router_F = new ArrayList<RoutingTableModel>();


        routingTable.createdRoutingTable(router_A, "Router_A.txt");
        routingTable.createdRoutingTable(router_B, "Router_B.txt");
        routingTable.createdRoutingTable(router_C, "Router_C.txt");
        routingTable.createdRoutingTable(router_D, "Router_D.txt");
        routingTable.createdRoutingTable(router_E, "Router_E.txt");
        routingTable.createdRoutingTable(router_F, "Router_F.txt");

        List<RouterModel> routerList = new ArrayList<RouterModel>();
        RouterModel routerModelA = new RouterModel(router_A,"Router A");
        RouterModel routerModelB = new RouterModel(router_B,"Router B");
        RouterModel routerModelC = new RouterModel(router_C,"Router C");
        RouterModel routerModelD = new RouterModel(router_D,"Router D");
        RouterModel routerModelE = new RouterModel(router_E,"Router E");
        RouterModel routerModelF = new RouterModel(router_F,"Router F");

        routerList.add(routerModelA);
        routerList.add(routerModelB);
        routerList.add(routerModelC);
        routerList.add(routerModelD);
        routerList.add(routerModelE);
        routerList.add(routerModelF);

        routingTable.printRouterList((routerList));
    }
}
