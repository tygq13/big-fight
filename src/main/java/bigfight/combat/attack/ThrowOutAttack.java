package bigfight.combat.attack;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.model.weapon.weapons.GasHammer;
import bigfight.ui.Uiable;

@Deprecated
public class ThrowOutAttack implements Attackable {
    private Fighter attacker;
    private Fighter defender;
    private Weapon weapon;
    private CombatRandom random;
    private Uiable ui;

    public ThrowOutAttack(Fighter attacker, Fighter defender, Weapon weapon, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.weapon = weapon;
        this.random = random;
        this.ui = ui;
    }

    @Override
    public void attack() {
        switch (weapon.getType()) {
            case BIG:
                new BigTypeAttack(attacker, defender, weapon, random, ui).attack();
                break;
            case MEDIUM:
                new MediumTypeAttack(attacker, defender, weapon, random, ui).attack();
                break;
            case SMALL:
                new SmallTypeAttack(attacker, defender, weapon, random, ui).attack();
        }
        attacker.changeWeapon(new Empowerment((Weapon) null));
    }
}
