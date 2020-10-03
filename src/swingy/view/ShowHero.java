package swingy.view;

import java.util.Scanner;

public class ShowHero
{

    public static String Welcome(){
        System.out.println("Please enter your name: ");
        Scanner myScanner = new Scanner(System.in);
        Scanner enterScanner = new Scanner(System.in);
        String playerName = null;

        while (myScanner.hasNextLine()){
            playerName = myScanner.nextLine();
            playerName = playerName.trim();

            if(playerName.length() > 0){
                String[] confirm = playerName.split("\\s");
                if(confirm != null){
                    playerName = String.join("_", confirm);
                    break;
                }
                else
                {
                    System.out.println("Invalid input\nPlease press Enter to Continue");
                    enterScanner.nextLine();
                }
            }
        }
        return playerName;
    }

    public static int playerSetUp(){
//        System.out.println("Hello " + playerName + ", let's start your adventure!");
        System.out.println("SWINGY" + ", let's start your adventure!");

        System.out.println("");
        System.out.println("\n-------------------------------------------------\n");
        System.out.println("");
        System.out.println("1: Create a new Hero");
        System.out.println("2: Select an existing Hero");
        System.out.println("\n-------------------------------------------------\n");

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

    public static int showHeroSelection()
    {
        System.out.println("Select your AVATOR!");
        System.out.println("");
        System.out.println("\n-------------------------------------------------\n");
        System.out.println("");
        System.out.println("1: Assassin");
        System.out.println("2: Ninja");
        System.out.println("\n-------------------------------------------------\n");

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

    public static void crossRoad()
    {
        System.out.println("\n-------------------------------------------------\n");
        System.out.println("You are at the crossroad.\nIf you go South, you will go back to the town.\n");
        System.out.println("1: Go north");
        System.out.println("2: Go east");
        System.out.println("3: Go south");
        System.out.println("4: Go west");
        System.out.println("\n-------------------------------------------------\n");
    }
}
