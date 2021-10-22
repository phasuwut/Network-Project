package Router.test;

public class t {
    public static void main(String[] args) {
        Time timeout =new Time();
        System.out.println("1");
        timeout.setTimeout(() -> System.out.println("เพื่อนบ้านตายแล้วนะ"), 1000);
        System.out.println("2");
        timeout. setInterval(() -> System.out.println("Interval"),2500);
        System.out.println("3");
    }
}
