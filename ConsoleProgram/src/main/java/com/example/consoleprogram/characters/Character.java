package com.example.consoleprogram.characters;

import java.util.Random;

public abstract class Character
{
    protected Random rand = new Random();
    
    protected String username;

    protected int level = 1;
    protected int evasion = 10;
    
    protected int maxHealth = 100;
    protected int health = maxHealth;

    Character(String username)
    {
        this.username = username;
    }

    public String toString() {
        return username + " (" + health + "/" + maxHealth + ")";
    }

    public String getDescription() {
        return "Character: " + username +
            "\nHealth: " + health +
            "\nLevel " + level + "\n";
    }

    public boolean equals(Character character) {
        return username.equals(character.username);
    }
    
    public String getUsername() {
        return username;
    }
    
    public int getHealth() {
        return health;
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

    public abstract void attack(Character target);
}
