package bigfight;

import bigfight.data.DataGetter;
import bigfight.logic.command.Commandable;
import bigfight.logic.parser.Parser;
import bigfight.model.skill.SkillData;
import bigfight.model.skill.SkillFactory;
import bigfight.model.warrior.builder.Warrior;
import bigfight.model.warrior.component.EmpowermentFactory;
import bigfight.model.warrior.database.WarriorDatabase;
import bigfight.model.weapon.WeaponData;
import bigfight.model.weapon.WeaponFactory;
import bigfight.ui.EnUi;
import bigfight.model.warrior.WarriorFactory;
import bigfight.data.DataConfig;
import bigfight.util.BigFightException;

public class Main {
    EnUi ui;
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

    public void run() throws BigFightException {
        ui.showWelcome();
        Warrior warrior = warriorFactory.create(new DataGetter(dataConfig), empowermentFactory, warriorDatabase, "hero");
        while (true) {
            String input = ui.readCommand();
            if (input.equals("exit") || input.equals("quit")) {
                break;
            }
            try {
                Commandable command = Parser.parse(input);
                warrior.execute(command, ui);
            } catch (BigFightException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws BigFightException {
        new Main().run();
    }
}
