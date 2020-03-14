package bigfight.model.warrior.component;

import bigfight.model.skill.*;
import bigfight.model.skill.skills.SkillModel;
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

    private Empowerment createSkillAtRandom(int random, Map<SkillIdentity, SkillModel> skillSet ) {
        // find the next skill not possessed yet.
        while (skillSet.containsKey(SkillIdentity.at(random))) {
            random += 1;
            // wrap around
            if (random == SkillIdentity.getSize()) {
                random = 0;
            }
        }
        return new Empowerment(skillFactory.create(SkillIdentity.at(random)));
    }

    private Empowerment createWeaponAtRandom(int random, ArrayList<Weapon> weaponList) {
        // create weapon map
        Map<WeaponIdentity, Weapon> weaponSet = new HashMap<>();
        for(Weapon weapon : weaponList) {
            weaponSet.put(weapon.getIdentity(), weapon);
        }

        // get the next weapon not possessed yet.
        while (weaponSet.containsKey(WeaponIdentity.at(random))) {
            random += 1;
            // wrap around
            if (random == WeaponIdentity.getSize()) {
                random = 0;
            }
        }
        return new Empowerment(weaponFactory.create(WeaponIdentity.at(random)));
    }

    public Empowerment randomGetNew(ArrayList<Weapon> weaponList, Map<SkillIdentity, SkillModel> skillSet) {
        int weaponLeft = WeaponIdentity.getSize() - weaponList.size();
        int skillLeft = SkillIdentity.getSize() - skillSet.size();
        int total = weaponLeft + skillLeft;

        if (total != 0) {
            Random rand = new Random();
            // decide to create weapon or skill by proportion of skills and weapons
            int lottery = rand.nextInt(total) + 1; //start from 1 up to total
            if (lottery > weaponLeft) {
                // create skill
                if (skillLeft != 0) {
                    return createSkillAtRandom(rand.nextInt(SkillIdentity.getSize()), skillSet);
                }
            } else {
                if (weaponLeft != 0) {
                    return createWeaponAtRandom(rand.nextInt(WeaponIdentity.getSize()), weaponList);
                }
            }
        }

        // already got all the skills and weapons
        return null;
    }
}
