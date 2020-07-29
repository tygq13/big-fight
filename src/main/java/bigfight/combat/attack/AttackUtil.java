package bigfight.combat.attack;

import bigfight.combat.fighter.FighterStatus;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.HakiProtect;
import bigfight.model.skill.struct.SkillIdentity;

public class AttackUtil {

    public static int invokeHakiProtect(FighterStatus fighter, int damage, CombatRandom random) {
        if (fighter.hasSkill(SkillIdentity.HAKI_PROTECT)) {
            HakiProtect hakiProtect = (HakiProtect) fighter.getSkill(SkillIdentity.HAKI_PROTECT);
            if (hakiProtect.getRemainingUsage() > 0 && random.getHakiProtectRandom() < hakiProtect.getInvokeChance()) {
                hakiProtect.invoke();
                return (int) (damage * (1 - hakiProtect.getProtectionPercentage()));
            }
        }
        return damage;
    }
}
