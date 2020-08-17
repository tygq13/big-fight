package bigfight.combat.attack;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.special.ApparentDeath;
import bigfight.model.skill.skills.special.SeaReflectUsable;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.weapon.Weapon;
import bigfight.ui.Uiable;


class CounterAttack {
    private Fighter defender;
    private Fighter attacker;
    private CombatRandom random;
    private Uiable ui;

    CounterAttack(Fighter defender, Fighter attacker, CombatRandom random, Uiable ui) {
        this.defender = defender;
        this.attacker = attacker;
        this.random = random;
        this.ui = ui;
    }

    boolean specialCounter(int damage) {
        if (defender.getHealth() <= 0 && defender.hasSkill(SkillIdentity.APPARENT_DEATH) && !defender.getFighterFlag().apparentDeathUsed) {
            defender.getFighterFlag().apparentDeathUsed = true;
            ApparentDeath apparentDeath = (ApparentDeath) defender.getSkill(SkillIdentity.APPARENT_DEATH);
            int lastHealth = apparentDeath.getLastHealth();
            defender.updateHealth(lastHealth);
            ui.printSkillApparentDeath(defender.getName());
            return true;
        } else if (damage != 0 && defender.hasSkill(SkillIdentity.SEA_REFLECT)) {
            SeaReflectUsable seaReflect = (SeaReflectUsable) defender.getSkill(SkillIdentity.SEA_REFLECT);
            if (seaReflect.getRemainingUsage() > 0 && random.getSeaReflectRandom() < seaReflect.getInvocationChance()) {
                seaReflect.invoke();
                attacker.updateHealth(attacker.getHealth() - damage);
                return true;
            }
        }
        return false;
    }

    int counterAttack() {
        // recursive counter
        if (isCounter()) {
            Weapon weapon = defender.getHoldingWeapon();
            ui.printCounterAttackWeapon(defender.getName(), weapon == null ? "his fist" : weapon.getName());
            if (weapon == null) {
                new UnarmedAttack(defender, attacker, random, ui).attack();
            } else {
                switch (weapon.getType()) {
                    case BIG:
                        new BigTypeAttack(defender, attacker, weapon, random, ui).attack();
                        break;
                    case MEDIUM:
                        new MediumTypeAttack(defender, attacker, weapon, random, ui).attack();
                        break;
                    case SMALL:
                        new SmallTypeAttack(defender, attacker, weapon, random, ui).attack();
                }
            }
        }
        return 0;
    }

    private boolean isCounter() {
        return random.getCounterAttackRandom() < defender.getAdvancedAttribute().counterAttackChance;
    }
}
