package spells;

public class EarthSpell extends Spell{
    public EarthSpell() {
        super();
    }

    public EarthSpell(int chakraCost, int damage) {
        super(chakraCost, damage);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EarthSpell{");
        sb.append("chakraCost=").append(chakraCost);
        sb.append(", damage=").append(damage);
        sb.append('}');
        return sb.toString();
    }
}
