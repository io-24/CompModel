package tree;

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
        while (stack.size() > currentId){
            makeTree();
        }


    }


    public static void main(String[] args) {
        try {
            Tree tree = new Tree("simple.xml");
            Status.showTable();
            Status.showGraph();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void makeTree() {
        Status.setNow(stack.get(currentId++));
        for (Point point : points) {
            int id = getCurrentStatusId();
            if (!point.sendTask()) continue;
            if (addStatus(getID(point.gethDevice()), getID(point.getchDevice()), point.getQmultLyamda(), id)){
                Status status = Status.getCurrentStatus();
                if (!stack.contains(status)) stack.add(status);
            }
            point.taskBack();
        }
    }



}
