package Socket_RIP;

import model.RoutingTableModel;

import java.util.List;

public class Status {
    public Status(int routerPort, String routerName ){
        this.routerPort = routerPort;
        this.routerName = routerName;

    }
    private int routerPort;
    private String routerName;

    public int getRouterPort() {
        return routerPort;
    }
    public void setRouterPort(int routerPort) {
        this.routerPort = routerPort;
    }

    public String getRouterName() {
        return routerName;
    }
    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }
}
