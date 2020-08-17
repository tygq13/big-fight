// a wrapper class around random so that the random behaviour can be controlled
package bigfight.combat.util;

import java.util.Random;

public class CombatRandom extends Random{
    private Random random;

    public CombatRandom() {
        random = new Random();
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

    public double getCriticalAttackRandom() {
        return random.nextDouble();
    }

    public double getThrowWeaponRandom() {
        return random.nextDouble();
    }

    public int selectWeaponOrSkill(int size) {
        return random.nextInt(size);
    }

    public int selectWeapon(int size) {
        return random.nextInt(size);
    }

    public int selectActiveSkill(int size) {
        return random.nextInt(size);
    }

    public int selectSpecialSkill(int size) {
        return random.nextInt(size);
    }

    public double selectHealingSkillRandom() {
        return random.nextDouble();
    }

    public double selectUnarmed() {
        return random.nextDouble();
    }

    public double getSeaReflectRandom() {
        return random.nextDouble();
    }

    public double getSingleSpecialRandom() {
        return random.nextDouble();
    }

    public double getOnePunchRandom() {
        return random.nextDouble();
    }

    public double getGlueRandom() {
        return random.nextDouble();
    }

    public double getLuckyOrNotRandom() {
        return random.nextDouble();
    }

    public double doubleHitRandom() {
        return random.nextDouble();
    }

}
