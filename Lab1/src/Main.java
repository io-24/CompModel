public class Main {
    public static void main(String[] args) {
        Stream stream =  new Stream(0.9, 2);
        stream.streamToFile("output.txt",2000);
    }
}