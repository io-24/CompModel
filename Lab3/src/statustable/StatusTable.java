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

    public boolean addStatus(Device device, double next) {
        problems.add(new Problem(device, current_time + next));
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
        problem.device.processingTask();

    }

    private class Problem{
        final Device device;
        final double arrival_time;

        public Problem(Device device, double arrival_time) {
            this.arrival_time = arrival_time;
            this.device = device;
        }

       /* @Override
        public int compareTo(Problem problem) {
            if (problem.arrival_time < arrival_time) return -1; else
            if (problem.arrival_time == arrival_time) return 0; else
                return 1;
        }*/
    }

}