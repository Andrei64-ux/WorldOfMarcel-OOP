package utils;

import java.util.concurrent.TimeUnit;

public class TheBeginningOfTheGame {
    public void tutorial(){
        try{
            for(int i = 0; i < 1; i++){
                System.out.println("* Loading. *");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("* Loading.. *");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("* Loading... *");
                TimeUnit.SECONDS.sleep(2);
            }

            System.out.println("* Welcome to the game World Of Marcel! *");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("* The rules are very simple! *");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("* You have an unvisited map and as a starting point you will have a cell marked with P. *");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("* But that's not all. *");
            TimeUnit.SECONDS.sleep(1);
            System.out.println("* Under each cell you can discover either stores (S), " +
                    "or enemies (E), or coins or you can't find anything (.) .*");
            TimeUnit.SECONDS.sleep(3);
            System.out.println("* If the cell is a store, you can select which potion to buy, of course if you have coins. *");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("* If the cell is an enemy, you can choose which method to attack. *");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("* If the cell is empty, continue on your way. *");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("* The game stops when you reach FINISH (F). *");
            TimeUnit.SECONDS.sleep(2);
            System.out.println("* We wish you success and the best to win. *");
            TimeUnit.SECONDS.sleep(5);

            for(int i = 0; i < 1; i++){
                System.out.println("* Loading. *");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("* Loading.. *");
                TimeUnit.SECONDS.sleep(2);
                System.out.println("* Loading... *");
                TimeUnit.SECONDS.sleep(2);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
