package device;

/**
 * Created by byte on 20.11.14.
 */
public class DeviceEcseption extends Exception{
    String message;
    public DeviceEcseption(String message){
        this.message = message;
    }

    public String toString(){
        return "error in SomeDevice method: " + message;
    }
}
