package gameWin;

import javax.swing.*;
import java.awt.*;

class ScorePanel extends JPanel implements ScoreListener {

    private JLabel scoreLabel;

    public ScorePanel() {
        scoreLabel = new JLabel("Score : 0");
        add(scoreLabel);
        addStyle();
        setSize(GamePanel.SCREEN_WIDTH, GamePanel.SCREEN_HEIGHT - 400);
        setLayout(new FlowLayout(FlowLayout.CENTER));
    }

    @Override
    public void updateScore(int score) {
        scoreLabel.setText("Score : " + score);
    }

    private void addStyle() {
        Color bgColor = new Color(55, 71, 79);
        Color textColor = Color.WHITE;
        Font font = new Font("Verdana", Font.PLAIN, 40);

        setBackground(bgColor);
        setForeground(textColor);
        setFont(font);
        scoreLabel.setBackground(bgColor);
        scoreLabel.setForeground(textColor);
        scoreLabel.setFont(font);
    }
}
