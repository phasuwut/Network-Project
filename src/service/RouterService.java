package service;

import Socket_RIP.Socket_RIP_Client;
import model.Neighbor;
import model.RouterModel;
import model.RoutingTableModel;

import javax.xml.ws.ServiceMode;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@ServiceMode
public class RouterService {

    public void addNeighbor(RouterModel routerModel, List<Neighbor> neighbors) {
        routerModel.setNeighbors(neighbors);
    }

    public void addForwarding(List<RoutingTableModel> router, String dest_subnet, String next_router, String hop_to_dest, String next_router_status) {
        RoutingTableModel routingTableModel = new RoutingTableModel(dest_subnet, next_router, hop_to_dest, next_router_status);
        router.add(routingTableModel);
    }

    public void updateForwarding(List<RoutingTableModel> router, String dest_subnet, String next_router, String hop_to_dest, String next_router_status) {
        for (int i = 0; i < router.size(); i++) {

//            System.out.println("next_router_status" + next_router_status);
            if (router.get(i).getDest_sub().equals(dest_subnet)) {

//                if (next_router_status.equals("connected")) {
//                        System.out.println("*******************");

                        if (parseInt(router.get(i).getHops_to_dest()) > 1 + parseInt(hop_to_dest)) {
                            router.get(i).setNext_router(next_router);
                            router.get(i).setHops_to_dest(String.valueOf(1 + parseInt(hop_to_dest)));
                        }
                    }

//                 else if (next_router_status.equals("disconnected")) {
////                        System.out.println("=======================");
//                        router.get(i).setNext_router(next_router);
//                        router.get(i).setHops_to_dest(String.valueOf(1 + parseInt(hop_to_dest)));
//                    }
//                }
//            }
        }
//                 count to infinity
//                router.get(i).setNext_router(next_router);
//                router.get(i).setHops_to_dest(String.valueOf(1 + parseInt(hop_to_dest)));


//                else if (parseInt(hop_to_dest) < parseInt(router.get(i).getHops_to_dest())) {
//                    router.get(i).setNext_router(next_router);
//                    router.get(i).setHops_to_dest(String.valueOf(parseInt(hop_to_dest)));
//                }

//            }
//
//            router.get(router.indexOf(dest_subnet)).setNext_router(next_router);
//            router.get(router.indexOf(dest_subnet)).setHops_to_dest(hop_to_dest);
//        }
//        System.out.println(router);

    }

    public List<RoutingTableModel> updateRoutingTable(List<RoutingTableModel> router, List<Neighbor> routerNeighbors) {

        for (int i = 0; i < routerNeighbors.size(); i++) {
            for (int j = 0; j < routerNeighbors.get(i).getRoutingTableModel().size(); j++) {

                if (haveDest(router, routerNeighbors.get(i).getRoutingTableModel().get(j).getDest_sub())  ) {
                    updateForwarding(router, routerNeighbors.get(i).getRoutingTableModel().get(j).getDest_sub(),
                            routerNeighbors.get(i).getName().split(" ")[1],
                            routerNeighbors.get(i).getRoutingTableModel().get(j).getHops_to_dest(),
                            routerNeighbors.get(i).getRoutingTableModel().get(j).getNext_router_state());


                } else {
//                        System.out.println(routerNeighbors.get(i).getName().split(" ")[1]);
//                        System.out.println(parseInt(routerNeighbors.get(i).getRoutingTableModel().get(j).getHops_to_dest()));

                    addForwarding(router, routerNeighbors.get(i).getRoutingTableModel().get(j).getDest_sub(),
                            routerNeighbors.get(i).getName().split(" ")[1],
                            String.valueOf(parseInt(routerNeighbors.get(i).getRoutingTableModel().get(j).getHops_to_dest()) + 1), "online");
                }
            }
        }

        return router;
    }


    public Boolean haveDest(List<RoutingTableModel> router, String dest_subnet) {

        for (int i = 0; i < router.size(); i++) {
            if (router.get(i).getDest_sub().equals(dest_subnet)) {
                return true;
            }
        }
        return false;

    }

    public void updateNeighborRoutingTable(List<RouterModel> routerList, RouterModel routerModel) {
        for (int i = 0; i < routerList.size(); i++) {
            for (int j = 0; j < routerList.get(i).getNeighbors().size(); j++) {
                if (routerList.get(i).getNeighbors().get(j).getName().equals(routerModel.getName())) { // ถ้าใครมีเพื่อนบ้านที่ update table
                    routerList.get(i).getNeighbors().get(j).setRoutingTableModel(routerModel.getRoutingTableModels());
                    updateRoutingTable(routerList.get(i).getRoutingTableModels(), routerList.get(i).getNeighbors());
                }

            }
        }
    }

    //
    public void tellNeighborToHaveUpdate(RouterModel routerModel) {
        for (int i = 0; i < routerModel.getNeighbors().size(); i++) {

            Socket_RIP_Client socket_RIP_client = new Socket_RIP_Client();
            socket_RIP_client.sendToServer(routerModel.getNeighbors().get(i), routerModel);
        }
    }

    public void getCompletedRoutingTable(List<RouterModel> routerList) {
        for (int i = 0; i < routerList.size(); i++) {
            this.updateRoutingTable(routerList.get(i).getRoutingTableModels(), routerList.get(i).getNeighbors());
            this.updateNeighborRoutingTable(routerList, routerList.get(i));
        }
    }

    public void deletedRouter(List<RouterModel> routerList, String routerName) {
        for (int i = 0; i < routerList.size(); i++) {
            for (int j = 0; j < routerList.get(i).getNeighbors().size(); j++) {
                if (routerList.get(i).getNeighbors().get(j).getName().equals(routerName)) {
                    routerList.get(i).getNeighbors().remove(j);
                }

            }
            if (routerList.get(i).getName().equals(routerName)) {

                routerList.remove(i);
            }
        }

//        this.getCompletedRoutingTable(routerList);
    }

    public void deletedRouter() {

    }

    public RouterModel getRouter_A() {
        RoutingTable routingTable = new RoutingTable();
        List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router, "Router_A.txt");

        RouterModel routerModelA = new RouterModel(router, "Router A", 9091);
        return routerModelA;
    }

    public RouterModel getRouter_B() {
        RoutingTable routingTable = new RoutingTable();
        List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router, "Router_B.txt");

        RouterModel routerModel = new RouterModel(router, "Router B", 9092);
        return routerModel;
    }

    public RouterModel getRouter_C() {
        RoutingTable routingTable = new RoutingTable();
        List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router, "Router_C.txt");

        RouterModel routerModel = new RouterModel(router, "Router C", 9093);
        return routerModel;
    }

    public RouterModel getRouter_D() {
        RoutingTable routingTable = new RoutingTable();
        List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router, "Router_D.txt");

        RouterModel routerModel = new RouterModel(router, "Router D", 9094);
        return routerModel;
    }

    public RouterModel getRouter_E() {
        RoutingTable routingTable = new RoutingTable();
        List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router, "Router_E.txt");

        RouterModel routerModel = new RouterModel(router, "Router E", 9095);
        return routerModel;
    }

    public RouterModel getRouter_F() {
        RoutingTable routingTable = new RoutingTable();
        List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router, "Router_F.txt");

        RouterModel routerModel = new RouterModel(router, "Router F", 9096);
        return routerModel;
    }

    public RouterModel getRouter_G() {
        RoutingTable routingTable = new RoutingTable();
        List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router, "Router_G.txt");

        RouterModel routerModel = new RouterModel(router, "Router G", 9097);
        return routerModel;
    }

    public RouterModel getRouter_H() {
        RoutingTable routingTable = new RoutingTable();
        List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router, "Router_H.txt");

        RouterModel routerModel = new RouterModel(router, "Router H", 9098);
        return routerModel;
    }

    public RouterModel getRouter_I() {
        RoutingTable routingTable = new RoutingTable();
        List<RoutingTableModel> router = new ArrayList<RoutingTableModel>();
        routingTable.createdRoutingTable(router, "Router_I.txt");

        RouterModel routerModel = new RouterModel(router, "Router I", 9099);
        return routerModel;
    }

    public List<RoutingTableModel> updateRoutingTableWhenNeighborOnline(List<RoutingTableModel> routingTableModel, List<RoutingTableModel> routingTableModel_neighbor, String neighborName) {

//        for (int i = 0; i < routingTableModel_neighbor.size(); i++) {
        for (int i = 0; i < routingTableModel_neighbor.size(); i++) {

            if (checkHaveDest(routingTableModel, routingTableModel_neighbor.get(i).getDest_sub())) {
                updateForwarding(routingTableModel, routingTableModel_neighbor.get(i).getDest_sub(), neighborName, routingTableModel_neighbor.get(i).getHops_to_dest()
                        , routingTableModel_neighbor.get(i).getNext_router_state());


            } else {
//                        System.out.println(routerNeighbors.get(i).getName().split(" ")[1]);
//                        System.out.println(parseInt(routerNeighbors.get(i).getRoutingTableModel().get(j).getHops_to_dest()));

                addForwarding(routingTableModel, routingTableModel_neighbor.get(i).getDest_sub(),
                        neighborName,
                        String.valueOf(parseInt(routingTableModel_neighbor.get(i).getHops_to_dest()) + 1), "connected");
            }
        }
//        }

        return routingTableModel;
    }
//    public List<RoutingTableModel> updateRoutingTableWhenNeighborOnline(List<RoutingTableModel> routingTableModel, List<RoutingTableModel> routingTableModel_neighbor, String neighborName) {

//    updateRoutingTableWhenNeighborDisconnected

    public Boolean checkHaveDest(List<RoutingTableModel> routingTableModel, String dest_subnet) {

        for (int i = 0; i < routingTableModel.size(); i++) {
            if (routingTableModel.get(i).getDest_sub().equals(dest_subnet)) {
                return true;
            }
        }
        return false;

    }



//
//    public List<RoutingTableModel> updateNeighborRoutingTableWhenRoutingTableChange(){
//
//    }

    public List<RoutingTableModel> updateRoutingTableWhenNeighborDisconnect(List<RoutingTableModel> routingTableModel, String neighborDisconnectedName, List<Neighbor> neighbors) {


        System.out.println("rrrrrrrrrrrrrrrrrrr");

        for(int j = 0; j < neighbors.size() ; j++){
            if(!neighbors.get(j).getName().equals(neighborDisconnectedName)){
                for (int i = 0; i < neighbors.get(j).getRoutingTableModel().size(); i++) {
                    if (checkHaveDest(routingTableModel, neighbors.get(j).getRoutingTableModel().get(i).getDest_sub()) &&
                            !neighbors.get(j).getRoutingTableModel().get(i).getNext_router().equals("-"))
                    {
                        updateForwardingCount(routingTableModel,  neighbors.get(j).getRoutingTableModel().get(i).getDest_sub(),
                                neighbors.get(j).getRoutingTableModel().get(i).getNext_router(),
                                neighbors.get(j).getRoutingTableModel().get(i).getHops_to_dest(),
                                neighbors.get(j).getRoutingTableModel().get(i).getNext_router_state());


                    }
                }
            }
        }




        return routingTableModel;
    }

    public void updateForwardingCount(List<RoutingTableModel> router, String dest_subnet, String next_router, String hop_to_dest, String next_router_status) {
        for (int i = 0; i < router.size(); i++) {

//            System.out.println("next_router_status" + next_router_status);
            if (router.get(i).getDest_sub().equals(dest_subnet)) {

//                        System.out.println("=======================");
                    router.get(i).setNext_router(next_router);
                    router.get(i).setHops_to_dest(String.valueOf(1 + parseInt(hop_to_dest)));

            }
//            }
        }
//                 count to infinity
//                router.get(i).setNext_router(next_router);
//                router.get(i).setHops_to_dest(String.valueOf(1 + parseInt(hop_to_dest)));


//                else if (parseInt(hop_to_dest) < parseInt(router.get(i).getHops_to_dest())) {
//                    router.get(i).setNext_router(next_router);
//                    router.get(i).setHops_to_dest(String.valueOf(parseInt(hop_to_dest)));
//                }

//            }
//
//            router.get(router.indexOf(dest_subnet)).setNext_router(next_router);
//            router.get(router.indexOf(dest_subnet)).setHops_to_dest(hop_to_dest);
//        }
//        System.out.println(router);

    }

}
