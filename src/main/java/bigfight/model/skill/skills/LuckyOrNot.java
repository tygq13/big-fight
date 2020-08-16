package bigfight.model.skill.skills;

import bigfight.combat.fighter.buff.Buff;
import bigfight.combat.fighter.buff.LuckyOrNotBuff;
import bigfight.model.skill.struct.SkillStruct;

public class LuckyOrNot extends SkillModel {
    private final double LUCKY_CHANCE = 0.7;
    private final int BASE_DAMAGE = 30;
    private final double LEVEL_MULTIPLY = 0.5;
    private final int HEAL = 25;
    private final double EXTRA_SKILL_HIT_RATE = 0.5;

    public LuckyOrNot(SkillStruct skill) {
        super(skill);
    }

    public int getDamage() {
        return BASE_DAMAGE;
    }

    public double getLevelMultiply() {
        return LEVEL_MULTIPLY;
    }

    public double getLuckyChance() {
        return LUCKY_CHANCE;
    }

    public double getExtraHitRate() {
        return EXTRA_SKILL_HIT_RATE;
    }

    public int getHeal() {
        return HEAL;
    }

    public Buff createBuff() {
        return new LuckyOrNotBuff(this);
    }
}
