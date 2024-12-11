package games.JavierFruitCatch;

import java.awt.Color;
import java.awt.Graphics2D;

public class Blueberry {
    private int x = (int)(Math.random() * (double)500.0F);
    private int y = 0;
    private int size = 10;
    private Color color;
    private int fallSpeed;

    public Blueberry() {
        this.color = Color.blue;
        this.fallSpeed = 1;
    }

    public int getSize() {
        return this.size;
    }

    public void fall() {
        if (this.y > 400) {
            this.resetPosition();
        }

        this.y += this.fallSpeed;
    }

    public void draw(Graphics2D g) {
        g.setColor(this.color);
        g.fillOval(this.x, this.y, this.size, 10);
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void resetPosition() {
        this.x = (int)(Math.random() * (double)380.0F);
        this.y = 0;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFallSpeed(int fallSpeed) {
        this.fallSpeed = 50;
    }
}