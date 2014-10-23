package SMOs;

public class Problem {
    public double arrival_time, treatment_time, processing_time, end_time, first_time;
    boolean processing;

    public Problem(double arrival_time, double treatment_time) {
        this.arrival_time = arrival_time;
        this.treatment_time = treatment_time;
        processing_time = 0;
        processing = false;
        first_time = -1;
        end_time = -1;
    }

    public Problem(Problem problem) {
        this(problem.arrival_time, problem.treatment_time);
    }


    public void show() {
        System.out.printf("arrival_time %1.4f treatment_time %1.4f processing_time %1.4f delta %1.4f\n", arrival_time, treatment_time, processing_time, processing_time - treatment_time);
//        System.out.println("arrival_time = "+arrival_time+" treatment_time = "+treatment_time+" prcessing_time "+processing_time);

    }

    public void setEnd_Time(double time){
        if (end_time == -1)
            end_time = time;
    }

    public void setFirs(double time){
        if (first_time == -1)
            first_time = time;
    }
}
