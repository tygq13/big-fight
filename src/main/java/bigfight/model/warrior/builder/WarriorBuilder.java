package bigfight.model.warrior.builder;

import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.component.Agility;
import bigfight.model.warrior.component.Speed;
import bigfight.model.warrior.component.Strength;
import bigfight.model.weapon.WeaponManager;

public class WarriorBuilder {
    public static NameStep stepBuilder() {
        return new WarriorSteps();
    }

    public interface NameStep {
        StrengthStep name(String name);
    }

    public interface StrengthStep {
        AgilityStep strength(Strength strength);
    }

    public interface  AgilityStep {
        SpeedStep agility(Agility agility);
    }

    public interface SpeedStep {
        HealthStep speed(Speed speed);
    }

    public interface HealthStep {
        WeaponManagerStep health(int health);
    }

    public interface WeaponManagerStep {
        SkillManagerStep weaponManager(WeaponManager weaponManager);
    }

    public interface SkillManagerStep {
        BuildStep skillManager(SkillManager skillManager);
    }

    public interface BuildStep {
        Warrior build();
    }

    private static class WarriorSteps implements NameStep, StrengthStep, AgilityStep, SpeedStep, HealthStep,
            WeaponManagerStep, SkillManagerStep, BuildStep {
        private String name;
        private Strength strength;
        private Agility agility;
        private Speed speed;
        private int health;
        private WeaponManager weaponManager;
        private SkillManager skillManager;

        @Override
        public StrengthStep name(String name) {
            this.name = name;
            return this;
        }

        @Override
        public AgilityStep strength(Strength strength) {
            this.strength = strength;
            return this;
        }

        @Override
        public SpeedStep agility(Agility agility) {
            this.agility = agility;
            return this;
        }

        @Override
        public HealthStep speed(Speed speed) {
            this.speed = speed;
            return this;
        }

        @Override
        public WeaponManagerStep health(int health) {
            this.health = health;
            return this;
        }

        @Override
        public SkillManagerStep weaponManager(WeaponManager weaponManager) {
            this.weaponManager = weaponManager;
            return this;
        }

        @Override
        public BuildStep skillManager(SkillManager skillManager) {
            this.skillManager = skillManager;
            return this;
        }

        @Override
        public Warrior build() {
            return new Warrior(name, strength, agility, speed, health, weaponManager, skillManager);
        }
    }

}
