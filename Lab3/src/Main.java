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

/*CP
	 pr:1 0.277542536751192
	 pr:2 0.1448037101733879
	 pr:3 0.08307535939598676
North bridge
	 pr:1 0.84496124954276
RAM
	 pr:1 0.367374456323732
South bridge
	 pr:1 0.27660653431973964
Video Processor
	 pr:1 0.1383032671616407
ISA
	 pr:1 0.07683514842310357
LPT
	 pr:1 0.007683514839559015
COM
	 pr:1 0.007683514839559015
USB
	 pr:1 0.007683514839559014 */
        Device CPU = new CP("CP", /*COUNT OF TASKS*/ 4,/*TIME OF PROCESSING*/ 1,/*COUNT OF PROCESSOR*/ 3);

        Device north_bridge = new SimpleDevice("North bridge", 2.3);

        CPU.addDevice(north_bridge, 0.4);
//        north_bridge.addDevice(CPU, 1);

        CPU.addDevice(CPU, 0.6);


        Device south_bridge = new SimpleDevice("South bridge", 7.5);

        Device RAM = new SimpleDevice("RAM", 2.5);

        Device video_Processor = new SimpleDevice("Video Processor", 7.5);

        Device ISA = new SimpleDevice("ISA", 375);

        Device LPT = new SimpleDevice("LPT", 375);

        Device COM = new SimpleDevice("COM", 375);

        Device USB = new SimpleDevice("USB", 375);


        north_bridge.addDevice(CPU, 0.5);



        RAM.addDevice(north_bridge, 1);
        north_bridge.addDevice(RAM, 0.4);


        north_bridge.addDevice(south_bridge, 0.1);
        south_bridge.addDevice(north_bridge, 0.495);

        south_bridge.addDevice(video_Processor, 0.5);
        video_Processor.addDevice(CPU, 1);

        south_bridge.addDevice(ISA, 0.005);
        ISA.addDevice(south_bridge, 0.7);

        ISA.addDevice(LPT, 0.1);
        LPT.addDevice(CPU, 1);

        COM.addDevice(ISA, 1);
        ISA.addDevice(COM, 0.1);

        USB.addDevice(CPU, 1);
        ISA.addDevice(USB, 0.1);



        StatusTable table  = StatusTable.getStatusTable();

        for (int i = 0; i < 1000000; i++) {
            table.next();
        }


        System.out.print(CPU);

        System.out.print(north_bridge);

        System.out.print(south_bridge);

        System.out.print(RAM);

        System.out.print(video_Processor);

        System.out.print(ISA);

        System.out.print(LPT);

        System.out.print(COM);

        System.out.print(USB);

        System.out.println("All tasks " + SimpleDevice.sum);
        System.out.println(table.getCurrentTime());

        /*System.out.println(CPU.getChildrenDevice().test());
        System.out.println(RAM.getChildrenDevice().test());
        System.out.println(south_bridge.getChildrenDevice().test());
        System.out.println(north_bridge.getChildrenDevice().test());
        System.out.println(video_Processor.getChildrenDevice().test());
        System.out.println(ISA.getChildrenDevice().test());
        System.out.println(COM.getChildrenDevice().test());
        System.out.println(USB.getChildrenDevice().test());
        System.out.println(LPT.getChildrenDevice().test());*/

        CPU.makeTree("", CPU.getProcessingParams());





    }
}
