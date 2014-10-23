package Streams;

public class Pusasson_Stream extends Stream {

    public Pusasson_Stream(double lyamda){
        super(lyamda);
    }

    @Override
    public double next(){
        return -1/lyamda*Math.log(random.nextDouble());
    }

}
