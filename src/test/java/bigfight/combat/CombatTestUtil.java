package bigfight.combat;

import bigfight.combat.fighter.*;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.special.*;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.struct.Damage;
import bigfight.model.weapon.struct.WeaponType;


import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CombatTestUtil {

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

    public static Weapon createThrowWeapon() {
        Weapon weapon = mock(Weapon.class);
        Damage damage = mock(Damage.class);
        when(damage.lower()).thenReturn(10);
        when(damage.higher()).thenReturn(10);
        when(weapon.getDamage()).thenReturn(damage);
        when(weapon.getType()).thenReturn(WeaponType.THROW);
        return weapon;
    }

    public static SkillModel createAnySkill() {
        return DEFAULT_SKILL_FACTORY.create(SkillIdentity.ROAR);
    }


    public static Fighter createDyingFighterWithApparentDeath() {
        return new FighterBuilderTestUtil().
                withSkill(DEFAULT_SKILL_FACTORY.create(SkillIdentity.APPARENT_DEATH)).
                withHealth(1).
                build();
    }

    public static Fighter createLargeHealthFighterWithHakiProtect() {
        SpecialSkill skill = (SpecialSkill) DEFAULT_SKILL_FACTORY.create(SkillIdentity.HAKI_PROTECT);
        SkillModel usableSkill = skill.getUsableInstance();
        return new FighterBuilderTestUtil().
                withSkill(usableSkill).
                withHealth(10000).
                build();
    }

    public static Fighter createHealthyFighterWithSeaIsUnfathomable() {
        SpecialSkill skill = (SpecialSkill) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SEA_REFLECT);
        SkillModel usableSkill = skill.getUsableInstance();
        return new FighterBuilderTestUtil().
                withSkill(usableSkill).
                withHealth(10000).
                build();
    }

    public static Fighter createFighterWithShadowMove() {
        SpecialSkill skill = (SpecialSkill) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SHADOW_MOVE);
        SkillModel usableSkill = skill.getUsableInstance();
        return new FighterBuilderTestUtil().
                withSkill(usableSkill).
                withHealth(10000).
                build();
    }

    public static Fighter createFighterWithMineWater(int health) {
        SpecialSkill skill = (SpecialSkill) DEFAULT_SKILL_FACTORY.create(SkillIdentity.MINE_WATER);
        SkillModel usableSkill = skill.getUsableInstance();
        return new FighterBuilderTestUtil().
                withSkill(usableSkill).
                withHealth(health).
                build();
    }
}
