package swingy.map;

import swingy.model.CreateHero.Deadpool;
import swingy.model.CreateHero.Hulk;
import swingy.model.CreateHero.Hero;
import swingy.model.CreateHero.Stats;
import swingy.model.Enemy.Monster;
import swingy.model.Weapons.Artifacts;
import swingy.model.Weapons.Armor;
import swingy.model.Weapons.Helm;
import swingy.model.Weapons.Weapon;
import java.util.*;

public class Player {
    public static Hero newPlayer(String hero, String player, Stats stat, Artifacts artifacts){

        if(hero.equals("Hulk")){
            return new Hulk(player, stat, artifacts);
        }
        else if(hero.equals("Deadpool")){
            return new Deadpool(player, stat, artifacts);
        }
        else{
            return null;
        }
    }

    public static Monster newEnemy(Hero hero){
        Random random = new Random();
        int villian = random.nextInt(2) + 1;
        String artifacts = Artifacts.randomArtifacts();

        int level = 0;
        int attack = 0;
        int defence = 0;
        int hitPoints = 0;
        int experience = 0;

        if(villian == 1){
            if(artifacts.equals("WEAPON")){
                Weapon weapon = new Weapon("Weapon");
                level = hero.getHeroStats().getLevel();
                attack = 100 + weapon.getAttack();
                defence = 100;
                hitPoints = 100;
                experience = 0;
                return new Monster(level, attack, defence, hitPoints, experience, weapon);
            }
            else if(artifacts.equals("ARMOR")){
                Armor armor = new Armor("Armor");
                level = hero.getHeroStats().getLevel();
                attack = 100;
                defence = 100 + armor.getDefence();
                hitPoints = 100;
                experience = 0;
                return new Monster(level, attack, defence, hitPoints, experience, armor);
            }
            else if(artifacts.equals("HELM")){
                Helm helm = new Helm("Helm");
                level = hero.getHeroStats().getLevel();
                attack = 100 + helm.getHitPoints();
                defence = 100;
                hitPoints = 100;
                experience = 0;
                return new Monster(level, attack, defence, hitPoints, experience, helm);
            }
        }
        else if (villian == 2){
            if (artifacts.equals("WEAPON")){
                Weapon weapon = new Weapon("Weapon");
                level = hero.getHeroStats().getLevel();
                attack = 100 + weapon.getAttack();
                defence = 100;
                hitPoints = 100;
                experience = 0;
                return new Monster(level, attack, defence, hitPoints, experience, weapon);
            }
            else if (artifacts.equals("ARMOR")){
                Armor armor = new Armor("Armor");
                level = hero.getHeroStats().getLevel();
                attack = 100;
                defence = 100 + armor.getDefence();
                hitPoints = 100;
                experience = 0;
                return new Monster(level, attack, defence, hitPoints, experience, armor);
            }
            else if (artifacts.equals("HELM")){
                Helm helm = new Helm("Helm");
                level = hero.getHeroStats().getLevel();
                attack = 100 + helm.getHitPoints();
                defence = 100;
                hitPoints = 100;
                experience = 0;
                return new Monster(level, attack, defence, hitPoints, experience, helm);
            }
        }
        return null;
    }

}
