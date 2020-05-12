package com.example.consoleprogram;

import java.util.Random;
import java.util.ArrayList;

public class MyProgram extends ConsoleProgram
{
    private ArrayList<Player> players;

    public void run()
    {
        System.out.println("Now playing Department of DRAGONS!");
        int numberOfPlayers = 0;
        while (numberOfPlayers < 2 || numberOfPlayers > 8) {
            numberOfPlayers = readInt("Choose how many players (2-8): ");
        }
        players = createPlayers(numberOfPlayers);
        for (Player player : players) {
            System.out.println(player);
        }
        
        Random rand = new Random();
        int currentPlayer = 0;
        while (getNumberOfPlayersAlive() >= 2) {
            Player player = players.get(currentPlayer);
            int damage = rand.nextInt(10);
            player.damage(damage);
            System.out.println(player.getUsername() + " took " + damage + " damage.");

            currentPlayer++;
            if (currentPlayer == numberOfPlayers) {
                currentPlayer = 0;
            }
        }
        // TODO: Say who won!
        // TODO: Credits
    }
    
    private ArrayList<Player> createPlayers(int numberOfPlayers) {
        ArrayList<Player> players = new ArrayList<Player>(numberOfPlayers);
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Player " + (i + 1));
            System.out.println("What class would you like to play as?");
            System.out.println("1. Mage");
            System.out.println("2. Archer");
            int classInput = 0;
            while (classInput < 1 || classInput > 2) {
                classInput = readInt("");
            }
            
            String username = "";
            while (username.length() == 0) {
                username = readLine("What is your username? ").trim();
            }
            
            Player newPlayer;
            switch (classInput) {
            case 1:
                newPlayer = new Mage(username);
                break;
            case 2:
                newPlayer = new Archer(username);
                break;
            default:
                System.out.println("Invalid selection made!");
                newPlayer = null;
            }
            
            players.add(newPlayer);
        }
        return players;
    }
    
    private int getNumberOfPlayersAlive() {
        int count = 0;
        for (Player player : players) {
            if (player.isAlive()) {
                count++;
            }
        }
        return count;
    }
}






















