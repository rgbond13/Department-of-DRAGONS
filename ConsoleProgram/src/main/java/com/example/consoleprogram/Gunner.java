package com.example.consoleprogram;

public class Gunner extends Player {


        // Store whether or not gunner is handicapped
        private boolean aimbotEnabled;

        // Archer-specific variables
        private int numberOfBullets;

        public Gunner(String username)
        {
            super(username);

            aimbotEnabled = false;

            numberOfBullets = 100;
        }

        public String toString()
        {
            return super.toString() +
                    "Class: Archer" +
                    "\nAimBot Enabled: " + aimbotEnabled +
                    "\nNumber of Arrows: " + numberOfBullets + "\n";
        }

        @Override
        public void attack(Player target)
        {
            // Ensure we have arrows
            if (numberOfBullets <= 0)
            {
                System.out.println(username + " tried to fire a bullet, but has none.");
                return;
            }

            // Shoot arrow
            numberOfBullets--;
            boolean hitTarget = true;
            if (!aimbotEnabled && rand.nextInt(4) == 0)
            {
                hitTarget = false;
            }
            if (rand.nextInt(100) < target.evasion)
            {
                hitTarget = false;
                System.out.println(target.username + " dodged " + username +   "'s attack!");
                return;
            }

            if (hitTarget)
            {
                int damageDealt = rand.nextInt(10) + 10;
                System.out.println(username + " shot " + target.username + " for " + damageDealt + " damage!");
                target.damage(damageDealt);
                addXp(damageDealt);
            } else {
                System.out.println(username + " fired at " + target.username + ", but missed.");
            }

            // Warn user if they're out of arrows
            if (numberOfBullets <= 0)
            {
                System.out.println(username + " is out of bullets!");
            }
        }

        public void enableAimbot()
        {
            System.out.println(username + " enabled aimbot");
            aimbotEnabled = true;
        }

        public void disableAimbot()
        {
            System.out.println(username + " disabled aimbot");
            aimbotEnabled = false;
        }

        // Level 1 range: 50m
        // Each levelup adds 10m

    }

}
