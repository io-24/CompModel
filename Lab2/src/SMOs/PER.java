package SMOs;

import java.util.LinkedList;

public class PER extends SMO {

    @Override
    public void model(Queue queue) {
        Problem[] problems = queue.getProblems();
        LinkedList<LinkedList<Problem>> queues = new LinkedList<LinkedList<Problem>>();
        LinkedList<Problem> list_first = new LinkedList<Problem>();
        queues.add(list_first);
        main: for (int i = 0; i < problems.length;  i++){
            for (int j = 0; j < queues.size(); j++){
            LinkedList<Problem> list = queues.get(j);
            if (list.size() > 0 && SMO.getCurrent_time() + Const.quantum <= problems[i].arrival_time){
                i--;
                Problem problem = list.get(0);
                list.remove(problem);
                if (SMO.getCurrent_time() - problem.arrival_time >= Const.t1 + Const.t2) {
                    SMO.count_not_processing_task++;
                    problem.setEnd_Time(SMO.getCurrent_time());
                    continue  main;
                }
                problem.processing_time+=Const.quantum;
                SMO.setCurrent_time(SMO.getCurrent_time() + Const.quantum);
                if (problem.treatment_time <= problem.processing_time) problem.setEnd_Time(SMO.getCurrent_time());
                else {
                    if (j == queues.size()-1) {
                        LinkedList<Problem> list1 = new LinkedList<Problem>();
                        list1.add(problem);
                        queues.add(list1);
                    } else queues.get(j+1).add(problem);
                }
                continue main;
            } }
            {
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
                    list_first.add(problem);
            }
        }

        boolean fl = true;
        main: while (fl){
            fl = false;
            for (int j = 0; j < queues.size(); j++){
                LinkedList<Problem> list = queues.get(j);
                if (list.size() > 0){
                    fl = true;
                    Problem problem = list.get(0);
                    list.remove(problem);
                    if (SMO.getCurrent_time() - problem.arrival_time >= Const.t1 + Const.t2) {
                        problem.setEnd_Time(SMO.getCurrent_time());
                        continue main;
                    }
                    problem.processing_time+=Const.quantum;
                    SMO.setCurrent_time(SMO.getCurrent_time() + Const.quantum);
                    if (problem.treatment_time <= problem.processing_time) problem.setEnd_Time(SMO.getCurrent_time());
                    else {
                        if (j == queues.size()-1) {
                            LinkedList<Problem> list1 = new LinkedList<Problem>();
                            list1.add(problem);
                            queues.add(list1);
                        } else queues.get(j+1).add(problem);
                    }
                    continue main;
                } }
        }

    }

    @Override
    public String getType() {
        return "PER";
    }
}
