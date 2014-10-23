import java.util.Random;
/**
 * Created by Ruslan on 17.09.2014.
 *
 * class wich ganerate random stream
 */
public class Diffusion_Nonmonotonic {
    private double v,t, max = 1e10, min = 0, maxS;
    private Random random;

    public Diffusion_Nonmonotonic(){
        this(1,1);
    }

    public Diffusion_Nonmonotonic(double v, double t){
        this.v = v;
        this.t = t;
        findMax();
        findMin();
        maxS = Simpson(min, max);
        random = new Random();
    }

    private double f(double x) {
        return Math.sqrt(t) / (v * x * Math.sqrt(2 * Math.PI * x)) * Math.exp(-Math.pow(x-t, 2) / (2 * v * v * t * x));
    }

    private void findMin(){
        double l = 0, r = 10, z, f;
        while (r-l>1e-10){
            z = (r + l) / 2;
            f = f(z);
            if (!Double.isFinite(f))
                l = z;
            else
                r = z;
        }
        min = r;
    }

    private void findMax(){
        double l = 0, r = 1e10, z, f;
        while (r-l>1e-6){
            z = (r + l) / 2;
            f = f(z);
            if (Math.abs(f)<1e-6)
                r = z;
            else
                l = z;
        }
        max = l;
    }

    private double Simpson(double l, double r){
        double ans = 0;
        double n = 100;
        while (n<1e6){
            double buf = 0;
            double h = (r - l)/n;
            for (int i = 0; i < n; i++){
                buf+= f(l + i * h) + 4 * f(l + i * h + h / 2) + f(l + (i + 1) * h);
            }
            buf*=h/6;
            if (ans != 0)
                if (Math.abs(ans - buf)<1e-5) break;
                ans = buf;
            n*=2;
        }
//        System.out.println(n);
        return ans;
    }


    public double nextTi(){
        double ri = random.nextDouble();
        double l = min, r = max, z, s;
        while (r - l > 1e-6){
            z = (r + l) / 2;
            s = Simpson(min, z)/maxS;
            if (s > ri) r = z;
             else
                l = z;
        }
        return r;
    }

}
