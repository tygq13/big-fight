package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;
import bigfight.model.warrior.component.Health;

public class StrongPhysique extends SkillModel{
    private final double STRONG_PHYSIQUE_ONE_MULTIPLY = 0.15;
    private final int STRONG_PHYSIQUE_ONE_ADDITION = 8;

    public StrongPhysique(SkillStruct skill) {
        super(skill);
    }

    public double getMultiply() {
        return STRONG_PHYSIQUE_ONE_MULTIPLY;
    }

    public int getAddition() {
        return STRONG_PHYSIQUE_ONE_ADDITION;
    }

    public void upgrade(Health health) {
        // todo: got problem here
        int addition = (int) (health.getBase() * STRONG_PHYSIQUE_ONE_MULTIPLY + STRONG_PHYSIQUE_ONE_ADDITION);
        health.addToBase(addition);
    }
}
