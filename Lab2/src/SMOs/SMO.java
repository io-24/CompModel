package SMOs;

import java.util.ArrayList;
import java.util.LinkedList;


public abstract class SMO {
    private static double current_time = 0;
    public static int count_not_processing_task = 0;

    public static double getCurrent_time(){
        return current_time;
    }


    public static void clear() {
         current_time = 0;
         count_not_processing_task = 0;
    }

    public abstract void model(Queue queue);

    public static void setCurrent_time(double current_time) {
        SMO.current_time = current_time;
    }

    public abstract String getType();
}
