package bigfight.model.skill.struct;

import java.util.ArrayList;

public class SkillStructArray {
    private ArrayList<SkillStruct> skillStructArray;

    public SkillStructArray(SkillStruct one) {
        skillStructArray = new ArrayList<>(5);
        skillStructArray.add(one);
    }

    public SkillStruct withStar(int num) {
        return skillStructArray.get(num - 1);
    }
}
