package device;

/**
 * Created by byte on 20.11.14.
 */
public class IDeviceException extends Exception {
    String message;

    IDeviceException(String message) {
        this.message = message;
    }

    public String toString() {
        return "IDevice error: " + message;
    }
}
