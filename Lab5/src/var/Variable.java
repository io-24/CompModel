package var;

public class Variable {

    private static final double h = 1e-1;
    protected double minVal;
    protected double maxVal;
    protected double currentVal;

    public Variable(double minVal, double maxVal) {
        this.minVal= minVal;
        this.maxVal = maxVal;
        currentVal = minVal;
    }

    public  double getValue(){
        return currentVal;
    }

    public boolean next(){
        currentVal += h;
        if (currentVal > maxVal) {
            currentVal = minVal;
            return false;
        }
        return true;
    }

}
