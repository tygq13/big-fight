package bigfight.model.weapon.weapons;

import bigfight.combat.attack.MediumTypeAttack;
import bigfight.model.weapon.struct.WeaponStruct;

public class MeteorBall extends WeaponModel {
    private final double METEOR_BALL_ONE_OPPONENT_ESCAPE = 0.15;

    public MeteorBall(WeaponStruct weaponStruct) {
        super(weaponStruct);
    }

    public double getIncreasedOpponentEscape() {
        return METEOR_BALL_ONE_OPPONENT_ESCAPE;
    }
}
