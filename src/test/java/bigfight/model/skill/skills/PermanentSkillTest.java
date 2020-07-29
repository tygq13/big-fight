package bigfight.model.skill.skills;

import bigfight.model.skill.SkillData;
import bigfight.model.skill.SkillFactory;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.builder.WarriorTestUtil;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermanentSkillTest {
    private static SkillData defaultSkillData = new SkillData();
    private static SkillFactory defaultSkillFactory = new SkillFactory(defaultSkillData);


    @Test
    void born_as_strong_upgrade_correct() {
        final int STRENGTH = 100;
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(STRENGTH, 1,1,1,1);
        BornAsStrong bornAsStrong = (BornAsStrong) defaultSkillFactory.create(SkillIdentity.BORN_AS_STRONG);
        bornAsStrong.upgrade(testWarrior.getStrengthObj());
        int expectedBase = STRENGTH;
        int expectedTotal = expectedBase + (int) (expectedBase * bornAsStrong.getMultiply()) + bornAsStrong.getAddition();
        assertEquals(expectedBase, testWarrior.getStrengthObj().getBase());
        assertEquals(expectedTotal, testWarrior.getStrength());
    }

    @Test
    void agile_body_upgrade_correct() {
        final int AGILITY = 100;
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, AGILITY,1,1,1);
        AgileBody agileBody = (AgileBody) defaultSkillFactory.create(SkillIdentity.AGILE_BODY);
        agileBody.upgrade(testWarrior.getAgilityObj());
        int expectedBase = AGILITY;
        int expectedTotal = expectedBase + (int) (expectedBase * agileBody.getMultiply()) + agileBody.getAddition();
        assertEquals(expectedBase, testWarrior.getAgilityObj().getBase());
        assertEquals(expectedTotal, testWarrior.getBasicAttribute());
    }

    @Test
    void a_step_ahead_upgrade_correct() {
        final int SPEED = 100;
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1,SPEED,1,1);
        AStepAhead aStepAhead = (AStepAhead) defaultSkillFactory.create(SkillIdentity.A_STEP_AHEAD);
        aStepAhead.upgrade(testWarrior.getSpeedObj());
        int expectedBase = SPEED;
        int expectedTotal = expectedBase + (int) (expectedBase * aStepAhead.getMultiply()) + aStepAhead.getAddition();
        assertEquals(expectedBase, testWarrior.getSpeedObj().getBase());
        assertEquals(expectedTotal, testWarrior.getSpeed());
    }

    @Test
    void strong_physique_upgrade_correct() {
        final int HEALTH = 100;
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,HEALTH,1);
        StrongPhysique strongPhysique = (StrongPhysique) defaultSkillFactory.create(SkillIdentity.STRONG_PHYSIQUE);
        strongPhysique.upgrade(testWarrior.getHealthObj());
        int expectedBase = HEALTH;
        int expectedTotal = expectedBase + (int) (expectedBase * strongPhysique.getMultiply()) + strongPhysique.getAddition();
        assertEquals(expectedBase, testWarrior.getHealthObj().getBase());
        assertEquals(expectedTotal, testWarrior.getHealth());
    }

    @Test
    void balanced_growth_upgrade_correct() {
        final int BASE = 100;
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(BASE, BASE, BASE,1,1);
        BalancedGrowth balancedGrowth = (BalancedGrowth) defaultSkillFactory.create(SkillIdentity.BALANCED_GROWTH);
        balancedGrowth.upgrade(testWarrior.getStrengthObj(), testWarrior.getAgilityObj(), testWarrior.getSpeedObj());
        int expectedBase = BASE;
        int expectedTotal = expectedBase + (int) (expectedBase * balancedGrowth.getMultiply()) + balancedGrowth.getAddition();
        assertEquals(expectedBase, testWarrior.getStrengthObj().getBase());
        assertEquals(expectedTotal, testWarrior.getStrength());
        assertEquals(expectedBase, testWarrior.getAgilityObj().getBase());
        assertEquals(expectedTotal, testWarrior.getBasicAttribute());
        assertEquals(expectedBase, testWarrior.getSpeedObj().getBase());
        assertEquals(expectedTotal, testWarrior.getSpeed());
    }

    @Test
    void weapons_handy_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        WeaponsHandy weaponsHandy = (WeaponsHandy) defaultSkillFactory.create(SkillIdentity.WEAPONS_HANDY);
        double bigExpected = testWarrior.getAdvancedAttribute().bigExtraPercentageDamage + weaponsHandy.getExtra();
        double mediumExpected = testWarrior.getAdvancedAttribute().mediumExtraPercentageDamage + weaponsHandy.getExtra();
        double smallExpected = testWarrior.getAdvancedAttribute().smallExtraPercentageDamage + weaponsHandy.getExtra();
        double throwExpected = testWarrior.getAdvancedAttribute().throwExtraPercentageDamage + weaponsHandy.getExtra();

        weaponsHandy.upgrade(testWarrior.getAdvancedAttribute());
        assertEquals(bigExpected, testWarrior.getAdvancedAttribute().bigExtraPercentageDamage);
        assertEquals(mediumExpected, testWarrior.getAdvancedAttribute().mediumExtraPercentageDamage);
        assertEquals(smallExpected, testWarrior.getAdvancedAttribute().smallExtraPercentageDamage);
        assertEquals(throwExpected, testWarrior.getAdvancedAttribute().throwExtraPercentageDamage);
    }

    @Test
    void body_combat_skilled_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        BodyCombatSkilled bodyCombatSkilled = (BodyCombatSkilled) defaultSkillFactory.create(SkillIdentity.BODY_COMBAT_SKILLED);
        double expected = testWarrior.getAdvancedAttribute().unarmedExtraPercentageDamage + bodyCombatSkilled.getExtra();

        bodyCombatSkilled.upgrade(testWarrior.getAdvancedAttribute());
        assertEquals(expected, testWarrior.getAdvancedAttribute().unarmedExtraPercentageDamage);
    }

    @Test
    void sixth_sense_skilled_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        SixSense sixthSense = (SixSense) defaultSkillFactory.create(SkillIdentity.SIXTH_SENSE);
        double expected = testWarrior.getAdvancedAttribute().counterAttackChance + sixthSense.getChance();

        sixthSense.upgrade(testWarrior.getAdvancedAttribute());
        assertEquals(expected, testWarrior.getAdvancedAttribute().counterAttackChance);
    }

}
