package bigfight.model.weapon;

import java.util.ArrayList;

public class WeaponList {
    private ArrayList<Weapon> weaponList;

    public WeaponList() {
        weaponList = new ArrayList<>();
    }

    public ArrayList getWeaponList() {
        return weaponList;
    }

    public void add(Weapon weapon) {
        weaponList.add(weapon);
    }

    public int getSize() {
        return weaponList.size();
    }
}
