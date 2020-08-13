package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class HitFromGod extends SkillModel {
    private double SECKILL_CHANCE_ONE = 0.08;

    public HitFromGod(SkillStruct skill) {
        super(skill);
    }

    public double getInvocationChance() {
        return SECKILL_CHANCE_ONE;
    }
}
