package bigfight.combat.attack;

import bigfight.combat.Round;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatRandom;
import bigfight.model.skill.skills.SkillModel;
import bigfight.model.skill.skills.*;
import bigfight.model.skill.struct.SkillIdentity;
import bigfight.model.warrior.component.Empowerment;
import bigfight.model.weapon.Weapon;
import bigfight.ui.Uiable;

public class SkillAttack implements Attackable {
    private Fighter attacker;
    private Fighter defender;
    private SkillModel skill;
    private CombatRandom random;
    private Uiable ui;
    private AttackCalculator attackCalculator;

    public SkillAttack(Fighter attacker, Fighter defender, SkillModel skill, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.skill = skill;
        this.random = random;
        this.ui = ui;
        this.attackCalculator = new AttackCalculator(attacker.getAdvancedAttribute().skillAttackAttribute(),
                defender.getAdvancedAttribute().skillDefenceAttribute(), random);
    }

    @Override
    public void attack() {
        // special case
        if (skill.getIdentity() == SkillIdentity.DISARM) {
            Empowerment empowerment = new Empowerment(defender.getHoldingWeapon());
            defender.changeWeapon(new Empowerment((Weapon) null));
            // not activating weapon round change effect
            new Round(attacker, defender, empowerment, random, ui).fight();
            return;
        }
        // should add exception if not initialized;
        ui.printSkillAttack(skill.getAttackDescription(), attacker.getName());
        if (isEscaped()) {
            ui.printSkillDodge(skill.getDodgeDescription(), defender.getName());
        } else {
            int damage = getSkillDamage();
            damage = attackCalculator.damageAttributeMultiply(damage);
            lifeSteal(damage);
            defender.getFighterFlag().ignored += ignoreOpponent();
            defender.updateHealth(defender.getHealth() - damage);
            ui.printInjury(defender.getName(), damage, defender.getHealth());
            CounterAttack counterAttack = new CounterAttack(defender, attacker, random, ui);
            if (!(counterAttack.specialCounter(damage))) {
                counterAttack.counterAttack();
            }
        }
    }

    private boolean isEscaped() {
        switch (skill.getIdentity()) {
            case SHOCK_WAVE: {
                ShockWave shockWave = (ShockWave) skill;
                double escape = 0 - attacker.getFighterFlag().rounds * shockWave.getHitRateIncrement();
                return attackCalculator.isEscape(escape, attacker.getAgility(), defender.getAgility());
            }
            case LUCKY_OR_NOT: {
                LuckyOrNot luckyOrNot = (LuckyOrNot) skill;
                double escape = 0 - luckyOrNot.getExtraHitRate();
                return attackCalculator.isEscape(escape, attacker.getAgility(), defender.getAgility());
            }
            case ACUPOINTER: {
                Acupointer acupointer = (Acupointer) skill;
                double escape = 0 - acupointer.getExtraHitRate();
                return attackCalculator.isEscape(escape, attacker.getAgility(), defender.getAgility());
            }
            case GHOST_ON: {
                GhostOn ghostOn = (GhostOn) skill;
                double escape = 0 - ghostOn.getExtraHitRate();
                return attackCalculator.isEscape(escape, attacker.getAgility(), defender.getAgility());
            }
        }
        return attackCalculator.isEscape(attacker.getAgility(), defender.getAgility());
    }

    private void lifeSteal(int damage) {
        double lifeSteal = attacker.getCombatSelector().selectBloodSacrifice(random);
        attacker.updateHealth(attacker.getHealth() + (int) (damage * lifeSteal));
    }

    private int ignoreOpponent() {
        switch (skill.getIdentity()) {
            case ROAR:
                Roar roar = (Roar) skill;
                return roar.getIgnore();
            case DASH:
                Dash dash = (Dash) skill;
                return dash.getIgnore();
        }
        return 0;
    }

    private int getSkillDamage() {
         switch (skill.getIdentity()) {
            case ROAR: {
                Roar roar = (Roar) skill;
                return roar.getDamage();
            }
             case BOLT_FROM_THE_BLUE: {
                 BoltFromTheBlue boltFromTheBlue = (BoltFromTheBlue) skill;
                 return boltFromTheBlue.getDamage() + (int) (attacker.getLevel() * boltFromTheBlue.getLevelMultiply());
             }
             case TORNADO: {
                 Tornado tornado = (Tornado) skill;
                 return tornado.getDamage() + (int) (attacker.getStrength() * tornado.getStrengthMultiply());
             }
             case ONE_PUNCH: {
                 return defender.getHealth() - 1;
             }
             case ANGELS_WINGS: {
                 AngelsWings angelsWings = (AngelsWings) skill;
                 return angelsWings.getDamage() + (int) (attacker.getAgility() * angelsWings.getAgilityMultiply());
             }
             case FOSHAN_KICK: {
                 FoshanKick foshanKick = (FoshanKick) skill;
                 return foshanKick.getDamage() + (int) (attacker.getStrength() * foshanKick.getStrengthMultiply());
             }
             case GLUE: {
                 defender.getFighterFlag().beingGlued = true;
                 return 0;
             }
             case WEAPON_RAINSTORM: {
                 WeaponRainstorm weaponRainstorm = (WeaponRainstorm) skill;
                 int weaponNum = weaponRainstorm.getNumOfWeapons();
                 int rainstormDamage = 0;
                 for (int i = 0; i < weaponNum; i++) {
                     Weapon weapon = attacker.getCombatSelector().selectWeapon(random).getWeapon();
                     if (weapon != null) {
                         rainstormDamage += random.getWeaponDamageRandom(weapon.getDamage().lower(), weapon.getDamage().higher());
                     }
                 }
                 return rainstormDamage;
             }
             case TICKLE: {
                 Tickle tickle = (Tickle) skill;
                 int tickleDamage = tickle.getDamage() + (int) (attacker.getAgility() * tickle.getAgilityMultiply());
                 defender.addBuff(tickle.createBuff(tickleDamage));
                 return tickleDamage;
             }
             case DASH: {
                 Dash dash = (Dash) skill;
                 return dash.getDamage() + (int) (attacker.getSpeed() * dash.getSpeedMultiply());
             }
             case SHAKE: {
                 Shake shake = (Shake) skill;
                 for (int i = 0; i < shake.getDisposeNum(); i++) {
                     defender.getCombatSelector().randomDisposeWeapon(random);
                 }
                 return shake.getDamage();
             }
             case WINDY_KICK: {
                 WindyKick windyKick = (WindyKick) skill;
                 return (int) (windyKick.getSpeedMultiply() * (attacker.getSpeed() + windyKick.getSpeedAddition()));
             }
             case LUCKY_OR_NOT: {
                 LuckyOrNot luckyOrNot = (LuckyOrNot) skill;
                 if (random.getLuckyOrNotRandom() < luckyOrNot.getLuckyChance()) {
                     return luckyOrNot.getDamage() + (int) (attacker.getLevel() * luckyOrNot.getLevelMultiply());
                 } else {
                     defender.updateHealth(defender.getHealth() + luckyOrNot.getHeal());
                     return 0;
                 }
             }
             case SHOCK_WAVE: {
                 ShockWave shockWave = (ShockWave) skill;
                 return shockWave.getDamage() + attacker.getFighterFlag().rounds * shockWave.getDamageIncrement();
             }
             case ACUPOINTER: {
                 Acupointer acupointer = (Acupointer) skill;
                 defender.getFighterFlag().noSelectSkill = acupointer.getNoSKillRounds();
                 return acupointer.getDamage() + (int) (attacker.getLevel() * acupointer.getLevelMultiply());
             }
             case STINKY_FEET: {
                 StinkyFeet stinkyFeet = (StinkyFeet) skill;
                 double sameSexMultiply = attacker.isMale() ^ defender.isMale() ? 0 : stinkyFeet.getSameSexMultiply();
                 double strengthDamage = attacker.getStrength() * stinkyFeet.getStrengthMultiply();
                 return (int) ((stinkyFeet.getDamage() + strengthDamage) * (1 + sameSexMultiply));
             }
             case GHOST_ON: {
                 GhostOn ghostOn = (GhostOn) skill;
                 defender.addBuff(ghostOn.createDebuff());
                 return ghostOn.getDamage() + (int) (attacker.getLevel() * ghostOn.getLevelMultiply());
             }
            default:
                return 0;
        }
    }
}
