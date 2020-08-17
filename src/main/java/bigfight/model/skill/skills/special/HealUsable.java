package bigfight.model.skill.skills.special;

import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillStruct;

public class HealUsable extends SkillModel {
    private int remainingInvocation;
    private int baseHeal;
    private double levelMultiply;

    public HealUsable(SkillStruct skillStruct, int maxInvocation, int baseHeal, double levelMultiply) {
        super(skillStruct);
        remainingInvocation = maxInvocation;
        this.baseHeal = baseHeal;
        this.levelMultiply = levelMultiply;
    }

    public int getRemainingUsage() {
        return remainingInvocation;
    }

    public void invoke() {
        remainingInvocation -= 1;
    }

    public int getBaseHeal() {
        return baseHeal;
    }

    public double getLevelMultiply() {
        return levelMultiply;
    }
}
