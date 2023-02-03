package spells;

public class IceSpell extends Spell{
    public IceSpell() {
        super();
    }

    public IceSpell(int chakraCost, int damage) {
        super(chakraCost, damage);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IceSpell{");
        sb.append("chakraCost=").append(chakraCost);
        sb.append(", damage=").append(damage);
        sb.append('}');
        return sb.toString();
    }
}
