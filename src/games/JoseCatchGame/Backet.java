package games.JoseCatchGame;

import java.awt.*;

class Basket {
    private int x = 180;
    private int y = 700; //350
    private int direction = 0;
    private int speed = 10; // Default speed

    public void move() {
        x += direction * speed;
        if (x < 0) x = 0;
        if (x > 750) x = 750;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, 50, 35); // Draw the basket as a blue rectangle
    }

    public void setDirection(int direction) {
        this.direction = direction; // Set basket direction
    }

    public void resetPosition() {
        x = 180; // Reset basket position to the center
    }

    public boolean catchApple(Apple apple) {
        return apple.getY() > y && apple.getY() < y + 30 && apple.getX() > x && apple.getX() < x + 50;
    }

    public void speedUp() {
        speed = 20; // Increase basket speed
    }

    public void slowDown() {
        speed = 7; // Decrease basket speed
    }

    public void normalSpeed() {
        speed = 13; // Reset to normal speed
    }
}