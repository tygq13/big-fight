package bigfight.model.skill.skills;

import bigfight.combat.CombatTestUtil;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.fighter.components.Health;
import bigfight.model.skill.skills.special.MineWater;
import bigfight.model.skill.struct.SkillIdentity;
import org.junit.jupiter.api.Test;

import static bigfight.model.skill.SkillFactoryTestUtil.DEFAULT_SKILL_FACTORY;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MineWaterTest {
    @Test
    void mine_water_activated() {
        final int HEALTH = 200;
        final double DAMAGE_PERCENTAGE = 0.5;
        Health health = new Health(HEALTH);
        health.update((int) (health.value() * DAMAGE_PERCENTAGE));
        MineWater mineWater = (MineWater) DEFAULT_SKILL_FACTORY.create(SkillIdentity.MINE_WATER);
        // test
        final int EXPECTED_HEALTH = health.value() + (int) (HEALTH * mineWater.getRegeneratePercentage());
        mineWater.updateHealth(health);
        assertEquals(EXPECTED_HEALTH, health.value());
    }

    @Test
    void mine_water_minimum_heal() {
        final int HEALTH = 50;
        final double DAMAGE_PERCENTAGE = 0.5;
        Health health = new Health(HEALTH);
        health.update((int) (health.value() * DAMAGE_PERCENTAGE));
        MineWater mineWater = (MineWater) DEFAULT_SKILL_FACTORY.create(SkillIdentity.MINE_WATER);
        // test
        final int EXPECTED_HEALTH = health.value() + (int) (mineWater.getRegeneratePercentage() * 100);
        mineWater.updateHealth(health);
        assertEquals(EXPECTED_HEALTH, health.value());
    }
}
