package bigfight.model.skill;

import bigfight.model.skill.struct.*;
import com.sun.tools.attach.AgentInitializationException;

import java.util.HashMap;
import java.util.Map;

public class SkillData {

    private final SkillStruct BORN_AS_STRONG_ONE = new SkillStruct(
            SkillType.PERMANENT,
            "You are a warrior with enormous strength.\n" +
                    "Your strength increase: basic strength * 50% + 3\n" +
                    "After level 60, the chance of getting this skill largely increases at every 5 level.",
            SkillIdentity.BORN_AS_STRONG,
            "born as strong"
    );

    private final SkillStructArray BORN_AS_STRONG_ARRAY = new SkillStructArray(
            BORN_AS_STRONG_ONE
    );

    private final SkillStruct AGILE_BODY_ONE = new SkillStruct(
            SkillType.PERMANENT,
            "Your agile body dodges every attacks.\n" +
                    "Your agility increase: basic agility * 50% + 3.\n" +
                    "After level 60, the chance of getting this skill largely increases at every 5 level.",
            SkillIdentity.AGILE_BODY,
            "agile body"
    );

    private final SkillStructArray AGILE_BODY_ARRAY = new SkillStructArray(
            AGILE_BODY_ONE
    );

    private final SkillStruct A_STEP_AHEAD_ONE = new SkillStruct(
            SkillType.PERMANENT,
            "You are always a step ahead of cataclysm.\n" +
                    "Your speed increase: basic speed * 50% + 3.\n" +
                    "After level 60, the chance of getting this skill largely increases at every 5 level.",
            SkillIdentity.A_STEP_AHEAD,
            "a step ahead"
    );

    private final SkillStructArray A_STEP_AHEAD_ARRAY = new SkillStructArray(
            A_STEP_AHEAD_ONE
    );

    private final SkillStruct ROAR_ONE = new SkillStruct(
            SkillType.DAMAGE,
            "Your fierce roar scares enemies away.\n" +
                    "Ignore opponent's next round. Cause damage 15.",
            SkillIdentity.ROAR,
            "roar"
    );

    private final SkillStructArray ROAR_ARRAY = new SkillStructArray(
            ROAR_ONE
    );

    private final SkillStruct APPARENT_DEATH_ONE = new SkillStruct(
            SkillType.SPECIAL,
            "Upmost danger lies in the apparent death.\n" +
                    "Upon fatal damage, you will not die but leave with 1 health.\n" +
                    "After level 60, the chance of getting this skill largely increases at every 5 level.",
            SkillIdentity.APPARENT_DEATH,
            "apparent death"
    );

    private final SkillStructArray APPARENT_DEATH_ARRAY = new SkillStructArray(
            APPARENT_DEATH_ONE
    );

    private final Map<SkillIdentity, SkillStructArray> SKILL_TABLE = new HashMap<>(
            Map.ofEntries(
                    Map.entry(SkillIdentity.BORN_AS_STRONG, BORN_AS_STRONG_ARRAY),
                    Map.entry(SkillIdentity.AGILE_BODY, AGILE_BODY_ARRAY),
                    Map.entry(SkillIdentity.A_STEP_AHEAD, A_STEP_AHEAD_ARRAY),
                    Map.entry(SkillIdentity.ROAR, ROAR_ARRAY),
                    Map.entry(SkillIdentity.APPARENT_DEATH, APPARENT_DEATH_ARRAY)
            )
    );

    public SkillStructArray get(SkillIdentity identity) {
        return SKILL_TABLE.get(identity);
    }

    public SkillStruct getWithStar(SkillIdentity identity, int star) {
        return SKILL_TABLE.get(identity).withStar(star);
    }
}
