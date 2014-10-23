package SMOs;

import java.util.LinkedList;

public class FB extends SMO{

    @Override
    public void model(Queue queue) {

        Problem[] problems = queue.getProblems();
        LinkedList<Problem> queue2 = new LinkedList<Problem>();
        for (int i = 0; i < problems.length;  i++){
            if (queue2.size() > 0 && SMO.getCurrent_time() + Const.quantum <= problems[i].arrival_time){
                i--;
                Problem problem = queue2.get(0);
                queue2.remove(problem);
                if (SMO.getCurrent_time() - problem.arrival_time >= Const.t1 + Const.t2) {
                    SMO.count_not_processing_task++;
                    problem.setEnd_Time(SMO.getCurrent_time());
                    continue;
                }
                problem.processing_time+=Const.quantum;
                SMO.setCurrent_time(SMO.getCurrent_time() + Const.quantum);
                if (problem.treatment_time <= problem.processing_time) problem.setEnd_Time(SMO.getCurrent_time());
                else
                    queue2.add(problem);
            } else {
                Problem problem = problems[i];
                double time = Math.max(SMO.getCurrent_time(), problem.arrival_time);
                problem.setFirs(time);
                SMO.setCurrent_time(time);
                if (SMO.getCurrent_time() - problem.arrival_time >= Const.t1 + Const.t2) {
                    SMO.count_not_processing_task++;
                    problem.setEnd_Time(SMO.getCurrent_time());
                    continue;
                }

                problem.processing_time += Const.quantum;
                SMO.setCurrent_time(SMO.getCurrent_time() + Const.quantum);

                if (problem.treatment_time <= problem.processing_time) problem.setEnd_Time(SMO.getCurrent_time());
                else
                    queue2.add(problem);
            }
        }

        boolean fl = true;
        while (queue2.size() > 0){
            Problem problem = queue2.get(0);
            queue2.remove(problem);
            if (SMO.getCurrent_time() - problem.arrival_time >= Const.t1 + Const.t2) {
                SMO.count_not_processing_task++;
                problem.setEnd_Time(SMO.getCurrent_time());
                continue;
            }
            problem.processing_time+=Const.quantum;
            SMO.setCurrent_time(SMO.getCurrent_time() + Const.quantum);
            if (problem.treatment_time <= problem.processing_time) problem.setEnd_Time(SMO.getCurrent_time());
            else
                queue2.add(problem);
        }

    }

    @Override
    public String getType() {
        return "FB";
    }
}
