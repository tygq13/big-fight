package bigfight.combat.fighter;

import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;

import java.util.ArrayList;

public class DisposableWeaponList {
    private ArrayList<Weapon> weaponArrayList;

    public DisposableWeaponList() {
        weaponArrayList = new ArrayList<>();
    }

    public DisposableWeaponList(ArrayList<Weapon> weaponArrayList) {
        this.weaponArrayList = (ArrayList<Weapon>) weaponArrayList;
    }

    public int size() {
        return weaponArrayList.size();
    }

    public void add(Weapon weapon) {
        weaponArrayList.add(weapon);
    }

    public Empowerment select(CombatRandom random) {
        int luckyDraw = random.selectWhichEmpowerment(weaponArrayList.size());
        Empowerment empowerment = new Empowerment(weaponArrayList.get(luckyDraw));
        weaponArrayList.remove(luckyDraw);
        return empowerment;
    }
}
