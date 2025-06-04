package simulation.core;

import simulation.entities.Herbivore;
import simulation.entities.River;
import simulation.ui.Frame_Settings;

public abstract class Animals {
    protected static int herbivorePoints;
    protected static int predatorPoints;
    protected static int herbivoresKilled;
    protected static int predatorsKilled;
    private static final int MAX_X = Simulation.SCREEN_WIDTH - 32;
    private static final int MAX_Y = Simulation.SCREEN_HEIGHT - 32;
    private Position position;
    private boolean onRiver = false;
    public static final int SPRITE_SIZE = 48;
    protected static final int drownCheckCooldown = 250;
    protected static int drownCooldown;

    public static void addHerbivorePoint(int pointsToAdd) {
        herbivorePoints += pointsToAdd;
    }

    public static void addPredatorPoint(int pointsToAdd) {
        predatorPoints += pointsToAdd;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position pos) {
        this.position.setX(pos.getX());
        this.position.setY(pos.getY());
    }
    public static int getHerbivorePoints() {
        return herbivorePoints;
    }

    public static int getPredatorPoints() {
        return predatorPoints;
    }

    public boolean isOnRiver() {
        return onRiver;
    }


    public Animals(int x, int y) {
        this.position = new Position(x, y);
    }

    public Animals(){}

    public void checkForBorder(int stepSize) {
        Position pos = getPosition();
        if (pos.getX() < 0) {
            pos.setX(0);
        } else if (pos.getX() >= MAX_X) {
            pos.setX(MAX_X - stepSize);
        }

        if (pos.getY() < 0) {
            pos.setY(0);
        } else if (pos.getY() >= MAX_Y) {
            pos.setY(MAX_Y - stepSize);
        }
    }

    protected double distanceTo(Herbivore h, Position pos) {
        //Calculates distance to the target
        int dx = h.getPosition().getX() - pos.getX();
        int dy = h.getPosition().getY() - pos.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Method used for moving towards the target herbivore
     */
    protected Position moveToward(Herbivore target, int stepSize, Position myPos) {
        //Moves towards the closest herbivore
        Position targetPos = target.getPosition();

        int dx = Integer.compare(targetPos.getX(), myPos.getX()) * stepSize;
        int dy = Integer.compare(targetPos.getY(), myPos.getY()) * stepSize;

        if (River.isOnRiver(myPos.getX(), myPos.getY())) {
            dy += stepSize;
            dx /= 2;
            dy /= 2;

            int roll = Simulation.random.nextInt(100);
            drownCooldown++;
            if(roll < Frame_Settings.chanceofdrowning && drownCooldown > drownCheckCooldown  ) {
                drown();
            }
        }
        return new Position(myPos.getX() + dx, myPos.getY() + dy);

    }

    public void drown(){};


    protected Position Move(int stepSize) {
        return new Position(this.position.getX() + (int) (Math.random() * 10) * stepSize, this.position.getY() + (int) (Math.random() * 10) * stepSize);
    }
}
