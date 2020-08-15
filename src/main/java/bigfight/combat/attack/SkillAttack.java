package bigfight.combat.attack;

import bigfight.combat.Round;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.*;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.ui.Uiable;

public class SkillAttack implements Attackable {
    private Fighter attacker;
    private Fighter defender;
    private SkillModel skill;
    private CombatRandom random;
    private Uiable ui;

    public SkillAttack(Fighter attacker, Fighter defender, SkillModel skill, CombatRandom random, Uiable ui) {
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
            Empowerment empowerment = new Empowerment(defender.getHoldingWeapon());
            defender.changeWeapon(new Empowerment((Weapon) null));
            // not activating weapon round change effect
            new Round(attacker, defender, empowerment, random, ui).fight();
            return;
        }
        // should add exception if not initialized;
        double escape = defender.getAdvancedAttribute().skillEvasionRate - attacker.getAdvancedAttribute().skillHitRate;
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        int damage = getSkillDamage();
        if (random.getEscapeRandom() < escape) {
            ui.printSkillRoarDodge(defender.getName());
        } else {
            // bad bad bad!!! change this
            if (skill.getIdentity() == SkillIdentity.TICKLE) {
                Tickle actualSkill = (Tickle) skill;
                defender.getFighterFlag().tickledRounds = actualSkill.getMaxRounds();
            }
            if (skill.getIdentity() == SkillIdentity.GLUE) {
                defender.getFighterFlag().beingGlued = true;
            }
            defender.getFighterFlag().ignored += ignoreOpponent();
            defender.updateHealth(defender.getHealth() - damage);
            ui.printInjury(defender.getName(), damage, defender.getHealth());
            CounterAttack counterAttack = new CounterAttack(defender, attacker, random, ui);
            if (!(counterAttack.specialCounter(damage))) {
                counterAttack.counterAttack();
            }
        }
    }


    private int ignoreOpponent() {
        switch (skill.getIdentity()) {
            case ROAR:
                Roar actualSkill = (Roar) skill;
                return actualSkill.getIgnore();
        }
        return 0;
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
             }
             case ONE_PUNCH: {
                 return defender.getHealth() - 1;
             }
             case ANGELS_WINGS: {
                 AngelsWings actualSkill = (AngelsWings) skill;
                 return actualSkill.getDamage() + (int) (attacker.getAgility() * actualSkill.getAgilityMultiply());
             }
             case FOSHAN_KICK: {
                 FoshanKick actualSkill = (FoshanKick) skill;
                 return actualSkill.getDamage() + (int) (attacker.getStrength() * actualSkill.getStrengthMultiply());
             }
             case GLUE: {
                 Glue actualSkill = (Glue) skill;
                 return 0;
             }
             case TICKLE: {
                 Tickle actualSkill = (Tickle) skill;
                 defender.getFighterFlag().tickledDamage = actualSkill.getDamage() + (int) (attacker.getAgility() * actualSkill.getAgilityMultiply());
                 return defender.getFighterFlag().tickledDamage;
             }
            default:
                return 0;
        }
    }
}
