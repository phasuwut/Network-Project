package controller;

import service.NewCheckNeighbor;
import service.ServerService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class RouterController {
    public static void main(String[] args) throws InterruptedException, IOException {
//        ServerService serverRouter_A = new ServerService();
//        serverRouter_A.OpenServer("Router_A.txt", "Router A" , 9091, new String[]{"B", "C", "F"});
//
//        ServerService serverRouter_B = new ServerService();
//        serverRouter_B.OpenServer("Router_B.txt", "Router B" , 9092, new String[]{"A", "E", "F"});
//
//        ServerService serverRouter_C = new ServerService();
//        serverRouter_C.OpenServer("Router_C.txt", "Router C" , 9093, new String[]{"A", "D"});
//
//        ServerService serverRouter_D = new ServerService();
//        serverRouter_D.OpenServer("Router_D.txt", "Router D" , 9094, new String[]{"C", "E"});
//
//        ServerService serverRouter_E= new ServerService();
//        serverRouter_E.OpenServer("Router_E.txt", "Router E" , 9095, new String[]{"B", "D", "F"});
//
//        ServerService serverRouter_F= new ServerService();
//        serverRouter_F.OpenServer("Router_F.txt", "Router F" , 9096, new String[]{"A", "B", "E"});

        new Timer().schedule(new NewCheckNeighbor(), 0, 18000);

//        for (int i = 0; i < 3; i++) {
//            Thread.sleep(1000);
//        }

    }

    private static void timerTick() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String formattedDate = df.format(c.getTime());

        System.out.println("Time now : " + formattedDate);

    }
}