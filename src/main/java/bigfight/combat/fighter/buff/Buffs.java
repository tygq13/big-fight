package bigfight.combat.fighter.buff;

import bigfight.combat.fighter.Fighter;

import java.util.LinkedList;
import java.util.ListIterator;

public class Buffs {
    private LinkedList<Buff> buffs;

    public Buffs() {
        buffs = new LinkedList<>();
    }

    public void add(Buff buff) {
        buffs.add(buff);
    }

    public void invoke(Fighter fighter) {
        ListIterator listIterator = (ListIterator) buffs.iterator();
        while (listIterator.hasNext()) {
            Buff buff = (Buff) listIterator.next();
            buff.invoke(fighter);
            if (buff.isEnd()) {
                listIterator.remove();
            }
        }
    }
}
