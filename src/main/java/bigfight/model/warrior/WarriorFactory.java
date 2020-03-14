package bigfight.model.warrior;

import bigfight.data.DataGetter;
import bigfight.model.warrior.component.EmpowermentFactory;

public class WarriorFactory {

    public Warrior create(DataGetter dataGetter, EmpowermentFactory empowermentFactory) {
        System.out.println("here");
        return new Warrior(dataGetter, empowermentFactory);
    }
}
