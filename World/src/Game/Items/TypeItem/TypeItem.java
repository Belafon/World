package Game.Items.TypeItem;

public class TypeItem {
    public final String name;
    public final int weight;
    public final int toss; // > 0 -> throwable, more describes demage
    public final int visibility;
    public final String look;
    public TypeItem(String name, int weight, int toss, int visibility, String look) {
        this.name = name;
        this.weight = weight;
        this.toss = toss;
        this.visibility = visibility;
        this.look = look;
    }
}
