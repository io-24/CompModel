import model.interfaces.Device;
import model.simpledevice.CP;
import model.simpledevice.SimpleDevice;
import statustable.StatusTable;

/**
 * Created by byte on 23.10.14.
 *
 */
public class Main {
    public static void main(String[] args) {
        Device processor = new CP("CP", 10, 1.5);
        Device processor2 =  new SimpleDevice("CP2", 0, 1);

        processor.addDevice(processor, 0.85)
                .addDevice(processor2, 0.15);

        processor2.addDevice(processor, 0.2)
                .addDevice(processor2, 0.8);

        StatusTable table  = StatusTable.getStatusTable();

        for (int i = 0; i < 100000; i++) {
            table.next();
        }

        System.out.println(processor.toString());

        System.out.println();

        System.out.println(processor2.toString());
    }
}
