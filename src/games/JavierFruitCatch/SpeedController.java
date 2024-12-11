package games.JavierFruitCatch;

public class SpeedController {
    private int speed;

    public SpeedController(int initialSpeed) {
        this.speed = initialSpeed;
    }

    public void increaseSpeed() {
        ++this.speed;
    }

    public void decreaseSpeed() {
        if (this.speed > 1) {
            this.speed -= 5;
        }

    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int speed) {
        if (speed > 0) {
            this.speed = speed;
        }

    }
}