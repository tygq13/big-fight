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
    void printCounterAttackDodge(String fighterName);
    void printCounterAttackInjury(String fighterName, int damage, int health);

    void printSkillRoarAttack(String fighterName);
    void printSkillRoarDodge(String fighterName);
    void printSkillApparentDeath(String fighterName);
}
