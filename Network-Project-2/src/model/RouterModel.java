package model;

import java.util.List;

public class RouterModel {

    private List<RoutingTableModel> routingTableModels;

    private String name;

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

    public RouterModel(List<RoutingTableModel> routingTableModels, String name) {
        this.routingTableModels = routingTableModels;
        this.name = name;
    }

    @Override
    public String toString() {
        return "[name = " + name + ", routingTableModels = " +routingTableModels +" ]";
    }
}