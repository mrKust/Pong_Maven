package model;

public class PlayerModel implements PPOGameObject {
    public static final int WIDTH = 10;
    public static final int HEIGHT = 80;

    public static final int UP = -1;
    public static final int STOP = 0;
    public static final int DOWN = 1;

    private double x;
    private double y;
    private double speed;

    private int direction;// >0 - down, =0 stop, <0 up

    PlayerModel(int x, int speed) {
        this.x = x;
        y = (double) PPOModel.HEIGHT / 2;
        direction = STOP;
        this.speed = speed;
    }

    void update() {
        if (direction != STOP) {
            y += speed * direction/Math.abs(direction);
        }

        if (y > PPOModel.HEIGHT - (double) HEIGHT / 2) {
            y = PPOModel.HEIGHT - (double) HEIGHT / 2;
        }

        if (y < HEIGHT / 2) {
            y = (double) HEIGHT / 2;
        }
    }

    void reset() {
        y = (double) PPOModel.HEIGHT / 2;
        direction = STOP;

    }

    public synchronized void setDirection(int dir) {
        this.direction = dir;
    }

    public synchronized double getX() {
        return x;
    }

    public synchronized void setX(double x) {
        this.x = x;
    }

    public synchronized double getY() {
        return y;
    }
    public synchronized void setY(double y) {
        this.y = y;
    }

    @Override
    public void setVspeed(double newval) {
        //unused
    }

    @Override
    public void setHspeed(double newval) {
        speed = newval;
    }

}
