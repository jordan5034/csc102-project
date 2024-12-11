package games.JavierFruitCatch;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.*;

public class CatchGame extends JFrame implements ActionListener, KeyListener {
    private Timer timer;
    private Basket basket;
    private ArrayList<Apple> apples;
    private ArrayList<Blueberry> blueberries;
    private int score;
    private SpeedController speedController = new SpeedController(5);
    private long startTime;
    private final int GAME_DURATION = 60000;
    private boolean gameEnded = false;

    public CatchGame() {
        this.basket = new Basket(this.speedController);
        this.apples = new ArrayList();
        this.blueberries = new ArrayList();
        this.timer = new Timer(30, this);
        this.timer.start();
        this.addKeyListener(this);
        this.setSize(400, 400);
        this.setDefaultCloseOperation(3);


        GamePanel gamePanel = new GamePanel();
        this.add(gamePanel);
//        InfoPanel infoPanel = new InfoPanel();
//        this.add(infoPanel);
        this.startTime = System.currentTimeMillis();
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (!this.gameEnded) {
            this.basket.move();

            for (Apple apple : this.apples) {
                apple.fall();
                if (this.basket.catchApple(apple)) {
                    ++this.score;
                    apple.resetPosition();
                }
            }

            for (Blueberry blueberry : this.blueberries) {
                blueberry.fall();
                if (this.basket.catchBlueberry(blueberry)) {
                    this.score += 3;
                    blueberry.resetPosition();
                }
            }

            if (System.currentTimeMillis() - this.startTime >= 60000L) {
                this.gameEnded = true;
                this.timer.stop();
            }

            if (Math.random() < 0.05) {
                this.apples.add(new Apple());
            }

            if (Math.random() < 0.001) {
                this.apples.add(new Apple.GoldenApple());
                this.speedController.increaseSpeed();
            }

            if (Math.random() < 0.004) {
                this.blueberries.add(new Blueberry());
                this.speedController.decreaseSpeed();
            }

            this.repaint();
        }
    }

    public void keyPressed(KeyEvent e) {
        this.basket.setDirection(0);
        if (e.getKeyCode() == 37) {
            this.basket.setDirection(-1);
        } else if (e.getKeyCode() == 39) {
            this.basket.setDirection(1);
        } else if (e.getKeyCode() == 38) {
            this.speedController.increaseSpeed();
        } else if (e.getKeyCode() == 40) {
            this.speedController.decreaseSpeed();
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 37 || e.getKeyCode() == 39) {
            this.basket.setDirection(0);
        }

    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        new CatchGame();
    }

    public void updateScore(int points) {
        this.score += points;
        this.repaint();
    }


    private class GamePanel extends JPanel {
        private GamePanel() {
        }


        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            CatchGame.this.basket.draw(g2d);

            for (Apple apple : CatchGame.this.apples) {
                apple.draw(g2d);
            }

            for (Blueberry mango : CatchGame.this.blueberries) {
                mango.draw(g2d);
            }
            g2d.setFont(new Font("Arial", 1, 15));
            g2d.setColor(Color.RED);
            g2d.drawString("Score: " + CatchGame.this.score, 10, 50);
            int remainingTime = (int) (60000L - (System.currentTimeMillis() - CatchGame.this.startTime));
            int secondsRemaining = remainingTime / 1000;
            g2d.drawString("Time: " + secondsRemaining, 320, 50);
            if (CatchGame.this.gameEnded) {
                g2d.setFont(new Font("Arial", Font.BOLD, 30));
                g2d.setColor(Color.RED);
                g2d.drawString("Game Over!", 115, 180);
            }

        }
    }
}
