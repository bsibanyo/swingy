package swingy.view;

import java.util.*;
import swingy.model.CreateHero.Hero;
import swingy.model.CreateHero.Stats;
import swingy.model.Weapons.Weapon;

public class Level extends ShowHero{

    public static int showDetails(long Choice, String player, Hero hero){
        System.out.println("\nWelcome to SWINGY " + (player) + "\n");
        if(Choice == 1){
            System.out.println("Character: Adventurous");
            System.out.println("\n--------------------------------------------------------------\n");
            System.out.println("Level: " + hero.getHeroStats().getLevel() + "\n");
            System.out.println("Attack: " + hero.getHeroStats().getAttack() + "\n");
            System.out.println("Defence: " + hero.getHeroStats().getDefence() + "\n");
            System.out.println("Experience: " + hero.getHeroStats().getExperience() + "\n");
            System.out.println("HP: " + hero.getHeroStats().getHitPoints() + "\n");
            System.out.println("\n--------------------------------------------------------------\n");
            gameChoice();
        }
        else if(Choice == 2){
            System.out.println("Character: Bulky");
            System.out.println("\n--------------------------------------------------------------\n");
            System.out.println("Level: " + hero.getHeroStats().getLevel() + "\n");
            System.out.println("Attack: " + hero.getHeroStats().getAttack() + "\n");
            System.out.println("Defence: " + hero.getHeroStats().getDefence() + "\n");
            System.out.println("Experience: " + hero.getHeroStats().getExperience() + "\n");
            System.out.println("HP: " + hero.getHeroStats().getHitPoints() + "\n");
            System.out.println("\n--------------------------------------------------------------\n");
            gameChoice();
        }

        int choice = 0;
        Scanner myScanner = new Scanner(System.in);
        Scanner enterScanner = new Scanner(System.in);

        while(myScanner.hasNextLine()){
            String lineReader = myScanner.nextLine();

            if(lineReader.matches("\\s*[1-2]\\s*")){
                choice = Integer.parseInt(lineReader);
                break;
            }
            else
            {
                System.out.println("\nInvalid input");
                System.out.println("You are not a hacker\n");
                System.out.println("Let's try this again..........\n");
                
                System.out.println("What do you wanna do?\n");
                System.out.println("1: START");
                System.out.println("2: Quit\n");
                // enterScanner.nextLine();
                // showDetails();
            }
        }
        return choice;
    }

    public static void gameChoice(){
        System.out.println("What do you wanna do?\n");
        System.out.println("1: START");
        System.out.println("2: Quit\n");
    }
}
