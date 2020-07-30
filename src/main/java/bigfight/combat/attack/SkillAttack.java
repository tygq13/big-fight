package bigfight.combat.attack;

import bigfight.combat.Round;
import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.logic.parser.Parser;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.*;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.ui.Uiable;

public class SkillAttack implements Attackable {
    private FighterStatus attacker;
    private FighterStatus defender;
    private SkillModel skill;
    private CombatRandom random;
    private Uiable ui;

    public SkillAttack(FighterStatus attacker, FighterStatus defender, SkillModel skill, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.skill = skill;
        this.random = random;
        this.ui = ui;
    }

    @Override
    public void attack() {
        // special case
        if (skill.getIdentity() == SkillIdentity.DISARM) {
            new Round(attacker, defender, new Empowerment(defender.getHoldingWeapon()), random, ui);
            return;
        }
        // should add exception if not initialized;
        double escape = defender.getAdvancedAttribute().skillEvasionRate - attacker.getAdvancedAttribute().skillHitRate;
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        int damage = getSkillDamage();
        if (random.getEscapeRandom() < escape && skill.getIdentity() != SkillIdentity.HIT_FROM_GOD) {
            ui.printSkillRoarDodge(defender.getName());
        } else {
            defender.updateHealth(defender.getHealth() - damage);
            ui.printInjury(defender.getName(), damage, defender.getHealth());
            CounterAttack counterAttack = new CounterAttack(defender, attacker, random, ui);
            if (!(counterAttack.specialCounter(damage))) {
                counterAttack.counterAttack();
            }
        }
    }

    @Override
    public int getRoundChange() {
        switch (skill.getIdentity()) {
            case ROAR:
                Roar actualSkill = (Roar) skill;
                return actualSkill.getIgnore();
            default:
                return 0;
        }
    }

    private int getSkillDamage() {
         switch (skill.getIdentity()) {
            case ROAR: {
                Roar actualSkill = (Roar) skill;
                ui.printSkillRoarAttack(attacker.getName());
                return actualSkill.getDamage();
            }
             case BOLT_FROM_THE_BLUE: {
                 BoltFromTheBlue actualSkill = (BoltFromTheBlue) skill;
                 return actualSkill.getDamage() + (int) (attacker.getLevel() * actualSkill.getLevelMultiply());
             }
             case TORNADO: {
                 Tornado actualSkill = (Tornado) skill;
                 return actualSkill.getDamage() + (int) (attacker.getStrength() * actualSkill.getStrengthMultiply());
             } case HIT_FROM_GOD: {
                 HitFromGod actualSkill = (HitFromGod) skill;
                 if (random.getHitFromGodRandom() < actualSkill.getSeckillChance()) {
                     return defender.getHealth() - 1;
                 } else {
                     return 0;
                 }
             }
            default:
                return 0;
        }
    }
}
