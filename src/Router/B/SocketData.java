package Router.B;
import java.io.Serializable;

public class SocketData implements Serializable  {


    public SocketData(int routerPort, String routerName){
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

    public String toString() {

        return  getRouterPort() + " " + getRouterName();
    }
}
