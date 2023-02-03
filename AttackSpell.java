package spells;

/*Am creat o abilitate , care defapt este un
  atac normal , de aceea si costul de mana e 0*/

public class AttackSpell extends Spell{
    public AttackSpell(int damage) {
        super(0, damage);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AttackSpell{");
        sb.append("chakraCost=").append(chakraCost);
        sb.append(", damage=").append(damage);
        sb.append('}');
        return sb.toString();
    }
}
