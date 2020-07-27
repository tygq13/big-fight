package bigfight.model.warrior.builder;

import bigfight.logic.command.Commandable;
import bigfight.model.warrior.component.*;
import bigfight.model.warrior.component.BasicAttribute;
import bigfight.model.warrior.component.WeaponAttribute;
import bigfight.model.warrior.database.Account;
import bigfight.model.warrior.database.DatabaseAccessor;
import bigfight.model.weapon.WeaponManager;
import bigfight.model.skill.SkillManager;
import bigfight.ui.Uiable;

public class Warrior {
    private Account account;
    private BasicAttribute speed;
    private BasicAttribute agility;
    private BasicAttribute strength;
    private BasicAttribute health;
    private int level;
    private WeaponManager weaponManager;
    private SkillManager skillManager;
    private Friends friends;
    private WeaponAttribute weaponAttribute;

    // although the interface is package private, lock ensures that only builder can access it
    Warrior(WarriorBuilder.Lock lock, Account account, BasicAttribute strength, BasicAttribute agility, BasicAttribute speed, BasicAttribute health,
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
        weaponAttribute = new WeaponAttribute();
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

    public BasicAttribute getSpeedObj() {
        return speed;
    }

    public int getStrength() {
        return strength.value();
    }

    public BasicAttribute getStrengthObj() {
        return strength;
    }

    public int getBasicAttribute() {
        return agility.value();
    }

    public BasicAttribute getAgilityObj() {
        return agility;
    }

    public int getHealth() {
        return health.value();
    }

    public BasicAttribute getHealthObj() {
        return health;
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        level += 1;
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

    public WeaponAttribute getWeaponAttribute() {
        return weaponAttribute;
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
