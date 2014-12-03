package test;

import var.Variable;

public class Test {

    private Variable[] x;

    public Test(Variable x1, Variable x2, Variable x3) {
        x = new Variable[]{x1, x2, x3};
    }

    public boolean test(){
        if (x[0].getValue() + x[2].getValue() >= 4) return false;
        if (x[2].getValue() >= x[1].getValue()) return false;
        if (x[0].getValue() + x[1].getValue() >= 8) return false;
        return true;
    }

    public double getFuncRez(){
        return x[0].getValue() + 2 * x[1].getValue() + 5 * x[2].getValue();
    }

    @Override
    public String toString() {
        return String.format("%2.2f %2.2f %2.2f",x[0].getValue(), x[1].getValue(),x[2].getValue());
    }


    public boolean next(){
        do {
            int i = 0;
            while (!x[i++].next()) {
                if (i == 3) return false;
            }
        } while (!test());
        return true;
    }


}
