package entities;

import interfaces.Element;
import spells.Spell;

import java.util.ArrayList;

public abstract class Entity implements Element {
    public ArrayList<Spell> abilities;
    public int maxHealth;
    public int currentHealth;
    public int maxChakra;
    public int currentChakra;
    public boolean isFireResistant;
    public boolean isIceResistant;
    public boolean isEarthResistant;

    public Entity(ArrayList<Spell> abilities, int maxHealth,
                  int maxChakra, boolean isFireResistant,
                  boolean isIceResistant, boolean isEarthResistant) {
        this.abilities = abilities;
        this.currentHealth = maxHealth;
        this.maxHealth = maxHealth;
        this.currentChakra = maxChakra;
        this.maxChakra = maxChakra;
        this.isFireResistant = isFireResistant;
        this.isIceResistant = isIceResistant;
        this.isEarthResistant = isEarthResistant;
    }

    public void healthRegeneration(int health){
        currentHealth = Math.min(currentHealth+health , maxHealth);
    }

    public void chakraRegeneration(int chakra){
        currentChakra = Math.min(currentChakra+chakra , maxChakra);
    }

    public boolean useAbility(Spell spell , Entity target){
        if(spell.chakraCost <= currentChakra) {
            spell.visit(target);
            currentChakra = currentChakra - spell.chakraCost;
            return true;
        }

        return false;
    }
    public abstract void receiveDamage(Spell ability);
    public abstract int getDamage();
}
