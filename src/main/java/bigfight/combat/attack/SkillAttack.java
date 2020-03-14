package bigfight.combat.attack;

import bigfight.combat.Combat;
import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatAlgo;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.SkillModel;

public class SkillAttack implements Attackable {
    private FighterStatus attacker;
    private FighterStatus defender;
    private SkillModel skill;
    private CombatRandom random;

    public SkillAttack(FighterStatus attacker, FighterStatus defender, SkillModel skill, CombatRandom random) {
        this.attacker = attacker;
        this.defender = defender;
        this.skill = skill;
        this.random = random;
    }

    @Override
    public void attack() {
        // should add exception if not initialized;
        double escape = attacker.getFocus() - defender.getEscape();
        escape += CombatAlgo.escapeByAgility(attacker.getAgility(), defender.getAgility());
        int damage = 0;
        if (random.getEscapeRandom() < escape) {
            // escaped
            return;
        }
        switch (skill.getIdentity()) {
            case ROAR:
                damage = getRoarDamage();
        }
        defender.updateHealth(defender.getHealth() - damage);
    }

    @Override
    public int getRoundChange() {
        switch (skill.getIdentity()) {
            case ROAR:
                return 1;
            default:
                return 0;
        }
    }

    @Override
    public void counterAttack() {
        // skill well not be counter attacked
    }

    private int getRoarDamage() {
        return 15;
    }
}
