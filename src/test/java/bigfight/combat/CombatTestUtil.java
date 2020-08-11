package bigfight.combat;

import bigfight.combat.fighter.DisposableWeaponList;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.SpecialSkillList;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.special.*;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.builder.FightableWarrior;
import bigfight.model.warrior.component.BasicAttribute;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.Damage;
import bigfight.model.weapon.struct.WeaponType;


import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CombatTestUtil {
    public static AdvancedAttribute DEFAULT_ADVANCED_ATTRIBUTE = new AdvancedAttribute();

    public static Fighter createSimpleFixedFighter() {
        FightableWarrior mockWarrior = mock(FightableWarrior.class);
        when(mockWarrior.getSpeed()).thenReturn(new BasicAttribute(5));
        when(mockWarrior.getAgility()).thenReturn(new BasicAttribute(5));
        when(mockWarrior.getStrength()).thenReturn(new BasicAttribute(5));
        when(mockWarrior.getHealthValue()).thenReturn(100);
        when(mockWarrior.getLevel()).thenReturn(1);
        when(mockWarrior.getWeaponAttributeCopy()).thenReturn(new AdvancedAttribute());
        when(mockWarrior.getUnarmedDamage()).thenReturn(new Damage(10, 10));
        return new Fighter(mockWarrior);
    }

    public static Fighter createSimpleFixedFighter(AdvancedAttribute advancedAttribute) {
        FightableWarrior mockWarrior = mock(FightableWarrior.class);
        when(mockWarrior.getSpeed()).thenReturn(new BasicAttribute(5));
        when(mockWarrior.getAgility()).thenReturn(new BasicAttribute(5));
        when(mockWarrior.getStrength()).thenReturn(new BasicAttribute(5));
        when(mockWarrior.getHealthValue()).thenReturn(100);
        when(mockWarrior.getWeaponAttributeCopy()).thenReturn(advancedAttribute);
        when(mockWarrior.getUnarmedDamage()).thenReturn(new Damage(10, 10));
        return new Fighter(mockWarrior);
    }

    public static Fighter createCustomFighter(int speed, int agility, int strength, int health) {
        FightableWarrior mockWarrior = mock(FightableWarrior.class);
        when(mockWarrior.getSpeed()).thenReturn(new BasicAttribute(speed));
        when(mockWarrior.getAgility()).thenReturn(new BasicAttribute(agility));
        when(mockWarrior.getStrength()).thenReturn(new BasicAttribute(strength));
        when(mockWarrior.getHealthValue()).thenReturn(health);
        when(mockWarrior.getWeaponAttributeCopy()).thenReturn(new AdvancedAttribute());
        when(mockWarrior.getUnarmedDamage()).thenReturn(new Damage(10, 10));
        return new Fighter(mockWarrior);
    }

    public static Empowerment createUnarmedEmpowerment() {
        Empowerment empowerment = mock(Empowerment.class);
        when(empowerment.getWeapon()).thenReturn(null);
        when(empowerment.getSkill()).thenReturn(null);
        return empowerment;
    }

    public static Weapon createUsableWeapon() {
        Weapon weapon = mock(Weapon.class);
        Damage damage = mock(Damage.class);
        // maybe use Law of Demeter and void deep methods call is a better choice?
        when(damage.lower()).thenReturn(0);
        when(damage.higher()).thenReturn(0);
        when(weapon.getDamage()).thenReturn(damage);
        when(weapon.getType()).thenReturn(WeaponType.BIG);
        return weapon;
    }

    public static Weapon createBigWeapon() {
        Weapon weapon = mock(Weapon.class);
        Damage damage = mock(Damage.class);
        when(damage.lower()).thenReturn(10);
        when(damage.higher()).thenReturn(10);
        when(weapon.getDamage()).thenReturn(damage);
        when(weapon.getType()).thenReturn(WeaponType.BIG);
        return weapon;
    }


    public static Fighter createDyingFighterWithApparentDeath() {
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.APPARENT_DEATH);
        SpecialSkillList specialSkillList = new SpecialSkillList();
        specialSkillList.add(skill);
        FightableWarrior warrior = mock(FightableWarrior.class);
        when(warrior.getSpecialSkills()).thenReturn(specialSkillList);
        when(warrior.getDisposableWeapons()).thenReturn(mock(DisposableWeaponList.class));
        when(warrior.getWeaponAttributeCopy()).thenReturn(DEFAULT_ADVANCED_ATTRIBUTE);
        when(warrior.getHealthValue()).thenReturn(3);
        when(warrior.getSpeed()).thenReturn(new BasicAttribute(5));
        when(warrior.getAgility()).thenReturn(new BasicAttribute(5));
        when(warrior.getStrength()).thenReturn(new BasicAttribute(5));
        return new Fighter(warrior);
    }

    public static Fighter createLargeHealthFighterWithHakiProtect() {
        SpecialSkill skill = (SpecialSkill) DEFAULT_SKILL_FACTORY.create(SkillIdentity.HAKI_PROTECT);
        SpecialSkillList specialSkillList = new SpecialSkillList();
        specialSkillList.add(skill.getUsableInstance());
        FightableWarrior warrior = mock(FightableWarrior.class);
        when(warrior.getSpecialSkills()).thenReturn(specialSkillList);
        when(warrior.getDisposableWeapons()).thenReturn(mock(DisposableWeaponList.class));
        when(warrior.getWeaponAttributeCopy()).thenReturn(DEFAULT_ADVANCED_ATTRIBUTE);
        when(warrior.getHealthValue()).thenReturn(100000000);
        when(warrior.getSpeed()).thenReturn(new BasicAttribute(5));
        when(warrior.getAgility()).thenReturn(new BasicAttribute(5));
        when(warrior.getStrength()).thenReturn(new BasicAttribute(5));
        return new Fighter(warrior);
    }

    public static Fighter createHealthyFighterWithSeaIsUnfathomable() {
        SpecialSkill skill = (SpecialSkill) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SEA_REFLECT);
        SpecialSkillList specialSkillList = new SpecialSkillList();
        specialSkillList.add(skill.getUsableInstance());
        FightableWarrior warrior = mock(FightableWarrior.class);
        when(warrior.getSpecialSkills()).thenReturn(specialSkillList);
        when(warrior.getDisposableWeapons()).thenReturn(mock(DisposableWeaponList.class));
        when(warrior.getWeaponAttributeCopy()).thenReturn(DEFAULT_ADVANCED_ATTRIBUTE);
        when(warrior.getHealthValue()).thenReturn(100000000);
        when(warrior.getSpeed()).thenReturn(new BasicAttribute(5));
        when(warrior.getAgility()).thenReturn(new BasicAttribute(5));
        when(warrior.getStrength()).thenReturn(new BasicAttribute(5));
        return new Fighter(warrior);
    }

    public static Fighter createFighterWithShadowMove() {
        SpecialSkill skill = (SpecialSkill) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SHADOW_MOVE);
        SpecialSkillList specialSkillList = new SpecialSkillList();
        specialSkillList.add(skill.getUsableInstance());
        FightableWarrior warrior = mock(FightableWarrior.class);
        when(warrior.getSpecialSkills()).thenReturn(specialSkillList);
        when(warrior.getDisposableWeapons()).thenReturn(mock(DisposableWeaponList.class));
        when(warrior.getWeaponAttributeCopy()).thenReturn(DEFAULT_ADVANCED_ATTRIBUTE);
        when(warrior.getHealthValue()).thenReturn(100000000);
        when(warrior.getSpeed()).thenReturn(new BasicAttribute(5));
        when(warrior.getSpeed()).thenReturn(new BasicAttribute(5));
        when(warrior.getAgility()).thenReturn(new BasicAttribute(5));
        when(warrior.getStrength()).thenReturn(new BasicAttribute(5));
        return new Fighter(warrior);
    }

    public static Fighter createFighterWithMineWater(int health) {
        SpecialSkill skill = (SpecialSkill) DEFAULT_SKILL_FACTORY.create(SkillIdentity.MINE_WATER);
        SpecialSkillList specialSkillList = new SpecialSkillList();
        specialSkillList.add(skill.getUsableInstance());
        FightableWarrior warrior = mock(FightableWarrior.class);
        when(warrior.getSpecialSkills()).thenReturn(specialSkillList);
        when(warrior.getDisposableWeapons()).thenReturn(mock(DisposableWeaponList.class));
        when(warrior.getWeaponAttributeCopy()).thenReturn(DEFAULT_ADVANCED_ATTRIBUTE);
        when(warrior.getHealthValue()).thenReturn(health);
        when(warrior.getSpeed()).thenReturn(new BasicAttribute(5));
        when(warrior.getAgility()).thenReturn(new BasicAttribute(5));
        when(warrior.getStrength()).thenReturn(new BasicAttribute(5));
        return new Fighter(warrior);
    }
}
