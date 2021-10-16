import java.net.InetAddress;

import RIP.controller.*;

public class ConfigRouter {
    static int port=9091;
    static String routerName="Too";
    static String routerFile="Router_A.txt";

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

    public void getFile(){
        FileReader fileReader =new FileReader();
        fileReader.readFile(routerFile);
      //  System.out.print(  fileReader.readFile("routerFile"));
        System.out.print("readFile");
    }



}
