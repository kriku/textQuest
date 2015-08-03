package org.innopolis;

import org.innopolis.quest.*;

import java.io.IOException;
import java.util.Scanner;

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
                int requirement = currentLocation.getExits()[choice].getRequired();
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

        }
    }

}
