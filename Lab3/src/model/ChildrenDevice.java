package model;

import model.interfaces.Device;

import java.util.ArrayList;
import java.util.Collection;

import static java.lang.Math.*;

/**
 * Created by byte on 23.10.14.
 *
 */
public class ChildrenDevice {

    private ArrayList<DeviceandProbability> devices;
    private boolean test;

    public ChildrenDevice(){
        devices = new ArrayList<DeviceandProbability>();
        test = false;
    }

    public ChildrenDevice addDevice(Device device, double q){
        devices.add(new DeviceandProbability(device, q));
        test = false;
        return this;
    }

    public ChildrenDevice addDevice(Device device){
        devices.add(new DeviceandProbability(device));
        test = false;
        return this;
    }

    public boolean test(){
        if (!test){
            test = true;
            double sum = 0;
            for (DeviceandProbability device:devices){
                sum+=device.q;
            }
            if (abs(sum - 1)<1e-1){
                return true;
            }
            test = false;
        }
        return test;
    }


    public Device sendTask(){
        double r = random(),
        sum = 0;
        Device device = null;

        for (DeviceandProbability deviceandProbability:devices){
            device = deviceandProbability.device;
            if (sum + deviceandProbability.q >= r) break;
            sum += deviceandProbability.q;
        }
        if (device != null)
        device.putTask();

        return device;
    }

    boolean fl = false;
    public void makeTree(String device_name) {
        if (!fl){
            fl = true;
            for(DeviceandProbability device:devices){
                device.makeTree(device_name);
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


    private class DeviceandProbability{
        Device device;
        double q;

        DeviceandProbability(Device device){
            this(device,1);
        }

        DeviceandProbability(Device device, double q){
            this.device = device;
            this.q = q;
        }

        public void makeTree(String device_name) {
            System.out.println("<" + device.getDeviseName() + " in=" + String.format("%2.2f", 0.0) + "out=" + String.format(", %2.2f", q) +">");
            device.makeTree();
            System.out.println("</" + device.getDeviseName() + ">");
        }
    }

}

