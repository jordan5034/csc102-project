package games.JoseCatchGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CatchGame extends JFrame implements ActionListener, KeyListener {
    private Timer timer;
    private Basket basket;
    private ArrayList<Apple> apples;
    private ArrayList<Lettuce> lettuces;
    private int score = 0;
    private boolean gameOver = false;
    private boolean isPaused = false;
    private float backgroundColorValue = 1; // 0 for white, 1 for black
    private boolean increasing = true;

    public CatchGame() {
        basket = new Basket();
        apples = new ArrayList<>();
        lettuces = new ArrayList<>();

        timer = new Timer(10, this);
        timer.start();

        addKeyListener(this);
        setSize(800, 800);
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;

        // Set background color to black
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        Font scoreFont = new Font("Arial", Font.BOLD, 24);
        g2d.setFont(scoreFont);

        basket.draw(g2d);
        for (Apple apple : apples) {
            apple.draw(g2d);
        }
        for (Lettuce lettuce : lettuces) {
            lettuce.draw(g2d);
        }

        g2d.setColor(Color.WHITE);
        g2d.drawString("Score: " + score, 10, 175);
        g2d.drawString("Welcome to the Catch Game!", 10, 100);
        g2d.drawString("Collect 20 Apples to Win!!", 10, 125);
        g2d.drawString("Press P to Pause or to Resume game", 10, 150);

        if (isPaused) {
            g2d.setColor(Color.WHITE   );
            g2d.setFont(new Font("Arial", Font.BOLD, 48));
            g2d.drawString("Game Paused", 350, 400);
            g2d.setFont(new Font("Arial", Font.BOLD, 28));
            g2d.drawString("Press P to Resume", 320, 430);
        }


        if (gameOver) {
            g2d.setColor(Color.RED);
            g2d.setFont(new Font("Arial", Font.BOLD, 48));
            g2d.drawString("Game Over!", 260, 400);
            g2d.setFont(new Font("Arial", Font.PLAIN, 24));
            g2d.drawString("Press R to Restart", 320, 430);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!gameOver && !isPaused ) {
            basket.move();
            for (int i = apples.size() - 1; i >= 0; i--) {
                Apple apple = apples.get(i);
                apple.fall();
                if (basket.catchApple(apple)) {
                    if (apple instanceof GoldenApple) {
                        score += 2; // Golden apple gives 2 points
                    } else {
                        score++; // Normal apple gives 1 point
                    }
                    apple.resetPosition();
                }
                if (apple.getY() > getHeight()) {
                    apples.remove(i); // Remove apples that fall below the screen
                }
            }

            // Handle Lettuce objects
            for (int i = lettuces.size() - 1; i >= 0; i--) {
                Lettuce lettuce = lettuces.get(i);
                lettuce.fall();
                if (basket.catchApple(lettuce)) {
                    score++; // Lettuce gives 1 point
                    lettuce.resetPosition();
                    basket.speedUp(); // Speed up the basket
                    new Timer(5000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            basket.slowDown(); // Slow down after 5 seconds
                        }
                    }).start();
                    new Timer(5000, new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            basket.normalSpeed(); // Return to normal speed after 5 more seconds
                        }
                    }).start();
                }
                if (lettuce.getY() > getHeight()) {
                    lettuces.remove(i); // Remove lettuces that fall below the screen
                }
            }

            // Randomly generate apples, golden apples, and lettuces
            if (Math.random() < 0.05) {
                if (Math.random() < 0.3) { // 20% chance to spawn a Golden Apple
                    apples.add(new GoldenApple());
                } else if (Math.random() < 0.15) { // 10% chance to spawn Lettuce
                      lettuces.add(new Lettuce());
                }



                else {
                    apples.add(new Apple());
                }
            }

            // Game over when score reaches 20
            if (score >= 20) {
                gameOver = true;
            }

            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (gameOver && e.getKeyCode() == KeyEvent.VK_R) {
            restartGame(); // Restart the game when pressing 'R'
        } else if (!gameOver) {
            if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                basket.setDirection(-1); // Move basket left
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                basket.setDirection(1); // Move basket right
            }
            if (e.getKeyCode() == KeyEvent.VK_P) {
                if (isPaused) {
                    resume();  // Resume the game if paused
                } else {
                    pause();   // Pause the game if running
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (!gameOver) {
            basket.setDirection(0); // Stop basket movement
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    private void restartGame() {
        score = 0;
        apples.clear();
        lettuces.clear();
        gameOver = false;
        isPaused = false;
        basket.resetPosition();
        repaint();
    }

    //@Override
    public void pause() {
        isPaused = true;
        timer.stop();  // Stop the game timer
    }

   // @Override
    public void resume() {
        isPaused = false;
        timer.start();  // Start the game timer
    }

    public static void main(String[] args) {
        new CatchGame();
    }
    }

