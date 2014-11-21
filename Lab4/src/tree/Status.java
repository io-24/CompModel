package tree;

import device.IDevice;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by byte on 20.11.14.
 */
public class Status {
    private final String[] name;
    private final int[] task;
    private final int[] pr_task;
    private final int[] pr_count;
    private static ArrayList<Status> statusList = new ArrayList<Status>();
    private static int buf;
    private static ArrayList<MyMap> map = new ArrayList<MyMap>();

    private Status() {
        int m = IDevice.getAllDeviseSize();
        task = new int[m];
        pr_count = new int[m];
        pr_task = new int[m];
        name = new String[m];
        for (int i = 0; i < m; i++) {
            IDevice buf = IDevice.getDevice(i);
            task[i] = buf.getTask();
            pr_count[i] = buf.getPr_count();
            pr_task[i] = buf.getPr_task();
            name[i] = buf.getName();
        }
    }

    public static boolean addStatus() {
        Status status = new Status();
        if (statusList.contains(status)) {
            return false;
        }
        statusList.add(status);
        return true;
    }

    @Override
    public boolean equals(Object status) {
        if (!(status instanceof Status)) return false;
        for (int i = 0; i < task.length; i++) {
            if (!(task[i] == ((Status) status).task[i] &&
                    pr_task[i] == ((Status) status).pr_task[i] &&
                    pr_count[i] == ((Status) status).pr_count[i])) return false;
        }
        return true;
    }

    public static boolean addStatus(int hId, int chId, double q, int hStatusId) {
        boolean ans = addStatus();
        map.add(new MyMap(hId, chId, q, hStatusId, getCurrentStatusId()));
        return ans;
    }

    public static int getCurrentStatusId() {
        return getStatusId();
    }

    private static int getStatusId() {
        Status status = new Status();
        int id = statusList.indexOf(status);
        if (id == -1) {
            statusList.add(status);
            id = statusList.indexOf(status);
        }
        return id;
    }

    public String toString() {
        StringBuilder ans = new StringBuilder("<");
        for (int i = 0; i < task.length; i++) {
            ans.append("<").
                    append(task[i]).append(", ").
                    append(pr_task[i]).append(", ").
                    append((pr_count[i] - pr_task[i])).
                    append("> ");
        }
        ans.append(">");
        return ans.toString();
    }

    public static void showTable() {
        StringBuilder head = new StringBuilder();
        for (int i = 0; i < Status.statusList.get(0).name.length; i++) {
            head.append(String.format("%9s ", Status.statusList.get(0).name[i]));
        }
        System.out.println(head);
        for (Status status : statusList) {
            System.out.println(status);
        }
    }

    public static void showGraph() {
        Collections.sort(map);
        for (MyMap myMap : map) {
            System.out.println(myMap);
        }
    }

    public static Status getCurrentStatus() {
        return statusList.get(getCurrentStatusId());
    }

    public static void setNow(Status status) {
        for (int i = 0; i < IDevice.getAllDeviseSize(); i++) {
            IDevice device = IDevice.getDevice(i);
            device.setProperties(status.pr_count[i], status.pr_task[i]);
        }
    }

    public static double[][] getMatrix() {
        double[][] ans = new double[statusList.size()][statusList.size()];
        for (int i = 0; i < map.size(); i++) {
            MyMap buf = map.get(i);
            ans[buf.getHStatusId()][buf.getHStatusId()] -= buf.getQ();
            ans[buf.getChStatusId()][buf.getHStatusId()] += buf.getQ();
        }

        for (int i = 0; i < ans.length; i++) {
            ans[0][i] = 1;
        }
        return ans;
    }

    public static double[][] getInten(double[] p) {
        double[][] ans = new double[IDevice.getAllDeviseSize()][4];
        for (int i = 0; i < statusList.size(); i++) {
            Status buf = statusList.get(i);
            for (int j = 0; j < buf.pr_task.length; j++) {
                ans[j][buf.pr_task[j]] +=p[i];
            }
        }
        return ans;
    }
}

class MyMap implements Comparable<MyMap> {
    private int hDevice;
    private int chDevice;
    private double q;
    private int hStatus;
    private int chStatus;

    public MyMap(int hDevice, int chDevice, double q, int hStatus, int chStatus) {
        this.hDevice = hDevice;
        this.chDevice = chDevice;
        this.q = q;
        this.hStatus = hStatus;
        this.chStatus = chStatus;
    }

    @Override
    public String toString() {
        return hDevice + " -> " + chDevice + " : " + String.format("%2.3f", q) + "; " +
                hStatus + " -> " + chStatus + ";";
    }

    @Override
    public int compareTo(MyMap o) {
        if (hDevice < o.hDevice) return -1;
        if (hDevice > o.hDevice) return 1;
        if (chDevice < o.chDevice) return -1;
        if (chDevice > o.chDevice) return 1;
        return 0;
    }

    public int getHStatusId() {
        return hStatus;
    }

    public int getChStatusId() {
        return chStatus;
    }

    public double getQ() {
        return q;
    }
}