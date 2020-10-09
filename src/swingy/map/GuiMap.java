package swingy.map;


import javax.swing.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Random;

import swingy.file.ReadFromFile;
import swingy.view.Gui;
import swingy.model.CreateHero.Hero;
import swingy.model.Enemy.Villian;

public class GuiMap extends JFrame {
    private static final long serialVersionUID = 42L;

    private static ArrayList<Villian> enemyArray = new ArrayList<>();
    private static ArrayList<Villian> tempArray = new ArrayList<>();
    private static int yCoordinates;
    private static int xCoordinates;
    private int[][] map;
    private int size, villian;
    private int xPos, yPos, previousX, previousY;
    private int level;
    private Hero hero;
    private Villian enemy = new Villian();
    private boolean set = false;
    private JTextArea textArea = new JTextArea();
    private JFrame frame;

    public GuiMap(Hero hero, JFrame frame) {
        this.hero = hero;
        this.frame = frame;
    }

    public void setMapSize() {
        size = (hero.getHeroStats().getLevel() - 1) * 5 + 10 - (hero.getHeroStats().getLevel() % 2);
        xCoordinates = size;
        yCoordinates = size;
        map = new int[size][size];
    }

    public void setEnemy() {
        this.villian = hero.getHeroStats().getLevel() * 8;
    }

    public void setHeroPosition() {
        int x = 0, y = 0;

        if ((size % 2) == 1) {
            x = (int) (size / 2);
            y = (int) (size / 2);
        } else if ((size % 2) == 0) {
            x = (size / 2);
            y = (size / 2);
        }
        this.xPos = x;
        this.yPos = y;
    }
    public void hasWon() {
        if (hero.getHeroStats().getExperience() > 1000 && hero.getHeroStats().getExperience() < 2000) {
            this.level = 1;
        } else if (hero.getHeroStats().getExperience() >= 2000 && hero.getHeroStats().getExperience() < 3000) {
            this.level = 2;
        } else if (hero.getHeroStats().getExperience() >= 3000 && hero.getHeroStats().getExperience() < 4000) {
            this.level = 3;
        } else if (hero.getHeroStats().getExperience() >= 4000 && hero.getHeroStats().getExperience() < 5000) {
            this.level = 4;
        } else if (hero.getHeroStats().getExperience() == 5000) {
            this.level = 5;
        }

        if (this.level > hero.getHeroStats().getLevel()) {
            hero.getHeroStats().setLevel(this.level);
            ReadFromFile.updateFile(hero);
            JOptionPane.showMessageDialog(null, "You've unlock a new map and level.");
            enemyArray.removeAll(enemyArray);
            textArea.append(this.level + "\n");
        } else if (this.level == hero.getHeroStats().getLevel()) {
            textArea.selectAll();
            textArea.replaceSelection("");
            tempArray.addAll(enemyArray);
            enemyArray.removeAll(enemyArray);
        }
    }


    public void updateHeroPosition(int xPos, int yPos) {
        this.previousX = this.xPos;
        this.previousY = this.yPos;
        this.xPos += xPos;
        if (this.xPos < 0) {
            this.xPos = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            MapDisplay();
        } else if (this.xPos >= this.size) {
            this.xPos = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            MapDisplay();
        } else {
            textArea.selectAll();
            textArea.replaceSelection("");
            MapDisplay();
        }

        this.yPos += yPos;
        if (this.yPos < 0) {
            this.yPos = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            MapDisplay();
        } else if (this.yPos >= this.size) {
            this.yPos = (int) (size / 2);
            upgradeXP(1);
            hasWon();
            set = false;
            MapDisplay();
        } else {
            textArea.selectAll();
            textArea.replaceSelection("");
            MapDisplay();
        }
    }


    public JTextArea MapDisplay() {

        if (set == false) {
            setMapSize();
            setHeroPosition();
            setEnemy();
            createEnemy();
            set = true;
        }

//  INITIALIZE MAP ARRAY TO ZERO

        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                map[y][x] = 0;
            }
        }

// ENEMY RANDOMIZING

        for (Villian villian : enemyArray) {
            map[villian.getVertY()][villian.getVertX()] = villian.getTypeId();
        }

// HERO INITIALIZATION

        map[this.yPos][this.xPos] = 4;

//  COLLIED WITH ENEMY

        for (Villian enemy : enemyArray) {
            boolean Enemy = crossedEnemy(this.yPos, this.xPos, enemy.getVertY(), enemy.getVertX());
            if (Enemy == true) {
                enemyArray.remove(enemy);
                set = false;
                MapDisplay();
                break;
            }

        }

        textArea.append("Level: " + String.valueOf(hero.getHeroStats().getLevel()) + " | " +
                "Attack: " + hero.getHeroStats().getAttack() + " | " +
                "Defence: " + hero.getHeroStats().getDefence() + " | " +
                "HP: " + String.valueOf(hero.getHeroStats().getHitPoints()) + " | " +
                "Experience: " + String.valueOf(hero.getHeroStats().getExperience()) + "\n\n");

        for (int y = 0; y < yCoordinates; y++) {
            for (int x = 0; x < xCoordinates; x++) {
                switch (map[y][x]) {
                    case 0:
                        textArea.append("|      |");
                        break;
                    case 1:
                        textArea.append("|  E  |");
                        break;
                    case 2:
                        textArea.append("|  E  |");
                        break;
                    default:
                        textArea.append("|  H  |");
                        break;
                }
            }
            textArea.append("\n");
        }

        return textArea;
    }

    public static void registerEnemy(Villian enemy) {
        if (enemyArray.contains(enemy))
            return;
        enemyArray.add(enemy);
    }


    public void createEnemy() {
        for (int i = 0; i < this.villian; i++) {
            Random random = new Random();
            int eposx = random.nextInt(size);
            int eposy = random.nextInt(size);
            while (eposy == this.yPos || eposx == this.xPos) {
                eposx = random.nextInt(size);
                eposy = random.nextInt(size);
            }
            enemy = Player.newEnemy(hero);
            enemy.setVPosition(eposx, eposy);
            registerEnemy(enemy);
        }
    }

    public Villian getCrossedVillian() {
        for (int i = 0; i < enemyArray.size(); i++) {
            if (enemyArray.get(i).getVertY() == this.yPos && enemyArray.get(i).getVertX() == this.xPos)
                return enemyArray.get(i);
        }
        return null;
    }

    public void upgradeXP(int type) {
        if (type == 1) {
            int xp;
            if (hero.getHeroStats().getExperience() < 1000) {
                xp = 1000;
                hero.getHeroStats().setExperience(xp);
            } else if (hero.getHeroStats().getExperience() < 2000) {
                xp = 2000;
                hero.getHeroStats().setExperience(xp);
            } else if (hero.getHeroStats().getExperience() < 3000) {
                xp = 3000;
                hero.getHeroStats().setExperience(xp);
            } else if (hero.getHeroStats().getExperience() < 4000) {
                xp = 4000;
                hero.getHeroStats().setExperience(xp);
            } else if (hero.getHeroStats().getExperience() < 5000) {
                textArea.append("            GAME COMPLETED.                   \n\n");
                Gui.gameOver();
            }
            hasWon();
        } else if (type == 2) {
            hasWon();
        }
    }

    public boolean crossedEnemy(int heroY, int heroX, int enemyY, int enemyX) {
        if ((heroX == enemyX) && (heroY == enemyY)) {
            enemy = getCrossedVillian();
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "You've encountered an Enemy,\n Do you wanna do?\n\n", "Fight = Yes <OR> Run = No ?", dialogButton);
            if (dialogResult == 0) {
                if (fight() == 1) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "DEAD\n\n<<<<       GAME OVER       >>>>>");
                    frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

                }
            } else {
                Random random = new Random();
                int run = random.nextInt(2) + 1;

                if (run == 1) {
                    textArea.selectAll();
                    textArea.replaceSelection("");
                    textArea.append("Weak\n\n");
                    this.yPos = this.previousY;
                    this.xPos = this.previousX;
                } else {
                    if (fight() == 1) {
                        enemyArray.remove(enemy);
                        upgradeXP(2);
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(null, "<<<<< DEAD >>>>>\n\n           GAME OVER           ");
                        frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                    }
                }

            }
        }
        return false;
    }


    public boolean dropChance() {
        Random random = new Random();
        int chance = random.nextInt(2) + 1;

        if (chance == 1)
            return true;
        return false;
    }

    public int fight() {
        int fight = 0;
        int won = 0;
        int hit = 0;
        Random random = new Random();

        if (dropChance() == true || hero.getHeroStats().getPower() > enemy.getPower()) {
            fight = 1;
        }

        if (hero.getHeroStats().getHitPoints() > 0) {
            while (hero.getHeroStats().getHitPoints() > 0 && enemy.getHitPoints() > 0) {
                if (fight == 0) {
                    hit = random.nextInt(20) + 1;
                    if (enemy.getHitPoints() > 0) {
                        hero.getHeroStats().setHitPoints(-hit);
                        ReadFromFile.updateFile(hero);

                        if (hero.getHeroStats().getHitPoints() <= 0) {
                            won = 0;
                            break;
                        }
                        fight = 1;
                    }
                } else if (fight == 1) {
                    hit = random.nextInt(50) + 1;
                    if (hero.getHeroStats().getHitPoints() > 0) {
                        enemy.setHitPoints(-hit);
                        if (enemy.getHitPoints() <= 0) {
                            won = 1;
                            break;
                        }
                        fight = 0;
                    }
                }
            }
        } else
            JOptionPane.showMessageDialog(null, "You weak\n\n           REST                ");
        return won;
    }
}

