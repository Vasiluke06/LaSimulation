package simulation.core;

import simulation.entities.Herbivore;
import simulation.entities.River;

public abstract class Animals {
    protected static int herbivorePoints;
    protected static int predatorPoints;
    private Position position;
    private boolean onRiver = false;
    private static final int MAX_X = 1100;
    private static final int MAX_Y = 832;

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

    public boolean isOnRiver() {
        return onRiver;
    }

    public Animals(int x, int y) {
        this.position = new Position(x, y);
    }

    /*Not needed
    public void updateRiverStatus() {
        this.onRiver = River.isOnRiver(getPosition().getX(), getPosition().getY());
    }*/


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
        //Calculates distance to the closest herbivore
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
            dy += 1;
            dx /= 2;
            dy /= 2;
        }
        return new Position(myPos.getX() + dx, myPos.getY() + dy);

    }

    protected Position Move(int stepSize) {
        return new Position(this.position.getX() + (int) (Math.random() * 10) * stepSize, this.position.getY() + (int) (Math.random() * 10) * stepSize);
    }

    /**
     * Animal 2D position
     */

}
