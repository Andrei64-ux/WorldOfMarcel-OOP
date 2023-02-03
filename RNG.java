package utils;

import java.util.Random;

public class RNG {
    public static Random rng = new Random();

    public static int getRandomInterval(int a , int b){
        return Math.abs(rng.nextInt()) % (b - a + 1) + a;
    }
}
