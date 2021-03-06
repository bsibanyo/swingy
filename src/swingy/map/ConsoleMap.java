package swingy.map;

import swingy.model.Enemy.Villian;
import swingy.model.Weapons.Weapon;
import swingy.model.Weapons.Armor;
import swingy.model.Weapons.Helm;
import swingy.file.ReadFromFile;
import swingy.model.CreateHero.Hero;

import swingy.map.ConsoleMap;

import java.util.*;

public class ConsoleMap {

    private static ArrayList<Villian> enemyArray = new ArrayList<>();
    private static ArrayList<Villian> tempArray = new ArrayList<>();
    private static int mapX;
    private static int mapY;
    private static int size;
    private static int[][] map;
    private static Hero hero;
    private static int enemies;
    private int xCoordinates;
    private int yCoordinates;
    private int level;
    private Villian villian = new Villian();
    private boolean set = false;

    public ConsoleMap(Hero hero){
        this.hero = hero;
    }

    public static void setMapSize(){
        size = (hero.getHeroStats().getLevel() - 1) * 5 + 10 - (hero.getHeroStats().getLevel() % 2);
        mapX = size;
        mapY = size;
        map = new int[size][size];
    }

    public void setEnemies(){
        switch (this.enemies = hero.getHeroStats().getLevel() * 8){

        }
    }

    public void setHeroPosition(){
        int x = 0;
        int y = 0;

        if((size % 2) == 1){
            x = (int) (size / 2);
            y = (int) (size / 2);
        }
        else if((size % 2) == 0){
            x = (size / 2);
            y = (size / 2);
        }
        this.xCoordinates = x;
        this.yCoordinates = y;
    }

    public void updatePosition(int xPos, int yPos){

        int previousX = this.xCoordinates;
        int previousY = this.yCoordinates;

        this.xCoordinates += xPos;
        if(this.xCoordinates < 0){
            this.xCoordinates = (int) (size/2);
            upgradeXP(1);
            hasWon();
            set = false;
            showMap();
        }
        else if (this.xCoordinates >= this.size){
            this.xCoordinates = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            showMap();
        }
        else
        {
            showMap();
        }

        this.yCoordinates += yPos;
        if(this.yCoordinates < 0){
            this.yCoordinates = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            showMap();
        }
        else if(this.xCoordinates >= this.size){
            this.yCoordinates = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            showMap();
        }
        else
        {
            showMap();
        }
    }

    public void showMap(){

        if(set == false){
            setMapSize();
            setHeroPosition();
            setEnemies();

            if(tempArray.isEmpty()){
                createEnemy();
            }
            else
            {
                enemyArray.addAll(tempArray);
            }
            set = true;
        }

//        INITIALIZE MAP ARRAY TO ZEROS
        for (int y = 0; y < size; y++){
            for (int x = 0; x < size; x++){
                map[y][x] = 0;
            }
        }

//        INITIALIZE OF ENEMY
        for(Villian villian : enemyArray){
            map[villian.getVertY()][villian.getVertX()] = villian.getTypeId();
        }

//        INITIALIZE OF HERO
        map[this.yCoordinates][this.xCoordinates] = 4;

//        CHECK IF HERO HAS CROSS PATH WITH THE ENEMY
        for(Villian villian : enemyArray){
            boolean meetEnemy = crossedEnemy(this.yCoordinates, this.xCoordinates, villian.getVertY(), villian.getVertX());
            if(meetEnemy == true){
                break;
            }
        }

        System.out.println("\n----------------------------");
        System.out.println("Level: " + hero.getHeroStats().getLevel() + "| " +
                "Attack: " + hero.getHeroStats().getAttack() + "| " +
                "Defence: " + hero.getHeroStats().getDefence() + "| " +
                "HP: " + hero.getHeroStats().getHitPoints() + "| " +
                "Experience: " + hero.getHeroStats().getExperience() + "|\n");

        for (int y = 0; y < mapY; y++){
            for(int x = 0; x < mapX; x++){
                switch(map[y][x]){
                    case 0:
                        System.out.print("|      |");
                        break;
                    case 1:
                        System.out.print("|  🍑  |");
                        break;
                    case 2:
                        System.out.print("|  🍑  |");
                        break;
                    default:
                        System.out.print("|  🍆  |");
                        break;
                }
            }
                System.out.println();
        }
    }

    public static void registerEnemy(Villian enemy){
        if(enemyArray.contains(enemy)){
            return;
        }
        enemyArray.add(enemy);
    }

    public static void removeEnemy(Villian enemy){
        if(enemyArray.contains(enemy)){
            enemyArray.remove(enemy);
        }
    }

    public void createEnemy(){
        for (int i = 0; i < this.enemies; i++){
            Random random = new Random();
            int ePosX = random.nextInt(size);
            int ePosY = random.nextInt(size);

            while(ePosY == this.yCoordinates || ePosX == this.xCoordinates){
                ePosX = random.nextInt(size);
                ePosY = random.nextInt(size);
            }
            villian = Player.newEnemy(hero);
            villian.setVPosition(ePosX, ePosY);
            registerEnemy(villian);
        }
    }

    public Villian getCrossedEnemy(){
        for(int i = 0; i < enemyArray.size(); i++){
            if(enemyArray.get(i).getVertY() == this.yCoordinates && enemyArray.get(i).getVertX() == this.xCoordinates){
                return enemyArray.get(i);
            }
        }
        return null;
    }

    public void upgradeXP(int type){
        if(type == 1){
            int xp;
            if(hero.getHeroStats().getExperience() < 1000){
                xp = 1000;
                hero.getHeroStats().setExperience(xp);
            }
            else if(hero.getHeroStats().getExperience() < 2000){
                xp = 2000;
                hero.getHeroStats().setExperience(xp);
            }
            else if(hero.getHeroStats().getExperience() < 3000){
                xp = 3000;
                hero.getHeroStats().setExperience(xp);
            }
            else if(hero.getHeroStats().getExperience() < 4000){
                xp = 4000;
                hero.getHeroStats().setExperience(xp);
            }
            else if(hero.getHeroStats().getExperience() < 5000){
                System.out.println("UNTIL NEXT TIME");
                System.exit(0);
            }
            hasWon();
        }
        else if(type == 2){
            hero.getHeroStats().setExperience(hero.getHeroStats().getExperience() + villian.getPower());
            ReadFromFile.updateFile(hero);
            hasWon();
        }
    }

    public void hasWon(){

        if(hero.getHeroStats().getExperience() > 1000 && hero.getHeroStats().getExperience() < 2000){
            this.level = 1;
        }
        else if (hero.getHeroStats().getExperience() >= 2000 && hero.getHeroStats().getExperience() < 3000) {
            this.level = 2;
        }
        else if (hero.getHeroStats().getExperience() >= 3000 && hero.getHeroStats().getExperience() < 4000) {
            this.level = 3;
        }
        else if (hero.getHeroStats().getExperience() >= 4000 && hero.getHeroStats().getExperience() < 5000) {
            this.level = 4;
        } else if (hero.getHeroStats().getExperience() == 5000) {
            this.level = 5;
        }

        if (this.level > hero.getHeroStats().getLevel()){
            hero.getHeroStats().setLevel(this.level);
            ReadFromFile.updateFile(hero);

            System.out.println("You're exploring the Map!\n");
            System.out.println("1: Continue");
            System.out.println("2: Quit");

            Scanner myScanner = new Scanner(System.in);
            while(myScanner.hasNextLine()){
                String readLine = myScanner.nextLine();

                if(readLine.matches("\\s*[1-2]\\s*")){
                    int choice = Integer.parseInt(readLine);

                    if(choice == 1){
                        enemyArray.removeAll(enemyArray);
                        Controller.run(hero);
                    }
                    else if(choice == 2){
                        System.out.println("THANK YOU FOR PLAYING");
                        System.exit(0);
                    }
                    else
                    {
                        System.out.println("\nInvalid input");
                        System.out.println("You are not a hacker\n");
                        System.out.println("LET'S TRY THIS AGAIN............\n");

                        System.out.println("You're exploring the Map!\n");
                        System.out.println("1: Continue\n");
                        System.out.println("2: Quit");
                    }
                }
            }
        }
        else if(this.level == hero.getHeroStats().getLevel()){
            enemyArray.removeAll(enemyArray);
        }
    }

    public boolean  crossedEnemy(int heroX, int heroY, int enemyX, int enemyY){
        if((heroX == enemyX) && (heroY == enemyY)){
            System.out.println("You encountered an Enemy\n");
            System.out.println("What do you wanna do?\n");
            System.out.println("--------------------------------\n");
            System.out.println("1: Run");
            System.out.println("2: Fight");

            Scanner myScanner = new Scanner(System.in);
            while(myScanner.hasNextLine()){
                String readLine = myScanner.nextLine();

                if(readLine.matches("\\s*[1-2]\\s*")){
                    int choice = Integer.parseInt(readLine);

                    if(choice == 1){

                            System.out.println("Enemy: You are a coward!!!");
                            System.out.println("Enemy: For that I took 10 XP\n");
                            System.out.println("--------------------------------");
                            System.out.println("Your current XP: " + (hero.getHeroStats().getExperience() - 10) + "\n");
                            showMap();
                            // updatePosition();
                        }
                    else if(choice == 2){
                        Villian crossed = getCrossedEnemy();
                        int won = Controller.fight(hero, crossed);
                        if(won == 1){
                            won(crossed);
                            removeEnemy(crossed);
                            return true;
                        }
                        else
                        {
                            dead();
                            break;
                        }
                    }
                    else
                    {
                        System.out.println("\nInvalid input");
                        System.out.println("You are not a hacker\n");
                        System.out.println("LET'S TRY THIS AGAIN............\n");

                        System.out.println("What do you wanna do?\n");
                        System.out.println("--------------------------------\n");
                        System.out.println("1: Run");
                        System.out.println("2: Fight");
                    }
                }
                else
                {
                    System.out.println("\nInvalid input");
                    System.out.println("You are not a hacker\n");
                    System.out.println("LET'S TRY THIS AGAIN............\n");

                    System.out.println("What do you wanna do?\n");
                    System.out.println("--------------------------------\n");
                    System.out.println("1: Run");
                    System.out.println("2: Fight");
                }
            }
        }
        return false;
    }

    public void won(Villian crossed){
        enemyArray.remove(crossed);
        upgradeXP(2);

        if(Controller.dropChance() == true){
            System.out.println("You killed the enemy, and he dropped down an artifact.\n");
            System.out.println("You can pickup enemy artifact (\" + crossed.getArtifacts().getType() + \n");
            System.out.println("1: Pick it up");
            System.out.println("2: Continue with the Adventure");

            Scanner myScanner =  new Scanner(System.in);
            while(myScanner.hasNextLine()){
                String readLine = myScanner.nextLine();

                if(readLine.matches("\\s*[1-2]\\s*")){
                    int choice = Integer.parseInt(readLine);

                    if(choice ==1 ){
                        String type = villian.getArtifacts().getType();

                        if(type.equals("Weapon")){
                            Weapon weapon = new Weapon("Weapon");
                            hero.setArtifacts(weapon);
                            hero.getHeroStats().setAttack(65);
                            ReadFromFile.updateFile(hero);
                            Controller.run(hero);
                        }
                        else if (type.equals("Armor")) {
                            Armor armor = new Armor("Armor");
                            hero.setArtifacts(armor);
                            hero.getHeroStats().setDefence(55);
                            ReadFromFile.updateFile(hero);
                            Controller.run(hero);
                            System.out.println("armor");
                        }
                        else if (type.equals("Helm")) {
                            Helm helm = new Helm("Helm");
                            hero.setArtifacts(helm);
                            hero.getHeroStats().setHitPoints(75);
                            ReadFromFile.updateFile(hero);
                            Controller.run(hero);
                            System.out.println("helm");
                        }
                    }
                    else if(choice == 2){
                        upgradeXP(2);
                    }
                }
                else {
                        System.out.println("\nInvalid input");
                        System.out.println("You are not a hacker\n");
                        System.out.println("LET'S TRY THIS AGAIN............\n");

                        System.out.println("You killed the enemy, and he dropped down an artifact.\n");
                        System.out.println("You can pickup enemy artifact (\" + crossed.getArtifacts().getType() + \n");
                        System.out.println("1: Pick it up");
                        System.out.println("2: Continue with the Adventure");
                }
            }
        }
        else{
            upgradeXP(2);
            System.out.println("<<<<<<<<GREAT.>>>>>>>>>>>>>");
            System.out.println("Battle Won.");
            System.out.println("550 XP EARNED!!!");

            try{
                Thread.sleep(1000);
            }
            catch(InterruptedException e){
                System.exit(0);
            }
            Controller.run(hero);
        }
    }

    public void dead(){
            System.out.println("You're DEAD......");
            System.out.println("GAME OVER");
            System.exit(0);
    }
}
