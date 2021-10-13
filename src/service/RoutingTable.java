package service;

import model.RouterModel;
import model.RoutingTableModel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;


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

}
