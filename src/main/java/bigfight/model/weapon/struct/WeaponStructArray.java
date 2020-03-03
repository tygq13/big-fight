package bigfight.model.weapon.struct;

import bigfight.model.weapon.Weapon;

import java.util.ArrayList;

public class WeaponStructArray {
    private  ArrayList<WeaponStruct> weaponStructArray;

    public WeaponStructArray(WeaponStruct one) {
        weaponStructArray = new ArrayList<>(5);
        weaponStructArray.add(one);
    }

    public WeaponStruct withStar(int num) {
        return weaponStructArray.get(num - 1);
    }
}
