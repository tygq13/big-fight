package bigfight.model.warrior.builder;

import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.component.*;
import bigfight.model.warrior.component.attr.Attribute;
import bigfight.model.warrior.database.Account;
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
        AttributeStep level(int level);
    }

    public interface AttributeStep {
        WeaponManagerStep attribute(Attribute attribute);
    }

    public interface WeaponManagerStep {
        SkillManagerStep weaponManager(WeaponManager weaponManager);
    }

    public interface SkillManagerStep {
        FriendsStep skillManager(SkillManager skillManager);
    }

    public interface FriendsStep {
        SexStep friends(Friends friends);
    }

    public interface SexStep {
        BuildStep isMale(boolean isMale);
    }

    public interface BuildStep {
        Warrior build();
    }

    private static class WarriorSteps implements AccountStep, LevelStep, AttributeStep,
            WeaponManagerStep, SkillManagerStep, FriendsStep, SexStep, BuildStep {
        private WarriorDatabase warriorDatabase;
        private Account account;
        private int level;
        private Attribute attribute;
        private WeaponManager weaponManager;
        private SkillManager skillManager;
        private Friends friends;
        private boolean isMale;

        WarriorSteps(WarriorDatabase warriorDatabase) {
            this.warriorDatabase = warriorDatabase;
        }

        @Override
        public LevelStep account(Account account) {
            this.account = account;
            return this;
        }

        @Override
        public AttributeStep level(int level) {
            this.level = level;
            return this;
        }

        @Override
        public WeaponManagerStep attribute(Attribute health) {
            this.attribute = health;
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
        public SexStep friends(Friends friends) {
            this.friends = friends;
            return this;
        }

        @Override
        public BuildStep isMale(boolean isMale) {
            this.isMale = isMale;
            return this;
        }

        @Override
        public Warrior build() {
            Warrior result = new Warrior(lock, account, attribute, weaponManager, skillManager, friends, isMale);
            warriorDatabase.insertWarrior(account.getId(), result);
            return result;
        }
    }

}
