package model;

import java.util.List;

public class RouterModel {

    private List<RoutingTableModel> routingTableModels;

    private String name;

    private String port;

    private List<Neighbor> neighbors;



    public List<RoutingTableModel> getRoutingTableModels() {
        return routingTableModels;
    }

    public void setRoutingTableModels(List<RoutingTableModel> routingTableModels) {
        this.routingTableModels = routingTableModels;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public List<Neighbor> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Neighbor> neighbors) {
        this.neighbors = neighbors;
    }

    public RouterModel(List<RoutingTableModel> routingTableModels, String name, String port) {
        this.routingTableModels = routingTableModels;
        this.name = name;
        this.port = port;
    }

    @Override
    public String toString() {
        return "[name = " + name + ", port =" + port + ", neighbors = " + neighbors +", routingTable = " +routingTableModels +" ]";
    }
}