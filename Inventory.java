package entities;

import potion.Potion;

import java.util.ArrayList;

public class Inventory {
    public ArrayList<Potion> potions;
    public int maxWeight;
    public int currentWeight;
    public int coins;

    public Inventory(int maxWeight) {
        this.maxWeight = maxWeight;
        potions = new ArrayList<>();
        currentWeight = 0;
        coins = 50;
    }

    public void addPotion(Potion potion){
        this.potions.add(potion);
        currentWeight += potion.getWeight();
        if(currentWeight > maxWeight){
            System.out.println("Inventory full!");
        }
    }

    public void removePotion(Potion potion){
        this.potions.remove(potion);
        currentWeight -= potion.getWeight();
    }

    public int getLeftWeight(){
        return maxWeight - currentWeight;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Inventory{");
        sb.append("potions=").append(potions);
        sb.append(", maxWeight=").append(maxWeight);
        sb.append(", currentWeight=").append(currentWeight);
        sb.append(", coins=").append(coins);
        sb.append('}');
        return sb.toString();
    }
}
