package com.example.consoleprogram;

class Mage extends Player
{
    public Mage(String username) {
        super(username);
    }
    
    public String toString()
    {
        return super.toString() +
            "Class: Mage\n";
    }
    
    public void castFireball(Player target) {
        int damageDealt = 10;
        
        if (rand.nextInt(10) == 0) {
            System.out.println(username + " tried to use fireball, but it backfired.");
            target = this;
        }
        
        if (rand.nextInt(20) == 0) {
            // 1 in 20 chance of explosion
            System.out.println(username + "'s fireball exploded, dealing extra damage.");
            damageDealt = 50;
        }
        
        System.out.println(username + " fireballed " + target.username + " for " + damageDealt + " damage!");
        target.damage(damageDealt);
        
        if (target != this) {
            addXp(damageDealt);
        } else {
            System.out.println(username + " was not awarded XP for hurting itself");
        }
    }
}
