package bigfight.model.warrior.component;

import bigfight.model.skill.*;
import bigfight.model.weapon.*;
import bigfight.model.weapon.struct.WeaponIdentity;
import bigfight.model.skill.struct.SkillIdentity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EmpowermentFactory {
    private WeaponFactory weaponFactory;
    private SkillFactory skillFactory;

    public EmpowermentFactory(WeaponFactory weaponFactory, SkillFactory skillFactory) {
        this.weaponFactory = weaponFactory;
        this.skillFactory = skillFactory;
    }

    public Empowerment randomGetNew(ArrayList<Weapon> weaponList, Map<SkillIdentity, SkillModel> skillSet) {
        int weaponLeft = WeaponIdentity.getSize() - weaponList.size();
        int skillLeft = SkillIdentity.getSize() - skillSet.size();
        int total = weaponLeft + skillLeft;

        // in case of already gotten every weapons and skills
        if (total == 0) {
            return null;
        }

        Random rand = new Random();
        int lottery = rand.nextInt(total) + 1; //start from 1 up to total
        if (lottery > weaponLeft) {
            // create skill
            int luckyDraw = rand.nextInt(SkillIdentity.getSize());
            // ensure that there is still skill to gain
            while(skillLeft != 0) {
                //wrap around
                if (luckyDraw == SkillIdentity.getSize()) {
                    luckyDraw = 0;
                }
                if (!skillSet.containsKey(SkillIdentity.at(luckyDraw))) {
                    return new Empowerment(skillFactory.create(SkillIdentity.at(luckyDraw)));
                }
                luckyDraw += 1;
            }
        } else {
            // create weapon
            Map<WeaponIdentity, Weapon> weaponSet = new HashMap<>();
            for(Weapon weapon : weaponList) {
                weaponSet.put(weapon.getIdentity(), weapon);
            }
            int luckDraw = rand.nextInt(WeaponIdentity.getSize());
            // ensure that there is still weapon to gain
            while(weaponLeft != 0) {
                // wrap around
                if (luckDraw == WeaponIdentity.getSize()) {
                    luckDraw = 0;
                }
                if(!weaponSet.containsKey(WeaponIdentity.at(luckDraw))) {
                    return new Empowerment(weaponFactory.create(WeaponIdentity.at(luckDraw)));
                }
                luckDraw += 1;
            }
        }
        return null;
    }
}
