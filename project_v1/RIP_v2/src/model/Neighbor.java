package model;
import java.util.List;
public class Neighbor {

    private  String  name;

    private String port;

    private List<RoutingTableModel> routingTableModel;

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

    public List<RoutingTableModel> getRoutingTableModel() {
        return routingTableModel;
    }

    public void setRoutingTableModel(List<RoutingTableModel> routingTableModel) {
        this.routingTableModel = routingTableModel;
    }

    public Neighbor(String name, String port, List<RoutingTableModel> routingTableModel) {
        this.name = name;
        this.port = port;
        this.routingTableModel = routingTableModel;
    }
}