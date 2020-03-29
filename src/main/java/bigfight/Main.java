package bigfight;

import bigfight.model.skill.SkillData;
import bigfight.model.skill.SkillFactory;
import bigfight.model.warrior.component.EmpowermentFactory;
import bigfight.model.warrior.database.WarriorDatabase;
import bigfight.model.weapon.WeaponData;
import bigfight.model.weapon.WeaponFactory;
import bigfight.ui.EnUi;
import bigfight.logic.command.FightCommand;
import bigfight.model.warrior.WarriorFactory;
import bigfight.data.DataConfig;

public class Main {
    EnUi ui;
    FightCommand fightCommand;
    WarriorFactory warriorFactory;
    DataConfig dataConfig;
    WeaponFactory weaponFactory;
    SkillFactory skillFactory;
    EmpowermentFactory empowermentFactory;
    WarriorDatabase warriorDatabase;

    public Main() {
        ui = new EnUi();
        dataConfig = new DataConfig();
        warriorFactory = new WarriorFactory();
        weaponFactory = new WeaponFactory(new WeaponData());
        skillFactory = new SkillFactory(new SkillData());
        empowermentFactory = new EmpowermentFactory(weaponFactory, skillFactory);
        warriorDatabase = new WarriorDatabase();
    }

    public void run() {
        ui.showWelcome();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
