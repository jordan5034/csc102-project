package games.JavierFruitCatch;

import java.awt.Color;
import java.awt.Graphics2D;

public class Basket {
    private int x = 180;
    private int y = 350;
    private int direction = 0;
    private SpeedController speedController;

    public Basket(SpeedController speedController) {
        this.speedController = speedController;
    }

    public void move() {
        int speed = this.speedController.getSpeed();
        this.x += this.direction * 10;
        if (this.x < 0) {
            this.x = 0;
        }

        if (this.x > 350) {
            this.x = 350;
        }

        this.x += this.direction * speed;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(this.x, this.y, 30, 10);
    }

    public int setDirection(int direction) {
        this.direction = direction;
        return direction;
    }

    public boolean catchApple(Apple apple) {
        return apple.getY() > this.y && apple.getY() < this.y + 30 && apple.getX() > this.x && apple.getX() < this.x + 50;
    }


    public boolean catchBlueberry(Blueberry blueberry) {
        return blueberry.getY() > this.y && blueberry.getY() < this.y + 30 && blueberry.getX() > this.x && blueberry.getX() < this.x + 50;
    }
}
