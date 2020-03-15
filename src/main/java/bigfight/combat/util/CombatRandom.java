// a wrapper class around random so that the random behaviour can be controlled
package bigfight.combat.util;

import java.util.Random;

public class CombatRandom extends Random{
    private Random random;

    public CombatRandom() {
        random = new Random();
    }

    public double getRoundRandom() {
        return random.nextDouble();
    }

    public double getIgnoreRandom() {
        return random.nextDouble();
    }

    public double getSpeedIgnoreRandom() {
        return random.nextDouble();
    }

    public int getWeaponDamageRandom(int lower, int higher) {
        return lower + nextInt(higher - lower);
    }

    public double getEscapeRandom() {
        return random.nextDouble();
    }

    public double getCounterAttackRandom() {
        return random.nextDouble();
    }

    public double getCounterEscapeRandom() {
        return random.nextDouble();
    }

    public double getThrowWeaponRandom() {
        return random.nextDouble();
    }
}
