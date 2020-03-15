# big-fight

**Big-Fight is a personal project to practice test-driven development. It is intended to be a simulation of the Chinese game "Q宠大乐斗".
In the initial stage it will be a command-line text-based game.** 

Cpy right issue: I once informally asked their management team for permission to simulate the game. If it violates any copyright, please contact me and I will turn the repo into private or remove it.

## The drive
I was unsatisfied with the CS2113T project. After reading some books such as _xunit-test-pattern_ and _tdd-by-example_, I become a fan of tdd and want to try it. I wanted to do a practice project with more focus on code designing rather than framework or GUI. I picked up "Q宠大乐斗" for childhood memory and its rich mechanism.

The good thing about a personal project is that there is no requirement and no deadline. Mostly importantly, I can be as intolerant to bad code as I like.

## How to run
1. Download the jar file from release 1.0.
2. Execute in command line: java -jar big-fight.jar

## Milestones
Current release: 1.0
Next version: 2.0

1. Milestone 1.0
    1. Able to simulate a fight between two warriors of level 1. No command interaction.
    2. Has at least 5 weapons and 5 skills. 
    3. Basic fight mechanism of attack, dodge, ignore and counter-attack. Consistent with the original game

2. MileStone 2.0
    1. Warrior able to level up
    2. Warrior can power-up the weapons and skills. At least 10 weapons and 10 skills.
    3. Warrior's weapons could inlay jewels
    4. Basic interaction of select opponents to fight, show certain info of the warrior etc.
    5. 100 layers for the Glory Tower
 
 ## Some reflection and refactoring note
 Difficulty to test: in-method new object, callback, private, static, intricate mechanism, intricate interaction, random
 
 Some problems pondering in my head:
 * The current feel is more like development with test rather then development driven by test
 * I massively use encapsulation but use few inheritance and interface
 * How much should I test the mechanism in method? Should I test the interface only?
 * Some code good for tests are not good in purely performance.
