package bigfight.model.weapon;

import bigfight.model.weapon.struct.*;
import javafx.util.Pair;

import java.util.HashMap;
import java.util.Map;
import static java.util.Map.entry;

public class WeaponData {
    public static final WeaponStruct TRIDENT_ONE = new WeaponStruct(
            new Pair<>(25, 50),
            WeaponType.BIG,
            "'The Emperor of Sea' Poseidon's most powerful trident. Need to rest for one round.",
            WeaponIdentity.TRIDENT,
            "trident"
    );

    public static final WeaponStructArray TRIDENT_ARRAY = new WeaponStructArray(
            TRIDENT_ONE
    );

    private static final WeaponStruct GAS_HAMMER_ONE = new WeaponStruct(
            new Pair<>(25, 50),
            WeaponType.BIG,
            "When you get naughty, you will be hit by this hammer. 10% chance ignore opponent's round",
            WeaponIdentity.GAS_HAMMER,
            "gas hammer"
    );

    private static final WeaponStructArray GAS_HAMMER_ARRAY = new WeaponStructArray(
            GAS_HAMMER_ONE
    );

    private static final WeaponStruct DEMON_SCYTHE_ONE = new WeaponStruct(
            new Pair<>(15, 25),
            WeaponType.BIG,
            "Mysterious magic makes it your eyes focus on it. Unescapable. Uncounterattackable.",
            WeaponIdentity.DEMON_SCYTHE,
            "demon scythe"
    );

    private static final WeaponStructArray DEMON_SCYTHE_ARRAY = new WeaponStructArray(
            DEMON_SCYTHE_ONE
    );

    private static final WeaponStruct METEOR_BALL_ONE = new WeaponStruct(
            new Pair<>(15, 24),
            WeaponType.THROW,
            "In old folk tales, it is remoulded from meteor hammer by Warrior Yuan." +
                    "Increase opponent's escape by 15%",
            WeaponIdentity.METEOR_BALL,
            "meteor ball"
    );

    private static final WeaponStructArray METEOR_BALL_ARRAY = new WeaponStructArray(
            METEOR_BALL_ONE
    );

    private static final WeaponStruct JUDGE_PENCIL_ONE = new WeaponStruct(
            new Pair<>(10, 15),
            WeaponType.SMALL,
            "It is the judge used by 'The God of Death' Yama. " +
                    "When pointed by it, no livings or the dead can escape. Unescapable.",
            WeaponIdentity.JUDGE_PENCIL,
            "judge's pencil"
    );

    private static final WeaponStructArray JUDGE_PENCIL_ARRAY = new WeaponStructArray(
            JUDGE_PENCIL_ONE
    );

    public static final Map<WeaponIdentity, WeaponStructArray> ARSENAL = new HashMap<>(
            Map.ofEntries(
                entry(WeaponIdentity.TRIDENT, TRIDENT_ARRAY),
                    entry(WeaponIdentity.GAS_HAMMER, GAS_HAMMER_ARRAY),
                    entry(WeaponIdentity.DEMON_SCYTHE, DEMON_SCYTHE_ARRAY),
                    entry(WeaponIdentity.METEOR_BALL, METEOR_BALL_ARRAY),
                    entry(WeaponIdentity.JUDGE_PENCIL, JUDGE_PENCIL_ARRAY)
            )
    );
}
