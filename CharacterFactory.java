package entities;

import spells.Spell;

import java.util.ArrayList;

public class CharacterFactory{
    private final ArrayList<Spell> abilities;
    private final int currentOx;
    private final int currentOy;
    private final Inventory inventory;
    private final int currentXp;
    private final int currentLvl;
    private final int charisma;
    private final int dexterity;
    private final int strength;

    public CharacterFactory(ArrayList<Spell> abilities, int currentOx, int currentOy,
                            Inventory inventory, int currentXp, int currentLvl, int charisma,
                            int dexterity, int strength) {
        this.abilities = abilities;
        this.currentOx = currentOx;
        this.currentOy = currentOy;
        this.inventory = inventory;
        this.currentXp = currentXp;
        this.currentLvl = currentLvl;
        this.charisma = charisma;
        this.dexterity = dexterity;
        this.strength = strength;
    }

    /*public Mage getMage(String name){
        return new Mage(abilities , 100 , 100 ,
                name , currentOx , currentOy , inventory , currentXp ,
                currentLvl , charisma , strength , dexterity);
    }

    public Warrior getWarrior(String name){
        return new Warrior(abilities , 100 , 100 ,
                name , currentOx , currentOy , inventory , currentXp ,
                currentLvl , charisma , strength , dexterity);
    }

    public Rogue getRogue(String name){
        return new Rogue(abilities , 100 , 100 ,
                name , currentOx , currentOy , inventory , currentXp ,
                currentLvl , charisma , strength , dexterity);
    }*/

    public Character getCharacater(String characterType , String name) {
        if (characterType == null) {
            return null;
        }
        if (characterType.equalsIgnoreCase("WARRIOR")) {
            return new Warrior(abilities, 100, 100,
                    name, currentOx, currentOy, inventory, currentXp,
                    currentLvl, charisma, strength, dexterity);

        } else if (characterType.equalsIgnoreCase("Mage")) {
            return new Mage(abilities, 100, 100,
                    name, currentOx, currentOy, inventory, currentXp,
                    currentLvl, charisma, strength, dexterity);

        } else if (characterType.equalsIgnoreCase("ROGUE")) {
            return new Rogue(abilities, 100, 100,
                    name, currentOx, currentOy, inventory, currentXp,
                    currentLvl, charisma, strength, dexterity);
        }

        return null;
    }
}
