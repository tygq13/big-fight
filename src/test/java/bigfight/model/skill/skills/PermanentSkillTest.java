package bigfight.model.skill.skills;

import bigfight.model.skill.skills.permanent.*;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.builder.WarriorTestUtil;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermanentSkillTest {

    @Test
    void born_as_strong_upgrade_correct() {
        final int STRENGTH = 100;
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(STRENGTH, 1,1,1,1);
        BornAsStrong bornAsStrong = (BornAsStrong) DEFAULT_SKILL_FACTORY.create(SkillIdentity.BORN_AS_STRONG);
        bornAsStrong.upgrade(testWarrior.getAttribute());
        int expectedBase = STRENGTH;
        int expectedTotal = expectedBase + (int) (expectedBase * bornAsStrong.getMultiply()) + bornAsStrong.getAddition();
        assertEquals(expectedBase, testWarrior.getStrengthObj().getBase());
        assertEquals(expectedTotal, testWarrior.getStrengthObj().value());
    }

    @Test
    void agile_body_upgrade_correct() {
        final int AGILITY = 100;
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, AGILITY,1,1,1);
        AgileBody agileBody = (AgileBody) DEFAULT_SKILL_FACTORY.create(SkillIdentity.AGILE_BODY);
        agileBody.upgrade(testWarrior.getAttribute());
        int expectedBase = AGILITY;
        int expectedTotal = expectedBase + (int) (expectedBase * agileBody.getMultiply()) + agileBody.getAddition();
        assertEquals(expectedBase, testWarrior.getAgilityObj().getBase());
        assertEquals(expectedTotal, testWarrior.getAgility());
    }

    @Test
    void a_step_ahead_upgrade_correct() {
        final int SPEED = 100;
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1,SPEED,1,1);
        AStepAhead aStepAhead = (AStepAhead) DEFAULT_SKILL_FACTORY.create(SkillIdentity.A_STEP_AHEAD);
        aStepAhead.upgrade(testWarrior.getAttribute());
        int expectedBase = SPEED;
        int expectedTotal = expectedBase + (int) (expectedBase * aStepAhead.getMultiply()) + aStepAhead.getAddition();
        assertEquals(expectedBase, testWarrior.getSpeedObj().getBase());
        assertEquals(expectedTotal, testWarrior.getSpeed());
    }

    @Test
    void strong_physique_upgrade_correct() {
        final int HEALTH = 100;
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,HEALTH,1);
        StrongPhysique strongPhysique = (StrongPhysique) DEFAULT_SKILL_FACTORY.create(SkillIdentity.STRONG_PHYSIQUE);
        strongPhysique.upgrade(testWarrior.getAttribute());
        int expectedBase = HEALTH;
        int expectedTotal = expectedBase + (int) (expectedBase * strongPhysique.getMultiply()) + strongPhysique.getAddition();
        assertEquals(expectedBase, testWarrior.getHealthObj().getBase());
        assertEquals(expectedTotal, testWarrior.getHealth());
    }

    @Test
    void balanced_growth_upgrade_correct() {
        final int BASE = 100;
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(BASE, BASE, BASE,1,1);
        BalancedGrowth balancedGrowth = (BalancedGrowth) DEFAULT_SKILL_FACTORY.create(SkillIdentity.BALANCED_GROWTH);
        balancedGrowth.upgrade(testWarrior.getAttribute());
        int expectedBase = BASE;
        int expectedTotal = expectedBase + (int) (expectedBase * balancedGrowth.getMultiply()) + balancedGrowth.getAddition();
        assertEquals(expectedBase, testWarrior.getStrengthObj().getBase());
        assertEquals(expectedTotal, testWarrior.getStrength());
        assertEquals(expectedBase, testWarrior.getAgilityObj().getBase());
        assertEquals(expectedTotal, testWarrior.getAgility());
        assertEquals(expectedBase, testWarrior.getSpeedObj().getBase());
        assertEquals(expectedTotal, testWarrior.getSpeed());
    }

    @Test
    void weapons_handy_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        WeaponsHandy weaponsHandy = (WeaponsHandy) DEFAULT_SKILL_FACTORY.create(SkillIdentity.WEAPONS_HANDY);
        double bigExpected = testWarrior.getAdvancedAttribute().bigExtraPercentageDamage + weaponsHandy.getExtra();
        double mediumExpected = testWarrior.getAdvancedAttribute().mediumExtraPercentageDamage + weaponsHandy.getExtra();
        double smallExpected = testWarrior.getAdvancedAttribute().smallExtraPercentageDamage + weaponsHandy.getExtra();
        double throwExpected = testWarrior.getAdvancedAttribute().throwExtraPercentageDamage + weaponsHandy.getExtra();

        weaponsHandy.upgrade(testWarrior.getAttribute());
        assertEquals(bigExpected, testWarrior.getAdvancedAttribute().bigExtraPercentageDamage);
        assertEquals(mediumExpected, testWarrior.getAdvancedAttribute().mediumExtraPercentageDamage);
        assertEquals(smallExpected, testWarrior.getAdvancedAttribute().smallExtraPercentageDamage);
        assertEquals(throwExpected, testWarrior.getAdvancedAttribute().throwExtraPercentageDamage);
    }

    @Test
    void body_combat_skilled_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        BodyCombatSkilled bodyCombatSkilled = (BodyCombatSkilled) DEFAULT_SKILL_FACTORY.create(SkillIdentity.BODY_COMBAT_SKILLED);
        double expected = testWarrior.getAdvancedAttribute().unarmedExtraPercentageDamage + bodyCombatSkilled.getExtra();

        bodyCombatSkilled.upgrade(testWarrior.getAttribute());
        assertEquals(expected, testWarrior.getAdvancedAttribute().unarmedExtraPercentageDamage);
    }

    @Test
    void sixth_sense_skilled_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        SixSense sixthSense = (SixSense) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SIXTH_SENSE);
        double expected = testWarrior.getAdvancedAttribute().counterAttackChance + sixthSense.getChance();

        sixthSense.upgrade(testWarrior.getAttribute());
        assertEquals(expected, testWarrior.getAdvancedAttribute().counterAttackChance);
    }

    @Test
    void stone_skin_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        StoneSkin stoneSkin = (StoneSkin) DEFAULT_SKILL_FACTORY.create(SkillIdentity.STONE_SKIN);
        double bigExpected = testWarrior.getAdvancedAttribute().antiBigExtraPercentageDamage + stoneSkin.getExtra();
        double mediumExpected = testWarrior.getAdvancedAttribute().antiMediumExtraPercentageDamage + stoneSkin.getExtra();
        double smallExpected = testWarrior.getAdvancedAttribute().antiSmallExtraPercentageDamage + stoneSkin.getExtra();
        double throwExpected = testWarrior.getAdvancedAttribute().antiThrowExtraPercentageDamage + stoneSkin.getExtra();
        double unarmedExpected = testWarrior.getAdvancedAttribute().antiUnarmedExtraPercentageDamage + stoneSkin.getExtra();

        stoneSkin.upgrade(testWarrior.getAttribute());
        assertEquals(bigExpected, testWarrior.getAdvancedAttribute().antiBigExtraPercentageDamage);
        assertEquals(mediumExpected, testWarrior.getAdvancedAttribute().antiMediumExtraPercentageDamage);
        assertEquals(smallExpected, testWarrior.getAdvancedAttribute().antiSmallExtraPercentageDamage);
        assertEquals(throwExpected, testWarrior.getAdvancedAttribute().antiThrowExtraPercentageDamage);
        assertEquals(unarmedExpected, testWarrior.getAdvancedAttribute().antiUnarmedExtraPercentageDamage);
    }

    @Test
    void rippleless_steps_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        RipplelessSteps ripplelessSteps = (RipplelessSteps) DEFAULT_SKILL_FACTORY.create(SkillIdentity.RIPPLESLESS_STEPS);
        double bigExpected = testWarrior.getAdvancedAttribute().bigEvasionRate + ripplelessSteps.getEvasion();
        double mediumExpected = testWarrior.getAdvancedAttribute().mediumEvasionRate + ripplelessSteps.getEvasion();
        double smallExpected = testWarrior.getAdvancedAttribute().smallEvasionRate + ripplelessSteps.getEvasion();
        double throwExpected = testWarrior.getAdvancedAttribute().throwEvasionRate + ripplelessSteps.getEvasion();
        double unarmedExpected = testWarrior.getAdvancedAttribute().unarmedEvasionRate + ripplelessSteps.getEvasion();
        double skillExpected = testWarrior.getAdvancedAttribute().skillEvasionRate + ripplelessSteps.getEvasion();

        ripplelessSteps.upgrade(testWarrior.getAttribute());
        assertEquals(bigExpected, testWarrior.getAdvancedAttribute().bigEvasionRate);
        assertEquals(mediumExpected, testWarrior.getAdvancedAttribute().mediumEvasionRate);
        assertEquals(smallExpected, testWarrior.getAdvancedAttribute().smallEvasionRate);
        assertEquals(throwExpected, testWarrior.getAdvancedAttribute().throwEvasionRate);
        assertEquals(unarmedExpected, testWarrior.getAdvancedAttribute().unarmedEvasionRate);
        assertEquals(skillExpected, testWarrior.getAdvancedAttribute().skillEvasionRate);
    }

    @Test
    void heavy_usual_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        HeavyUsual heavyUsual = (HeavyUsual) DEFAULT_SKILL_FACTORY.create(SkillIdentity.HEAVY_USUAL);
        double bigExpected = testWarrior.getAdvancedAttribute().bigExtraPercentageDamage + heavyUsual.getExtraDamage();

        heavyUsual.upgrade(testWarrior.getAttribute());
        assertEquals(bigExpected, testWarrior.getAdvancedAttribute().bigExtraPercentageDamage);
    }

    @Test
    void opt_for_lightness_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        OptForLightness optForLightness = (OptForLightness) DEFAULT_SKILL_FACTORY.create(SkillIdentity.OPT_FOR_LIGHTNESS);
        double smallExpected = testWarrior.getAdvancedAttribute().smallExtraPercentageDamage + optForLightness.getSmallMeiumExtraDamage();
        double mediumExpected = testWarrior.getAdvancedAttribute().mediumExtraPercentageDamage + optForLightness.getSmallMeiumExtraDamage();
        double bigExpected = testWarrior.getAdvancedAttribute().bigEvasionRate + optForLightness.getBigEvasion();

        optForLightness.upgrade(testWarrior.getAttribute());
        assertEquals(smallExpected, testWarrior.getAdvancedAttribute().smallExtraPercentageDamage);
        assertEquals(mediumExpected, testWarrior.getAdvancedAttribute().mediumExtraPercentageDamage);
        assertEquals(bigExpected, testWarrior.getAdvancedAttribute().bigEvasionRate);
    }

    @Test
    void assassins_technique_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        AssassinsTechnique assassinsTechnique = (AssassinsTechnique) DEFAULT_SKILL_FACTORY.create(SkillIdentity.ASSASSINS_TECHNIQUE);
        double smallExpected = testWarrior.getAdvancedAttribute().smallHitRate + assassinsTechnique.getHitRate();
        double mediumExpected = testWarrior.getAdvancedAttribute().throwHitRate + assassinsTechnique.getHitRate();

        assassinsTechnique.upgrade(testWarrior.getAttribute());
        assertEquals(smallExpected, testWarrior.getAdvancedAttribute().smallHitRate);
        assertEquals(mediumExpected, testWarrior.getAdvancedAttribute().throwHitRate);
    }

    @Test
    void valiant_force_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        ValiantForce valiantForce = (ValiantForce) DEFAULT_SKILL_FACTORY.create(SkillIdentity.VALIANT_FORCE);
        double smallExpected = testWarrior.getAdvancedAttribute().bigHitRate + valiantForce.getHitRate();

        valiantForce.upgrade(testWarrior.getAttribute());
        assertEquals(smallExpected, testWarrior.getAdvancedAttribute().bigHitRate);
    }

    @Test
    void sword_art_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        SwordArt swordArt = (SwordArt) DEFAULT_SKILL_FACTORY.create(SkillIdentity.SWORD_ART);
        double smallExpected = testWarrior.getAdvancedAttribute().mediumHitRate + swordArt.getHitRate();

        swordArt.upgrade(testWarrior.getAttribute());
        assertEquals(smallExpected, testWarrior.getAdvancedAttribute().mediumHitRate);
    }

    @Test
    void tai_chi_upgrade_correct() {
        Warrior testWarrior = WarriorTestUtil.createCustomAttributeWarrior(1, 1, 1,1,1);
        TaiChi taiChi = (TaiChi) DEFAULT_SKILL_FACTORY.create(SkillIdentity.TAI_CHI);
        double smallExpected = testWarrior.getAdvancedAttribute().unarmedHitRate + taiChi.getHitRate();

        taiChi.upgrade(testWarrior.getAttribute());
        assertEquals(smallExpected, testWarrior.getAdvancedAttribute().unarmedHitRate);
    }

}
