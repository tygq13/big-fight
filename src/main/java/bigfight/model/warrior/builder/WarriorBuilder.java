package bigfight.model.warrior.builder;

import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.component.*;
import bigfight.model.warrior.database.Account;
import bigfight.model.warrior.database.DatabaseAccessor;
import bigfight.model.warrior.database.WarriorDatabase;
import bigfight.model.weapon.WeaponManager;

public class WarriorBuilder {
    static final class Lock {
        private Lock() {}
    }

    private static final Lock lock = new Lock();

    public static AccountStep stepBuilder(WarriorDatabase warriorDatabase) {
        return new WarriorSteps(warriorDatabase);
    }

    public interface AccountStep {
        LevelStep account(Account account);
    }

    public interface LevelStep {
        StrengthStep level(int level);
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
        WeaponManagerStep health(Health health);
    }

    public interface WeaponManagerStep {
        SkillManagerStep weaponManager(WeaponManager weaponManager);
    }

    public interface SkillManagerStep {
        FriendsStep skillManager(SkillManager skillManager);
    }

    public interface FriendsStep {
        BuildStep friends(Friends friends);
    }

    public interface BuildStep {
        Warrior build();
    }

    private static class WarriorSteps implements AccountStep, LevelStep, StrengthStep, AgilityStep, SpeedStep, HealthStep,
            WeaponManagerStep, SkillManagerStep, FriendsStep, BuildStep {
        private WarriorDatabase warriorDatabase;
        private Account account;
        private int level;
        private Strength strength;
        private Agility agility;
        private Speed speed;
        private Health health;
        private WeaponManager weaponManager;
        private SkillManager skillManager;
        private Friends friends;

        WarriorSteps(WarriorDatabase warriorDatabase) {
            this.warriorDatabase = warriorDatabase;
        }

        @Override
        public LevelStep account(Account account) {
            this.account = account;
            return this;
        }

        @Override
        public StrengthStep level(int level) {
            this.level = level;
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
        public WeaponManagerStep health(Health health) {
            this.health = health;
            return this;
        }

        @Override
        public SkillManagerStep weaponManager(WeaponManager weaponManager) {
            this.weaponManager = weaponManager;
            return this;
        }

        @Override
        public FriendsStep skillManager(SkillManager skillManager) {
            this.skillManager = skillManager;
            return this;
        }

        @Override
        public BuildStep friends(Friends friends) {
            this.friends = friends;
            return this;
        }

        @Override
        public Warrior build() {
            Warrior result = new Warrior(lock, account, strength, agility, speed, health, weaponManager, skillManager, friends);
            warriorDatabase.insertWarrior(account.getId(), result);
            return result;
        }
    }

}
