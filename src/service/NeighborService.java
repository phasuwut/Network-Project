package service;

import Socket_RIP.Socket_RIP_Client;
import model.Count;
import model.Neighbor;
import model.RouterModel;
import model.RoutingTableModel;

import java.util.ArrayList;
import java.util.List;

public class NeighborService {


    public List<Neighbor> addNeighborToRouter(String[] myNeighbors) {
        List<Neighbor> neighbors = new ArrayList<Neighbor>();
        RouterService routerService = new RouterService();

        for (int i = 0; i < myNeighbors.length; i++) {
            switch (myNeighbors[i]) {
                case "A":
                    neighbors.add(new Neighbor(routerService.getRouter_A().getName(), routerService.getRouter_A().getPort(), routerService.getRouter_A().getRoutingTableModels()));
                    break;
                case "B":
                    neighbors.add(new Neighbor(routerService.getRouter_B().getName(), routerService.getRouter_B().getPort(), routerService.getRouter_B().getRoutingTableModels()));
                    break;
                case "C":
                    neighbors.add(new Neighbor(routerService.getRouter_C().getName(), routerService.getRouter_C().getPort(), routerService.getRouter_C().getRoutingTableModels()));
                    break;
                case "D":
                    neighbors.add(new Neighbor(routerService.getRouter_D().getName(), routerService.getRouter_D().getPort(), routerService.getRouter_D().getRoutingTableModels()));
                    break;
                case "E":
                    neighbors.add(new Neighbor(routerService.getRouter_E().getName(), routerService.getRouter_E().getPort(), routerService.getRouter_E().getRoutingTableModels()));
                    break;
                case "F":
                    neighbors.add(new Neighbor(routerService.getRouter_F().getName(), routerService.getRouter_F().getPort(), routerService.getRouter_F().getRoutingTableModels()));
                    break;
                case "G":
                    neighbors.add(new Neighbor(routerService.getRouter_G().getName(), routerService.getRouter_G().getPort(), routerService.getRouter_G().getRoutingTableModels()));
                    break;
                case "H":
                    neighbors.add(new Neighbor(routerService.getRouter_H().getName(), routerService.getRouter_H().getPort(), routerService.getRouter_H().getRoutingTableModels()));
                    break;
                case "I":
                    neighbors.add(new Neighbor(routerService.getRouter_I().getName(), routerService.getRouter_I().getPort(), routerService.getRouter_I().getRoutingTableModels()));
                    break;
            }

        }

        return neighbors;
    }


    public void checkStatusNeighbors(RouterModel routerModel, List<Count> countList, String name) {
        RoutingTable routingTable = new RoutingTable();
        RouterService routerService = new RouterService();
        Socket_RIP_Client socketClient = new Socket_RIP_Client();

        ArrayList desSud = new ArrayList();


        for (int i = 0; i < countList.size(); i++) {
            if (countList.get(i).getName().equals(name)) {
//

                if (System.currentTimeMillis() - countList.get(i).getUpdatedTime() >= 18000) {
//                    System.out.println("System.currentTimeMillis() - countList.get(i).getUpdatedTime() " + (System.currentTimeMillis() - countList.get(i).getUpdatedTime()));
                    for (int j = 0; j < routerModel.getRoutingTableModels().size(); ) {
                        if (routerModel.getRoutingTableModels().get(j).getNext_router().equals(name)) {
//                            System.out.println("2222222222222222222222222222");


                            desSud.add(routerModel.getRoutingTableModels().get(j).getDest_sub());



                            routerModel.getRoutingTableModels().remove(j);
//                            routerService.tellNeighborToHaveUpdate(routerModel);
//                            System.out.println(      "333333333");


                        } else {
                            j++;
                        }
                    }



                    routingTable.printRouterModel(routerModel);
//                    routerService.tellNeighborToHaveUpdate(routerModel);


                    for (int p = 0; p < routerModel.getNeighbors().size(); p++){
                        for (int k = 0 ; k < desSud.size(); k++){
//                            System.out.println("5555555");

                            socketClient.myNeighborDisconnect(routerModel.getNeighbors().get(p),desSud.get(k).toString(), routerModel.getName());

                        }

                    }



                }

            }
        }



    }

}
