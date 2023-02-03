package shop;

import cell.CellElement;
import entities.Character;
import exceptions.InvalidCommandException;
import potion.ChakraPotion;
import potion.HealthPotion;
import potion.Potion;
import utils.RNG;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Shop implements CellElement {
    public static int MIN_COST = 5;
    public static int MAX_COST = 15;
    public static int MIN_WEIGHT = 5;
    public static int MAX_WEIGHT = 10;
    public static int MIN_REGEN = 5;
    public static int MAX_REGEN = 15;
    public ArrayList<Potion> potions;

    private static ArrayList<Potion> buyPotions(){
        ArrayList<Potion> potions = new ArrayList<>();
        int number = RNG.getRandomInterval(2,4);
        for(int i = 1; i <= number; i++){
            int cost = RNG.getRandomInterval(MIN_COST , MAX_COST);
            int weight = RNG.getRandomInterval(MIN_WEIGHT , MAX_WEIGHT);
            int regen = RNG.getRandomInterval(MIN_REGEN , MAX_REGEN);
            int type = RNG.getRandomInterval(0,1);
            if(type == 0){
                potions.add(new ChakraPotion(cost , weight , regen));
            }
            else{
                potions.add(new HealthPotion(cost , weight , regen));
            }
        }
        return potions;
    }

    public Shop(){
        potions = buyPotions();
    }

    @Override
    public String toCharacter() {
        return "S";
    }

    @Override
    public void interract(Character player) {
        Scanner scanner = new Scanner(System.in);
        String p = scanner.next();

        while(!p.equals("P")){
            p = scanner.next();
        }

        System.out.println("0. Exit Shop");
        for(int i = 0; i < potions.size(); i++){
            System.out.println((i+1) + ". " + potions.get(i));
        }

        int option = -1;

        try {
            option = scanner.nextInt();
        } catch (InputMismatchException e){
            String invalidInput = scanner.next();
            try {
                throw new InvalidCommandException("InvalidCommandException");
            } catch (InvalidCommandException ex) {
                ex.printStackTrace();
            }
        }

        if(option == 0){
            return;
        }

        option--;
        if(option < 0 || option > potions.size())
            try {
                throw new InvalidCommandException("InvalidCommandException");
            } catch (InvalidCommandException e) {
                e.printStackTrace();
            }

        if(player.buyPotion(potions.get(option))) {
            System.out.println("Potion get succesfully");
            System.out.println("Coins -> " + player.inventory.coins);
            System.out.println("New Inventory weight -> " + player.inventory.currentWeight);
            this.removePotion(potions.get(option));
        }
        else {
            System.out.println("Not enough coins");
        }
    }

    public Potion getPotion(int index){
        return potions.get(index);
    }

    public void removePotion(Potion potion){
        potions.remove(potion);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Shop{");
        sb.append("potions=").append(potions);
        sb.append('}');
        return sb.toString();
    }
}
