package com.example.consoleprogram.characters;

public class Archer extends Player
{
    // Archer-specific variables
    private int numberOfArrows = 100;
    
    public Archer(String username)
    {
        super(username);
    }
    
    public String getDescription()
    {
        return super.getDescription() +
            "Class: Archer" +
            "\nNumber of Arrows: " + numberOfArrows + "\n";
    }

    @Override
    public void attack(Character target)
    {
        // Ensure we have arrows
        if (numberOfArrows <= 0)
        {
            System.out.println(username + " tried to shoot an arrow, but has none.");
            return;
        }
        
        // Shoot arrow
        numberOfArrows--;
        boolean hitTarget = true;
        if (rand.nextInt(4) == 0)
        {
            hitTarget = false;
        }
        if (rand.nextInt(100) < target.evasion)
        {
            hitTarget = false;
            System.out.println(target.username + " dodged " + username +   "'s attack!");
        }
        
        if (hitTarget)
        {
            int damageDealt = rand.nextInt(10) + 10;
            System.out.println(username + " shot " + target.username + " for " + damageDealt + " damage!");
            target.damage(damageDealt);
            addXp(damageDealt);
        } else {
            System.out.println(username + " shot at " + target.username + ", but missed.");
        }
        
        // Warn user if they're out of arrows
        if (numberOfArrows <= 0)
        {
            System.out.println(username + " is out of arrows!");
        }
    }
}
