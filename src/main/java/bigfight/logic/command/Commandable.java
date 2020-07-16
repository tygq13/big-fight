package bigfight.logic.command;

import bigfight.model.warrior.builder.Warrior;
import bigfight.ui.Uiable;

public interface Commandable {

    void execute(Warrior warrior, Uiable ui);
}
