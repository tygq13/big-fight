package bigfight.model.warrior;

import bigfight.model.warrior.builder.Warrior;

import java.util.ArrayList;

public class WarriorList {
    ArrayList<Warrior> warriorArrayList;

    public WarriorList() {
        warriorArrayList = new ArrayList<>();
    }

    public void add(Warrior warrior) {
        warriorArrayList.add(warrior);
    }

    public Warrior find(String name) {
        for (Warrior warrior : warriorArrayList) {
            if (warrior.getName().equals(name)) {
                return warrior;
            }
        }
        return null;
    }
}
