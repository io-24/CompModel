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
        Device CPU = new CP("CP", /*COUNT OF TASKS*/ 4,/*TIME OF PROCESSING*/ 1,/*COUNT OF PROCESSOR*/ 1);

        Device north_bridge = new SimpleDevice("Norh bridge", 2.3);

        Device south_bridge = new SimpleDevice("South bridge", 7.5);

        Device GPU = new SimpleDevice("GPU", 5);

        Device RAM = new SimpleDevice("RAM", 2.5);

        Device video_Processor = new SimpleDevice("Video Processor", 7.5);

        Device ISA = new SimpleDevice("ISA", 375);

        Device LPT = new SimpleDevice("LPT", 375);

        Device COM = new SimpleDevice("COM", 375);

        CPU.addDevice(north_bridge, 0.3);
        north_bridge.addDevice(CPU, 0.4);

        CPU.addDevice(CPU, 0.7);

        RAM.addDevice(north_bridge, 1);
        north_bridge.addDevice(RAM, 0.25);

        GPU.addDevice(north_bridge, 1);
        north_bridge.addDevice(GPU, 0.1);

        north_bridge.addDevice(south_bridge, 0.25);
        south_bridge.addDevice(north_bridge, 0.58);

        south_bridge.addDevice(video_Processor, 0.4);
        video_Processor.addDevice(south_bridge, 1);

        south_bridge.addDevice(ISA, 0.02);
        ISA.addDevice(south_bridge, 0.7);

        ISA.addDevice(LPT, 0.15);
        LPT.addDevice(ISA, 1);

        COM.addDevice(ISA, 1);
        ISA.addDevice(COM, 0.15);




        StatusTable table  = StatusTable.getStatusTable();

        for (int i = 0; i < 20000; i++) {
            table.next();
        }

        System.out.println(CPU.toString());

        System.out.println(north_bridge.toString());

        System.out.println(south_bridge.toString());

        System.out.println(GPU);

        System.out.println(RAM);

        System.out.println(video_Processor);

        System.out.println(ISA);

        System.out.println(LPT);

        System.out.println(COM);


        System.out.println();

        CPU.makeTree();





    }
}
