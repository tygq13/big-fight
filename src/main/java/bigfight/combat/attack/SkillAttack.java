package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.*;
import bigfight.ui.Uiable;

public class SkillAttack implements Attackable {
    private FighterStatus attacker;
    private FighterStatus defender;
    private SkillModel skill;
    private CombatRandom random;
    private boolean isEscaped;
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
        // should add exception if not initialized;
        double escape = defender.getAdvancedAttribute().skillEvasionRate - attacker.getAdvancedAttribute().skillHitRate;
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        ui.printSkillRoarAttack(attacker.getName());
        if (random.getEscapeRandom() < escape) {
            // escaped
            ui.printSkillRoarDodge(defender.getName());
            return;
        }
        int damage = getSkillDamage();
        defender.updateHealth(defender.getHealth() - damage);
        ui.printInjury(defender.getName(), damage, defender.getHealth());
        CounterAttack counterAttack = new CounterAttack(defender, attacker, random, ui);
        if (!(counterAttack.specialCounter(damage))) {
            counterAttack.counterAttack();
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
                return actualSkill.getDamage();
            }
             case BOLT_FROM_THE_BLUE: {
                 BoltFromTheBlue actualSkill = (BoltFromTheBlue) skill;
                 return actualSkill.getDamage() + (int) (attacker.getLevel() * actualSkill.getLevelMultiply());
             }
            default:
                return 0;
        }
    }
}
