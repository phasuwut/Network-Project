package model;

public class RouterModel {

    private RoutingTableModel routingTableModels;

    public RouterModel(RoutingTableModel routingTableModels) {
        this.routingTableModels = routingTableModels;
    }

    public RoutingTableModel getRoutingTableModels() {
        return routingTableModels;
    }

    public void setRoutingTableModels(RoutingTableModel routingTableModels) {
        this.routingTableModels = routingTableModels;
    }
}
