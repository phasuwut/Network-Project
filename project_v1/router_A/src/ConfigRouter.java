import java.net.InetAddress;

public class ConfigRouter {

    static int port=9091;
    InetAddress address = InetAddress.getLocalHost();
    String ipAddress = address.getHostAddress();

    //System.out.println(ipAddress);
}
