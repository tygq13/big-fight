package bigfight.model.skill.skills;

import bigfight.model.skill.struct.SkillStruct;

public class WeaponRainstorm extends SkillModel{
    final int WEAPON_NUM = 3;

    public WeaponRainstorm(SkillStruct skillStruct) {
        super(skillStruct);
    }

    public int getNumOfWeapons(){
        return WEAPON_NUM;
    }
}
