package bigfight.combat;

import bigfight.combat.fighter.Fighter;
import bigfight.model.skill.SkillManager;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.WeaponManager;
import bigfight.model.weapon.struct.Damage;
import bigfight.model.weapon.struct.WeaponType;

import java.util.ArrayList;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CombatTestUtil {
    public static AdvancedAttribute DEFAULT_ADVANCED_ATTRIBUTE = new AdvancedAttribute();

    public static Fighter createSimpleFixedFighter() {
        Warrior mockWarrior = mock(Warrior.class);
        when(mockWarrior.getSpeed()).thenReturn(5);
        when(mockWarrior.getAgility()).thenReturn(5);
        when(mockWarrior.getStrength()).thenReturn(5);
        when(mockWarrior.getHealth()).thenReturn(100);
        when(mockWarrior.getLevel()).thenReturn(1);
        ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
        WeaponManager mockManager = mock(WeaponManager.class);
        when(mockManager.getWeaponList()).thenReturn(weaponList);
        when(mockWarrior.getWeaponManager()).thenReturn(mockManager);
        when(mockWarrior.getSkillManager()).thenReturn(mock(SkillManager.class));
        when(mockWarrior.getWeaponAttributeCopy()).thenReturn(new AdvancedAttribute());
        return new Fighter(mockWarrior);
    }

    public static Fighter createSimpleFixedFighter(AdvancedAttribute advancedAttribute) {
        Warrior mockWarrior = mock(Warrior.class);
        when(mockWarrior.getSpeed()).thenReturn(5);
        when(mockWarrior.getAgility()).thenReturn(5);
        when(mockWarrior.getStrength()).thenReturn(5);
        when(mockWarrior.getHealth()).thenReturn(100);
        ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
        WeaponManager mockManager = mock(WeaponManager.class);
        when(mockManager.getWeaponList()).thenReturn(weaponList);
        when(mockWarrior.getWeaponManager()).thenReturn(mockManager);
        when(mockWarrior.getSkillManager()).thenReturn(mock(SkillManager.class));
        when(mockWarrior.getWeaponAttributeCopy()).thenReturn(advancedAttribute);
        return new Fighter(mockWarrior);
    }

    public static Fighter createCustomFighter(int speed, int agility, int strength, int health) {
        Warrior mockWarrior = mock(Warrior.class);
        when(mockWarrior.getSpeed()).thenReturn(speed);
        when(mockWarrior.getAgility()).thenReturn(agility);
        when(mockWarrior.getStrength()).thenReturn(strength);
        when(mockWarrior.getHealth()).thenReturn(health);
        ArrayList<Weapon> weaponList = new ArrayList<Weapon>();
        WeaponManager mockManager = mock(WeaponManager.class);
        when(mockManager.getWeaponList()).thenReturn(weaponList);
        when(mockWarrior.getWeaponManager()).thenReturn(mockManager);
        when(mockWarrior.getSkillManager()).thenReturn(mock(SkillManager.class));
        when(mockWarrior.getWeaponAttributeCopy()).thenReturn(new AdvancedAttribute());
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
        SkillManager skillManager = new SkillManager();
        skillManager.add(skill);
        Warrior warrior = mock(Warrior.class);
        when(warrior.getSkillManager()).thenReturn(skillManager);
        when(warrior.getWeaponManager()).thenReturn(new WeaponManager());
        when(warrior.getWeaponAttributeCopy()).thenReturn(DEFAULT_ADVANCED_ATTRIBUTE);
        when(warrior.getHealth()).thenReturn(3);
        return new Fighter(warrior);
    }

    public static Fighter createLargeHealthFighterWithHakiProtect() {
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.HAKI_PROTECT);
        SkillManager skillManager = new SkillManager();
        skillManager.add(skill);
        Warrior warrior = mock(Warrior.class);
        when(warrior.getSkillManager()).thenReturn(skillManager);
        when(warrior.getWeaponManager()).thenReturn(new WeaponManager());
        when(warrior.getWeaponAttributeCopy()).thenReturn(DEFAULT_ADVANCED_ATTRIBUTE);
        when(warrior.getHealth()).thenReturn(100000000);
        return new Fighter(warrior);
    }

    public static Fighter createHealthyFighterWithSeaIsUnfathomable() {
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.SEA_REFLECT);
        SkillManager skillManager = new SkillManager();
        skillManager.add(skill);
        Warrior warrior = mock(Warrior.class);
        when(warrior.getSkillManager()).thenReturn(skillManager);
        when(warrior.getWeaponManager()).thenReturn(new WeaponManager());
        when(warrior.getWeaponAttributeCopy()).thenReturn(DEFAULT_ADVANCED_ATTRIBUTE);
        when(warrior.getHealth()).thenReturn(100000000);
        return new Fighter(warrior);
    }

    public static Fighter createFighterWithShadowMove() {
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.SHADOW_MOVE);
        SkillManager skillManager = new SkillManager();
        skillManager.add(skill);
        Warrior warrior = mock(Warrior.class);
        when(warrior.getSkillManager()).thenReturn(skillManager);
        when(warrior.getWeaponManager()).thenReturn(new WeaponManager());
        when(warrior.getWeaponAttributeCopy()).thenReturn(DEFAULT_ADVANCED_ATTRIBUTE);
        when(warrior.getHealth()).thenReturn(100000000);
        when(warrior.getSpeed()).thenReturn(5);
        return new Fighter(warrior);
    }

    public static Fighter createFighterWithMineWater(int health) {
        SkillModel skill = DEFAULT_SKILL_FACTORY.create(SkillIdentity.MINE_WATER);
        SkillManager skillManager = new SkillManager();
        skillManager.add(skill);
        Warrior warrior = mock(Warrior.class);
        when(warrior.getSkillManager()).thenReturn(skillManager);
        when(warrior.getWeaponManager()).thenReturn(new WeaponManager());
        when(warrior.getWeaponAttributeCopy()).thenReturn(DEFAULT_ADVANCED_ATTRIBUTE);
        when(warrior.getHealth()).thenReturn(health);
        when(warrior.getSpeed()).thenReturn(5);
        return new Fighter(warrior);
    }
}
