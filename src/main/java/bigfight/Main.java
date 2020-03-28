package bigfight;

import bigfight.combat.fighter.Fighter;
import bigfight.model.skill.SkillData;
import bigfight.model.skill.SkillFactory;
import bigfight.model.warrior.component.EmpowermentFactory;
import bigfight.model.warrior.database.WarriorDatabase;
import bigfight.model.weapon.WeaponData;
import bigfight.model.weapon.WeaponFactory;
import bigfight.ui.EnUi;
import bigfight.command.FightCommand;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.WarriorFactory;
import bigfight.data.DataConfig;
import bigfight.data.DataGetter;

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
        Warrior mainCharacter = warriorFactory.create(new DataGetter(dataConfig), empowermentFactory, warriorDatabase, "Hero");
        Warrior npc = warriorFactory.create(new DataGetter(dataConfig), empowermentFactory, warriorDatabase,"Villain");
        System.out.println(mainCharacter);
        System.out.println(npc);
        fightCommand = new FightCommand(new Fighter(mainCharacter), new Fighter(npc), ui);
        fightCommand.execute();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
