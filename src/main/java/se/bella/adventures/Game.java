package se.bella.adventures;

import se.bella.adventure.model.Burglar;
import se.bella.adventure.model.Entity;
import se.bella.adventure.model.Resident;

import java.util.Scanner;

public class Game {
    private boolean running = true;
    private Scanner scanner = new Scanner(System.in);

    private final String LIVINGROOM_CENTRE = "livingroom";
    private final String KITCHEN = "kitchen";
    private final String BEDROOM = "bedroom";
    private final String HALL = "hall";
    private final String OFFICE = "office";
    private final String START = "start";

    private String currentRoom = START;
    private boolean fryingPanFound = false;
    private Resident resident;
    private Burglar burglar;

    public void start() {
        resident = new Resident();
        burglar = new Burglar();

        welcomeMeny();
        livingRoomStart();

        while (running && resident.isConscious()) {
            String userInput = getUserInput();
            if (!running) break;
            running = processInput(userInput);
        }

    }

    private void welcomeMeny() {
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Welcome to Burglar game! You slept on the couch and heard a noise.");
        System.out.println("Try to find out whom or what made that noise.");
        System.out.println("-----------------------------------------------------------------");
        System.out.println("Choose one of the following rooms to start looking: ");
        System.out.println("1. Kitchen");
        System.out.println("2. Bedroom");
        System.out.println("3. Hall");
        System.out.println("4. Office");
        System.out.println("5. Quit");
        System.out.println();
    }

    private String getUserInput() {
        String input = scanner.nextLine();

        if (input.isBlank() && !currentRoom.equals(LIVINGROOM_CENTRE)) {
            return "living room";
        }
        return input;
    }

    public boolean processInput(String input) {
        switch (input) {
            case "kitchen" -> kitchen();
            case "bedroom" -> bedroom();
            case "hall" -> hall();
            case "office" -> office();
            case "fight" -> fight();
            case "living room" -> livingRoomStart();
            case "quit" -> {
                System.out.println("Goodbye!");
                running = false;
                return false;
            }
            default -> System.out.println("Invalid input. try again");
        }
        return running;
    }

    public void livingRoomStart() {

        System.out.println("You are in the living room, where do you wanna go?");

        currentRoom = LIVINGROOM_CENTRE;

    }

    public void kitchen() {
        if (currentRoom.equals(LIVINGROOM_CENTRE)) {
            currentRoom = KITCHEN;

            if (!fryingPanFound) {
                System.out.println("You found a frying pan! you wanna pick it up, yes/no?");
                String input = scanner.nextLine();
                System.out.println();
                if (input.equals("yes")) {
                    resident.addDamage(3);
                    fryingPanFound = true;
                    System.out.println("You picked up the frying pan!");
                } else {
                    System.out.println("You left the frying pan.");
                }

            } else {
                System.out.println("You are in the kitchen. Nothing is here!");
            }

        } else {
            System.out.println("You can only enter the kitchen from the living room. Press [enter].");
        }

        System.out.println("Press [enter] to return to the living room.");

    }

    public void bedroom() {
        if (currentRoom.equals(LIVINGROOM_CENTRE)) {
            System.out.println("You go to the bedroom and notice a open window.");
            currentRoom = BEDROOM;

        } else {
            System.out.println("You can only enter the bedroom from the Living room. Press [enter].");
        }

        System.out.println("Press [enter] to return to the living room.");

    }

    public void hall() {
        if (currentRoom.equals(LIVINGROOM_CENTRE)) {
            System.out.println("You see a Thief! You wanna fight or run to the living room, Press [enter].");
            currentRoom = HALL;
        } else {
            System.out.println("You can only enter the hall from the Living room. Press [enter]");
        }

    }

    public void office() {
        if (!currentRoom.equals(LIVINGROOM_CENTRE)) {
            System.out.println("You can only enter the office from the Living room. Press [enter].");
            return;
        }
        currentRoom = OFFICE;

        if (burglar.isConscious()) {
            System.out.println("You go to the office and see a phone but are too " +
                    "stressed to call the police.");

        } else {
            System.out.println();
            System.out.println("Do you want to call 911, yes/no?");
            String input = scanner.nextLine();
            if (input.equals("yes")) {
                System.out.println();
                System.out.println("You called the police. You won!");
                running = false;
                return;
            }
        }

        if (running) {
            System.out.println("Press [enter] to return to the living room.");

        }
    }

    public void fight() {
        if (!currentRoom.equals(HALL)) {
            System.out.println("There is no fight here");
            System.out.println("Press [enter] to return to the living room.");
            return;

        }

        System.out.println("Fight starts!");
        System.out.println();

        while (resident.isConscious() && burglar.isConscious()) {
            executeFight(burglar, resident);
        }
        if (!resident.isConscious()) {
            System.out.println();
            System.out.println("You lost the fight.");
            System.out.println("Game over");
            running = false;
        } else if (!burglar.isConscious()) {
            System.out.println();
            System.out.println("You knocked out the Thief! You can now go to office and call the police.");
            currentRoom = LIVINGROOM_CENTRE;
        }
    }

    public void executeFight(Entity defender, Entity attacker) {
        attacker.punch(defender);
        System.out.println(attacker.getRole() + " punches " + defender.getRole());
        if (defender.isConscious()) {
            System.out.println(defender.getRole() + " has health: " + defender.getHealth());
        } else {
            System.out.println(defender.getRole() + " is unconscious");
            return;
        }
        defender.punch(attacker);
        System.out.println(defender.getRole() + " punches " + attacker.getRole());
        if (attacker.isConscious()) {
            System.out.println(attacker.getRole() + " has health: " + attacker.getHealth());
        } else {
            System.out.println(attacker.getRole() + " is unconscious");
        }
    }
}



