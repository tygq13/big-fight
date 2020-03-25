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
        int damage = 0;
        ui.printSkillRoarAttack(attacker.getName());
        if (random.getEscapeRandom() < escape) {
            ui.printSkillRoarDodge(defender.getName());
            // escaped
            isEscaped = true;
            return;
        }
        isEscaped = false;
        switch (skill.getIdentity()) {
            case ROAR:
                damage = getRoarDamage();
        }
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

    private int getRoarDamage() {
         switch (skill.getIdentity()) {
            case ROAR:
                Roar actualSkill = (Roar) skill;
                return actualSkill.getDamage();
            default:
                return 0;
        }
    }
}
