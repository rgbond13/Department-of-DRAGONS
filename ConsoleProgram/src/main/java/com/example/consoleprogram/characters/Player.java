package com.example.consoleprogram.characters;

public abstract class Player extends Character {
    // Store how much XP the player has
    protected int experience = 0;
    protected int experienceForNextLevelUp = 100;
    protected int bandages = 3;

    Player(String username) {
        super(username);
    }

    public String getDescription() {
        return super.getDescription() +
            "XP: " + experience + " / " + experienceForNextLevelUp + "\n";
    }

    protected void addXp(int xp) {
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

    public int getBandages() {
        return bandages;
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
}
