package Streams;


public class Regular extends Stream {

    public Regular(double lyamda) {
        super(lyamda);
    }

    @Override
    public double next() {
        return lyamda;
    }
}
