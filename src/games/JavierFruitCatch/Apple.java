package games.JavierFruitCatch;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class Apple {
    private int x = (int)(Math.random() * (double)500.0F);
    private int y = 0;
    private int size = 15;
    private Color color;
    private int fallSpeed;

    public Apple() {
        this.color = Color.red;
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
        g.fillOval(this.x, this.y, this.size, this.size);
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
        this.changeColor();
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void changeColor() {
        Random rand = new Random();
        int colorChoice = rand.nextInt(3);
        switch (colorChoice) {
            case 0:
                this.color = Color.RED;
                break;

            case 1:
                this.color = Color.ORANGE;
        }

    }

    public void setFallSpeed(int fallSpeed) {
        this.fallSpeed = fallSpeed;
    }

    public void increaseFallSpeed() {
        ++this.fallSpeed;
    }

    static class GoldenApple extends Apple {
        public GoldenApple() {
        }

        public void draw(Graphics2D g) {
            g.setColor(Color.YELLOW);
            g.fillOval(this.getX(), this.getY(), this.getSize(), this.getSize());
        }

        public int getPointValue() {
            return 2;
        }
    }
}
