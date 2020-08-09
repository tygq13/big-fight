package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.ApparentDeath;
import bigfight.model.skill.skills.SeaReflect;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.weapon.Weapon;
import bigfight.ui.Uiable;


class CounterAttack {
    private FighterStatus defender;
    private FighterStatus attacker;
    private CombatRandom random;
    private Uiable ui;

    CounterAttack(FighterStatus defender, FighterStatus attacker, CombatRandom random, Uiable ui) {
        this.defender = defender;
        this.attacker = attacker;
        this.random = random;
        this.ui = ui;
    }

    boolean specialCounter(int damage) {
        if (defender.getHealth() <= 0 && defender.hasSkill(SkillIdentity.APPARENT_DEATH)) {
            ApparentDeath apparentDeath = (ApparentDeath) defender.getSkill(SkillIdentity.APPARENT_DEATH);
            int lastHealth = apparentDeath.getLastHealth();
            defender.updateHealth(lastHealth);
            defender.removeSkill(SkillIdentity.APPARENT_DEATH);
            ui.printSkillApparentDeath(defender.getName());
            return true;
        } else if (damage != 0 && defender.hasSkill(SkillIdentity.SEA_REFLECT)) {
            SeaReflect seaReflect = (SeaReflect) defender.getSkill(SkillIdentity.SEA_REFLECT);
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
