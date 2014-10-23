package Streams;

import java.util.Random;

public abstract class Stream {
    double lyamda;
    Random random;

    Stream(double lyamda){
        this.lyamda = lyamda;
        random = new Random();
    }

    public abstract double next();
}
