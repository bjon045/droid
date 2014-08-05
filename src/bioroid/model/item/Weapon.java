package bioroid.model.item;

public class Weapon extends Item {

    private int shots;

    private int damageDice;

    private int damageModifier;

    private int range;

    private int ammo;

    private WeaponType weaponType;

    public int getShots() {
        return shots;
    }

    public void setShots(int shots) {
        this.shots = shots;
    }

    public int getDamageDice() {
        return damageDice;
    }

    public void setDamageDice(int damageDice) {
        this.damageDice = damageDice;
    }

    public int getDamageModifier() {
        return damageModifier;
    }

    public void setDamageModifier(int damageModifier) {
        this.damageModifier = damageModifier;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(WeaponType weaponType) {
        this.weaponType = weaponType;
    }

    @Override
    public boolean validate() {
        if (weaponType == null) {
            return false;
        }
        return super.validate();
    }

}
