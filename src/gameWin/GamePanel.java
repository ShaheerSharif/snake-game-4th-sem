package gameWin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import gameMenu.Menu;
import highscore.Highscore;
import score.ScoreListener;

class GamePanel extends JPanel implements ActionListener, KeyListener {

    public static final int SCREEN_WIDTH = 600;
    public static final int SCREEN_HEIGHT = 600;

    private final int unitSize;
    private final int gameUnits;
    private final int delay;
    private final int[] snakeX;
    private final int[] snakeY;
    private final String fontName = "Verdana";

    private int appleX;
    private int appleY;

    private int bodyParts = 6;
    private int score = 0;
    private char direction;
    private boolean running = false;
    private boolean paused = false;
    private Timer timer;
    private Random random;

    // * This will listen to the current score
    private ScoreListener scoreListener;

    public GamePanel(int unitSize, int delay, ScoreListener scoreListener) {
        this.unitSize = unitSize;
        this.delay = delay;
        this.scoreListener = scoreListener;
        gameUnits = (SCREEN_WIDTH * SCREEN_HEIGHT) / (unitSize * unitSize);
        snakeX = new int[gameUnits];
        snakeY = new int[gameUnits];
        random = new Random();

        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);
        startGame();
    }

    /**
     * The function starts a game by initializing the state, spawning an apple,
     * setting the game to
     * running, creating a timer, and starting the timer.
     */
    private void startGame() {
        initialState();
        spawnApple();
        running = true;
        timer = new Timer(delay, this);
        timer.start();
    }

    /**
     * The function sets the initial position and direction of a snake in a game.
     */
    private void initialState() {

        // * initial position of the snake
        for (int i = bodyParts; i >= 0; i--) {
            snakeX[i] = 0;
            snakeY[i] = unitSize * (bodyParts - i - 1);
        }

        // * initial direction of the snake
        direction = 'D';
    }

    /**
     * This function draws the game screen, including the snake, apple, and score.
     * 
     * @param g The Graphics object used for drawing on a component or image.
     */
    private void draw(Graphics g) {
        // * if game is not running then display game over
        if (!running) {
            gameOver(g);
            return;
        }

        // * color snake's head a darker shade of green
        g.setColor(new Color(45, 180, 0));
        g.fillRect(snakeX[0], snakeY[0], unitSize, unitSize);

        // * color the rest of the snake's body a lighter shade of green
        g.setColor(Color.GREEN);
        for (int i = 1; i < bodyParts; i++) {
            g.fillRect(snakeX[i], snakeY[i], unitSize, unitSize);
        }

        // * sets the color and dimension of the apple
        g.setColor(Color.RED);
        g.fillOval(appleX, appleY, unitSize, unitSize);
    }

    private void updateScore() {
        score++;

        if (scoreListener != null) {
            scoreListener.updateScore(score);
        }
    }

    /**
     * This function spawns an apple at a random location on the screen with a size
     * of UNIT_SIZE.
     */
    private void spawnApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / unitSize)) * unitSize;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / unitSize)) * unitSize;
    }

    /**
     * The function checks if the snake's head collides with an apple and increases
     * the body parts and
     * score if it does.
     */
    private void eatApple() {
        // * check if head collides with apple
        if (snakeX[0] == appleX && snakeY[0] == appleY) {
            bodyParts++;
            updateScore();
            Highscore.updateHighscore(score);
            spawnApple();
        }
    }

    /**
     * The function moves the snake's body by shifting its coordinates based on the
     * direction of
     * movement.
     */
    private void move() {
        // * shifting snake's body
        for (int i = bodyParts; i > 0; i--) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        switch (direction) {
            case 'U':
                snakeY[0] -= unitSize; // * shifts head up
                break;
            case 'D':
                snakeY[0] += unitSize; // * shifts head down
                break;
            case 'L':
                snakeX[0] -= unitSize; // * shifts head left
                break;
            case 'R':
                snakeX[0] += unitSize; // * shifts head right
        }

    }

    /**
     * The function checks for collisions between the snake's head and its body or
     * the borders of the
     * game panel, and stops the game if a collision occurs.
     */
    private void checkCollisions() {
        // * check if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if (snakeX[0] == snakeX[i] && snakeY[0] == snakeY[i]) {
                running = false;
            }
        }

        // * checks if head touches the borders of the panel
        if (snakeX[0] < 0 || snakeX[0] > (SCREEN_WIDTH - unitSize) || snakeY[0] < 0
                || snakeY[0] > (SCREEN_HEIGHT - unitSize)) {
            running = false;
        }

        // * if snake's head collides with it's body or with the borders stop the game
        if (!running) {
            timer.stop();
        }
    }

    /**
     * The function displays the game over message and the final score on the
     * screen.
     * 
     * @param g The Graphics object used for drawing on the screen.
     */
    private void gameOver(Graphics g) {
        final String strHighScore = "High Score : " + Highscore.readHighScore();
        final String strGameOver = "Game Over";
        final String strRestart = "To Restart Game Press 'r'";
        final String strMenu = "To Return To Menu Press 'm'";
        final Font smallFont = new Font(fontName, Font.PLAIN, 20);
        final Font mediumFont = new Font(fontName, Font.PLAIN, 40);
        final Font largeFont = new Font(fontName, Font.BOLD, 75);
        FontMetrics metrics;

        Highscore.readHighScore();

        // highscore text
        g.setColor(Color.WHITE);
        g.setFont(mediumFont);
        metrics = getFontMetrics(g.getFont());
        g.drawString(strHighScore, (SCREEN_WIDTH - metrics.stringWidth(strHighScore)) / 2,
                g.getFont().getSize() * 2 + 10);

        // game over text
        g.setFont(largeFont);
        metrics = getFontMetrics(g.getFont());
        g.drawString(strGameOver, (SCREEN_WIDTH - metrics.stringWidth(strGameOver)) / 2, SCREEN_HEIGHT / 2);

        // restart text
        g.setFont(smallFont);
        metrics = getFontMetrics(g.getFont());
        g.drawString(strRestart, (SCREEN_WIDTH - metrics.stringWidth(strRestart)) / 2, SCREEN_HEIGHT - 50);

        // menu text
        g.drawString(strMenu, (SCREEN_WIDTH - metrics.stringWidth(strMenu)) / 2, SCREEN_HEIGHT - 10);

    }

    /**
     * The function toggles the value of a boolean variable and calls another
     * function to pause the game.
     */
    private void togglePause() {
        // * If paused is true then set to false otherwise true
        paused = paused ? false : true;
        pauseGame();
    }

    /**
     * This function pauses or resumes a timer used in a game depending on
     * its current state.
     */
    private void pauseGame() {
        if (paused) {
            timer.stop();
        } else if (!timer.isRunning()) {
            timer.start();
        }
    }

    /**
     * The function resets the game by setting the score to 0 and the number of body
     * parts to 6.
     */
    private void resetGame() {
        score = 0;
        bodyParts = 6;
    }

    /**
     * This function closes the current window and returns to menu.
     */
    private void gotoMenu() {
        // * Get the parent window of the JPanel
        Window parentWindow = SwingUtilities.getWindowAncestor(this);

        // * If a parent window does exist then close it and open menu
        if (parentWindow != null) {
            parentWindow.dispose();
            new Menu();
        }
    }

    /**
     * This function overrides the paintComponent method in Java to draw grids and
     * other elements using
     * the Graphics object.
     * 
     * @param g The parameter "g" is an instance of the Graphics class, which is
     *          used to draw graphics
     *          on the component. It provides methods for drawing lines, shapes,
     *          text, images, and more. In this
     *          code snippet, the paintComponent method is being overridden to draw
     *          grids and other graphics on
     *          the component
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * This function performs actions when an event is triggered, including moving,
     * eating, and
     * checking for collisions, but only if a certain condition is met.
     * 
     * @param e The parameter "e" is an object of the ActionEvent class, which
     *          represents an action
     *          that occurred, such as a button press or menu selection. It is used
     *          in this method to determine
     *          if the game is currently running and to perform certain actions
     *          accordingly.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            eatApple();
            checkCollisions();
        }
        repaint();
    }

    /**
     * The function handles key inputs for controlling the movement of a snake in a
     * game, as well as pausing and resetting the game.
     * 
     * @param e KeyEvent object representing the key event that occurred.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        // * Used for handling character and non-character key inputs such as arrow keys
        int key = e.getKeyCode();

        // Moves the snake up if its not facing down and game is not paused
        if ((key == KeyEvent.VK_W || key == KeyEvent.VK_UP) && direction != 'D' && !paused) {
            direction = 'U';
        }

        // Moves the snake down if its not facing up and game is not paused
        else if ((key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) && direction != 'U' && !paused) {
            direction = 'D';
        }

        // Moves the snake left if its not facing right and game is not paused
        else if ((key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT) && direction != 'R' && !paused) {
            direction = 'L';
        }

        // Moves the snake right if its not facing left and game is not paused
        else if ((key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT) && direction != 'L' && !paused) {
            direction = 'R';
        }

        // Pauses the game
        else if (key == KeyEvent.VK_P) {
            togglePause();
        }

        // Resets the game and starts a new one
        else if (key == KeyEvent.VK_R && !running) {
            resetGame();
            startGame();
        }

        // Return to main menu
        else if (key == KeyEvent.VK_M && !running) {
            gotoMenu();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // * Used for handling character key inputs
        return;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        return;
    }
}
