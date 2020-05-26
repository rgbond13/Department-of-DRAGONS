package com.example.consoleprogram.characters;

public class Dragon extends Monster {
    static private int numDragons = 0;

    public Dragon() {
        super("Dragon " + (++numDragons));
    }

    @Override
    public void attack(Character target) {
        String attackName;
        int damageDealt;

        int attackDecider = rand.nextInt(100);
        if (attackDecider < 15) {
            attackName = "Breath Fire";
            damageDealt = 50;
        } else if (attackDecider < 60) {
            attackName = "Claw Slash";
            damageDealt = 15;
        } else {
            attackName = "Bite";
            damageDealt = 5;
        }

        System.out.println(username + " used " + attackName + " on " + target.username + " for " + damageDealt + " damage!");
        target.damage(damageDealt);
    }
}
