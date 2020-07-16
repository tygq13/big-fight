package bigfight.model.warrior.database;

public class Account {

    private String name;
    private int id;
    private DatabaseAccessor accessor;

    // lock to ensure that account can only be created through the database
    Account(WarriorDatabase.AccountLock lock, String name, int id, DatabaseAccessor accessor) {
        this.name = name;
        this.id = id;
        this.accessor = accessor;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public DatabaseAccessor getDatabaseAccessor() {
        return accessor;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (other instanceof Account) {
            Account b = (Account) other;
            return this.getId() == b.getId();
        } else {
            return false;
        }
    }
}
