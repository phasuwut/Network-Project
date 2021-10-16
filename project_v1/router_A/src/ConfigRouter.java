import java.net.InetAddress;

import RIP.controller.*;
import RIP.model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigRouter {
    static int port=9091;
    static String routerName="Too";
    static String routerFile="Router_A.txt";

    public int getPort(){
        return port;
    }
    public String getIp(){
        try {
            InetAddress  address= InetAddress.getLocalHost();
            String ipAddress =  address.getHostAddress();
            return ipAddress;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
    public String getRouterName(){
        return routerName;
    }

    public static List<RoutingTableModel> dataRouting = new ArrayList<RoutingTableModel>();

    public void getFile(){
        FileReader fileReader =new FileReader();
        System.out.println(   fileReader.createdRoutingTable(dataRouting,routerFile));
    }

}
