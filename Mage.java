package entities;

import interfaces.Visitor;
import spells.EarthSpell;
import spells.FireSpell;
import spells.IceSpell;
import spells.Spell;
import utils.RNG;

import java.util.ArrayList;

public class Mage extends Character {
    public Mage(ArrayList<Spell> abilities, int maxHealth,
                int maxChakra, String currentCharacter, int currentOx,
                int currentOy, Inventory inventory, int currentXp,
                int currentLvl, int charisma, int strength, int dexterity) {
        super(abilities, maxHealth, maxChakra, false,
                true, false, currentCharacter,
                currentOx, currentOy, inventory, currentXp, currentLvl,
                charisma, strength, dexterity);
        inventory.maxWeight = RNG.getRandomInterval(inventory.maxWeight/5 , inventory.maxWeight/10);
    }

    @Override
    public void receiveDamage(Spell ability) {
        if(RNG.getRandomInterval(1,100) >= 50) {
            System.out.println("The player avoided damage.");
            return;
        }
        if(ability instanceof IceSpell && isIceResistant){
            System.out.println("The entity is immune to this ability.");
        } else if(ability instanceof FireSpell && !isFireResistant) {
            currentHealth -= Math.max((ability.damage - dexterity / strength)/2 , 0);
            if (currentHealth < 0)
                currentHealth = 0;
        } else if(ability instanceof EarthSpell && !isEarthResistant){
            currentHealth -= Math.max((ability.damage - dexterity % strength)/2 , 0);
            if (currentHealth < 0)
                currentHealth = 0;
        } else {
            currentHealth -= Math.max(ability.damage , 0);
        }
    }

    @Override
    public int getDamage() {
        if(RNG.getRandomInterval(1,100) >= 50)
            return 2*(abilities.get(0).damage+charisma/RNG.getRandomInterval(50,100));
        return abilities.get(0).damage+charisma/RNG.getRandomInterval(50,100);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Mage{");
        sb.append("currentCharacter='").append(currentCharacter).append('\'');
        sb.append(", currentOx=").append(currentOx);
        sb.append(", currentOy=").append(currentOy);
        sb.append(", inventory=").append(inventory);
        sb.append(", currentXp=").append(currentXp);
        sb.append(", currentLvl=").append(currentLvl);
        sb.append(", charisma=").append(charisma);
        sb.append(", strength=").append(strength);
        sb.append(", dexterity=").append(dexterity);
        sb.append(", abilities=").append(abilities);
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
