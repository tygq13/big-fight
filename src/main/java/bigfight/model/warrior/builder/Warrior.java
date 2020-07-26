package bigfight.model.warrior.builder;

import bigfight.algo.BigFightAlgo;
import bigfight.data.DataConfig;
import bigfight.logic.command.Commandable;
import bigfight.model.skill.skills.*;
import bigfight.model.skill.struct.SkillType;
import bigfight.model.warrior.component.*;
import bigfight.model.warrior.database.Account;
import bigfight.model.warrior.database.DatabaseAccessor;
import bigfight.model.weapon.WeaponManager;
import bigfight.model.skill.SkillManager;
import bigfight.ui.Uiable;

public class Warrior {
    protected Account account;
    protected Speed speed;
    protected Agility agility;
    protected Strength strength;
    protected Health health;
    protected int level;
    protected WeaponManager weaponManager;
    protected SkillManager skillManager;
    protected Friends friends;

    // although the interface is package private, lock ensures that only builder can access it
    Warrior(WarriorBuilder.Lock lock, Account account, Strength strength, Agility agility, Speed speed, Health health,
            WeaponManager weaponManager, SkillManager skillManager, Friends friends) {
        this.account = account;
        this.strength = strength;
        this.agility = agility;
        this.speed = speed;
        this.health = health;
        level = 1;
        this.weaponManager = weaponManager;
        this.skillManager = skillManager;
        this.friends = friends;
    }

    public void execute(Commandable command, Uiable ui) {
        command.execute(this, ui);
    }

    public String getName() {
        return account.getName();
    }

    public int getId() {
        return account.getId();
    }

    public int getSpeed() {
        return speed.value();
    }

    public int getStrength() {
        return strength.value();
    }

    public int getAgility() {
        return agility.value();
    }

    public int getLevel() {
        return level;
    }

    public int getHealth() {
        return health.value();
    }

    public WeaponManager getWeaponManager() {
        return weaponManager;
    }

    public SkillManager getSkillManager() {
        return skillManager;
    }

    public Friends getFriends() {
        return friends;
    }

    public int getFriend(int index) {
        return friends.get(index);
    }

    public DatabaseAccessor getDatabaseAccessor() {
        return account.getDatabaseAccessor();
    }

    @Override
    public String toString() {
        String result = String.format("Fighter name: %s\n", account.getName())
                + String.format("Level: %d\n", level)
                + String.format("Strength: %d\n", strength.value())
                + String.format("Agility %d\n", agility.value())
                + String.format("Speed %d\n", speed.value())
                + String.format("Health %d\n", health);
        result += "Weapons: " + weaponManager.toString() + System.lineSeparator();
        result += "Skills: " + skillManager.toString() + System.lineSeparator();
        return result;
    }

}
