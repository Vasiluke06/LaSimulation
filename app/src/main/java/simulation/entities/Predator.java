package simulation.entities;

import simulation.core.Animals;
import simulation.core.Movable;
import simulation.core.Position;
import simulation.core.Simulation;
import simulation.ui.Frame_Simulation;

import java.awt.*;

public class Predator extends Animals implements Movable {
    private static final int STEP_SIZE = 12;
    private static final int POINTS_FOR_EATING = 15;

    /* ************************************** */

    /*Constructors*/
    public Predator(int x, int y) {
        //setPosition(x, y);
        super(x, y);
    }

    /*Implementation of Movable interface and movement logic*/
    @Override
    public void nextMove() {
        //Pathfinding to the closest herbivore
        Herbivore target = findClosestHerbivore();
        if (target != null) {
            this.setPosition(moveToward(target, STEP_SIZE, this.getPosition()));
            checkForBorder(STEP_SIZE);
            deleteHerbivorePredator(target);
        }

        super.checkForBorder(STEP_SIZE);
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
            double dist = distanceTo(h, this.getPosition());
            if (dist < closestDistance) {
                closestDistance = dist;
                closest = h;
            }
        }
        return closest;
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
            herbivoresKilled++;
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

    public static void newPredator(int x, int y){
        Simulation.predator.add(new Predator(x, y));
    }

    public void drawPredator(Graphics g){
        Position p = getPosition();
        g.drawImage(Simulation.predatorImg, p.getX(), p.getY(), 32, 32, null);
    }

    /**
     * Method used for initializing the predators
     */
    public static void initPredator(int numofpredators){
        for (int i = 0; i < numofpredators; i++){
            Position pos = new Position(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - 32), Simulation.random.nextInt(Simulation.SCREEN_HEIGHT - 32));

            for (; River.isOnRiver(pos.getX(), pos.getY()); ) {
                pos.setX(Simulation.random.nextInt(Frame_Simulation.WIDTH - 32));
            }

            Predator.newPredator(pos.getX(), pos.getY());
        };
    }

}

