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




        System.out.printf("Router_A = %s\n",router_A.toString());
        System.out.printf("Router_B = %s\n",router_B.toString());
        System.out.printf("Router_C = %s\n",router_C.toString());
        System.out.printf("Router_D = %s\n",router_D.toString());
        System.out.printf("Router_E = %s\n",router_E.toString());
        System.out.printf("Router_F = %s\n",router_F.toString());



    }
}
