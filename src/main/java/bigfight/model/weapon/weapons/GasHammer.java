package bigfight.model.weapon.weapons;

import bigfight.model.weapon.struct.WeaponStruct;

public class GasHammer extends WeaponModel {
    private final double GAS_HAMMER_ONE_IGNORE_CHANCE = 0.1;

    public GasHammer(WeaponStruct weaponStruct) {
        super(weaponStruct);
    }

    public double getIgnoreChance() {
        return GAS_HAMMER_ONE_IGNORE_CHANCE;
    }
}
