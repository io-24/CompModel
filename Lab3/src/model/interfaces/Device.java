package model.interfaces;

import model.simpledevice.DeviceAndProbability;

import java.util.Collection;

/**
 * Created by byte on 23.10.14.
 */
public interface Device {

    Device putTask();

    boolean deviceReady();

    Device addDevice(Device device, double d);

    Device processingTask(int number_processor);

    int getNumberofResolvedTasks();

    String getDeviseName();

    double getEfficiency(int number_processor);

    void makeTree(String tab, String... param);

    Collection<DeviceAndProbability> getChildrenDevice();

    String getProcessingParams();

    void getAllDevices(Collection<Device> list);

    String toString();

    String getParam();

    int getTask();

    int getProcessorCount();

    int getReadyDeviceCount();


    void clear();
}
