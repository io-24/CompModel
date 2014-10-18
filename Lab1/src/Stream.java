import javafx.beans.binding.DoubleExpression;

import javax.rmi.CORBA.Util;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by Ruslan on 16.09.2014.
 *
 */
public class Stream {

    private double v,t;

    public Stream(double v, double t){
        this.v = v;
        this.t = t;

    }

    public Stream(){
        this(0.9,2);
    }



    public void streamToFile(File file, int n){
        long timeStart = System.currentTimeMillis();
        int k = 2;
        ArrayList<A> listT = new ArrayList<A>();

        for (int i = 0; i < k; i++){
            Diffusion_Nonmonotonic df = new Diffusion_Nonmonotonic(v, t);
            A t = new A(df, n / k);
            t.start();
            listT.add(t);
        }
        ArrayList<Double> ans  = new ArrayList<Double>();
        for (A t:listT){
            try {
                t.join();
                ans.addAll(t.getList());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (double d:ans){
                writer.write(d+"\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        long timeEnd = System.currentTimeMillis()-timeStart;
        System.out.println(timeEnd/1000/60+" : "+timeEnd/1000%60);
    }

    public void streamToFile(String fileName, int n){
        File file = new File(fileName);
        streamToFile(file, n);
    }

    class A extends Thread{

        private Diffusion_Nonmonotonic df;
        private ArrayList<Double> list;
        private int n;
        private boolean f;
        public A(Diffusion_Nonmonotonic df, int n){
            super();
            this.df = df;
            list = new ArrayList<Double>(n);
            this.n = n;
            f = false;
        }

        @Override
        public void run() {
            for (int i = 0; i<n; i++){
                list.add(df.nextTi());
            }
            f  = true;
        }

        public boolean isReady(){
            return f;
        }

        public ArrayList<Double> getList(){
            return list;
        }
    }
}
