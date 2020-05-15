package com.example.consoleprogram;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyProgram extends ConsoleProgram
{
    private ArrayList<Player> players;

    public void run()
    {
        System.out.println("Now playing Department of DRAGONS!");

        // Ask for amount of players
        int numberOfPlayers = 0;
        while (numberOfPlayers < 2 || numberOfPlayers > 8) {
            numberOfPlayers = readInt("Choose how many players (2-8): ");
        }

        // Create the players, and print their stats
        players = createPlayers(numberOfPlayers);
        for (Player player : players) {
            System.out.println(player.getUsername() + " Joined!");
        }

        // TODO: Sudden Death
        // TODO: Random Player Order
        // Take turns until there are less than 2 players remaining
        int currentPlayer = 0;
        while (getNumberOfPlayersAlive() >= 2) {
            Player player = players.get(currentPlayer);
            playTurn(player);

            // Iterate to the next player
            currentPlayer++;
            if (currentPlayer == numberOfPlayers) {
                currentPlayer = 0;
            }
        }

        // Print who is alive at the end, or "tie" if there are no alive players.
        for (Player player : players) {
            if (player.isAlive()) {
                System.out.println(player.getUsername() + " won!");
                playCredits();
                return;
            }
        }
        System.out.println("It's a tie! Nobody won.");
        playCredits();
    }

    private void playTurn(Player player) {
        if (!player.isAlive()) {
            return;
        }

        String username = player.getUsername();
        System.out.println("Player: " + username);
        int option = readOption(Arrays.asList("Attack", "Heal", "Pass", "Surrender"));
        switch (option) {
            case 1:
                // Attack

                // Build a list of players who are alive, except the player whose turn it is.
                ArrayList<String> targets = new ArrayList<>();
                for (Player target : players) {
                    if (target != player && target.isAlive()) {
                        targets.add(target.getUsername());
                    }
                }

                // Present players to user to pick from
                int targetNumber = readOption(targets) - 1;
                String targetUsername = targets.get(targetNumber);

                // Use player's choice to determine which Player object the user is targeting.
                Player target = null;
                for (Player possibleTarget : players) {
                    if (targetUsername.equals(possibleTarget.getUsername())) {
                        target = possibleTarget;
                    }
                }

                player.attack(target);
                break;
            case 2:
                // Heal
                player.bandage();
                break;
            case 3:
                // Pass
                System.out.println(username + " chose to skip their turn.");
                break;
            case 4:
                // Surrender
                player.damage(player.getHealth());
                System.out.println(username + " surrendered!");
                break;
        }
    }

    private int readOption(List<String> options) {
        // Print all of the users options
        int optionNumber = 1;
        for (String option : options) {
            System.out.println(optionNumber + ": " + option);
            optionNumber++;
        }
        // Input from the user what option they want
        while (true) {
            String input = readLine("Choice: ");
            // Check if they typed out one of the options
            optionNumber = 1;
            for (String option : options) {
                if (option.equals(input)) {
                    return optionNumber;
                }
                optionNumber++;
            }
            // If they didn't type out an option, check if they inputted a number
            try {
                int choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= options.size()) {
                    return choice;
                }
            } catch (NumberFormatException ignored) {}
        }
    }
    
    private ArrayList<Player> createPlayers(int numberOfPlayers) {
        ArrayList<Player> players = new ArrayList<Player>(numberOfPlayers);
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Player " + (i + 1));
            System.out.println("What class would you like to play as?");
            System.out.println("1. Mage");
            System.out.println("2. Archer");
            System.out.println("3. Pistolier");
            int classInput = 0;
            while (classInput < 1 || classInput > 3) {
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
            case 3:
                newPlayer = new Pistolier(username);
                break;
            default:
                System.out.println("Invalid Class. Try again.");
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

    private void playCredits() {
        System.out.println("Executive Programmers: \nCody \nZoe \n\nProgrammers: \nRaine \nIden \nAiden \nColin \nMichael \nIsaac B. \nJoe \nIsaac R. \nDominic \nWillam \n\nLet me know if I forgot your name at https://github.com/rgbond13/Department-of-DRAGONS. \n Thank you for playing!");
    }

}






















