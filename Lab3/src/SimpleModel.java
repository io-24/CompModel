import model.interfaces.Device;
import model.simpledevice.CP;

/**
 * Created by byte on 20.11.14.
 */
public class SimpleModel {
    public static void main(String[] args) {
        Device d1 = new CP("dev1", 2, 1);
        Device d2 = new CP("dev2", 1, 1);
        d1.addDevice(d2, 1);
        d2.addDevice(d1, 0.5);
        d2.addDevice(d2, 0.5);
        d1.makeTree("", d1.getProcessingParams());
    }
}
