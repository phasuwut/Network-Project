package Router.B;



import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class ConfigRouter {
    public void ConfigRouter(){

    }
    static int port=9093;
    static String routerName="Router_C";
    static String routerFile="Router_C.txt";
    static String folder="Data/";

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
    public String getFolder(){
        return folder;
    }
    public String getFile(){
        return routerFile;
    }
}