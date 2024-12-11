package games.JoseCatchGame;

import java.awt.*;

class GoldenApple extends Apple {
    public GoldenApple() {
        super(); // Call the constructor of Apple
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.YELLOW); // Change color for golden apple
        g.fillOval(getX(), getY(), getSize(), getSize());
    }

    public int getPointValue() {
        return 2; // Golden apple gives 2 points
    }
}
