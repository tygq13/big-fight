package bigfight;

import bigfight.model.skill.SkillData;
import bigfight.model.skill.SkillFactory;
import bigfight.model.skill.SkillManager;
import bigfight.model.warrior.component.EmpowermentFactory;
import bigfight.model.weapon.WeaponData;
import bigfight.model.weapon.WeaponFactory;
import bigfight.ui.Ui;
import bigfight.command.FightCommand;
import bigfight.model.warrior.Warrior;
import bigfight.model.warrior.WarriorFactory;
import bigfight.data.DataConfig;
import bigfight.data.DataGetter;

public class Main {
    Ui ui;
    FightCommand fightCommand;
    WarriorFactory warriorFactory;
    DataConfig dataConfig;
    WeaponFactory weaponFactory;
    SkillFactory skillFactory;
    EmpowermentFactory empowermentFactory;

    public Main() {
        ui = new Ui();
        dataConfig = new DataConfig();
        warriorFactory = new WarriorFactory();
        weaponFactory = new WeaponFactory(new WeaponData());
        skillFactory = new SkillFactory(new SkillData());
        empowermentFactory = new EmpowermentFactory(weaponFactory, skillFactory);
    }

    public void run() {
        ui.showWelcome();
        fightCommand.execute();
        Warrior mainCharacter = warriorFactory.create(new DataGetter(dataConfig), empowermentFactory);
        Warrior npc = warriorFactory.create(new DataGetter(dataConfig), empowermentFactory);
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
