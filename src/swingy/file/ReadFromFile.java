package swingy.file;

import swingy.model.CreateHero.Hero;
import java.io.*;

public class ReadFromFile {
    public static int getLineCounter(){
        try{
            File file = new File("Heroes.txt");
            FileReader fileReader = new FileReader(file);
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
            lineNumberReader.skip(Long.MAX_VALUE);

            int count =  lineNumberReader.getLineNumber();
            lineNumberReader.close();
            return count;
        }
        catch (IOException e){
            e.getMessage();
        }
        return -1;
    }

    public static String[] readLine(){
        try{
            File file = new File("Heroes.txt");
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader((fileReader));
            String line = null;
            String items[] = new String[getLineCounter()];
            int index = 0;

            while((line = bufferedReader.readLine()) != null){
                items[index] = line;
                index++ ;
            }
            bufferedReader.close();
            return items;
        }
        catch (IOException e){
            e.getMessage();
        }
        return null;
    }

    public static void getHeroDB(){
        String items[];
        int index = 0;
        int counter = 1;
        items = readLine();


// \033 - ASCII escape character
// [H - move the cursor to the home position
// [J - erases the screen from the current line down to the bottom of the screen
        System.out.println("\033[H\033[2J");
        System.out.flush();
        System.out.println("CHOOSE A SAVED HERO\n");

        while(index < getLineCounter()){
            System.out.println(counter++ + ". " + items[index++]);
        }
    }

    public static String getHero(long hero){
        String items[] = new String[getLineCounter()];
        items = readLine();
        return items[(int) hero - 1];
    }

    public static void updateFile(Hero hero){
        try{
            String[] items = readLine();
            String deleteLine = null;
            String newLine = null;
            File file = new File("Heroes.txt");
            FileWriter fileWriter = new FileWriter(file);

            for (String line : items){
                if(line.contains(hero.getNewHero()) && line.contains(hero.getHeroStats().getHeroType())){
                    deleteLine = line;
                }
            }

            newLine = (hero.getHeroStats().getHeroType() + " " + hero.getNewHero() + " " +
                    Integer.toString(hero.getHeroStats().getLevel()) + " " +
                    Integer.toString(hero.getHeroStats().getAttack()) + " " +
                    Integer.toString(hero.getHeroStats().getDefence()) + " " +
                    Integer.toString(hero.getHeroStats().getHitPoints()) + " " +
                    Integer.toString(hero.getHeroStats().getExperience()) + " " +
                    hero.getArtifacts().getType().toUpperCase());

            if(newLine != null){
                for(String line : items){
                    if(line.equals(deleteLine)){
                        fileWriter.write(newLine + "\n");
                    }
                    else{
                        fileWriter.write(line + "\n");
                    }
                }
            }
            fileWriter.close();
        }
        catch(IOException e){
            System.out.println("ERROR UPDATING HERO IN FILE" + e);
        }
    }
}
