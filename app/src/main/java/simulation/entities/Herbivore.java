package simulation.entities;

import simulation.core.*;
import java.util.Random;
import simulation.ui.Frame_Settings;
import simulation.ui.Frame_Simulation;

import java.awt.*;

import java.awt.image.ImageObserver;

public class Herbivore extends Animals implements Movable {
    private final int STEP_SIZE = 10;
    private final int POINTS_FOR_REPRODUCTION = 6;
    private final int POINTS_FOR_EATING = 8;
    private static int eatingCooldown = Simulation.random.nextInt(31);
    private static int cooldown = 30;

    private boolean isFed = Math.random() < 0.5;  /* **********Only for testing, should be changed to FALSE or Randomized********/
    private Herbivore mate;
    /**
     * Getters, setters, and constructors
     */
    public boolean isFed() {
        return isFed;
    }

    public void setFed(boolean fed) {
        isFed = fed;
    }

    public Herbivore(int x, int y) {
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
        } else if (eatingCooldown >= cooldown) {
            getToThePlant();
        }
        if(eatingCooldown < cooldown && !isFed) {
            eatingCooldown++;
            Move(STEP_SIZE);
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
    public static void newHerbivore(int x, int y){
        Simulation.herbivore.add(new Herbivore(x, y));
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
            double dist = distanceToPlant( other.getPosition().getX(), other.getPosition().getY());
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
        int targetX = target.getPosition().getX();
        int targetY = target.getPosition().getY();

        int dx = Integer.compare(targetX, myPos.getX())*STEP_SIZE;
        int dy = Integer.compare(targetY, myPos.getY())*STEP_SIZE;

        if (River.isOnRiver(myPos.getX(), myPos.getY())) {
            dy += STEP_SIZE;
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
        g.drawImage(Simulation.herbivoreImg, p.getX(), p.getY(), SPRITE_SIZE, SPRITE_SIZE, null);
    }
    /**
     * Method used for initializing the herbivores
     */

    public static void initHerbivore(int numofherbivores){
        for (int i = 0; i < numofherbivores; i++){
            Position pos = new Position(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE), Simulation.random.nextInt(Simulation.SCREEN_HEIGHT - SPRITE_SIZE));

            for (; River.isOnRiver(pos.getX(), pos.getY()); ) {
                pos.setX(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE));
            }

            Herbivore.newHerbivore(pos.getX(), pos.getY());
        };
    }

}
