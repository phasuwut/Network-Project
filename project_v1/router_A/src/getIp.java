import java.net.InetAddress;

public class getIp {
    public static void main(String[] args) throws Exception {
        try {
            InetAddress ia = InetAddress.getLocalHost();
            String str = ia.getHostAddress();
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
