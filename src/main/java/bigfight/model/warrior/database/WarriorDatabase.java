package bigfight.model.warrior.database;


import bigfight.model.warrior.builder.Warrior;

import java.util.HashMap;

public class WarriorDatabase {
    private int serialNumber;

    static class AccountLock {
        private AccountLock() {}
    }
    private static AccountLock lock = new AccountLock();

    private HashMap<Account, Warrior> database;

    public WarriorDatabase() {
        serialNumber = 0;
        database = new HashMap<>();
    }

    public Account createAccount(String name) {
        Account result = new Account(lock, name, serialNumber);
        serialNumber += 1;
        database.put(result, null);
        return result;
    }

    public int size() {
        return database.size();
    }

    public void insertWarrior(Account account, Warrior warrior) {
        database.replace(account, warrior);
    }

    public Warrior get(Account account) {
        return database.get(account);
    }
}
