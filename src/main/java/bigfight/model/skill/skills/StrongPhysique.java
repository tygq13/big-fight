package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.BasicAttribute;

public class StrongPhysique extends SkillModel{
    private final double STRONG_PHYSIQUE_ZERO_MULTIPLY = 0.15;
    private final int STRONG_PHYSIQUE_ZERO_ADDITION = 8;

    public StrongPhysique(SkillStruct skill) {
        super(skill);
    }

    public double getMultiply() {
        return STRONG_PHYSIQUE_ZERO_MULTIPLY;
    }

    public int getAddition() {
        return STRONG_PHYSIQUE_ZERO_ADDITION;
    }


    public void upgrade(BasicAttribute health) {
        int addition = (int) (health.getBase() * STRONG_PHYSIQUE_ZERO_MULTIPLY + STRONG_PHYSIQUE_ZERO_ADDITION);
        health.addToAddition(addition);
    }
}
