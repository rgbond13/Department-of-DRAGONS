package com.example.consoleprogram;

import java.util.Random;

class Player
{
    protected Random rand = new Random();
    
    protected String username;
    
    // Store the position of the player
    protected double x = 0.0;
    protected double y = 0.0;
    protected double z = 0.0;
    
    // TODO: Store character's facing direction
    
    // Store how much XP the player has
    protected int experience = 0;
    protected int experienceForNextLevelUp = 100;
    protected int level = 1;
    protected int evasion = 10;
    
    protected int maxHealth = 100;
    protected int health = maxHealth;
    protected int bandages = 3;
    
    public Player(String username)
    {
        this.username = username;
    }
    
    public String toString()
    {
        return "Player: " + username +
            "\nHealth: " + health +
            "\nPosition: (" + x + ", " + y + ", " + z + ")" +
            "\nLevel " + level + ", XP: " + experience + " / " + experienceForNextLevelUp + "\n";
    }

    public boolean equals(Player player)
    {
        return username == player.username &&
            x == player.x &&
            y == player.y &&
            z == player.z &&
            experience == player.experience &&
            experienceForNextLevelUp == player.experienceForNextLevelUp &&
            level == player.level &&
            evasion == player.evasion &&
            maxHealth == player.maxHealth &&
            health == player.health &&
            bandages == player.bandages;
    }
    
    public String getUsername() {
        return username;
    }
    
    public int getHealth() {
        return health;
    }
    
    public void move(double moveX, double moveY)
    {
        x += moveX;
        y += moveY;
    }
    
    public void jump()
    {
        z += 3.0;
        // TODO: Move in the character's FORWARDS direction
    }
    
    protected void addXp(int xp)
    {
        experience += xp;
        while (experience >= experienceForNextLevelUp)
        {
            levelUp();
        }
    }
    
    private void levelUp() {
        System.out.println(username + " leveled up!");
        level++;
        experience -= experienceForNextLevelUp;
        experienceForNextLevelUp += 50;
        
        maxHealth += 25;
        health = maxHealth;
    }
    
    public void bandage() {
        if (bandages <= 0) {
            System.out.println(username + " tried to bandage, but they have none.");
            return;
        }
        
        System.out.println(username + " is applying a bandage.");
        bandages--;
        health += 25;
        
        if (health > maxHealth) {
            health = maxHealth;
        }
    }
    
    public void damage(int points) {
        health -= points;
        if (health < 0) {
            health = 0;
        }
    }
    
    public boolean isAlive() {
        return health > 0;
    }
    
    // Level 1 range: 50m
    // Each levelup adds 10m
    
}
