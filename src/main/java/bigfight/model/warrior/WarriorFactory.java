package bigfight.model.warrior;

import bigfight.data.DataGetter;

public class WarriorFactory {

    public Warrior create(DataGetter dataGetter) {
        return new Warrior(dataGetter);
    }
}
