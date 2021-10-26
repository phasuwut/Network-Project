package service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.TimerTask;

public class NewCheckNeighbor extends TimerTask {
    @Override
    public void run() {
        System.out.println("Email sent at: "
                + LocalDateTime.ofInstant(Instant.ofEpochMilli(scheduledExecutionTime()),
                ZoneId.systemDefault()));
    }
}