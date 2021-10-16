package RIP.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import RIP.model.*;

public class FileReader {

    public List<RoutingTableModel>  createdRoutingTable(List<RoutingTableModel> router,String filename){
        try {
            File  myObj = new File ("router_A/src/RIP/data/" + filename);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                RoutingTableModel routingTableModel = new RoutingTableModel(data.split(":")[0], data.split(":")[1], data.split(":")[2]);
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