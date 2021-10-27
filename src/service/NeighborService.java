package service;

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

//        for(int i = 0 ; i < countList.size(); i++){
//            if(countList.get(i).getName().equals(name)){
//                if (countList.get(i).getValue() == countList.get(i).getCount()){ // เพื่อนบ้านยังไม่ตาย
//                    countList.get(i).setStatus(true); //
////                    countList.get(i).setCount(0);
//
////                    countList.get(i).setValue(0);
//                }
//                else if(countList.get(i).getCount() - countList.get(i).getValue() > 6){
//                    countList.get(i).setStatus(false);
//                    countList.get(i).setValue(0);
//                    countList.get(i).setCount(0);
//
//                }
//            }
//        }
        for (int i = 0; i < countList.size(); i++) {
            if (countList.get(i).getName().equals(name)) {
//                System.out.println(System.currentTimeMillis());
//                System.out.println(countList.get(i).getUpdatedTime());

                if (System.currentTimeMillis() - countList.get(i).getUpdatedTime() >= 18000) {
//                    System.out.println("System.currentTimeMillis() - countList.get(i).getUpdatedTime() " + (System.currentTimeMillis() - countList.get(i).getUpdatedTime()));
                    for (int j = 0; j < routerModel.getRoutingTableModels().size(); ) {
                        if (routerModel.getRoutingTableModels().get(j).getNext_router().equals(name)) {

                            routerModel.getRoutingTableModels().remove(j);

                        } else {
                            j++;
                        }
                    }
                }
            }
        }
        routerService.tellNeighborToHaveUpdate(routerModel);
        routingTable.printRouterModel(routerModel);


    }

}
