package model.interfaces;

import statustable.StatusTable;
import java.util.Collection;

/**
 * Created by byte on 23.10.14.
 *
 */
public interface Device {

    Device putTask();
    boolean isEmty();
    Device addDevice(Device device, double d);
    Device processingTask();
    int getNumberofResolvedTasks();
    String getDeviseName();
    double getEfficiency();

}
