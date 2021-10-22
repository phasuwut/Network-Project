package Socket_RIP;

public class Time {
    public static void setTimeout(Runnable runnable, int delay){
        new Thread(() -> {
            try {
                Thread.sleep(delay);
                runnable.run();
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
    public static void setInterval(Runnable runnable, int interval){
        new Thread(() -> {
            try {
                while (true) {
                    Thread.sleep(interval);
                    runnable.run();
                }
            }
            catch (Exception e){
                System.err.println(e);
            }
        }).start();
    }
}
