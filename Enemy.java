package entities;

import cell.CellElement;
import exceptions.InvalidCommandException;
import interfaces.*;
import spells.*;
import utils.RNG;

import java.util.ArrayList;
import java.util.Scanner;

public class Enemy extends Entity implements CellElement{
    public static int MIN_DAMAGE = 5;
    public static int MAX_DAMAGE = 10;
    public static int MIN_HEALTH = 20;
    public static int MAX_HEALTH = 25;
    public static int MIN_CHAKRA = 10;
    public static int MAX_CHAKRA = 15;
    public static int MIN_CHAKRA_COST = 3;
    public static int MAX_CHAKRA_COST = 8;

    private static ArrayList<Spell> createAttacks(){
        ArrayList<Spell> attacks = new ArrayList<>();
        attacks.add(new AttackSpell(RNG.getRandomInterval(MIN_DAMAGE,MAX_DAMAGE)));
        int spellNumber = RNG.getRandomInterval(1,3);
        for(int i = 1; i<=spellNumber; i++){
            int cost = RNG.getRandomInterval(MIN_CHAKRA_COST,MAX_CHAKRA_COST);
            int damage = RNG.getRandomInterval(MIN_DAMAGE , MAX_DAMAGE)*2;
            int type = RNG.getRandomInterval(0,2);
            if(type == 0){
                attacks.add(new FireSpell(cost , damage));
            }
            else if(type == 1){
                attacks.add(new IceSpell(cost , damage));
            }
            else{
                attacks.add(new EarthSpell(cost , damage));
            }
        }
        return attacks;
    }

    public Enemy() {
        super(createAttacks() , RNG.getRandomInterval(MIN_HEALTH , MAX_HEALTH) ,
                RNG.getRandomInterval(MIN_CHAKRA , MAX_CHAKRA), RNG.rng.nextBoolean(),
                RNG.rng.nextBoolean(), RNG.rng.nextBoolean());
    }

    @Override
    public String toCharacter() {
        return "E";
    }

    @Override
    public void interract(Character player) {
        Scanner scanner = new Scanner(System.in);
        String p = scanner.next();

        while(!p.equals("P")){
            p = scanner.next();
        }

        System.out.println();
        System.out.println("Player current Chakra -> " + player.currentChakra);
        System.out.println("Player current Health -> " + player.currentHealth);
        System.out.println();
        System.out.println("Enemy current Chakra -> " + currentChakra);
        System.out.println("Enemy current Health -> " + currentHealth);
        System.out.println();
        System.out.println("You can use a potion(0) or you can use a spell("
                + 1 + "-" + player.abilities.size() + ")");
        System.out.println("Please select an option ->");

        System.out.println("0. Use potion");

        for(int i = 0; i < player.abilities.size(); i++){
            System.out.println((i + 1) + ". " + player.abilities.get(i));
        }

        boolean succesfullAction = false;

        while (!succesfullAction) {
            int option = scanner.nextInt();

            if(option == 0){
                player.usePotion();
                break;
            }

            option--;
            if (option < 0 || option > player.abilities.size())
                try {
                    throw new InvalidCommandException("InvalidCommandException");
                } catch (InvalidCommandException e) {
                    e.printStackTrace();
                }

            if (player.useAbility(player.abilities.get(option), this)) {
                System.out.println("Attack succesfully");
                succesfullAction = true;
            } else {
                System.out.println("Not enough chakra");
                System.out.println("Please try another spell!");
                System.out.println("Or you can choose Attack Spell for normal damage (chakraCost = 0) .");
            }
        }

        System.out.println();
        System.out.println("Enemy abilities ->");
        for(int i = 0; i < this.abilities.size(); i++){
            System.out.println((i+1) + ". " + this.abilities.get(i));
        }

        int index = RNG.getRandomInterval(0, abilities.size() - 1);
        this.useAbility(abilities.get(index), player);
        System.out.println("Enemy use ability -> " + abilities.get(index));
    }

    @Override
    public void receiveDamage(Spell ability) {
        if(RNG.getRandomInterval(1,100) >= 50) {
            System.out.println("The enemy avoided damage.");
            return;
        }
        if(ability instanceof FireSpell && isFireResistant) {
            currentHealth -= ability.damage / 2;
            if (currentHealth < 0) {
                currentHealth = 0;
            }
        }
        else if(ability instanceof IceSpell && isIceResistant) {
            currentHealth -= ability.damage / 2;
            if (currentHealth < 0) {
                currentHealth = 0;
            }
        }
        else if(ability instanceof EarthSpell && isEarthResistant) {
            currentHealth -= ability.damage / 2;
            if (currentHealth < 0) {
                currentHealth = 0;
            }
        }
        else {
            currentHealth -= ability.damage;
            if (currentHealth < 0) {
                currentHealth = 0;
            }
        }
    }

    @Override
    public int getDamage() {
        if(RNG.getRandomInterval(1,100) >= 50)
            return 2*abilities.get(0).damage;
        return abilities.get(0).damage;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Enemy{");
        sb.append("abilities=").append(abilities);
        sb.append(", maxHealth=").append(maxHealth);
        sb.append(", currentHealth=").append(currentHealth);
        sb.append(", maxChakra=").append(maxChakra);
        sb.append(", currentChakra=").append(currentChakra);
        sb.append(", isFireResistant=").append(isFireResistant);
        sb.append(", isIceResistant=").append(isIceResistant);
        sb.append(", isEarthResistant=").append(isEarthResistant);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
