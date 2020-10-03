package swingy.view;

import java.util.*;
import swingy.model.CreateHero.Hero;
import swingy.model.CreateHero.HeroStats;
import swingy.model.Weapons.Weapon;

public class Level extends ShowHero{

    public static int showDetails(long Choice, String player, Hero hero){
        System.out.println("Welcome to SWINGY" + player );
        if(Choice == 1){
            System.out.println("You're Fighter");
            System.out.println("Level: " + hero.getHeroStats().getLevel() + "\n");
            System.out.println("Attack: " + hero.getHeroStats().getAttack() + "\n");
            System.out.println("Defence: " + hero.getHeroStats().getDefence() + "\n");
            System.out.println("Experience: " + hero.getHeroStats().getExperience() + "\n");
            System.out.println("Hit Points: " + hero.getHeroStats().getHitPoints() + "\n");
            gameChoice();
        }
        else if(Choice == 1){
            System.out.println("You're Hero");
            System.out.println("Level: " + hero.getHeroStats().getLevel() + "\n");
            System.out.println("Attack: " + hero.getHeroStats().getAttack() + "\n");
            System.out.println("Defence: " + hero.getHeroStats().getDefence() + "\n");
            System.out.println("Experience: " + hero.getHeroStats().getExperience() + "\n");
            System.out.println("Hit Points: " + hero.getHeroStats().getHitPoints() + "\n");
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
                System.out.println("Invalid input\nPlease press Enter to Continue");
                enterScanner.nextLine();
            }
        }
        return choice;
    }

    public static void gameChoice(){
        System.out.println("What do you wanna do?\n");
        System.out.println("1: START\n");
        System.out.println("2: Quit");
    }
}
