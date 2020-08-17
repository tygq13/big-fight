package bigfight.combat.fighter;

import bigfight.combat.fighter.buff.Buff;
import bigfight.combat.fighter.buff.Buffs;
import bigfight.combat.fighter.components.*;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.attr.BasicAttribute;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.attr.AdvancedAttribute;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.Damage;

public class Fighter {
    private String name;
    private BasicAttribute speed;
    private BasicAttribute strength;
    private BasicAttribute agility;
    private Health health;
    private int level;
    private AdvancedAttribute advancedAttribute;
    private Damage unarmedDamage;
    private Weapon holdingWeapon;
    private DisposableWeaponList weaponList;
    private ActiveSkillList activeSkillList;
    private SpecialSkillList specialSkillList;
    private FighterFlag fighterFlag;
    private Buffs buffs;
    private CombatSelector combatSelector;

    public Fighter(FightableWarrior warrior) {
        name = warrior.getName();
        speed = warrior.getSpeed();
        strength = warrior.getStrength();
        agility = warrior.getAgility();
        health = new Health(warrior.getHealthValue());
        level = warrior.getLevel();
        advancedAttribute = warrior.getWeaponAttribute();
        unarmedDamage = warrior.getUnarmedDamage();
        weaponList = warrior.getDisposableWeapons();
        activeSkillList = warrior.getActiveSkills();
        specialSkillList = warrior.getSpecialSkills();
        fighterFlag = new FighterFlag();
        buffs = new Buffs();
        combatSelector = new CombatSelector(activeSkillList, specialSkillList, weaponList, fighterFlag);
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

    public BasicAttribute getSpeedObj() {
        return speed;
    }

    public int getStrength() {
        return strength.value();
    }

    public int getAgility() {
        return agility.value();
    }

    public int getHealth() {
        return health.value();
    }

    public Health getHealthObj() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public void updateHealth(int value) {
        health.update(value);
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

    public CombatSelector getCombatSelector() {
        return combatSelector;
    }

    public void addBuff(Buff buff) {
        buffs.add(buff);
    }

    public void newRoundUpdate() {
        // buff update
        buffs.invoke(this);

        // fighter flag update
        fighterFlag.rounds += 1;
        if (fighterFlag.noSelectSkill > 0) {
            fighterFlag.noSelectSkill -= 1;
        }
    }
}
