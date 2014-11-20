package device;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by byte on 20.11.14.
 */


public class SomeDevice extends IDevice {

    public static String pattern = String.format("(?ix)<device\\s name\\s*=\\s*\"([\\w\\s]+)\" " +
            "\\s+(out\\s*=\"(\\d+\\.\\d+)\")?\\s*" +
            "countProcessor\\s*=\\s*\"(\\d+)\"\\s+" +
            "processingTime\\s*=\\s*\"(\\d+\\.\\d+)\"\\s*" +
            "task\\s*=\\s*\"(\\d+)\"\\s*" +
            "pr_task\\s*=\\s*\"(\\d+)\"\\s*>");

    private SomeDevice(String name, int pr_count, int task, double pr_time, int pr_task) {
        super(name, pr_count, task, pr_time, pr_task);
    }

    @Override
    public boolean sentTask() throws IDeviceException {
        if (task > 0 && pr_task != pr_count) throw new IDeviceException("sentTask in " + name);
        if (task > 0) {
            task--;
            return true;
        }
        if (pr_task > 0) {
            pr_task--;
            return true;
        }
        return false;
    }

    @Override
    public void receiveTask() {
        if (pr_task < pr_count) pr_task++;
        else task++;
    }

    @Override
    public void sentBackTask() {
        if (pr_task == pr_count) task++;
        else pr_task++;
    }

    @Override
    public void receiveBackTask() throws IDeviceException {
        if (task == 0) pr_task--;
        else task--;
        if (pr_task < 0 || task < 0) throw new IDeviceException("receiveBackTask in " + name);
    }

    @Override
    public void setProperties(int pr_count, int pr_task) {
        this.pr_count = pr_count;
        this.pr_task = pr_task;
    }

    public static IDevice getDevice(String s) throws IDeviceException {
        Matcher m = Pattern.compile(pattern).matcher(s);
        if (m.find()) {
            String name = m.group(1);
            int pr_count = Integer.parseInt(m.group(4));
            double pr_time = Double.parseDouble(m.group(5));
            int task = Integer.parseInt(m.group(6));
            buf = (m.group(3) != null) ? Double.parseDouble(m.group(3)) : -1;
            int pr_task = Integer.parseInt(m.group(7));
            return getDevice(name, pr_count, task, pr_time, pr_task);
        }
        throw new IDeviceException("parse error");
    }

    static protected IDevice getDevice(String name, int pr_count, int task, double pr_time, int pr_task) {
        for (IDevice device : allIDevices) {
            if (device.name.equals(name)) return device;
        }

        IDevice device = new SomeDevice(name, pr_count, task, pr_time, pr_task);
        allIDevices.add(device);
        return device;
    }

    public void add(IDevice device, double probability) {
        childDevice.put(device, probability);
    }

    public static void main(String[] args) {
        try {
            IDevice device = getDevice("<device name = \"Video Processor\" out=\"0.5\" countProcessor=\"1\" processingTime=\"7.5\" task=\"0\" >");
            System.out.println(device.getBuf());
        } catch (IDeviceException iDeviceEception) {
            iDeviceEception.printStackTrace();
        }
    }


}

