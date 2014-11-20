package init;

import device.IDevice;
import device.IDeviceException;

/**
 * Created by byte on 20.11.14.
 */
public class Point {
    private final IDevice hDevice, chDevice;
    private final double q;

    Point(IDevice hDevice, IDevice chDevice, double q) {
        this.chDevice = chDevice;
        this.q = q;
        this.hDevice = hDevice;
    }

    public String toString(){
        return hDevice.getName() + " -> " + chDevice.getName() +" : " + String.format("%2.2f", q);
    }

    public boolean canSendTask() {
        return  (hDevice.getPr_task() > 0);
    }

    public boolean sendTask() {
        try {
            if (!hDevice.sentTask()) return false;
        } catch (IDeviceException e) {
            e.printStackTrace();
        }
        chDevice.receiveTask();
        return true;
    }

    public void taskBack() {
        hDevice.sentBackTask();
        try {
            chDevice.receiveBackTask();
        } catch (IDeviceException e) {
            e.printStackTrace();
        }
    }

    public IDevice gethDevice() {
        return hDevice;
    }

    public IDevice getchDevice() {
        return chDevice;
    }

    public double getQmultLyamda() {
        return q * hDevice.getLyamda();
    }

}
