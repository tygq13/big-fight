package bigfight.combat.util;

public class CombatAlgo {

    // the chance of ignore depends on absolute difference between the two speeds
    public static double ignoreBySpeed(int first, int second) {
        double ignore = (first - second);
        if (ignore > 80) {
            // cap at 80%, so that it is not possible to ignore all the times by purely speed
            ignore = 80;
        }
        ignore = ignore / 100.0;
        if (ignore < 0) {
            ignore = 0;
        }
        return ignore;
    }

    public static double escapeByAgility(int first, int second) {
        double escape = (first - second);
        if (escape > 80) {
            // cap at 80%, so that it is not possible to escape all the times by purely agility
            escape = 80;
        }
        escape = escape / 100.0;
        if (escape < 0) {
            escape = 0;
        }
        return escape;
    }

    public static int extraDamageByAttribute(int first, int second) {
        int damage = first - second;
        return Math.max(damage, 0);
    }
}
