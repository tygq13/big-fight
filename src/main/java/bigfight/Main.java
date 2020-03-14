package bigfight;

import bigfight.combat.fighter.Fighter;
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
    private Ui ui;
    private FightCommand fightCommand;
    private WarriorFactory warriorFactory;
    private DataConfig dataConfig;
    private WeaponFactory weaponFactory;
    private SkillFactory skillFactory;
    private EmpowermentFactory empowermentFactory;

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
        Warrior mainCharacter = warriorFactory.create(new DataGetter(dataConfig), empowermentFactory);
        Warrior npc = warriorFactory.create(new DataGetter(dataConfig), empowermentFactory);
        fightCommand = new FightCommand(new Fighter(mainCharacter), new Fighter(npc));
        fightCommand.execute();
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
