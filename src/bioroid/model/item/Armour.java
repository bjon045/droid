package bioroid.model.item;

public class Armour extends Item {

    private int acAdjustment;

    private ArmourType armourType;

    public int getAcAdjustment() {
        return acAdjustment;
    }

    public void setAcAdjustment(int acAdjustment) {
        this.acAdjustment = acAdjustment;
    }

    public ArmourType getArmourType() {
        return armourType;
    }

    public void setArmourType(ArmourType armourType) {
        this.armourType = armourType;
    }

    @Override
    public boolean validate() {
        if (armourType == null) {
            return false;
        }
        return super.validate();
    }

}
