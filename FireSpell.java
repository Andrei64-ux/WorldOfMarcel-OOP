package spells;

public class FireSpell extends Spell{
    public FireSpell() {
        super();
    }

    public FireSpell(int chakraCost, int damage) {
        super(chakraCost, damage);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("FireSpell{");
        sb.append("chakraCost=").append(chakraCost);
        sb.append(", damage=").append(damage);
        sb.append('}');
        return sb.toString();
    }
}