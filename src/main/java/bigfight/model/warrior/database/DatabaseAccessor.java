package bigfight.model.warrior.database;

import bigfight.model.warrior.builder.Warrior;

public class DatabaseAccessor {
    private WarriorDatabase warriorDatabase;

    DatabaseAccessor(WarriorDatabase warriorDatabase) {
        this.warriorDatabase = warriorDatabase;
    }

    public Warrior find(int id) {
        return warriorDatabase.get(id);
    }
}
