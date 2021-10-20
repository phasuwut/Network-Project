package Router.A;
import java.io.Serializable;

public class Data implements Serializable  {

    public Data(String routerPort, String routerName) {

        this.routerPort = routerPort;
        this.routerName = routerName;
    }
    private static final long serialVersionUID = 1L;

    private String routerPort;
    private String routerName;

    public String getRouterPort() {
        return routerPort;
    }
    public void setRouterPort(String routerPort) {
        this.routerPort = routerPort;
    }

    public String getRouterName() {
        return routerName;
    }
    public void setRouterName(String routerName) {
        this.routerName = routerName;
    }

    public String toString() {
        return  getRouterPort() + " " + getRouterName();
    }

}
