package spells;

import entities.Entity;
import interfaces.Visitor;

public abstract class Spell implements Visitor {
    public int chakraCost;
    public int damage;

    public Spell() {
    }

    public Spell(int chakraCost, int damage) {
        this.chakraCost = chakraCost;
        this.damage = damage;
    }

    public void visit(Entity entity){
        entity.receiveDamage(this);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Spell{");
        sb.append("chakraCost=").append(chakraCost);
        sb.append(", damage=").append(damage);
        sb.append('}');
        return sb.toString();
    }
}
