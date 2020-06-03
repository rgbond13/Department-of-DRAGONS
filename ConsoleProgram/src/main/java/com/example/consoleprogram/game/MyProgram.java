package com.example.consoleprogram.game;

import com.example.consoleprogram.characters.Archer;
import com.example.consoleprogram.characters.Dragon;
import com.example.consoleprogram.characters.Mage;
import com.example.consoleprogram.characters.Character;
import com.example.consoleprogram.characters.Monster;
import com.example.consoleprogram.characters.Player;
import com.example.consoleprogram.characters.RatMischief;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MyProgram extends ConsoleProgram
{
    private Random rand = new Random();
    private ArrayList<Character> characters;

    private static final int MAX_PLAYERS = 8;
    private static final int MIN_PLAYERS = 2;
    private static final int MAX_MONSTERS = 8;
    private static final int MIN_MONSTERS = 2;

    private GameMode mode;

    enum GameMode {
        FREE_FOR_ALL,
        COOP
    }

    public void run() throws Exception {
        System.out.println("Now playing Department of DRAGONS!");

        System.out.println("Game Mode");
        switch (readOption(Arrays.asList("Free for All", "Co-Op"))) {
            case 1:
                mode = GameMode.FREE_FOR_ALL;
                break;
            case 2:
                mode = GameMode.COOP;
                break;
        }

        // Create the players, and print their stats
        characters = createCharacters();
        for (Character character : characters) {
            System.out.println(character.getDescription());
        }

        // TODO: Sudden Death
        // Take turns until there are less than 2 players remaining
        int currentCharacter = 0;
        while (!isGameFinished()) {
            Character character = characters.get(currentCharacter);
            playTurn(character);

            // Iterate to the next player
            currentCharacter++;
            if (currentCharacter == characters.size()) {
                currentCharacter = 0;
            }
        }

        if (mode == GameMode.FREE_FOR_ALL) {
            // Print who is alive at the end, or "tie" if there are no alive players.
            Character winner = null;
            for (Character character : characters) {
                if (character.isAlive()) {
                    winner = character;
                }
            }
            if (winner == null) {
                System.out.println("Nobody won - It's a tie!");
            } else {
                System.out.println(winner.getUsername() + " won with " + winner.getHealth() + " health left!");
            }
        } else if (mode == GameMode.COOP) {
            if (getNumberOfMonstersAlive() >= 1)
                System.out.println("Monsters won!");
            else if (getNumberOfPlayersAlive() >= 1)
                System.out.println("Players won!");
            else
                System.out.println("It's a tie! Nobody won.");
        }
        // TODO: Credits
    }

    private boolean isGameFinished() throws Exception {
        if (mode == GameMode.COOP)
            return getNumberOfMonstersAlive() <= 0 || getNumberOfPlayersAlive() <= 0;
        else if (mode == GameMode.FREE_FOR_ALL)
            return getNumberOfMonstersAlive() + getNumberOfPlayersAlive() <= 1;
        else
            throw new Exception("Invalid Game Mode");
    }

    private void playTurn(Character character) {
        if (!character.isAlive()) {
            return;
        }

        if (character instanceof Player) {
            Player player = (Player) character;
            playTurn(player);
        } else if (character instanceof Monster) {
            Monster monster = (Monster) character;
            playTurn(monster);
        }
    }

    private void playTurn(Monster monster) {
        // Build a list of players who are alive, except the player whose turn it is.
        ArrayList<Character> targets = new ArrayList<>();
        for (Character target : characters) {
            if (target != monster && target.isAlive() && (mode == GameMode.FREE_FOR_ALL || target instanceof Player)) {
                targets.add(target);
            }
        }

        Character target = targets.get(rand.nextInt(targets.size()));
        monster.attack(target);
    }

    private void playTurn(Player player) {
        System.out.println("Player: " + player);
        int option = readOption(Arrays.asList("Attack", "Heal (" + player.getBandages() + " bandages)", "Pass", "Surrender", "Info"));
        switch (option) {
            case 1:
                // Attack

                // Build a list of characters who are alive, except the player whose turn it is.
                ArrayList<String> targets = new ArrayList<>();
                for (Character target : characters) {
                    if (target != player && target.isAlive() && (mode == GameMode.FREE_FOR_ALL || target instanceof Monster)) {
                        targets.add(target.toString());
                    }
                }

                // Present players to user to pick from
                int targetNumber = readOption(targets) - 1;
                String targetString = targets.get(targetNumber);

                // Use player's choice to determine which Player object the user is targeting.
                Character target = null;
                for (Character possibleTarget : characters) {
                    if (targetString.equals(possibleTarget.toString())) {
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
                System.out.println(player + " chose to skip their turn.");
                break;
            case 4:
                // Surrender
                player.damage(player.getHealth());
                System.out.println(player + " surrendered!");
                break;
            case 5:
                // Info
                System.out.println(player.getDescription());
                playTurn(player);
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
    
    private ArrayList<Character> createCharacters() {
        // Ask for amount of players
        int numberOfPlayers;
        do {
            numberOfPlayers = readInt("Choose how many Human Players (" + MIN_PLAYERS + "-" + MAX_PLAYERS + "): ");
        } while (numberOfPlayers < MIN_PLAYERS || numberOfPlayers > MAX_PLAYERS);

        // Create Players
        ArrayList<Character> characters = new ArrayList<Character>(numberOfPlayers);
        for (int i = 0; i < numberOfPlayers; i++) {
            System.out.println("Player " + (i + 1));
            System.out.println("What class would you like to play as?");
            int characterType = readOption(Arrays.asList("Mage", "Archer"));
            
            String username = "";
            while (username.length() == 0) {
                username = readLine("What is your username? ").trim();
            }
            
            Player newCharacter;
            switch (characterType) {
            case 1:
                newCharacter = new Mage(username);
                break;
            case 2:
                newCharacter = new Archer(username);
                break;
            default:
                System.out.println("Invalid selection made!");
                newCharacter = null;
            }
            
            characters.add(newCharacter);
        }

        // Ask for amount of monsters
        int numberOfMonsters;
        do {
            numberOfMonsters = readInt("Choose how many Monsters (" + MIN_MONSTERS + "-" + MAX_MONSTERS + "): ");
        } while (numberOfMonsters < MIN_MONSTERS || numberOfMonsters > MAX_MONSTERS);

        // Create Monsters
        boolean randomize = false;
        for (int i = 0; i < numberOfMonsters; i++) {
            System.out.println("Pick a monster type.");

            int characterType = rand.nextInt(1) + 1;
            if (!randomize) {
                int choice = readOption(Arrays.asList("Randomize Rest", "Dragon", "Rat Mischief"));
                if (choice == 1)
                    randomize = true;
                else
                    characterType = choice - 1;
            }

            Monster newCharacter;
            switch (characterType) {
                case 1:
                    newCharacter = new Dragon();
                    break;
                case 2:
                    newCharacter = new RatMischief();
                    break;
                default:
                    System.out.println("Invalid selection made!");
                    newCharacter = null;
            }

            characters.add(newCharacter);
        }

        return characters;
    }

    private int getNumberOfMonstersAlive() {
        int count = 0;
        for (Character character : characters) {
            if (character.isAlive() && character instanceof Monster) {
                count++;
            }
        }
        return count;
    }

    private int getNumberOfPlayersAlive() {
        int count = 0;
        for (Character character : characters) {
            if (character.isAlive() && character instanceof Player) {
                count++;
            }
        }
        return count;
    }
}






















