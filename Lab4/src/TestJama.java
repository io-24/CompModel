import Jama.Matrix;

/**
 * Created by byte on 21.11.14.
 */
public class TestJama {

    public static void main(String[] args) {
        double[][] mat = new double[][]{{1,2},
                                        {2,1}};
        Matrix matrix = new Matrix(mat);
        System.out.println(matrix.det());


    }
}
