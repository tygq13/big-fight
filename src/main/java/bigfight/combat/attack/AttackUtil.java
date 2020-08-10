package bigfight.combat.attack;

import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.special.HakiProtectUsable;
import bigfight.model.skill.struct.SkillIdentity;

public class AttackUtil {

    public static int invokeHakiProtect(Fighter fighter, int damage, CombatRandom random) {
        if (fighter.hasSkill(SkillIdentity.HAKI_PROTECT)) {
            HakiProtectUsable hakiProtect = (HakiProtectUsable) fighter.getSkill(SkillIdentity.HAKI_PROTECT);
            if (hakiProtect.getRemainingUsage() > 0 && random.getHakiProtectRandom() < hakiProtect.getInvocationChance()) {
                hakiProtect.invoke();
                return (int) (damage * (1 - hakiProtect.getProtectionPercentage()));
            }
        }
        return damage;
    }
}
