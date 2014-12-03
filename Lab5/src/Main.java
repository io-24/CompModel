import test.Test;
import var.Variable;

/**
 * Created by root on 03.12.14.
 */
public class Main {
    public static void main(String[] args) {
        Variable x1 = new Variable(0, 100);
        Variable x2 = new Variable(0, 100);
        Variable x3 = new Variable(0, 100);

        Test t = new Test(x1, x2, x3);
        double ans = Double.MIN_VALUE;
        String ansStr = null;
        while (t.next()){
            if (t.getFuncRez() > ans){
                ansStr = t.toString();
                ans = t.getFuncRez();
            }
        }

        System.out.printf("%3.3f\n", ans);
        System.out.println(ansStr);

    }
}
