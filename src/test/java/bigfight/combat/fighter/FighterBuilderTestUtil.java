package bigfight.combat.fighter;

import bigfight.combat.fighter.components.ActiveSkillList;
import bigfight.combat.fighter.components.DisposableWeaponList;
import bigfight.combat.fighter.components.SpecialSkillList;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.skill.struct.SkillType;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.component.BasicAttribute;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.Damage;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FighterBuilderTestUtil {
    private BasicAttribute strength = new BasicAttribute(5);
    private BasicAttribute agility = new BasicAttribute(5);
    private BasicAttribute speed = new BasicAttribute(5);
    private int health = 100;
    private int level = 1;
    private AdvancedAttribute advancedAttribute = new AdvancedAttribute();;
    private Damage unarmedDamage = new Damage(10, 10);
    private DisposableWeaponList disposableWeaponList = new DisposableWeaponList();
    private ActiveSkillList activeSkillList = new ActiveSkillList();
    private SpecialSkillList specialSkillList = new SpecialSkillList();

    public FighterBuilderTestUtil withStrength(int strength) {
        this.strength = new BasicAttribute(strength);
        return this;
    }

    public FighterBuilderTestUtil withAgility(int agility) {
        this.agility = new BasicAttribute(agility);
        return this;
    }

    public FighterBuilderTestUtil withSpeed(int speed) {
        this.speed = new BasicAttribute(speed);
        return this;
    }

    public FighterBuilderTestUtil withHealth(int health) {
        this.health = health;
        return this;
    }

    public FighterBuilderTestUtil withSkill(SkillModel skill) {
        if (skill.getType() == SkillType.SPECIAL) {
            specialSkillList.add(skill);
        } else if (skill.getType() == SkillType.ACTIVE) {
            activeSkillList.add(skill);
        }
        return this;
    }

    public FighterBuilderTestUtil withSkill(SkillIdentity skill) {
        SkillModel skillModel = DEFAULT_SKILL_FACTORY.create(skill);
        if (skillModel.getType() == SkillType.SPECIAL) {
            specialSkillList.add(skillModel);
        } else if (skillModel.getType() == SkillType.ACTIVE) {
            activeSkillList.add(skillModel);
        }
        return this;
    }

    public FighterBuilderTestUtil withWeapon(Weapon weapon) {
        disposableWeaponList.add(weapon);
        return this;
    }

    public FighterBuilderTestUtil withAdvancedAttribute(AdvancedAttribute advancedAttribute) {
        this.advancedAttribute = advancedAttribute;
        return this;
    }

    public Fighter build() {
        FightableAdapter fightableAdapter = mock(FightableAdapter.class);
        when(fightableAdapter.getStrength()).thenReturn(strength);
        when(fightableAdapter.getAgility()).thenReturn(agility);
        when(fightableAdapter.getSpeed()).thenReturn(speed);
        when(fightableAdapter.getHealthValue()).thenReturn(health);
        when(fightableAdapter.getLevel()).thenReturn(level);
        when(fightableAdapter.getWeaponAttribute()).thenReturn(advancedAttribute);
        when(fightableAdapter.getDisposableWeapons()).thenReturn(disposableWeaponList);
        when(fightableAdapter.getActiveSkills()).thenReturn(activeSkillList);
        when(fightableAdapter.getSpecialSkills()).thenReturn(specialSkillList);
        when(fightableAdapter.getUnarmedDamage()).thenReturn(unarmedDamage);
        return new Fighter(fightableAdapter);
    }
}
