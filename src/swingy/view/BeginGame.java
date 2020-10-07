package swingy.view;

import swingy.map.Player;
import swingy.model.CreateHero.Hero;
import swingy.model.CreateHero.Stats;
import swingy.model.Weapons.Artifacts;
import swingy.model.Weapons.Armor;
import swingy.model.Weapons.Helm;
import swingy.model.Weapons.Weapon;
import swingy.file.WriteToFile;

public class BeginGame {

    private static int level;
    private static int attack;
    private static int defence;
    private static int hitPoints;
    private static int experience;
    private static String artifacts;
    private static String displayStats;
    private static Hero newHero = new Hero();
    private static Hero heroDB = new Hero();

    public static Hero addHero(String type, String player){
        artifacts = Artifacts.randomArtifacts();

        if(artifacts.equals("WEAPON")){
            Weapon weapon = new Weapon("Weapon");
            level = 1;
            attack = 100 + weapon.getAttack();
            defence = 100;
            hitPoints = 100;
            experience = 100;
            Stats stats = new Stats(type, level, attack, defence, hitPoints, experience);
            newHero = Player.newPlayer(type, player, stats, weapon);
            displayStats = type + " " + player + " " + level + " " + attack + " " + defence + " " + hitPoints + " " + experience + " " + artifacts;
        }
        else if(artifacts.equals("ARMOR")){
            Armor armor = new Armor("Armor");
            level = 1;
            attack = 100;
            defence = 100 + armor.getDefence();
            hitPoints = 100;
            experience = 100;
            Stats stats = new Stats(type, level, attack, defence, hitPoints, experience);
            newHero = Player.newPlayer(type, player, stats, armor);
            displayStats = type + " " + player + " " + level + " " + attack + " " + defence + " " + hitPoints + " " + experience + " " + artifacts;
        }
        else if(artifacts.equals("HELM")){
            Helm helm = new Helm("Helm");
            level = 1;
            attack = 100 + helm.getHitPoints();
            defence = 100;
            hitPoints = 100;
            experience = 100;
            Stats stats = new Stats(type, level, attack, defence, hitPoints, experience);
            newHero = Player.newPlayer(type, player, stats, helm);
            displayStats = type + " " + player + " " + level + " " + attack + " " + defence + " " + hitPoints + " " + experience + " " + artifacts;
        }

        WriteToFile.writeToFile(displayStats);
        return newHero;
    }

    public static Hero newHero(String player, long type){
        if(type == 1){
            return addHero("Deadpool", player);
        }
        else if(type == 2){
            return addHero("Hulk", player);
        }
        else
        {
            return null;
        }
    }

    public static Hero heroDB(String hero){
        String[] items = hero.split(" ");
        int i = 0;

        String type = items[0];
        String player = items[1];
        int level = Integer.parseInt(items[2]);
        int attack = Integer.parseInt(items[3]);
        int defence = Integer.parseInt(items[4]);
        int hitPoints = Integer.parseInt(items[5]);
        int experience = Integer.parseInt(items[6]);
        String artifacts = items[7];
        Stats stats = new Stats(type, level, attack, defence, hitPoints, experience);

        if(artifacts.equals("WEAPON")){
            Weapon weapon = new Weapon("Weapon");
            heroDB = Player.newPlayer(type, player, stats, weapon);
        }
        else if(artifacts.equals("ARMOR")){
            Armor armor = new Armor("Armor");
            heroDB = Player.newPlayer(type, player, stats, armor);
        }
        else if(artifacts.equals("HELM")){
            Helm helm = new Helm("Helm");
            heroDB = Player.newPlayer(type, player, stats, helm);
        }
        return heroDB;
    }

}
