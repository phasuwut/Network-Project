package RIP.controller;

import RIP.model.RoutingTableModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    ConfigRouter configRouter =new ConfigRouter();

    public List<RoutingTableModel> createdRoutingTable(List<RoutingTableModel> router){
        try {
            File  myObj = new File (configRouter.getFolder().concat(configRouter.getRouterFile()));
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                RoutingTableModel routingTableModel = new RoutingTableModel();
                routingTableModel.AddRoutingTableModel(data.split(":")[0], data.split(":")[1], data.split(":")[2]);

                router.add(routingTableModel);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return router;
    }
}