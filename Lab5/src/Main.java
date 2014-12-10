import org.apache.commons.math3.optim.PointValuePair;
import org.apache.commons.math3.optim.linear.*;
import org.apache.commons.math3.optim.nonlinear.scalar.GoalType;
import test.Test;
import var.Variable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 03.12.14.
 */
public class Main {

    static void simplex() {
        LinearObjectiveFunction lof = new LinearObjectiveFunction(new double[]{1, 2, 5}, 0);
        List<LinearConstraint> l = new ArrayList<LinearConstraint>();
        l.add(new LinearConstraint(new double[]{1, 0, 1}, Relationship.LEQ, 4));
        l.add(new LinearConstraint(new double[]{-1, 0, 1}, Relationship.GEQ, 0));
        l.add(new LinearConstraint(new double[]{1, 1, 0}, Relationship.LEQ, 8));
        l.add(new LinearConstraint(new double[]{1,0,0}, Relationship.GEQ, 0));
        PointValuePair p = new SimplexSolver().optimize(lof, new LinearConstraintSet(l), GoalType.MAXIMIZE);
        System.out.println(p.getValue()+" "+ Arrays.toString(p.getPoint()));
    }

    public static void main(String[] args) {
        Variable x1 = new Variable(0, 100);
        Variable x2 = new Variable(0, 100);
        Variable x3 = new Variable(0, 100);

        Test t = new Test(x1, x2, x3);
        double ans = Double.MIN_VALUE;
        String ansStr = null;
        while (t.next()) {
            if (t.getFuncRez() > ans) {
                ansStr = t.toString();
                ans = t.getFuncRez();
            }
        }

        System.out.printf("%3.3f\n", ans);
        System.out.println(ansStr);
        simplex();
    }
}
