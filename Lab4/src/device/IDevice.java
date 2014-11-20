package device;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by byte on 20.11.14.
 */
public abstract class IDevice {
    protected String name;
    protected int pr_count, task, pr_task;
    protected double pr_time;
    protected HashMap<IDevice, Double> childDevice;
    protected static ArrayList<IDevice> allIDevices = new ArrayList<IDevice>();
    protected static double buf;

    protected IDevice(String name, int pr_count, int task, double pr_time, int pr_task){
        this.name = name;
        this.pr_count = pr_count;
        this.task = task;
        childDevice = new HashMap<IDevice, Double>();
        this.pr_time = pr_time;
        this.pr_task = pr_task;
    }


    public static double getBuf() {
        return buf;
    }

    public String getName() {
            return name;
    }

    public static int getAllDeviseSize() {
        return allIDevices.size();
    }

    public static IDevice getDevice(int i) {
        return allIDevices.get(i);
    }

    public int getTask() {
        return task;
    }

    public int getPr_count() {
        return pr_count;
    }

    public int getPr_task() {
        return pr_task;
    }

    public abstract boolean sentTask() throws IDeviceException;

    public abstract void receiveTask();

    public abstract void sentBackTask();

    public abstract void receiveBackTask() throws IDeviceException;

    public static int getID(IDevice device) {
        return allIDevices.indexOf(device);
    }

    public double getLyamda() {
        return 1/pr_time;
    }

    public abstract void setProperties(int pr_count, int pr_task);
}
