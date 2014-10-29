package model.simpledevice;

/**
 * Created by byte on 23.10.14.
 *
 */
public class CP extends SimpleDevice {

    public CP(String device_name, int tasks, double processing_time) {
        this(device_name, tasks, processing_time, 1);
    }

    /**
     *
     * @param device_name - DEVICE NAME
     * @param tasks - COUNT OF TASKS
     * @param processing_time - TIME OF PROCESSING
     * @param count_processor - NUMBER OF PROCESSOR
     */
    public CP(String device_name, int tasks, double processing_time, int count_processor) {
        super(device_name, tasks, processing_time, count_processor);
        processingTask(0);
    }
}
