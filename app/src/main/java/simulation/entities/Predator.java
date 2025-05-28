package simulation.entities;

import simulation.core.Animals;
import simulation.core.Movable;

public class Predator extends Animals implements Movable {
    private static final int STEP_SIZE = 14;
    private static final int POINTS_FOR_EATING = 15;

    /* ************************************** */

    /*Constructors*/
    public Predator(int x_pos, int y_pos) {
        super(x_pos, y_pos);
    }

    /*Implementation of Movable interface and movement logic*/
    @Override
    public void nextMove() {
        //Pathfinding to the closest herbivore
        Herbivore target = findClosestHerbivore();
        if (target != null) {
            moveToward(target);
            deleteHerbivorePredator(target);
        }

        super.checkForBorder();

        //River influence (downstream movement)
        if (isOnRiver()) {
            getPosition().setY(getPosition().getY() + 1);
        }
    }
    /*Methods used for defining interactions between predator and herbivore*/

    /**
     * Method used for finding the closest herbivore
     */
    private Herbivore findClosestHerbivore() {
        Herbivore closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (Herbivore h : simulation.core.Simulation.herbivore) {
            double dist = distanceTo(h);
            if (dist < closestDistance) {
                closestDistance = dist;
                closest = h;
            }
        }
        return closest;
    }

    /**
     * Method used for moving towards the target herbivore
     */
    private void moveToward(Herbivore target) {
        //Moves towards the closest herbivore
        Position myPos = getPosition();

        int dx = Integer.compare(target.getPosition().getX(), getPosition().getX());
        int dy = Integer.compare(target.getPosition().getY(), getPosition().getY());

        if (River.isOnRiver(myPos.getX(), myPos.getY())) {
            dy += 1;
            dx /= 2;
            dy /= 2;
        }

        getPosition().setX(getPosition().getX() + dx);
        getPosition().setY(getPosition().getY() + dy);
    }

    /**
     * Method used for deleting the herbivore and earning points
     */
    public void deleteHerbivorePredator(Herbivore target) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        int targetX = target.getPosition().getX();
        int targetY = target.getPosition().getY();

        if (Math.abs(x - targetX) < STEP_SIZE &&
                Math.abs(y - targetY) < STEP_SIZE) {

            getPosition().setX(targetX);
            getPosition().setY(targetY);

            simulation.core.Simulation.herbivore.remove(target);
            Animals.addPredatorPoint(POINTS_FOR_EATING);
        }
    }
    /* Also not sure if this is needed
    public void deletePredator(){
        //usual delete of predator
    } */

    /* ************************************** */

    /**
     * Method used for determining the range to the target
     */

    private double distanceTo(Herbivore h) {
        //Calculates distance to the closest herbivore
        int dx = h.getPosition().getX() - getPosition().getX();
        int dy = h.getPosition().getY() - getPosition().getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

}

