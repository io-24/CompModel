package statustable;

import model.interfaces.Device;

import java.util.ArrayList;

/**
 * Created by byte on 23.10.14.
 *
 */
public class StatusTable {
    private static StatusTable statusTable;

    static {
        statusTable = new StatusTable();
    }

    private double current_time;
    private ArrayList<Problem> problems;



    private StatusTable(){
        current_time = 0;
        problems = new ArrayList<Problem>();
    }


    public static StatusTable getStatusTable(){
        return  statusTable;
    }

    public boolean addStatus(Device device, double next, int number_processor) {
        problems.add(new Problem(device, current_time + next, number_processor));
        return true;
    }

    public double getCurrentTime() {
        return current_time;
    }

    public void next(){
        Problem problem = problems.get(0);
        for (Problem buf:problems){
            if (buf.arrival_time < problem.arrival_time) problem = buf;
        }
        problems.remove(problem);
        current_time = problem.arrival_time;
        problem.device.processingTask(problem.number_processor);

    }

    private class Problem{
        final Device device;
        final double arrival_time;
        final int number_processor;

        public Problem(Device device, double arrival_time, int number_processor) {
            this.arrival_time = arrival_time;
            this.device = device;
            this.number_processor = number_processor;
        }

       /* @Override
        public int compareTo(Problem problem) {
            if (problem.arrival_time < arrival_time) return -1; else
            if (problem.arrival_time == arrival_time) return 0; else
                return 1;
        }*/
    }

}