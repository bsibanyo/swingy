package swingy.file;

import java.io.*;

public class WriteToFile {

    public static File file = null;
    public static FileWriter fileWriter;

    public WriteToFile(){}

    public static void createFile(){
        try{
            if(file == null){
                file = new File("Heroes.txt");
                file.createNewFile();
            }

            fileWriter = new FileWriter(file, true);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void writeToFile(String ln){
        try{
            file = new File("Heroes.txt");
            fileWriter = new FileWriter(file, true);

            fileWriter.append(ln);
            fileWriter.append("\n");
            fileWriter.close();
            System.out.println(ln);
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void closeFile(){
        try{
            if(fileWriter != null){
                fileWriter.close();
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

}
