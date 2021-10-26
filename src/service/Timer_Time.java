package service;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Timer_Time {

    public static void timerTick() {

        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        String formattedDate = df.format(c.getTime());

        System.out.println("Time now : " + formattedDate);

    }

}
