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
        double escape = attacker.getFocus() - defender.getEscape();
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        ui.printSkillRoarAttack(attacker.getName());
        if (random.getEscapeRandom() < escape) {
            // escaped
            ui.printSkillRoarDodge(defender.getName());
            isEscaped = true;
            return;
        }
        isEscaped = false;
        int damage = getSkillDamage();
        defender.updateHealth(defender.getHealth() - damage);
        ui.printInjury(defender.getName(), damage, defender.getHealth());
        counterAttack();
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

    private void counterAttack() {
        new CounterAttack(defender, attacker, isEscaped, random, ui).specialCounter();
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
