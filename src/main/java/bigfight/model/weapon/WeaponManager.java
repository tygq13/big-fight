package bigfight.model.weapon;

import java.util.ArrayList;

public class WeaponManager {
    private ArrayList<Weapon> weaponList;

    public WeaponManager() {
        weaponList = new ArrayList<>();
    }

    public ArrayList<Weapon> getWeaponList() {
        return weaponList;
    }

    //todo: exception for repeated weapon
    public void add(Weapon weapon) {
        weaponList.add(weapon);
    }

    public int getSize() {
        return weaponList.size();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Weapon weapon : weaponList) {
            result.append(weapon.getName()).append(", ");
        }
        if (result.length() > 0) {
            result.delete(result.length() - 2, result.length());
        }
        return result.toString();
    }
}
