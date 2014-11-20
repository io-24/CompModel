package init;

import device.IDevice;
import device.IDeviceException;
import device.SomeDevice;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by byte on 20.11.14.
 */

public class InitFactory {

    private BufferedReader bufferedReader;
    private ArrayList<Point> point_list;

    private String andTag = "</device>";

    private InitFactory(String file) throws IOException {
        bufferedReader = new BufferedReader(new FileReader(file));
        point_list = new ArrayList<Point>();
        while (bufferedReader.ready()) {
            parseFile(null);
        }
    }

    private void parseFile(IDevice device) throws IOException {
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine().trim();

            if (line.equals(andTag)) return;
            try {
                IDevice chDevice = SomeDevice.getDevice(line);
                if (device != null && chDevice != null && SomeDevice.getBuf() != -1) {
                    Point point = new Point(device, chDevice, SomeDevice.getBuf());
                    point_list.add(point);
                }
                parseFile(chDevice);
            } catch (IDeviceException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Point> getPoint_list(String file) throws IOException {
        InitFactory factory = new InitFactory(file);
        return factory.point_list;
    }

    public static void main(String[] args) throws IOException {
        ArrayList<Point> points = getPoint_list("init.xml");
        for (Point point : points) {
            System.out.println(point);
        }
    }

}


