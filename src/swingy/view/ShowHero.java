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
                    System.out.println("\nInvalid input");
                    System.out.println("You are not a hacker\n");
                    // System.out.println("Please press Enter to continue...");
                    // enterScanner.nextLine();
                    // Welcome();
                }
            }
        }
        return playerName;
    }

    public static int playerSetUp(){
//        System.out.println("Hello " + playerName + ", let's start your adventure!");
        System.out.println("SWINGY" + ", Your adventure awaits!");
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
                System.out.println("\nInvalid input");
                System.out.println("You are not a hacker\n");
                System.out.println("LET'S TRY THIS AGAIN............\n");

                System.out.println("SWINGY" + ", Your adventure awaits!\n");
                System.out.println("1: Create a new Hero");
                System.out.println("2: Select an existing Hero");
                // enterScanner.nextLine();
                // playerSetUp();
            }
        }
        return choice;
    }

    public static int showHeroSelection()
    {
        System.out.println("");
        System.out.println("\n-------------------------------------------------\n");
        System.out.println("                  Select your AVATOR!\n");
        System.out.println("1: Deadpool");
        System.out.println("2: Hulk");
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
                System.out.println("\nInvalid input");
                System.out.println("You are not a hacker\n");
                System.out.println("LET'S TRY THIS AGAIN............\n");
                
                System.out.println("                  Select your AVATOR!\n");
                System.out.println("1: Deadpool");
                System.out.println("2: Hulk");
            }
        }
        return choice;
    }

    public static void crossRoad()
    {
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
