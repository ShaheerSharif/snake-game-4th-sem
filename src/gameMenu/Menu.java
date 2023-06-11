package gameMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gameWin.GameWin;

public class Menu extends JFrame {

    private static final int SCREEN_WIDTH = 600;
    private static final int SCREEN_HEIGHT = 600;

    // * game map size
    private static final int SMALL = 50;
    private static final int MEDIUM = 25;
    private static final int LARGE = 15;

    // * game difficulty
    private static final int EASY = 200;
    private static final int NORMAL = 150;
    private static final int HARD = 100;

    private JPanel mainPanel;

    private JPanel startPanel;
    private JLabel title;
    private JLabel instructions;

    private JPanel endPanel;
    private JButton startGameBtn;

    private JPanel choicePanel;

    private JPanel mapPanel;
    private JLabel mapLabel;
    private ButtonGroup mapGroup;
    private JRadioButton mapSize_small;
    private JRadioButton mapSize_medium;
    private JRadioButton mapSize_large;

    private JPanel difficultyPanel;
    private JLabel difficultyLabel;
    private ButtonGroup difficultyGroup;
    private JRadioButton difficulty_easy;
    private JRadioButton difficulty_normal;
    private JRadioButton difficulty_hard;

    private JButton resetHighScoreBtn;

    public Menu() {
        super("Snake Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        mainPanel = new JPanel();
        startPanel = new JPanel();
        endPanel = new JPanel();
        choicePanel = new JPanel();
        startGameBtn = new JButton("Start");
        title = new JLabel("Snake Game");
        resetHighScoreBtn = new JButton("Reset HighScore");
        instructions = new JLabel("""
                <html>
                    To Pause Press 'p' <br>
                    To Restart Press 'r' <br>
                    To Return To Menu Press 'm' <br>
                    Controls : WASD or Arrow Keys <br>
                </html>
                """);

        startPanel.add(title);
        startPanel.add(instructions);
        addChooseMap();
        addChooseDifficulty();
        endPanel.add(startGameBtn);
        endPanel.add(resetHighScoreBtn);
        mainPanel.add(startPanel);
        mainPanel.add(choicePanel);
        mainPanel.add(endPanel);

        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        choicePanel.setLayout(new BoxLayout(choicePanel, BoxLayout.X_AXIS));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        instructions.setHorizontalAlignment(SwingConstants.CENTER);
        choicePanel.setAlignmentX(CENTER_ALIGNMENT);
        endPanel.setLayout(new BoxLayout(endPanel, BoxLayout.Y_AXIS));

        addBtnFunctionality();
        addStyle();

        setContentPane(mainPanel);
        getRootPane().setDefaultButton(startGameBtn);
        setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    private void addChooseMap() {
        mapPanel = new JPanel();
        mapLabel = new JLabel("Map Size");
        mapGroup = new ButtonGroup();

        mapSize_small = new JRadioButton("Small", true);
        mapSize_medium = new JRadioButton("Medium");
        mapSize_large = new JRadioButton("Large");

        mapGroup.add(mapSize_small);
        mapGroup.add(mapSize_medium);
        mapGroup.add(mapSize_large);

        mapPanel.add(mapLabel);
        mapPanel.add(mapSize_small);
        mapPanel.add(mapSize_medium);
        mapPanel.add(mapSize_large);

        choicePanel.add(mapPanel);

        mapPanel.setLayout(new BoxLayout(mapPanel, BoxLayout.Y_AXIS));
    }

    private void addChooseDifficulty() {
        difficultyPanel = new JPanel();
        difficultyLabel = new JLabel("Game Difficulty");
        difficultyGroup = new ButtonGroup();

        difficulty_easy = new JRadioButton("Easy", true);
        difficulty_normal = new JRadioButton("Normal");
        difficulty_hard = new JRadioButton("Hard");

        difficultyGroup.add(difficulty_easy);
        difficultyGroup.add(difficulty_normal);
        difficultyGroup.add(difficulty_hard);

        difficultyPanel.add(difficultyLabel);
        difficultyPanel.add(difficulty_easy);
        difficultyPanel.add(difficulty_normal);
        difficultyPanel.add(difficulty_hard);

        choicePanel.add(difficultyPanel);

        difficultyPanel.setLayout(new BoxLayout(difficultyPanel, BoxLayout.Y_AXIS));
    }

    private void addBtnFunctionality() {
        startGameBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int size = 0, speed = 0;

                // * After selecting map size
                if (mapSize_small.isSelected()) {
                    size = SMALL;
                } else if (mapSize_medium.isSelected()) {
                    size = MEDIUM;
                } else if (mapSize_large.isSelected()) {
                    size = LARGE;
                }

                // * After selecting difficulty
                if (difficulty_easy.isSelected()) {
                    speed = EASY;
                } else if (difficulty_normal.isSelected()) {
                    speed = NORMAL;
                } else if (difficulty_hard.isSelected()) {
                    speed = HARD;
                }

                dispose();
                new GameWin(size, speed);
            }
        });

        resetHighScoreBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ResetHighscoreDialog();
            }
        });
    }

    private void addStyle() {
        final Color bgColor = Color.BLACK;
        final Color textColor = Color.WHITE;
        final String fontName = "Verdana";
        final Font largeFont = new Font(fontName, Font.BOLD, 40);
        final Font mediumFont = new Font(fontName, Font.PLAIN, 20);
        final Font smallFont = new Font(fontName, Font.PLAIN, 15);

        setBackground(bgColor);

        // * JPanel styles
        mainPanel.setBackground(bgColor);
        startPanel.setBackground(bgColor);
        choicePanel.setBackground(bgColor);
        mapPanel.setBackground(bgColor);
        difficultyPanel.setBackground(bgColor);
        endPanel.setBackground(bgColor);

        // * JLabel styles
        title.setBackground(bgColor);
        title.setForeground(textColor);
        title.setFont(largeFont);
        instructions.setBackground(bgColor);
        instructions.setForeground(textColor);
        instructions.setFont(smallFont);
        mapLabel.setBackground(bgColor);
        mapLabel.setForeground(textColor);
        mapLabel.setFont(mediumFont);
        difficultyLabel.setBackground(bgColor);
        difficultyLabel.setForeground(textColor);
        difficultyLabel.setFont(mediumFont);

        // * JRadioButton styles
        mapSize_small.setBackground(bgColor);
        mapSize_small.setForeground(textColor);
        mapSize_small.setFont(smallFont);
        mapSize_medium.setBackground(bgColor);
        mapSize_medium.setForeground(textColor);
        mapSize_medium.setFont(smallFont);
        mapSize_large.setBackground(bgColor);
        mapSize_large.setForeground(textColor);
        mapSize_large.setFont(smallFont);
        difficulty_easy.setBackground(bgColor);
        difficulty_easy.setForeground(textColor);
        difficulty_easy.setFont(smallFont);
        difficulty_normal.setBackground(bgColor);
        difficulty_normal.setForeground(textColor);
        difficulty_normal.setFont(smallFont);
        difficulty_hard.setBackground(bgColor);
        difficulty_hard.setForeground(textColor);
        difficulty_hard.setFont(smallFont);

        // * JButton styles
        startGameBtn.setBackground(bgColor);
        startGameBtn.setForeground(textColor);
        startGameBtn.setFont(smallFont);
        resetHighScoreBtn.setBackground(bgColor);
        resetHighScoreBtn.setForeground(textColor);
        resetHighScoreBtn.setFont(smallFont);

        // * JPanel dimensions
        // startPanel.setBounds(0, 0, 50, 50);
        // choicePanel.setBounds(0, 0, 100, 100);

        // * JPanel positioning
        startPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        mapPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 210));
        difficultyPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));
    }
}
