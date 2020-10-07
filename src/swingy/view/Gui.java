package swingy.view;

import swingy.model.CreateHero.*;
import swingy.view.BeginGame;
import swingy.file.ReadFromFile;
import swingy.file.WriteToFile;
import swingy.map.GuiMap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame{

    private String player;
    private String artifacts;
    private String heroDB;
    private String[] inPut = null;
    private int type;
    private static final long serialVersionUID = 42L;
    private final JFrame welcomeFrame = new JFrame("Create Player");
    private final JFrame playerFrame = new JFrame("Welcome to SWING");
    private final JFrame createFrame = new JFrame("Create HERO");
    private final JFrame selectFrame = new JFrame("Select HERO");
    private final JFrame statsFrame = new JFrame("HERO Status");
    private final JFrame gameFrame = new JFrame("Progress......");
    private final static JFrame gameOverFrame = new JFrame("COMPLETE");
    private final JRadioButton deadpool = new JRadioButton("Deadpool");
    private final JRadioButton hulk = new JRadioButton("Hulk");
    private final String[] items = ReadFromFile.readLine();
    private final JList heroList = new JList<>(items);
    private JLabel labelCreatePlayer, labelName;
    private JTextField playerName;
    private JTextArea textArea;
    private JButton welcomeButton, createPlayer, selectPlayer;
    private Hero hero = new Hero();
    private GuiMap map;


    public Gui(){}

    public void makeFrame(){

        labelCreatePlayer = new JLabel("CREATE PLAYER");
        labelCreatePlayer.setBackground(Color.blue);
        labelCreatePlayer.setBounds(220,200, 200,30);

        labelName = new JLabel("PLEASE ENTER YOUR NAME");
        labelName.setBounds(210,240, 200,30);
        labelName.setBackground(Color.green);

        playerName =  new JTextField();
        playerName.setBounds(200, 280, 200, 30);
        playerName.setCaretColor(Color.cyan);

        welcomeButton = new JButton("ENTER");
        welcomeButton.setBounds(200, 320, 200, 30);
        welcomeButton.setBackground(Color.red);

        welcomeFrame.add(labelCreatePlayer);
        welcomeFrame.setBackground(Color.yellow);
        welcomeFrame.add(labelName);
        welcomeFrame.add(playerName);
        welcomeFrame.add(welcomeButton);
        welcomeFrame.setSize(600, 600);
        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setLayout(null);
        welcomeFrame.setVisible(true);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                player = playerName.getText();
                player = player.trim();

                if(player.length() > 0)
                {
                    inPut = player.split("\\s");
                {
                        if(inPut != null)
                        {
                            player =String.join("_", inPut);
                        }
                        else if(player.isEmpty())
                        {
                            JOptionPane.showConfirmDialog(null, "Please enter your name");
                        }
                        else
                        {
                            createPlayer();
                            welcomeFrame.setVisible(false);
                            welcomeFrame.dispose();
                        }
                    }
                }
                else
                {
                    JOptionPane.showConfirmDialog(null, "No blank spaces allowed!!!");
                }
            }
        });
    }

    public void guiWelcomeScreen() {
        createPlayer = new JButton("CREATE A PLAYER");
        createPlayer.setBackground(Color.yellow);
        createPlayer.setBounds(100, 100, 600, 250);

        selectPlayer = new JButton("CHOOSE A PLAYER");
        selectPlayer.setBackground(Color.magenta);
        selectPlayer.setBounds(100, 100, 600, 250);

        playerFrame.add(createPlayer);
        playerFrame.add(selectPlayer);
        playerFrame.setSize(600, 600);
        playerFrame.setLocationRelativeTo(null);
        playerFrame.setLayout(null);
        playerFrame.setVisible(true);
        playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                makeFrame();
                playerFrame.dispose();
            }
        });

        selectPlayer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPlayer();
                playerFrame.setVisible(false);
                playerFrame.dispose();
            }
        });
    }

        public void createPlayer() {

            ButtonGroup buttonGroup = new ButtonGroup();
            JButton enter;

            enter = new JButton("CONTINUE.....");
            deadpool.setBounds(210,240, 200,30);
            hulk.setBounds(210,280, 200,30);
            enter.setBounds(210,280, 200,30);

            buttonGroup.add(deadpool);
            buttonGroup.add(hulk);

            createFrame.add(deadpool);
            createFrame.add(hulk);
            createFrame.add(enter);
            createFrame.setSize(600, 600);
            createFrame.setLocationRelativeTo(null);
            createFrame.setLayout(null);
            createFrame.setVisible(true);
            createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }

        public void selectPlayer(){

            JLabel labelSelectPlayer;
            JButton enter, exit;

            labelSelectPlayer = new JLabel("SELECT A SAVED PLAYER");
            labelSelectPlayer.setBounds(20, 20, 200, 30);

            heroList.setBounds(20, 50, 350, 520);
            enter = new JButton("Continue");
            enter.setBounds(400, 50, 100, 30);
            exit = new JButton("Quit Game");
            exit.setBounds(400, 105, 100, 30);

            heroList.addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    heroDB = heroList.getSelectedValue().toString();
                    hero = BeginGame.heroDB(heroDB);
                }
            });


            enter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    if(heroDB == null)
                    {
                        JOptionPane.showMessageDialog(null, "Select a HERO first!");
                    }
                    else
                        {
                            playGame();
                            selectFrame.setVisible(false);
                            selectFrame.dispose();
                        }
                }
            });

            exit.addActionListener(e -> selectFrame.dispose());

            selectFrame.add(labelSelectPlayer);
            selectFrame.add(enter);
            selectFrame.add(exit);
            selectFrame.add(heroList);
            selectFrame.setSize(600, 600);
            selectFrame.setLocationRelativeTo(null);
            selectFrame.setLayout(null);
            selectFrame.setVisible(true);
            selectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void Stats(){
        hero = BeginGame.newHero(player, type);
        JLabel lvl1, lvl2, lvl3, lvl4, lvl5, lvl6, lvl7, lvl8, lvl9;
        JButton enter;

        lvl8 = new JLabel("YOUR STATUS");
        lvl8.setBounds(200,200, 200,30);

        lvl1 = new JLabel("HERO: " + player);
        lvl1.setBounds(200, 220, 200, 30);
        String heroType;

        lvl2 = new JLabel("Hero: " + (heroType = hero.getHeroStats().getHeroType()));
        lvl2.setBounds(200,240, 200,30);

        int level;
        lvl3 = new JLabel("Level: " +  (level = hero.getHeroStats().getLevel()));
        lvl3.setBounds(200,260, 200,30);

        int attack;
        lvl4 = new JLabel("Attack: " + (attack = hero.getHeroStats().getAttack()));
        lvl4.setBounds(200,280, 200,30);

        int defense;
        lvl5 = new JLabel("Defense: " + (defense = hero.getHeroStats().getDefence()));
        lvl5.setBounds(200,300, 200,30);

        int hitPoints;
        lvl6 = new JLabel("Hit Points: " + (hitPoints = hero.getHeroStats().getHitPoints()));
        lvl6.setBounds(200,320, 200,30);

        lvl7 = new JLabel("Artifact: " + (artifacts = hero.getArtifacts().getType()));
        lvl7.setBounds(200,340, 200,30);
        enter = new JButton("Enter");
        enter.setBounds(200,370, 200,30);

        statsFrame.add(lvl1);
        statsFrame.add(lvl2);
        statsFrame.add(lvl3);
        statsFrame.add(lvl4);
        statsFrame.add(lvl5);
        statsFrame.add(lvl6);
        statsFrame.add(lvl7);
        statsFrame.add(lvl8);
        statsFrame.add(enter);
        statsFrame.setSize(600, 600);
        statsFrame.setLocationRelativeTo(null);
        statsFrame.setLayout(null);
        statsFrame.setVisible(true);
        statsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                heroDB = hero.getHeroStats().getHeroType() + " " +
                        player + " " + hero.getHeroStats().getLevel() + " " +
                        hero.getHeroStats().getAttack() + " " + hero.getHeroStats().getAttack() +
                        " " + hero.getHeroStats().getHitPoints() + " " + hero.getHeroStats().getExperience() +
                        " " + artifacts.toUpperCase();

                WriteToFile.writeToFile(heroDB);
                WriteToFile.closeFile();
                playGame();
                statsFrame.setVisible(false);
                statsFrame.dispose();
            }
        });
    }

    public void playGame(){
        JButton north, south, east, west;

        map = new GuiMap(hero, gameFrame);

        textArea = map.showMap();


        north = new JButton("NORTH");
        north.setBounds(20,580, 100, 30);
        south = new JButton("SOUTH");
        south.setBounds(140,580, 100, 30);
        east = new JButton("EAST");
        east.setBounds(280,580, 100, 30);
        west = new JButton("WEST");
        west.setBounds(400,580, 100, 30);

        north.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updatePosition(0, -1);
            }
        });

        south.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updatePosition(0, 1);
            }
        });

        east.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updatePosition(1, 0);
            }
        });

        west.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updatePosition(-1, 0);
            }
        });

        textArea.setBounds(20,90, 800, 480);
        gameFrame.add(textArea);
        gameFrame.add(north);
        gameFrame.add(south);
        gameFrame.add(east);
        gameFrame.add(west);
        gameFrame.setSize(850, 650);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setLayout(null);
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void gameOver(){
        JLabel label;
        JButton close;

        label = new JLabel("ADVENTURE TIME");
        label.setBounds(200,240, 400,30);
        close = new JButton("GAME COMPLETE....");
        close.setBounds(210,280, 200,30);

        close.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent arg0) {
                gameOverFrame.dispose();
                System.exit(0);
            }
        });

        gameOverFrame.add(label);
        gameOverFrame.add(close);
        gameOverFrame.setSize(600, 600);
        gameOverFrame.setLocationRelativeTo(null);
        gameOverFrame.setLayout(null);
        gameOverFrame.setVisible(true);
        gameOverFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
