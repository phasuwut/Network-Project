package model;

import java.util.List;

public class RouterModel {

    private List<RoutingTableModel> routingTableModels;

    private String name;

    private Integer port;

    private List<Neighbor> neighbors;

    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

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

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public List<Neighbor> getNeighbors() {
        return neighbors;
    }

    public void setNeighbors(List<Neighbor> neighbors) {
        this.neighbors = neighbors;
    }

    public RouterModel(List<RoutingTableModel> routingTableModels, String name, Integer port) {
        this.routingTableModels = routingTableModels;
        this.name = name;
        this.port = port;
        this.status = false;
    }

    @Override
    public String toString() {
        return "[name = " + name + ", port =" + port + ", neighbors = " + neighbors +", routingTable = " +routingTableModels +" ]";
    }

//    public RouterModel(List<RoutingTableModel> routingTableModels, String name, Integer port,, Boolean status)
//        this.routingTableModels = routingTableModels;
//        this.name = name;
//        this.port = port;
//        this.neighbors = neighbors;
//        this.status = status;
//    }
}
