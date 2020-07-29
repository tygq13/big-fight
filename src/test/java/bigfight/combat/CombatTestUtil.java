package bigfight.combat;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.FighterStatus;
import bigfight.model.skill.SkillData;
import bigfight.model.skill.SkillFactory;
import bigfight.model.skill.SkillManager;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.component.BasicAttribute;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.weapon.Weapon;
import bigfight.model.weapon.WeaponData;
import bigfight.model.weapon.WeaponFactory;
import bigfight.model.weapon.WeaponManager;
import bigfight.model.weapon.struct.Damage;
import bigfight.model.weapon.struct.WeaponType;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CombatTestUtil {
    private static WeaponData defaultWeaponData = new WeaponData();
    public static WeaponFactory defaultWeaponFactory = new WeaponFactory(defaultWeaponData);
    private static SkillData defaultSkillDate = new SkillData();
    public static SkillFactory defaultSkillFactory = new SkillFactory(defaultSkillDate);

    public static FighterStatus createSimpleFixedFighter() {
        Fighter modelFighter = mock(Fighter.class);
        when(modelFighter.getSpeed()).thenReturn(5);
        when(modelFighter.getAgility()).thenReturn(5);
        when(modelFighter.getStrength()).thenReturn(5);
        when(modelFighter.getHealth()).thenReturn(100);
        when(modelFighter.getAdvancedAttribute()).thenReturn(mock(AdvancedAttribute.class));
        when(modelFighter.getUnarmedDamage()).thenReturn(new Damage(10, 10));
        return new FighterStatus(modelFighter);
    }

    public static FighterStatus createSimpleFixedFighter(AdvancedAttribute advancedAttribute) {
        Fighter modelFighter = mock(Fighter.class);
        when(modelFighter.getSpeed()).thenReturn(5);
        when(modelFighter.getAgility()).thenReturn(5);
        when(modelFighter.getStrength()).thenReturn(5);
        when(modelFighter.getHealth()).thenReturn(100);
        when(modelFighter.getAdvancedAttribute()).thenReturn(advancedAttribute);
        when(modelFighter.getUnarmedDamage()).thenReturn(new Damage(10, 10));
        return new FighterStatus(modelFighter);
    }

    public static FighterStatus createCustomFighter(int speed, int agility, int strength, int health, int unarmed) {
        Fighter modelFighter = mock(Fighter.class);
        when(modelFighter.getSpeed()).thenReturn(speed);
        when(modelFighter.getAgility()).thenReturn(agility);
        when(modelFighter.getStrength()).thenReturn(strength);
        when(modelFighter.getHealth()).thenReturn(health);
        when(modelFighter.getAdvancedAttribute()).thenReturn(mock(AdvancedAttribute.class));
        when(modelFighter.getUnarmedDamage()).thenReturn(new Damage(unarmed, unarmed));
        return new FighterStatus(modelFighter);
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


    public static FighterStatus createDyingFighterWithApparentDeath() {
        SkillModel skill = defaultSkillFactory.create(SkillIdentity.APPARENT_DEATH);
        SkillManager skillManager = new SkillManager();
        skillManager.add(skill);
        BasicAttribute health = mock(BasicAttribute.class);
        when(health.value()).thenReturn(2);
        Warrior warrior = mock(Warrior.class);
        when(warrior.getSkillManager()).thenReturn(skillManager);
        when(warrior.getWeaponManager()).thenReturn(new WeaponManager());
        Fighter fighter = new Fighter(warrior);
        return new FighterStatus(fighter);
    }
}
