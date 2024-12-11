package games.JoseCatchGame;

import java.awt.*;

class Lettuce extends Apple {
    public Lettuce() {
        super(); // Call the constructor of Apple
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.GREEN); // Lettuce is green
        g.fillOval(getX(), getY(), getSize(), getSize());
    }
}