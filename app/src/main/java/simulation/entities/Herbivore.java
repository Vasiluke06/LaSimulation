package simulation.entities;

import simulation.core.Animals;
import simulation.core.Movable;
import simulation.core.Plants;
import simulation.core.Simulation;

import java.awt.*;

import java.awt.image.ImageObserver;

public class Herbivore extends Animals implements Movable {
    private final int STEP_SIZE = 10;
    private final int POINTS_FOR_REPRODUCTION = 6;
    private final int POINTS_FOR_EATING = 8;

    private boolean isFed = true;  /* **********Only for testing, should be changed to FALSE or Randomized********/
    private Herbivore mate;
    /**
     * Getters, setters and constructors
     */
    public boolean isFed() {
        return isFed;
    }

    public void setFed(boolean fed) {
        isFed = fed;
    }

    public Herbivore(int x, int y) {
       // setPosition(x, y);
        super(x, y);
    }
    /**
     * Implementation of Movable interface and movement logic
     */
    @Override
    public void nextMove() {
        if (isFed) {
            if (mate == null) {
                findMate();
            }

            if (mate != null) {
                this.setPosition(moveToward(mate, STEP_SIZE, this.getPosition()));
                checkForBorder(STEP_SIZE);
                herbivoreReproduction(mate);
            } else {
                Move(STEP_SIZE);
            }
        } else {
            getToThePlant();
        }
    }
    /*Herbivore-Herbivore interactions*/
    /**
     * Method used for finding the closest fed herbivore
     */
    private void findMate() {
        double closestDistance = Double.MAX_VALUE;

        for (Herbivore other : Simulation.herbivore) {
            if (other != this && other.isFed()) {
                double dist = distanceTo(other, this.getPosition()); //
                if (dist < closestDistance) {
                    closestDistance = dist;
                    mate = other;
                }
            }
        }
    }
    /**
     * Method used for reproduction of herbivores
     */
    public void herbivoreReproduction(Herbivore mate){
       if (this.distanceTo(mate, this.getPosition()) < STEP_SIZE) {
           int x = (this.getPosition().getX() + mate.getPosition().getX()) / 2;
           int y = (this.getPosition().getY() + mate.getPosition().getY()) / 2;


           Simulation.herbivore.add(new Herbivore(x, y));
           this.setFed(false);
           mate.setFed(false);
           mate = null;
           Animals.addHerbivorePoint(POINTS_FOR_REPRODUCTION);
        }
    }

 //   Kind of unsure if this is still needed
    public static void newHerbivore(int xPos, int yPos){
        Simulation.herbivore.add(new Herbivore(xPos, yPos));
    }
/*
    public void deleteHerbivore(){

    } */

    /* ************************************** */

    /*Herbivore-Plant interactions*/
    /**
     * Method used for finding the closest plant
     */
    private Plants findFood(){
        Plants closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (Plants other : Simulation.plants) {
            double dist = distanceToPlant(other.getXPos(),other.getYPos());
            if (dist < closestDistance) {
                closestDistance = dist;
                closest = other;
            }
        }
        return closest;
    }

    /**
     * Method used for "eating" the target plant
     */
    public void getToThePlant() {
        Plants target = findFood();
        if (target == null) return;

        Position myPos = getPosition();
        int targetX = target.getXPos();
        int targetY = target.getYPos();

        int dx = Integer.compare(targetX, myPos.getX())*STEP_SIZE;
        int dy = Integer.compare(targetY, myPos.getY())*STEP_SIZE;

        if (River.isOnRiver(myPos.getX(), myPos.getY())) {
            dy += 1;
            dx /= 2;
            dy /= 2;
        }

        myPos.setX(myPos.getX() + dx);
        myPos.setY(myPos.getY() + dy);

        checkForBorder(STEP_SIZE);

        if (Math.abs(myPos.getX() - targetX) < STEP_SIZE &&
                Math.abs(myPos.getY() - targetY) < STEP_SIZE) {

            myPos.setX(targetX);
            myPos.setY(targetY);

            Simulation.plants.remove(target);
            setFed(true);
            Animals.addHerbivorePoint(POINTS_FOR_EATING);
        }
    }

    /* ************************************** */

    /*Methods used for determining the range to the target*/

    private double distanceToPlant(int x, int y) {
        int dx = x - getPosition().getX();
        int dy = y - getPosition().getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    /* ************************************** */


    /*Method for drawing a herbivore*/
    public void drawHerbivore(Graphics g){
        Position p = getPosition();
        g.drawImage(Simulation.herbivoreImg, p.getX(), p.getY(), 32, 32, null);
    }

    public static void initHerbivore(int numofherbivores){
        for (int i = 0; i < numofherbivores; i++){
            int xPos = Simulation.random.nextInt(1068);
            int yPos = Simulation.random.nextInt(968);

            for (; xPos >= (River.RIVER_X - 32) &&
                    xPos <= (River.RIVER_X + River.RIVER_WIDTH + 32); ) {
                xPos = Simulation.random.nextInt(1068);
            }

            Herbivore.newHerbivore(xPos, yPos);
        };
    }

}
