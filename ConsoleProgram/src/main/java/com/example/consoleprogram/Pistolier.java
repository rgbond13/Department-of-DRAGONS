package com.example.consoleprogram;

public class Pistolier extends Player {


        // Store whether or not gunner is handicapped
        private boolean aimbotEnabled;

        // Archer-specific variables
        private int numberOfBullets;

        public Pistolier(String username)
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
            // Ensure we have bullets
            if (numberOfBullets <= 0)
            {
                System.out.println(username + " tried to fire a bullet, but has none.");
                return;
            }

            // Shoot bullet
            numberOfBullets--;
            boolean hitTarget = true;
            if (!aimbotEnabled && rand.nextInt(10) == 0)
            {
                hitTarget = false;
            }
            if (rand.nextInt(200) < target.evasion)
            {
                System.out.println(target.username + " dodged " + username +   "'s attack!");
                return;
            }

            if (hitTarget)
            {
                int damageDealt = rand.nextInt(10) + 5;
                System.out.println(username + " shot " + target.username + " for " + damageDealt + " damage!");
                target.damage(damageDealt);
                addXp(damageDealt);
            } else {
                System.out.println(username + " fired at " + target.username + ", but missed.");
            }

            // Warn user if they're out of bullets
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
