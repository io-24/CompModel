package tree;

import Jama.Matrix;
import device.IDevice;
import init.Point;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import static device.IDevice.getID;
import static init.InitFactory.getPoint_list;
import static tree.Status.addStatus;
import static tree.Status.getCurrentStatusId;

/**
 * Created by byte on 20.11.14.
 */

public class Tree {
    ArrayList<Point> points;
    private LinkedList<Status> stack;
    private int currentId;


    public Tree(String file_name) throws IOException {
        points = getPoint_list(file_name);
        Status.addStatus();
        stack = new LinkedList<Status>();
        stack.add(Status.getCurrentStatus());
        currentId = 0;
        makeTree();
    }


    public static void main(String[] args) {
        try {
            Tree tree = new Tree("simpleS.xml");
            Status.showTable();
            Status.showGraph();
            double[][] m = Status.getMatrix();
            double[] p = getP(m);
            double[][] intes = Status.getInten(p);

            for (int i = 0; i < intes.length; i++) {
                System.out.println(IDevice.getDevice(i).getName());
                for (int j = 1; j < intes[i].length; j++) {
                    if (intes[i][j] != 0) System.out.println("\t pr:" + j + " " + intes[i][j]);
                }
            }


/*Device name: CP
Count of processor: 2
      Processor 0
          Count of processing task: 4243444
          Efficiency: 0.17
      Processor 1
          Count of processing task: 4243444
          Efficiency: 0.02

Device name: Noth Bridge
          Count of processing task: 3387143
          Efficiency: 0.33

Device name: South Bridge
          Count of processing task: 669463
          Efficiency: 0.21

Device name: COM
          Count of processing task: 3692
          Efficiency: 0.06

Device name: RAM
Count of processor: 2
      Processor 0
          Count of processing task: 1354308
          Efficiency: 0.13
      Processor 1
          Count of processing task: 1354308
          Efficiency: 0.01

Device name: md
          Count of processing task: 33543
          Efficiency: 0.54*/
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static double[] getP(double[][] m) throws Exception {
        double ans[] = new double[m.length];
        double[][] buf = new double[m.length][m.length];
        for (int i = 0; i < buf.length; i++) {
            System.arraycopy(m[i], 0, buf[i], 0, buf[i].length);
        }

//        System.out.println("------");
//
//        for (double[] aBuf : buf) {
//            for (int k = 0; k < buf.length; k++) {
//                System.out.print(aBuf[k] + " ");
//            }
//            System.out.println();
//        }

//        System.out.println("------");

        double det = (new Matrix(m)).det();
        if (det == 0) throw new Exception();
        for (int i = 0; i < ans.length; i++) {
            for (int j = 1; j < buf.length; j++) {
                buf[j][i] = 0;
            }
            buf[0][i] = 1;

            ans[i] = (new Matrix(buf)).det() / det ;

//            System.out.println("------");
//
//            for (double[] aBuf : buf) {
//                for (int k = 0; k < buf.length; k++) {
//                    System.out.print(aBuf[k] + " ");
//                }
//                System.out.println();
//            }
//
//            System.out.println("------");

            for (int j = 0; j < buf.length; j++) {
                buf[j][i] = m[j][i];
            }

        }

        return ans;
    }

    private void makeTree() {
        for (Point point : points) {
            int id = getCurrentStatusId();
            if (!point.sendTask()) continue;
            if (addStatus(getID(point.gethDevice()), getID(point.getchDevice()), point.getQmultLyamda(), id)){
                makeTree();
            }
            point.taskBack();
        }
    }

}