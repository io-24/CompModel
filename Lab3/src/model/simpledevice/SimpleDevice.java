package model.simpledevice;

import model.ChildrenDevice;
import model.interfaces.Device;
import statustable.StatusTable;

import java.util.Collection;

import static java.lang.Math.log;
import static java.lang.Math.random;

/**
 * Created by byte on 23.10.14.
 */
public class SimpleDevice implements Device {

    private int tasks, count_processing_task, count_processor;
    private double[] active_time, processing;
    private ChildrenDevice childrenDevice;
    private StatusTable statusTable;
    private double processing_time;
    private String device_name;

    public SimpleDevice(String device_name, double processing_time) {
        this(device_name, 0, processing_time);
    }

    protected SimpleDevice(String device_name, int tasks, double processing_time) {
        this(device_name, tasks, processing_time, 1);
    }

    protected SimpleDevice(String device_name, int tasks, double processing_time, int count_processor) {
        this.device_name = device_name;
        this.tasks = tasks;
        this.count_processor = count_processor;
        processing = new double[count_processor];
        for (int i = 0; i < processing.length; i++) {
            processing[i] = -1;
        }
        childrenDevice = new ChildrenDevice();
        statusTable = StatusTable.getStatusTable();
        this.processing_time = processing_time;
        count_processing_task = 0;
        active_time = new double[count_processor];

    }


    @Override
    public Device putTask() {
        tasks++;
        if (deviceReady()) addProcessingTask();
        return this;
    }

    @Override
    public boolean deviceReady() {
        for (double fl : processing) {
            if (fl < 0) return true;
        }
        return false;
    }


    private int getReadyDeviceNumber() {
        for (int i = 0; i < processing.length; i++) {
            if (processing[i] < 0) return i;
        }
        return -1;
    }



    @Override
    public Device addDevice(Device device, double q) {
        childrenDevice.addDevice(device, q);
        return this;
    }


    @Override
    public Device processingTask(int number_processor) {
        if (number_processor < 0 || number_processor >= count_processor) return this;
        if (processing[number_processor] >= 0) {
            count_processing_task++;
            active_time[number_processor] += processing[number_processor];
            processing[number_processor] = -1;
            childrenDevice.sendTask();
        }
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
    public double getEfficiency(int number_processor) {
        return active_time[number_processor] / statusTable.getCurrentTime();
    }


    private boolean fl = false;

    @Override
    public void makeTree(String tab, String... param) {
        System.out.print(tab + "<device name = \"" + getDeviseName() + "\" ");
        for (int i = 0; i < param.length; i++) {
            System.out.print(param[i] + " ");
        }
        System.out.println(">");
        if (!fl) childrenDevice.makeTree(tab + "\t");
        System.out.println(tab + "</device>");
        fl = true;
    }

    @Override
    public Collection<DeviceAndProbability> getChildrenDevice() {
        return childrenDevice.getAllDevices();
    }

    @Override
    public String getProcessingParams() {
        return "countProcessor=\""
                + count_processor
                + "\" processingTime=\""
                + processing_time + "\" "
                + "task=\"" +
                tasks +"\" " +
                "pr_task=\"" +
                (getProcessorCount() - getReadyDeviceCount()) +"\"" ;

    }

    @Override
    public void getAllDevices(Collection<Device> list) {
        if (!list.contains(this)) {
            list.add(this);
            childrenDevice.getAllDevices(list);
        }
    }


    private boolean addProcessingTask() {
        if (!deviceReady() || tasks == 0) return false;
        int k;
        while (tasks > 0 && (k = getReadyDeviceNumber()) >= 0) {
            double next = next();
            statusTable.addStatus(this, next, k);
            tasks--;
            processing[k] = next;
        }
        return true;
    }

    public String toString() {
        StringBuffer ans;
        if (count_processor > 1) {
            ans = new StringBuffer(String.format("Device name: %s\n" +
                    "Count of processor: %d\n"
                    , device_name, count_processor));
            for (int i = 0; i < count_processor; i++) {
                ans.append(String.format("      Processor %d \n" +
                        "          Count of processing task: %d\n" +
                        "          Efficiency: %1.2f\n"
                        , i, count_processing_task, getEfficiency(i)));
            }
        } else {
            ans = new StringBuffer(String.format("Device name: %s\n" +
                    "          Count of processing task: %d\n" +
                    "          Efficiency: %1.2f\n"
                    , device_name, count_processing_task, getEfficiency(0)));
        }


        sum += count_processing_task;

        return ans.toString();
    }

    public static int sum = 0;

    private double next() {
        return -processing_time * log(random());
    }


    public String getParam(){
        StringBuilder ans = new StringBuilder();

        ans.append("Device name: ").append(device_name).append("\n");
        ans.append("\t count processor: ").append(count_processor).append("\n");
        ans.append("\t task: ").append(tasks).append("\n");
        ans.append("\t ready processor: ").append(getReadyDeviceCount()).append("\n");
        return ans.toString();
    }

    @Override
    public int getTask() {
        return tasks;
    }

    @Override
    public int getProcessorCount() {
        return count_processor;
    }

    public int getReadyDeviceCount() {
        int ans = 0;
        for (int i = 0; i < processing.length; i++) {
            if (processing[i] < 0) ans++;
        }
        return ans;
    }

    @Override
    public void clear() {
        tasks = 0;
        for (int i = 0; i < processing.length; i++) {
            processing[i] = -1;
            active_time[i] = 0;
        }
        processing_time = 0;


    }


}