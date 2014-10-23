package model.simpledevice;

/**
 * Created by byte on 23.10.14.
 *
 */
public class CP extends SimpleDevice {

    public CP(String device_name, int tasks, double processing_time) {
        super(device_name, tasks, processing_time);
        processingTask();
    }
}
