package gameWin;

import java.awt.Color;

import javax.swing.*;

public class GameWin extends JFrame {

    private JPanel mainPanel;
    private GamePanel gamePanel;
    private ScorePanel scorePanel;

    public GameWin(int unitSize, int delay) {
        setTitle("Snake Game");
        scorePanel = new ScorePanel();
        mainPanel = new JPanel();
        gamePanel = new GamePanel(unitSize, delay, scorePanel);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.add(scorePanel);
        mainPanel.add(gamePanel);
        setContentPane(mainPanel);

        addStyle();

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private void addStyle() {
        setBackground(Color.BLACK);
        setForeground(Color.WHITE);
    }
}
