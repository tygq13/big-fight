package bigfight.ui;

public interface Uiable {
    void showWelcome();
    String readCommand();

    void printWin();
    void printLose();

    void printWeaponSmallAttack(String fighterName, String weaponName);
    void printWeaponMediumAttack(String fighterName, String weaponName);
    void printWeaponBigAttack(String fighterName, String weaponName);
    void printWeaponThrowAttack(String fighterName, String weaponName);
    void printUnarmedAttack(String fighterName);
    void printThrowOutAttack(String fighterName, String weaponName);
    void printDodge(String fighterName);
    void printInjury(String fighterName, int damage, int health);
    void printCounterAttackWeapon(String fighterName, String weaponName);

    void printSkillAttack(String skillAttackDescription, String fighterName);
    void printSkillDodge(String skillDodgeDescription, String fighterName);
    void printSkillApparentDeath(String fighterName);
}
