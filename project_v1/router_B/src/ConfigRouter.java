import RIP.controller.FileReader;
import RIP.model.RoutingTableModel;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class ConfigRouter {
    static int port=9092;
    static String routerName="Pom";
    static String routerFile="Router_B.txt";

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

    public List<RoutingTableModel> getFile(){
        FileReader fileReader =new FileReader();
        //System.out.println(fileReader.createdRoutingTable(dataRouting,routerFile));
        return fileReader.createdRoutingTable(dataRouting,routerFile);
    }

}
