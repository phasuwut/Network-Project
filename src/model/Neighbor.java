package model;

import java.net.InetAddress;
import java.util.List;

public class Neighbor {

    private  String  name;

    private Integer port;


    private List<RoutingTableModel> routingTableModel;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPort() {
        return port;
    }

    public void Integer(Integer port) {
        this.port = port;
    }

    public List<RoutingTableModel> getRoutingTableModel() {
        return routingTableModel;
    }

    public void setRoutingTableModel(List<RoutingTableModel> routingTableModel) {
        this.routingTableModel = routingTableModel;
    }

    public String getRouterIp(){
        try {
            InetAddress address= InetAddress.getLocalHost();
            String ipAddress =  address.getHostAddress();
            return ipAddress;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public Neighbor(String name, Integer port, List<RoutingTableModel> routingTableModel) {
        this.name = name;
        this.port = port;
        this.routingTableModel = routingTableModel;
    }

    @Override
    public String toString() {
        return "[name = " + name + ", port =" + port + ", routingTable = " +routingTableModel +" ]";
    }


}
