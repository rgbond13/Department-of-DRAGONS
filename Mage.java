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
        if (rand.nextInt(10) == 0) {
            System.out.println(username + " tried to use fireball, but it backfired!");
            damage(10);
            return;
        }
        
        int damageDealt = 10;
        if (rand.nextInt(10) == 0) {
            // 1 in 10 change of explosion
            damageDealt = 50;
        }
        
        System.out.println(username + " fireballed " + target.username + " for " + damageDealt + " damage!");
        target.damage(damageDealt);
        addXp(damageDealt);
    }
}