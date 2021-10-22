package controller;

import service.ServerService;

import java.io.IOException;

public class RouterController {
    public static void main(String[] args) throws InterruptedException, IOException {
        ServerService serverRouter_A = new ServerService();
        serverRouter_A.OpenServer("Router_A.txt", "Router A" , 9091, new String[]{"B", "C", "F"});

        ServerService serverRouter_B = new ServerService();
        serverRouter_B.OpenServer("Router_B.txt", "Router B" , 9091, new String[]{"A", "E", "F"});

        ServerService serverRouter_C = new ServerService();
        serverRouter_C.OpenServer("Router_C.txt", "Router C" , 9091, new String[]{"A", "D"});

        ServerService serverRouter_D = new ServerService();
        serverRouter_D.OpenServer("Router_D.txt", "Router D" , 9091, new String[]{"C", "E"});

        ServerService serverRouter_E= new ServerService();
        serverRouter_E.OpenServer("Router_E.txt", "Router E" , 9091, new String[]{"B", "D", "F"});

        ServerService serverRouter_F= new ServerService();
        serverRouter_F.OpenServer("Router_F.txt", "Router F" , 9091, new String[]{"A", "B", "E"});

    }
}
