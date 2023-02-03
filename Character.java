package entities;

import exceptions.InvalidCommandException;
import potion.ChakraPotion;
import potion.HealthPotion;
import potion.Potion;
import spells.Spell;
import utils.RNG;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class Character extends Entity{
    public String currentCharacter;
    public int currentOx;
    public int currentOy;
    public Inventory inventory;
    public int currentXp;
    public int currentLvl;
    public int charisma;
    public int strength;
    public int dexterity;
    public int enemyCount;

    public Character(ArrayList<Spell> abilities,
                     int maxHealth, int maxChakra,
                     boolean isFireResistant, boolean isIceResistant,
                     boolean isEarthResistant, String currentCharacter, int currentOx,
                     int currentOy, Inventory inventory, int currentXp, int currentLvl,
                     int charisma, int strength, int dexterity) {
        super(abilities, maxHealth, maxChakra, isFireResistant, isIceResistant, isEarthResistant);
        this.currentCharacter = currentCharacter;
        this.currentOx = currentOx;
        this.currentOy = currentOy;
        this.inventory = inventory;
        this.currentXp = currentXp;
        this.currentLvl = currentLvl;
        this.charisma = charisma;
        this.strength = strength;
        this.dexterity = dexterity;
    }

    public int getEnemyCount() {
        return enemyCount;
    }

    public void setEnemyCount() {
        this.enemyCount = 0;
    }

    public boolean buyPotion(Potion potion){
        if(inventory.coins >= potion.getCost() && inventory.getLeftWeight() >= potion.getWeight()){
            inventory.addPotion(potion);
            inventory.coins -= potion.getCost();
            return true;
        }
        return false;
    }

    public void setAttribute(){
        if(currentLvl >= 2){
            if(this instanceof Warrior){
                strength += RNG.getRandomInterval(1,10);
                dexterity += RNG.getRandomInterval(1,10);
                charisma += RNG.getRandomInterval(1,10);
            }

            else if(this instanceof Rogue){
                strength += RNG.getRandomInterval(1,8);
                dexterity += RNG.getRandomInterval(1,8);
                charisma += RNG.getRandomInterval(1,8);
            }

            else{
                strength += RNG.getRandomInterval(1,6);
                dexterity += RNG.getRandomInterval(1,6);
                charisma += RNG.getRandomInterval(1,6);
            }
        }
    }

    public void usePotion(){
        Scanner scanner = new Scanner(System.in);

        String p = scanner.next();
        while (!p.equals("P")){
            p = scanner.next();
        }

        System.out.println("Available potion");
        for(int i = 0 ; i < inventory.potions.size() ; i++){
            System.out.println(i + ". " + inventory.potions.get(i));
        }

        if(inventory.potions.size() == 0){
            System.out.println("Not available potions");
            return;
        }

        int option = scanner.nextInt();

        if (option < 0 || option > inventory.potions.size())
            try {
                throw new InvalidCommandException("InvalidCommandException");
            } catch (InvalidCommandException e) {
                e.printStackTrace();
            }

        Potion potion = inventory.potions.get(option);

        if(potion instanceof HealthPotion){
            healthRegeneration(potion.getRegen());
        }
        else if(potion instanceof ChakraPotion){
            chakraRegeneration(potion.getRegen());
        }

        inventory.removePotion(potion);
    }
}
