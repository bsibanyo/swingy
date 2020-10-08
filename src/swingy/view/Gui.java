package swingy.view;

import swingy.model.CreateHero.*;
import swingy.file.ReadFromFile;
import swingy.file.WriteToFile;
import swingy.map.GuiMap;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame {

    private String heroData;
    private String player;
    private String artifacts;
    private String[] confirm = null;
    private int type;
    private static final long serialVersionUID = 42L;
    private final JFrame welcomeFrame = new JFrame("Player CREATION");
    private final JFrame playerFrame = new JFrame("SWINGY...\nThe Heroes playground");
    private final JFrame createFrame = new JFrame("Hero Selection");
    private final JFrame selectFrame = new JFrame("Select a Hero");
    private final JFrame statsFrame = new JFrame("Hero Status");
    private final JFrame gameFrame = new JFrame("GAME Progress.....");
    private static JFrame gameOverFrame = new JFrame("Game Completed");
    private final JRadioButton hulk = new JRadioButton("Hulk");
    private final JRadioButton deadpool = new JRadioButton("Deadpool");
    private final String[] items = ReadFromFile.readLine();
    private final JList heroList = new JList<>(items);
    private JLabel label, label1;
    private JTextField playerName;
    private JTextArea textArea;
    private JButton welcomeBtn, createPlayer, selectPlayer;
    private Hero hero = new Hero();
    private GuiMap map;


    public Gui (){
    }

    public void createFrame(){
        label = new JLabel("<<<< CREATE PLAYER >>>>");
        label.setBackground(Color.blue);
        label.setBounds(220,200, 200,30);
        label1 = new JLabel(" YOUR  NAME");
        label1.setBounds(210,240, 200,30);
        label.setBackground(Color.GREEN);
        playerName = new JTextField();
        playerName.setBounds(200, 280, 200, 30);
        playerName.setCaretColor(Color.CYAN);
        welcomeBtn = new JButton("BEGIN");
        welcomeBtn.setBounds(200, 320, 200, 30);
        welcomeBtn.setBackground(Color.red);
        welcomeFrame.add(label);
        welcomeFrame.setBackground(Color.yellow);
        welcomeFrame.add(label1);
        welcomeFrame.add(playerName);
        welcomeFrame.add(welcomeBtn);
        welcomeFrame.setSize(600,600);
        welcomeFrame.setLocationRelativeTo(null);
        welcomeFrame.setLayout(null);
        welcomeFrame.setVisible(true);
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        welcomeBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                player = playerName.getText();
                player = player.trim();

                if (player.length() > 0){
                    confirm = player.split("\\s");

                    if (confirm != null)
                        player = String.join("_", confirm);

                    if (player.isEmpty())
                        JOptionPane.showMessageDialog(null, "We need you name!");
                    else{
                        createPlayer();
                        welcomeFrame.setVisible(false);
                        welcomeFrame.dispose();
                    }
                }
                else
                    JOptionPane.showMessageDialog(null, "Please fill in the text area!");

            }
        });
    }

    public void welcomeToGui(){
        createPlayer = new JButton("CREATE PLAYER");
        createPlayer.setBackground(Color.yellow);
        createPlayer.setBounds(210,240, 200,30);
        selectPlayer = new JButton("SELECT PLAYER");
        selectPlayer.setBackground(Color.MAGENTA);
        selectPlayer.setBounds(210,280, 200,30);
        playerFrame.add(createPlayer);
        playerFrame.add(selectPlayer);
        playerFrame.setSize(600,600);
        playerFrame.setLocationRelativeTo(null);
        playerFrame.setLayout(null);
        playerFrame.setVisible(true);
        playerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createPlayer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                createFrame();
                playerFrame.dispose();
            }
        });

        selectPlayer.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                selectPlayer();
                playerFrame.setVisible(false);
                playerFrame.dispose();
            }
        });
    }

    public void createPlayer(){
        ButtonGroup group = new ButtonGroup();
        JButton enter;

        enter = new JButton("CONTINUE...");

        hulk.setBounds(210,240, 200,30);
        deadpool.setBounds(210,260, 200,30);
        enter.setBounds(210,300, 200,30);

        group.add(deadpool);
        group.add(hulk);

        createFrame.add(deadpool);
        createFrame.add(hulk);
        createFrame.add(enter);
        createFrame.setSize(600,600);
        createFrame.setLocationRelativeTo(null);
        createFrame.setLayout(null);
        createFrame.setVisible(true);
        createFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (hulk.isSelected())
                    type = 2;
                else if (deadpool.isSelected())
                    type = 1;
                heroStats();
                createFrame.setVisible(false);
                createFrame.dispose();
            }
        });

    }

    public void selectPlayer(){
        JLabel label;
        JButton enter, exit;

        label= new JLabel("SELECT EXISTING HERO");
        label.setBounds(20, 20, 200, 30);

        heroList.setBounds(20, 50, 350, 520);
        enter = new JButton("CONTINUE");
        enter.setBounds(400, 50, 100, 30);
        exit = new JButton("QUIT");
        exit.setBounds(400, 105, 100, 30);

        heroList.addListSelectionListener(new ListSelectionListener(){
            @Override
            public void valueChanged(ListSelectionEvent arg0) {
                heroData = heroList.getSelectedValue().toString();
                hero = BeginGame.heroDB(heroData);

            }
        });

        enter.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if (heroData == null)
                    JOptionPane.showMessageDialog(null, "Begin by selecting a hero!");
                else{
                    playGame();
                    selectFrame.setVisible(false);
                    selectFrame.dispose();
                }

            }
        });

        exit.addActionListener(e -> selectFrame.dispose());

        selectFrame.add(label);
        selectFrame.add(enter);
        selectFrame.add(exit);
        selectFrame.add(heroList);
        selectFrame.setSize(600, 600);
        selectFrame.setLocationRelativeTo(null);
        selectFrame.setLayout(null);
        selectFrame.setVisible(true);
        selectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

    public void heroStats(){
        hero = BeginGame.newHero(player, type);
        JLabel lvl1, lvl2, lvl3, lvl4, lvl5, lvl6, lvl7, lvl8;
        JButton enter;

        lvl1 = new JLabel("Player Name : " + player);
        lvl1.setBounds(200,220, 200,30);
        String heroType;
        lvl2 = new JLabel("Avatar : " + (heroType = hero.getHeroStats().getHeroType()));
        lvl2.setBounds(200,240, 200,30);
        int level;
        lvl3 = new JLabel("Level : " +  (level = hero.getHeroStats().getLevel()));
        lvl3.setBounds(200,260, 200,30);
        int attack;
        lvl4 = new JLabel("Attack : " + (attack = hero.getHeroStats().getAttack()));
        lvl4.setBounds(200,280, 200,30);
        int defense;
        lvl5 = new JLabel("Defence : " + (defense = hero.getHeroStats().getDefence()));
        lvl5.setBounds(200,300, 200,30);
        int hitpoints;
        lvl6 = new JLabel("HP : " + (hitpoints = hero.getHeroStats().getHitPoints()));
        lvl6.setBounds(200,320, 200,30);
        lvl7 = new JLabel("Artifacts : " + (artifacts = hero.getArtifacts().getType()));
        lvl7.setBounds(200,340, 200,30);
        lvl8 = new JLabel("CURRENT STATUS\n");
        lvl8.setBounds(200,200, 200,30);
        enter = new JButton("Continue");
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
                heroData = hero.getHeroStats().getHeroType() + " " +
                        player + " " + hero.getHeroStats().getLevel() + " " +
                        hero.getHeroStats().getAttack() + " " + hero.getHeroStats().getAttack() +
                        " " + hero.getHeroStats().getHitPoints() + " " + hero.getHeroStats().getExperience() +
                        " " + artifacts.toUpperCase();

                WriteToFile.writeToFile(heroData);
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

        textArea = map.MapDisplay();


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
                map.updateHeroPosition(0, -1);
            }
        });

        south.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(0, 1);
            }
        });

        west.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(1, 0);
            }
        });

        east.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                map.updateHeroPosition(-1, 0);
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

        label = new JLabel("Go on fly little Birdie\n Thank you for playing\n\nBYE");
        label.setBounds(200,240, 400,30);
        close = new JButton("END");
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
