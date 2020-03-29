package bigfight.ui;

import java.util.Scanner;

public class EnUi implements Uiable{
    private Scanner in = new Scanner((System.in));
    @Override
    public String readCommand() {
        return in.nextLine();
    }

    @Override
    public void showWelcome() {
        System.out.println("hello, welcome to Big Fight");
    }

    @Override
    public void printWin() {
        System.out.println("You have won");
    }

    @Override
    public void printLose() {
        System.out.println("You have lost");
    }

    @Override
    public void printWeaponSmallAttack(String fighterName, String weaponName) {
        String result = String.format("%s stand still, suddenly pocketing out a small weapon %s stabbing " +
                "towards the center of the opponent. ", fighterName, weaponName);
        System.out.println(result);
    }

    @Override
    public void printWeaponMediumAttack(String fighterName, String weaponName) {
        String result = String.format("%s hold your breath and prepare to attack, then you wield the medium weapon %s" +
                "rush towards the opponent. ", fighterName, weaponName);
        System.out.println(result);
    }

    @Override
    public void printWeaponBigAttack(String fighterName, String weaponName) {
        String result = String.format("As heard a howl, %s swing big weapon %s and step forwards. ",
                fighterName, weaponName);
        System.out.println(result);
    }

    @Override
    public void printWeaponThrowAttack(String fighterName, String weaponName) {
        String result = String.format("%s swing a circle the weapon %s. The %s fleets towards the opponent. ",
                fighterName, weaponName, weaponName);
        System.out.println(result);
    }

    @Override
    public void printUnarmedAttack(String fighterName) {
        String result = String.format("%s's body light as a swallow, running towards the opponent in quick step " +
                "and attack with cannon fist. ", fighterName);
        System.out.println(result);
    }

    @Override
    public void printThrowOutAttack(String fighterName, String weaponName) {
        String result = String.format("%s hurl his weapon %s, pointing towards the head of the opponent. ", fighterName, weaponName);
        System.out.println(result);
    }

    @Override
    public void printDodge(String fighterName) {
        String result = String.format("%s dances in the air, twisting his body and dodge the attack. ", fighterName);
        System.out.println(result);
    }

    @Override
    public void printInjury(String fighterName, int damage, int health) {
        String result = String.format("%s covers his wound in pain, losing HP %d (HP %d remains). ",
                fighterName, damage, health);
        System.out.println(result);
    }

    @Override
    public void printCounterAttackWeapon(String fighterName, String weaponName) {
        String result = String.format("%s skillfully counter-attacks with %s. ", fighterName, weaponName);
        System.out.println(result);
    }

    @Override
    public void printCounterAttackDodge(String fighterName) {
        String result = String.format("%s carefully dodge the counter-attack. ", fighterName);
        System.out.println(result);
    }

    @Override
    public void printCounterAttackInjury(String fighterName, int damage, int health) {
        String result = String.format("%s neglect the defence the lose HP %d (HP %d remains). ", fighterName, damage, health);
        System.out.println(result);
    }

    @Override
    public void printSkillRoarDodge(String fighterName) {
        String result = String.format("%s sums up his courage and seems unaffected by the attack. ", fighterName);
        System.out.println(result);
    }

    @Override
    public void printSkillRoarAttack(String fighterName) {
        String result = String.format("%s roar in anger, as if the king of beasts to scare the enemy away. ", fighterName);
        System.out.println(result);
    }

    @Override
    public void printSkillApparentDeath(String fighterName) {
        System.out.println("The opponent falls smoothly, breathe stopped, in apparent death");
    }

}


