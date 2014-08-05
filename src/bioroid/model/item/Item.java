package bioroid.model.item;

import bioroid.model.ModelObject;

public abstract class Item extends ModelObject {

    private int weight;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

}
