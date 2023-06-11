package gameMenu;

import javax.swing.*;
import java.awt.*;

import highscore.Highscore;

class ResetHighscoreDialog extends JDialog {

    private JPanel mainPanel;
    private JPanel btnPanel;
    private JLabel prompt;
    private JButton yesBtn;
    private JButton cancelBtn;

    public ResetHighscoreDialog() {
        setTitle("Reset Highscore");
        mainPanel = new JPanel();
        btnPanel = new JPanel();
        prompt = new JLabel("Are you sure?");
        yesBtn = new JButton("Yes. I'm sure");
        cancelBtn = new JButton("No cancel that");

        btnPanel.add(yesBtn);
        btnPanel.add(cancelBtn);
        mainPanel.add(prompt);
        mainPanel.add(btnPanel);
        setContentPane(mainPanel);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));

        addStyle();

        yesBtn.addActionListener(e -> {
            Highscore.resetHighscore();
            dispose();
        });

        cancelBtn.addActionListener(e -> {
            dispose();
        });

        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setFocusable(true);
        setResizable(false);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void addStyle() {
        final Color bgColor = Color.BLACK;
        final Color textColor = Color.WHITE;
        final String fontName = "Verdana";
        final Font smallFont = new Font(fontName, Font.PLAIN, 15);

        setBackground(bgColor);
        mainPanel.setBackground(bgColor);
        btnPanel.setBackground(bgColor);
        prompt.setBackground(bgColor);
        prompt.setForeground(textColor);
        prompt.setFont(smallFont);
        yesBtn.setBackground(bgColor);
        yesBtn.setForeground(textColor);
        yesBtn.setFont(smallFont);
        cancelBtn.setBackground(bgColor);
        cancelBtn.setForeground(textColor);
        cancelBtn.setFont(smallFont);
    }
}
