package potion;

public class ChakraPotion implements Potion{
    private int cost;
    private int weight;
    private int regen;

    public ChakraPotion(int cost, int weight, int regen) {
        this.cost = cost;
        this.weight = weight;
        this.regen = regen;
    }

    @Override
    public String getUse() {
        return "Chakra";
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
        final StringBuilder sb = new StringBuilder("ChakraPotion{");
        sb.append("cost=").append(cost);
        sb.append(", weight=").append(weight);
        sb.append(", regen=").append(regen);
        sb.append('}');
        return sb.toString();
    }
}
