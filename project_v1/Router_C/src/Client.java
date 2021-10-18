import RIP.controller.FileReader;
import RIP.model.RoutingTableModel;
import RIP.controller.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    public static List<RoutingTableModel> dataRouting = new ArrayList<RoutingTableModel>();

    public static void main(String[] args) {
        System.out.println("C");
        FileReader fileReader =new FileReader();
        fileReader.createdRoutingTable(dataRouting );
        System.out.println(dataRouting);  //  ArrayList
        // System.out.println(dataRouting.getClass());

        Scanner sc = new Scanner(System.in);

    }
}
