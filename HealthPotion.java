package potion;

public class HealthPotion implements Potion{
    private int cost;
    private int weight;
    private int regen;

    public HealthPotion(int cost, int weight, int regen) {
        this.cost = cost;
        this.weight = weight;
        this.regen = regen;
    }

    @Override
    public String getUse() {
        return "Health";
    }

    @Override
    public int getCost() {
        return cost;
    }

    @Override
    public int getRegen() {
        return regen;
    }

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("HealthPotion{");
        sb.append("cost=").append(cost);
        sb.append(", weight=").append(weight);
        sb.append(", regen=").append(regen);
        sb.append('}');
        return sb.toString();
    }
}
