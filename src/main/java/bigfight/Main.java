package bigfight;

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

    public Main() {
        ui = new Ui();
        dataConfig = new DataConfig();
        warriorFactory = new WarriorFactory();
    }

    public void run() {
        ui.showWelcome();
        fightCommand.execute();
        Warrior mainCharacter = warriorFactory.create(new DataGetter(dataConfig));
        Warrior npc = warriorFactory.create(new DataGetter(dataConfig));
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
