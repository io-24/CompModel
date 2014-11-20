package model;

import model.interfaces.Device;
import model.simpledevice.DeviceAndProbability;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Math.abs;
import static java.lang.Math.random;

/**
 * Created by byte on 23.10.14.
 */
public class ChildrenDevice {

    private ArrayList<DeviceAndProbability> devices;
    private boolean test;

    public ChildrenDevice() {
        devices = new ArrayList<DeviceAndProbability>();
        test = false;
    }

    public ChildrenDevice addDevice(Device device, double q) {
        devices.add(new DeviceAndProbability(device, q));
        test = false;
        return this;
    }

    public ChildrenDevice addDevice(Device device) {
        devices.add(new DeviceAndProbability(device));
        test = false;
        return this;
    }

    public boolean test() {
        if (!test) {
            test = true;
            double sum = 0;
            for (DeviceAndProbability device : devices) {
                sum += device.q;
            }
            if (abs(sum - 1) < 1e-1) {
                return true;
            }
            test = false;
        }
        return test;
    }


    public Device sendTask() {
        double r = random(),
                sum = 0;
        Device device = null;

        for (DeviceAndProbability deviceAndProbability : devices) {
            device = deviceAndProbability.getDevice();
            if (sum + deviceAndProbability.q >= r) break;
            sum += deviceAndProbability.q;
        }
        if (device != null)
            device.putTask();

        return device;
    }

    boolean fl = false;

    public void makeTree(String tab) {
        if (!fl) {
            fl = true;
            for (DeviceAndProbability device : devices) {
                device.getDevice().makeTree(tab, "out=\"" + device.q + "\"", device.getDevice().getProcessingParams());

            }
//            fl = false;
        }

        /*@Override
        public void makeTree(double in, double out) {
            if (!fl) {
                fl = true;
                System.out.println("<" + device_name + " in=" + String.format("%2.2f", in) + "out=" + String.format(", %2.2f", out) +">");
                childrenDevice.makeTree();
                System.out.println("</" + device_name + ">");
                fl = false;
            }
        }*/
    }

    public void getAllDevices(Collection<Device> list) {
        for (DeviceAndProbability device : devices) {
            device.getDevice().getAllDevices(list);
        }
    }

    public Collection<DeviceAndProbability> getAllDevices() {
        return devices;
    }




}

