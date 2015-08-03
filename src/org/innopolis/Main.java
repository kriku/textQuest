package org.innopolis;

import org.innopolis.quest.*;
import org.innopolis.quest.Character;
import org.innopolis.quest.Object;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Parser parser = new Parser("quest.xml");
        Scanner scanner = new Scanner(System.in);

        Game game = parser.getStory();
        System.out.println(game.getName());

        System.out.print("enter your name: ");
        Player player = new Player(scanner.nextLine());

        while (game.getPlay()) {
            Location currentLocation = game.getLocations()[player.getLocation()];
            System.out.println(currentLocation);

            String answer = scanner.nextLine();
            int choice = -1;

            try {
//              try to parse int from answer string
                choice = Integer.parseInt(answer, 10);
            } catch (Exception ignored) {}

//          check for exist way
            if (choice >= 0 && choice < currentLocation.getExits().length) {
                int targetLocation = currentLocation.getExits()[choice].getLocation();
                int requirement = game.getLocations()[targetLocation].getRequired();
                if (requirement > 0) {
                    if (player.hasItem(requirement)) {
                        player.setLocation(currentLocation.getExits()[choice].getLocation());
                    } else {
                        System.out.println("you need " + game.getObjectById(requirement) + " to move there..");
                    }
                } else {
                    player.setLocation(currentLocation.getExits()[choice].getLocation());
                }
            } else if (choice > 0) {
                System.out.println("no way");
            }

            if ("exit".equals(answer)) game.setPlay(false);

            if ("save".equals(answer)) {
                try {
                    game.save(game.getName() + ".sav");
                } catch (IOException e) {
                    System.out.println("unable to save the game");
                    e.printStackTrace();
                }
            }

            if (answer.length()>4 && "kill".equals(answer.substring(0, 4))) {
                String who = answer.substring(4).trim();
                Character findCharacter = currentLocation.getCharacterByName(who);
                if (findCharacter != null) {
                    player.addObjects(findCharacter.getObjects());
                    findCharacter.killHim();
                }
            }

            if (answer.length()>7 && "pick up".equals(answer.substring(0, 7))) {
                String what = answer.substring(7).trim();
                Object findObject = currentLocation.getObjectByName(what);
                if (findObject != null) {
                    player.addObject(findObject);
                    currentLocation.pickUpObject(findObject);
                }
            }

            if ("inventory".equals(answer)) {
                Set<Integer> inventory = player.getInventory();
                if (inventory.isEmpty()) System.out.println("nothing in inventory");
                for (int item : inventory) {
                    System.out.println(game.getObjectById(item));
                }
            }

            if (answer.length()>5 && "throw".equals(answer.substring(0,5))) {
                String what = answer.substring(5).trim();

            }
        }
    }

}
