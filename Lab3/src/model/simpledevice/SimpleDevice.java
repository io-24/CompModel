package model.simpledevice;

import model.ChildrenDevice;
import model.interfaces.Device;
import statustable.StatusTable;
import static java.lang.Math.*;

/**
 * Created by byte on 23.10.14.
 *
 */
public class SimpleDevice implements Device{

    private int tasks, count_processing_task, active_time;
    boolean proccesing;
    private ChildrenDevice childrenDevice;
    private StatusTable statusTable;
    private double processing_time;
    private String device_name;

    public SimpleDevice(String device_name, int tasks, double processing_time){
        this.device_name = device_name;
        this.tasks = tasks;
        proccesing = false;
        childrenDevice = new ChildrenDevice();
        statusTable = StatusTable.getStatusTable();
        this.processing_time = processing_time;
        count_processing_task = 0;
        active_time = 0;
    }

    @Override
    public Device putTask() {
        tasks++;
        if (!proccesing) addProcessingTask();
        return this;
    }

    @Override
    public boolean isEmty() {
        return !proccesing;
    }

    @Override
    public Device addDevice(Device device, double q) {
        childrenDevice.addDevice(device, q);
        return this;
    }


    @Override
    public Device processingTask() {
        if (proccesing) count_processing_task++;
        proccesing = false;
        childrenDevice.sendTask();
        addProcessingTask();
        return this;
    }

    @Override
    public int getNumberofResolvedTasks() {
        return count_processing_task;
    }

    @Override
    public String getDeviseName() {
        return device_name;
    }

    @Override
    public double getEfficiency() {
        return active_time / statusTable.getCurrentTime();
    }


    private boolean addProcessingTask(){
        if (proccesing) return false;
        if (tasks > 0){
            double next = next();
            active_time += next;
            statusTable.addStatus(this, next);
            tasks --;
            proccesing = true;
            return true;
        }
        return false;
    }

    public String toString(){
        return String.format("Device name: %s\n" +
                "Count of processing task: %d\n" +
                "Efficiency: %1.2f", device_name, count_processing_task, getEfficiency());
    }

    private double next() {
        return -1/processing_time*log(random());
    }
}