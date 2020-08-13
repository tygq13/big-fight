package bigfight.combat.fighter;

import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponType;

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

    public Empowerment select(CombatRandom random, FighterFlag flag) {
        int luckyDraw = random.selectWeapon(weaponArrayList.size());
        Weapon weapon = weaponArrayList.get(luckyDraw);
        if (flag.beingGlued && weapon.getType() != WeaponType.THROW) {
            flag.ignoredByUnselection = true;
            return new Empowerment((Weapon) null);
        }
        Empowerment empowerment = new Empowerment(weapon);
        weaponArrayList.remove(luckyDraw);
        return empowerment;
    }
}
