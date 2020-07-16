package bigfight.model.warrior.component;

import bigfight.model.warrior.database.Account;

import java.util.ArrayList;

public class Friends {
    private ArrayList<Integer> friends;

    public Friends() {
        friends = new ArrayList<>();
    }

    public int get(int index) {
        return friends.get(index);
    }

    public void add(int id) {
        friends.add(id);
    }
}
