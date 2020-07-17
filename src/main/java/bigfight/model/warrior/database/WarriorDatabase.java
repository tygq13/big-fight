package bigfight.model.warrior.database;


import bigfight.model.warrior.npc.NpcFactory;
import bigfight.model.warrior.npc.NpcIdentity;
import bigfight.model.warrior.builder.Warrior;

import java.util.HashMap;

public class WarriorDatabase {
    private int serialNumber;

    static class AccountLock {
        private AccountLock() {}
    }
    private static AccountLock lock = new AccountLock();

    private HashMap<Integer, Warrior> database;

    public WarriorDatabase() {
        serialNumber = 0;
        database = new HashMap<>();
        // get default nps
        initializeNpc();
    }

    public Account createAccount(String name) {
        DatabaseAccessor accessor = new DatabaseAccessor(this);
        Account result = new Account(lock, name, serialNumber, accessor);
        database.put(serialNumber, null);
        serialNumber += 1;
        return result;
    }

    public int size() {
        return database.size();
    }

    public void insertWarrior(int id, Warrior warrior) {
        database.replace(id, warrior);
    }

    public Warrior get(int id) {
        return database.get(id);
    }

    private void initializeNpc() {
        for (NpcIdentity identity : NpcIdentity.getArray()) {
            //todo: need to check the identity's value increase in sequence from zero
            Account account = createAccount(identity.toString());
            Warrior npc = NpcFactory.create(identity, account, this);
        }
    }

    public Warrior getNpc(NpcIdentity identity) {
        return database.get(identity.getValue());
    }
}
