package swingy.view;

import swingy.model.CreateHero.Hero;
import swingy.file.ReadFromFile;
import swingy.map.Controller;
import java.awt.*;
import java.util.*;

public class Console {

    public static void begin()
    {
        String player;
        int type;
        int createHero;
        int choice;
        int play;
        Hero hero;

        Scanner myScanner = new Scanner(System.in);
        Scanner enterScanner = new Scanner(System.in);

        try{
            createHero = ShowHero.playerSetUp();

            if(createHero == 1) {
                player = ShowHero.Welcome();
                type = ShowHero.showHeroSelection();
                hero = BeginGame.NewHero(player, type);
                play = Level.showDetails(type, player, hero);

                if(play == 1){
                    Controller.run(hero);
                }
                else
                {
                    System.out.println("\n-------------BYE-------------------\n");
                    System.exit(0);
                }
            }
            else if(createHero == 2){
                ReadFromFile.getHeroDB();
                while(myScanner.hasNextLine()){
                    String lineReader = myScanner.nextLine();
                    int lineCounter = lineReader.getLineCounter();

                    if(isDigit(lineReader) == true){
                        try{
                            int index = Integer.parseInt(lineReader);
                            if(index > 0 && index <= lineCounter){
                                choice = index;
                                break;
                            }
                        }
                        catch(Exception e){
                            System.out.println("Invalid input\nPlease press Enter to Continue");
                            enterScanner.nextLine();
                        }
                    }
                    else
                    {
                        System.out.println("Invalid input\nPlease press Enter to Continue");
                        enterScanner.nextLine();
                    }
                }
                hero = BeginGame.heroDB(ReadFromFile.getHero(choice));
                Controller.run(hero);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static boolean isDigit(String lineReader){
        for(char c : lineReader.toCharArray()){
            if(Character.isDigit(c) != true){
                return false;
            }
        }
        return true;
    }
}
