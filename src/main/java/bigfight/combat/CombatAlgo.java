package bigfight.combat;

public class CombatAlgo {

    // the chance of ignore depends on absolute difference between the two speeds
    public static double ignoreBySpeed(int first, int second) {
        double ignore = (first - second);
        if (ignore > 80) {
            // cap at 80%, so that it is not possible to ignore all the times by purely speed
            ignore = 80;
        }
        ignore = ignore / 100.0;
        return ignore;
    }

    public static double escapeByAgility(int first, int second) {
        double escape = (first - second);
        if (escape > 80) {
            // cap at 80%, so that it is not possible to escape all the times by purely agility
            escape = 80;
        }
        escape = escape / 100.0;
        return escape;
    }

    public static double multiplyByStrength(int first, int second) {
        double multiply = (first - second);
        // no cap in strength
        multiply = multiply / 100.0;
        return multiply;
    }
}
