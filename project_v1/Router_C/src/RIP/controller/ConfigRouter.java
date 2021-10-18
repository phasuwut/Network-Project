package RIP.controller;

import RIP.model.RoutingTableModel;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class ConfigRouter {
    static int port=9093;
    static String routerName="C";
    static String routerFile="Router_C.txt";
    static String folder="Router_C/src/RIP/data/";

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
    public String getFolder(){
        return folder;
    }
    public String getRouterFile(){
        return routerFile;
    }

}
