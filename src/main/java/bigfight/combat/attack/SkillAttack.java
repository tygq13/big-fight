package bigfight.combat.attack;

import bigfight.combat.Round;
import bigfight.combat.fighter.Fighter;
import bigfight.combat.util.CombatAlgo;
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

    public SkillAttack(Fighter attacker, Fighter defender, SkillModel skill, CombatRandom random, Uiable ui) {
        this.attacker = attacker;
        this.defender = defender;
        this.skill = skill;
        this.random = random;
        this.ui = ui;
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
        double escape = defender.getAdvancedAttribute().skillEvasionRate - attacker.getAdvancedAttribute().skillHitRate;
        escape += CombatAlgo.escapeByAgility(defender.getAgility(), attacker.getAgility());
        ui.printSkillAttack(skill.getAttackDescription(), attacker.getName());
        if (random.getEscapeRandom() < escape) {
            ui.printSkillDodge(skill.getDodgeDescription(), defender.getName());
        } else {
            int damage = getSkillDamage();
            damage = (int) (damage * (1 + attacker.getAdvancedAttribute().skillExtraPercentageDamage));
            defender.getFighterFlag().ignored += ignoreOpponent();
            defender.updateHealth(defender.getHealth() - damage);
            ui.printInjury(defender.getName(), damage, defender.getHealth());
            CounterAttack counterAttack = new CounterAttack(defender, attacker, random, ui);
            if (!(counterAttack.specialCounter(damage))) {
                counterAttack.counterAttack();
            }
        }
    }


    private int ignoreOpponent() {
        switch (skill.getIdentity()) {
            case ROAR:
                Roar actualSkill = (Roar) skill;
                return actualSkill.getIgnore();
        }
        return 0;
    }

    private int getSkillDamage() {
         switch (skill.getIdentity()) {
            case ROAR:
                Roar roar = (Roar) skill;
                return roar.getDamage();
             case BOLT_FROM_THE_BLUE:
                 BoltFromTheBlue boltFromTheBlue = (BoltFromTheBlue) skill;
                 return boltFromTheBlue.getDamage() + (int) (attacker.getLevel() * boltFromTheBlue.getLevelMultiply());
             case TORNADO:
                 Tornado tornado = (Tornado) skill;
                 return tornado.getDamage() + (int) (attacker.getStrength() * tornado.getStrengthMultiply());
             case ONE_PUNCH:
                 return defender.getHealth() - 1;
             case ANGELS_WINGS:
                 AngelsWings angelsWings = (AngelsWings) skill;
                 return angelsWings.getDamage() + (int) (attacker.getAgility() * angelsWings.getAgilityMultiply());
             case FOSHAN_KICK:
                 FoshanKick foshanKick = (FoshanKick) skill;
                 return foshanKick.getDamage() + (int) (attacker.getStrength() * foshanKick.getStrengthMultiply());
             case GLUE:
                 defender.getFighterFlag().beingGlued = true;
                 return 0;
             case WEAPON_RAINSTORM:
                 WeaponRainstorm weaponRainstorm = (WeaponRainstorm) skill;
                  int weaponNum = weaponRainstorm.getNumOfWeapons();
                  int rainstormDamage = 0;
                  for(int i = 0; i < weaponNum; i++) {
                      Weapon weapon = attacker.selectWeapon(random).getWeapon();
                      if (weapon != null) {
                          rainstormDamage += random.getWeaponDamageRandom(weapon.getDamage().lower(), weapon.getDamage().higher());
                      }
                  }
                  return rainstormDamage;
             case TICKLE:
                 Tickle tickle = (Tickle) skill;
                 int tickleDamage = tickle.getDamage() + (int) (attacker.getAgility() * tickle.getAgilityMultiply());
                 defender.addBuff(tickle.createBuff(tickleDamage));
                 return tickleDamage;
            default:
                return 0;
        }
    }
}
