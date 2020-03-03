package bigfight.model.weapon;

import java.util.ArrayList;

public class WeaponManager {
    private ArrayList<Weapon> weaponList;

    public WeaponManager() {
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
