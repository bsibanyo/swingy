package swingy;

import swingy.file.WriteToFile;
import swingy.view.Console;
import swingy.view.Gui;
public class main {

    public static void main(String[] args){
        try{
            WriteToFile.createFile();
            if(args.length != 1 || (!args[0].equals("console") && !args[0].equals("gui"))){
                System.out.println("Console OR Gui");
                System.exit(1);
            }
            if(args[0].equals("console")){
                Console.begin();
            }
            else
            {

            }
        }
        finally {
            WriteToFile.closeFile();
        }
    }
}
