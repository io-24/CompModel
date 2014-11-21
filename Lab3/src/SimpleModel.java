import model.interfaces.Device;
import model.simpledevice.CP;
import model.simpledevice.SimpleDevice;
import statustable.StatusTable;

/**
 * Created by byte on 20.11.14.
 */
public class SimpleModel {
    public static void main(String[] args) {
        Device cpu = new CP("CP", 3, 1, 2);
        Device ram = new CP("RAM", 0, 2.5, 2);

        Device nothBridge = new SimpleDevice("Noth Bridge", 2.3);
        Device south = new SimpleDevice("South Bridge", 7.5);

        Device isa = new SimpleDevice("ISA", 375);
        Device au = new SimpleDevice("AU", 7.5);
        Device lpt = new SimpleDevice("LPT", 375);
        Device com = new SimpleDevice("COM", 375);
        Device md = new SimpleDevice("md", 375);


        cpu.addDevice(cpu, 0.6);
        cpu.addDevice(nothBridge, 0.6);

        nothBridge.addDevice(cpu, 0.5);
        nothBridge.addDevice(ram, 0.4);
        nothBridge.addDevice(south, 0.1);

        ram.addDevice(nothBridge, 1);

        south.addDevice(nothBridge, 0.5);
        south.addDevice(au, 0.4);
        south.addDevice(isa, 0.05);
        south.addDevice(md, 0.05);

        md.addDevice(south, 1);

        au.addDevice(south, 1);

        isa.addDevice(south, 0.8);
        isa.addDevice(com, 0.1);
        isa.addDevice(lpt, 0.1);

        com.addDevice(isa, 1);

        lpt.addDevice(cpu, 1);

        StatusTable table = StatusTable.getStatusTable();

        for (int i = 0;  i++ < 10000000;) {
            table.next();
        }

        System.out.println(cpu);

        System.out.println(nothBridge);

        System.out.println(south);

        System.out.println(com);

        System.out.println(ram);

        System.out.println(md);

        cpu.makeTree("", cpu.getProcessingParams());

        /*Device dev1 = new CP("dev1", 2, 1, 1);
        Device dev2 = new CP("dev2", 1, 1, 1);

        dev1.addDevice(dev2, 1);

        dev2.addDevice(dev1, 0.5);
        dev2.addDevice(dev2, 0.5);

        StatusTable table = StatusTable.getStatusTable();

        for (int i = 0; i < 1000000; i++) {
            table.next();
        }

        System.out.println(dev1);

        System.out.println(dev2);*/

        /*CP
        pr:1 0.10991328049789909
        pr:2 0.011439598504502303
        Noth Bridge
        pr:1 0.33419944177898103
        RAM
        pr:1 0.1289032541490343
        pr:2 0.016400850972261627
        South Bridge
        pr:1 0.21556103507005414
        AU
        pr:1 0.10562490718432643
        ISA
        pr:1 0.5987806529723659
        COM
        pr:1 0.05987806529723616
        LPT
        pr:1 0.05987806529723653
        md
        pr:1 0.5389025876751281*/




    }
}
