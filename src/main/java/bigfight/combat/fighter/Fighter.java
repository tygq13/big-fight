package bigfight.combat.fighter;

import bigfight.combat.util.CombatRandom;
import bigfight.data.DataConfig;
import bigfight.model.skill.skills.special.MineWater;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.special.ShadowMoveUsable;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.builder.FightableWarrior;
import bigfight.model.warrior.component.BasicAttribute;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.Damage;

public class Fighter {
    private String name;
    private BasicAttribute speed;
    private BasicAttribute strength;
    private BasicAttribute agility;
    private int health;
    private int maxHealth;
    private int level;
    private AdvancedAttribute advancedAttribute;
    private Damage unarmedDamage;
    private Weapon holdingWeapon;
    private DisposableWeaponList weaponList;
    private ActiveSkillList activeSkillList;
    private SpecialSkillList specialSkillList;
    private FighterFlag fighterFlag;

    public Fighter(FightableWarrior warrior) {
        name = warrior.getName();
        speed = warrior.getSpeed();
        strength = warrior.getStrength();
        agility = warrior.getAgility();
        health = warrior.getHealthValue();
        maxHealth = warrior.getHealthValue();
        level = warrior.getLevel();
        advancedAttribute = warrior.getWeaponAttributeCopy();
        unarmedDamage = warrior.getUnarmedDamage();
        weaponList = warrior.getDisposableWeapons();
        activeSkillList = warrior.getActiveSkills();
        specialSkillList = warrior.getSpecialSkills();
        fighterFlag = new FighterFlag();
    }

    public void changeWeapon(Empowerment empowerment) {
        holdingWeapon = empowerment.getWeapon();
    }

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed.value();
    }

    public int getStrength() {
        return strength.value();
    }

    public int getAgility() {
        return agility.value();
    }

    public int getHealth() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public void updateHealth(int newHealth) {
        health = newHealth <= maxHealth ? newHealth : maxHealth;
    }

    public Damage getUnarmedDamage() {
        return unarmedDamage;
    }

    public Weapon getHoldingWeapon() {
        return holdingWeapon;
    }

    public AdvancedAttribute getAdvancedAttribute() {
        return advancedAttribute;
    }

    public FighterFlag getFighterFlag() {
        return fighterFlag;
    }

    public int getWeaponSize() {
        return weaponList.size();
    }

    public boolean hasSkill(SkillIdentity identity) {
        return specialSkillList != null && specialSkillList.contains(identity);
    }

    public SkillModel getSkill(SkillIdentity identity) {
        return specialSkillList.get(identity);
    }

    // In this implementation, the chance is not exactly equal to the intended invocation chance
    // nice fun to have, isn't it?
    public void selectAuxiliarySkill(CombatRandom random) {
        int totalSize = weaponList.size() + specialSkillList.size() + activeSkillList.size();
        specialSkillList.select(fighterFlag, random, totalSize);
    }

    public Empowerment selectEmpowerment(CombatRandom random) {
        int totalSize = weaponList.size() + activeSkillList.size();
        if (totalSize == 0 || random.selectUnarmed() < DataConfig.UNARMED_CHANCE) {
            // unarmed attack
            return new Empowerment((Weapon) null);
        }
        int weaponOrSkill = random.selectWeaponOrSkill(totalSize);
        if (weaponOrSkill < weaponList.size() && weaponList.size() > 0) {
            return weaponList.select(random);
        } else {
            // create skills
            return activeSkillList.select(random);
        }
    }

    public void updateStatusByFlag() {
        if (fighterFlag.shadowMoveFlag) {
            // todo: throw exception if doesn't have
            // todo: multiply by base speed instead of speed
            ShadowMoveUsable shadowMove = (ShadowMoveUsable) specialSkillList.get(SkillIdentity.SHADOW_MOVE);
            if (fighterFlag.shadowMoveRound == 0) {
                fighterFlag.shadowMoveFlag = false;
                speed.assignBase((int) (speed.getBase() / (1 + shadowMove.getSpeedMultiply())));
                shadowMove.unInvoke(advancedAttribute);
            } else {
                fighterFlag.shadowMoveRound -= 1;
                speed.assignBase((int) (speed.getBase() * (1 + shadowMove.getSpeedMultiply())));
                shadowMove.invoke(advancedAttribute);
            }
        }
        if (fighterFlag.mineWaterFlag) {
            MineWater mineWater = (MineWater) specialSkillList.get(SkillIdentity.MINE_WATER);
            int minimum = (int) (mineWater.getRegeneratePercentage() * 100);
            int regen = minimum > maxHealth * mineWater.getRegeneratePercentage() ? minimum : (int) (maxHealth * mineWater.getRegeneratePercentage());
            updateHealth(health + regen);
        }
    }
}
