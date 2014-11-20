package model.simpledevice;

import model.interfaces.Device;

public class DeviceAndProbability {
        private Device device;
        public double q;

        public DeviceAndProbability(Device device) {
            this(device, 1);
        }

        public DeviceAndProbability(Device device, double q) {
            this.device = device;
            this.q = q;
        }

    public Device getDevice() {
        return device;
    }
}