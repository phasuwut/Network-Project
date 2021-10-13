package service;

import model.RouterModel;
import model.RoutingTableModel;

import javax.xml.ws.ServiceMode;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

@ServiceMode
public class RoutingTable {

    public List<RoutingTableModel> createdRoutingTable(List<RoutingTableModel> router, String filename){
//        RoutingTableModel routingTableModel = new RoutingTableModel();
        try {
            File myObj = new File ("Network-Project/" + filename);
            Scanner myReader = new Scanner(myObj);


            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                RoutingTableModel routingTableModel = new RoutingTableModel(data.split(":")[0], data.split(":")[1], data.split(":")[2]);
                router.add(routingTableModel);

            }
//
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        return router;

    }

    public void printRoutingTable(List<RoutingTableModel> router, String routerName){
        System.out.printf("|                    %-41s|\n", "Routing Table in " + routerName);
        System.out.println("|   destination subnet   |   next router   |   hops to dest   |");
        for (int i = 0; i < router.size() ; i++){
            System.out.printf("|       %-17s|       %-10s|         %-9s|\n", router.get(i).getDest_sub(), router.get(i).getNext_router(), router.get(i).getHops_to_dest());
        }

    }

}
