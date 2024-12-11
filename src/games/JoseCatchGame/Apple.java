package games.JoseCatchGame;

import java.awt.*;

class Apple {
    private int x;
    private int y;
    private int size = 20;

    public Apple() {
        resetPosition();
    }

    public void fall() {
        y += 5;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.RED);
        g.fillOval(x, y, size, size);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size; // Added to access size from GoldenApple
    }

    public void resetPosition() {
        x = (int) (Math.random() * 780);
        y = 0;
    }
}

