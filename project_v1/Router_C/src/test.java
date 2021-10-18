import RIP.model.RoutingTableModel;
import RIP.controller.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class test {
    public static List<RoutingTableModel> dataRouting = new ArrayList<RoutingTableModel>();

    public static void main(String[] args) {
        System.out.println("a");
        FileReader fileReader =new FileReader();

        fileReader.createdRoutingTable(dataRouting);
        System.out.println(dataRouting);  //  ArrayList
       // System.out.println(dataRouting.getClass());

    }
}

