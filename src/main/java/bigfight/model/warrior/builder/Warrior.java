package bigfight.model.warrior.builder;

import bigfight.logic.command.Commandable;
import bigfight.model.warrior.component.*;
import bigfight.model.warrior.component.BasicAttribute;
import bigfight.model.warrior.component.AdvancedAttribute;
import bigfight.model.warrior.database.Account;
import bigfight.model.warrior.database.DatabaseAccessor;
import bigfight.model.weapon.WeaponManager;
import bigfight.model.skill.SkillManager;
import bigfight.ui.Uiable;

// represent the user role-playing a warrior
public class Warrior {
    private Account account;
    private int level;
    private WeaponManager weaponManager;
    private SkillManager skillManager;
    private Friends friends;
    private AdvancedAttribute advancedAttribute;
    private Attribute attribute;

    // although the interface is package private, lock ensures that only builder can access it
    Warrior(WarriorBuilder.Lock lock, Account account, Attribute attribute,
            WeaponManager weaponManager, SkillManager skillManager, Friends friends) {
        this.account = account;
        this.attribute = attribute;
        level = 1;
        this.weaponManager = weaponManager;
        this.skillManager = skillManager;
        this.friends = friends;
        advancedAttribute = new AdvancedAttribute();
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
        return attribute.getSpeed();
    }

    public BasicAttribute getSpeedObj() {
        return attribute.getSpeedObj();
    }

    public int getStrength() {
        return attribute.getStrength();
    }

    public BasicAttribute getStrengthObj() {
        return attribute.getStrengthObj();
    }

    public int getAgility() {
        return attribute.getAgility();
    }

    public BasicAttribute getAgilityObj() {
        return attribute.getAgilityObj();
    }

    public int getHealth() {
        return attribute.getHealth();
    }

    public BasicAttribute getHealthObj() {
        return attribute.getHealthObj();
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

    public AdvancedAttribute getAdvancedAttribute() {
        return attribute.getAdvancedAttribute();
    }

    public AdvancedAttribute getWeaponAttributeCopy() {
        return (AdvancedAttribute) advancedAttribute.clone();
    }

    public Attribute getAttribute() {
        return attribute;
    }

    @Override
    public String toString() {
        String result = String.format("Fighter name: %s\n", account.getName())
                + String.format("Level: %d\n", level)
                + String.format("Strength: %d\n", getStrength())
                + String.format("Agility %d\n", getAgility())
                + String.format("Speed %d\n", getSpeed())
                + String.format("Health %d\n", getHealth());
        result += "Weapons: " + weaponManager.toString() + System.lineSeparator();
        result += "Skills: " + skillManager.toString() + System.lineSeparator();
        return result;
    }

}
