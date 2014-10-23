import java.util.Random;

/**
 * Crea=ted by Ruslan on 08.10.2014.
 */
public class Main {
    public static void main(String[] args) {
        int N = 50000000;
        double k = 9, n = 15, p = 0;
        Random r = new Random();
        for (int i = 0; i<N; i++){
            int p1 = r.nextInt(8);
            int p2 = r.nextInt(8);
            if ((p1==0 && p2==6)||(p2==0 && p1==6)) p++;


        }
        System.out.println(p/N);
    }
}
