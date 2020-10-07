package swingy.map;

import swingy.file.ReadFromFile;
import swingy.view.ShowHero;
import swingy.model.CreateHero.Hero;
import swingy.model.Enemy.Villian;
import java.io.*;
import java.util.*;

public class Controller {

    public Controller(){}

    public static void run(Hero hero){
        ConsoleMap showMap = new ConsoleMap(hero);

        showMap.showMap();
        ShowHero.crossRoad();
        Console console = System.console();
        while(true){
            String consoleReader = console.readLine();

            if(consoleReader.matches("\\s*[1-5]\\s*")){
                int direction = Integer.parseInt(consoleReader);

                if(direction == 1){
                    showMap.updatePosition(0, -1);
                    showMap.showMap();
                    ShowHero.crossRoad();
                }
                else if(direction == 2){
                    showMap.updatePosition(0, 1);
                    showMap.showMap();
                    ShowHero.crossRoad();
                }
                else if(direction == 3){
                    showMap.updatePosition(-1, 0);
                    showMap.showMap();
                    ShowHero.crossRoad();
                }
                else if(direction == 4){
                    showMap.updatePosition(1, 0);
                    showMap.showMap();
                    ShowHero.crossRoad();
                }
                else if(direction == 5){
                    System.exit(0);
                }
            }
            else
            {
                System.out.println("\nInvalid input");
                System.out.println("You are not a hacker\n");
                System.out.println("LET'S TRY THIS AGAIN............\n");

                System.out.println("\n-------------------------------------------------\n");
                System.out.println("You are at the crossroad.\n Choose a direction to move.\n");
                System.out.println("1: North");
                System.out.println("2: South");
                System.out.println("3: East");
                System.out.println("4: West\n");
                System.out.println("5: QUIT");
                System.out.println("\n-------------------------------------------------\n");
            }
        }
    }

    public static boolean dropChance(){
        Random random = new Random();
        int chance = random.nextInt(2) + 1;

        if (chance == 1)
            return true;
        return false;
    }

    public static int fight(Hero hero, Villian enemy){

        int fight = 0;
        int won = 0;
        int hit = 0;
        Random random = new Random();

        if (dropChance() == true || hero.getHeroStats().getPower() > enemy.getPower()) {
            fight = 1;
        }

        if (hero.getHeroStats().getHitPoints() > 0) {
            while (hero.getHeroStats().getHitPoints() > 0 && enemy.getHitPoints() > 0) {
                System.out.println("Your HP: " + hero.getHeroStats().getHitPoints());
                System.out.println("Enemy HP: " + enemy.getHitPoints());
                if (fight == 0) {
                    hit = random.nextInt(30) + 1;
                    if (enemy.getHitPoints() > 0) {
                        hero.getHeroStats().setHitPoints(-hit);
                        ReadFromFile.updateFile(hero);
                        System.out.println("The Enemy attacked " + hit + " hits.");

                        if (hero.getHeroStats().getHitPoints() <= 0) {
                            won = 0;
                            break;
                        }
                        fight = 1;
                    }
                }
                else if (fight == 1) {
                    hit = random.nextInt(50) + 1;
                    if (hero.getHeroStats().getHitPoints() > 0) {
                        enemy.setHitPoints(-hit);
                        System.out.println("You attacked the Enemy and gave " + hit + " damage.");
                        if (enemy.getHitPoints() <= 0) {
                            won = 1;
                            break;
                        }
                        fight = 0;
                    }
                }
            }
        }
        else
            System.out.println("You are too weak to fight, go and REST!!!\n\n" +
                    "Your HP is " + hero.getHeroStats().getHitPoints());
        return won;
    }
}
