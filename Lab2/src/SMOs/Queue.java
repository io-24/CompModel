package SMOs;

import Streams.*;


public class Queue {
    public Problem[] problems;
    public Stream stream_input, stream_intensity;
    public double average_time_in_system, average_square_time_in_system, ping, ratio;

    public Queue(){
        problems = new Problem[Const.N];
        stream_input = new Pusasson_Stream(Const.lyamda);
        stream_intensity = new Regular(Const.myu);
        genarate(problems, stream_input, stream_intensity);
        average_time_in_system = -1;
        average_square_time_in_system = -1;
        ping = -1;
        ratio = -1;
    }

    public Queue(Queue queue) {
        problems = new Problem[queue.problems.length];
        for (int i = 0; i<problems.length; i++){
            problems[i] =  new Problem(queue.problems[i]);
            average_time_in_system = -1;
            average_square_time_in_system = -1;
            ping = -1;
            ratio = -1;
        }
    }

    public Queue clone(){
        return new Queue(this);
    }

    private void genarate(Problem[] problems, Stream stream1, Stream stream2) {
        for (int i = 0; i < problems.length; i++) {
            problems[i] = new Problem(stream1.next(), stream2.next());
            if (i>0) problems[i].arrival_time += problems[i-1].arrival_time;
        }
    }


    public Problem[] getProblems() {
        return problems;
    }

    public double getAverage_time_in_system(){
        if (average_time_in_system == -1){
            average_time_in_system = 0;
            int count = 0;
            for (Problem problem:problems){
                if (problem.end_time != -1 && problem.first_time != -1) {
                    average_time_in_system += problem.end_time - problem.arrival_time;
                    count++;
                }
            }

            average_time_in_system /= count;
        }
        return average_time_in_system;
    }

    public double getAverage_square_time_in_system(){
        if (average_square_time_in_system == -1){
            double mx = getAverage_time_in_system();
            average_square_time_in_system = 0;
            int count = 0;
            for (Problem problem:problems){
                if (problem.end_time != -1 && problem.first_time != -1) {
                    average_square_time_in_system += Math.pow(problem.end_time - problem.arrival_time - mx, 2);
                    count++;
                }
            }
            average_square_time_in_system/=count;
        }
        return average_square_time_in_system;
    }


    public double getPing(){
        if (ping == -1){
            ping = 0;
            int count = 0;
            for (Problem problem:problems){
                if (problem.first_time != -1) {
                    ping += problem.first_time - problem.arrival_time;
                    count++;
                }
            }
            ping /= count;
        }
        return ping;
    }
    
    public double getRatio(){
        if (ratio == -1){
            ratio = (SMO.count_not_processing_task+0.0)/problems.length;
        }
        return ratio;
    }

    public double getSumMark(){
        double ans = 0;
        for (Problem problem:problems){
            if(problem.end_time - problem.arrival_time <= Const.t1){
                ans += 1;
            } else
            if (problem.end_time - problem.arrival_time <= Const.t1 + Const.t2) {
                double h = (Const.t2+Const.t1-(problem.end_time - problem.arrival_time)) / Const.t2;
//                System.out.println(h);
                ans += h;
            }
        }
        return ans/problems.length;
    }


}
