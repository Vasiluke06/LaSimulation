

import java.awt.*;

import java.awt.image.ImageObserver;

public class Herbivore extends Animals implements Movable {
    final int STEP_SIZE = 10;
    final int POINTS_FOR_REPRODUCTION = 6;
    final int POINTS_FOR_EATING = 8;
    private boolean isFed = false;  /* **********Only for testing, should be changed to FALSE or Randomized********/

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
        super(x, y);
    }
    /**
     * Implementation of Movable interface and movement logic
     */
    @Override
    public void nextMove(){
        if(isFed){
            Herbivore mate = findMate();
            if(mate != null){
            moveToward(mate);}
        } else {
            getToThePlant();
        }

    }
    /*Herbivore-Herbivore interactions*/
    /**
     * Method used for finding the closest fed herbivore
     */
    private Herbivore findMate() {
        Herbivore closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (Herbivore other : Simulation.herbivore) {
            if (other == this || !other.isFed()) continue;
            double dist = distanceTo(other);
            if (dist < closestDistance) {
                closestDistance = dist;
                closest = other;
            }
        }

        return closest;
    }
    /**
     * Method used for reproduction of herbivores
     */
    public void herbivoreReproduction(Herbivore mate){
        if (mate == null || !mate.isFed() || !this.isFed()) return;

        int x = (this.getPosition().getX() + mate.getPosition().getX()) / 2;
        int y = (this.getPosition().getY() + mate.getPosition().getY()) / 2;

        Simulation.herbivore.add(new Herbivore(x, y));
        this.setFed(false);
        mate.setFed(false);
        Animals.addHerbivorePoint(POINTS_FOR_REPRODUCTION);
    }

    /**
     * Method used for pathfinding towards the target herbivore
     */
    private void moveToward(Herbivore target) {
        Position myPos = getPosition();
        Position targetPos = target.getPosition();
        if(targetPos != null){
        int dx = Integer.compare(targetPos.getX(), myPos.getX()) * STEP_SIZE;
        int dy = Integer.compare(targetPos.getY(), myPos.getY()) * STEP_SIZE;

        if (River.onRiver(myPos.getX(), myPos.getY())) {
            dy += 1;
            dx /= 2;
            dy /= 2;
        }

        myPos.setX(myPos.getX() + dx);
        myPos.setY(myPos.getY() + dy);
        checkForBorder();
        if (Math.abs(myPos.getX() - targetPos.getX()) < STEP_SIZE &&
                Math.abs(myPos.getY() - targetPos.getY()) < STEP_SIZE) {

            myPos.setX(targetPos.getX());
            myPos.setY(targetPos.getY());

            herbivoreReproduction(target);
        }
        } else {
            myPos = myPos;
        }

    }

   /* Kind of unsure if this is still needed
    public void newHerbivore(int x, int y){
        Simulation.herbivore.add(new Herbivore(x, y));
    }

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

        if (River.onRiver(myPos.getX(), myPos.getY())) {
            dy += 1;
            dx /= 2;
            dy /= 2;
        }

        myPos.setX(myPos.getX() + dx);
        myPos.setY(myPos.getY() + dy);

        checkForBorder();

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
    private double distanceTo(Herbivore other) {
        //Helping method for calculating distance to a herbivore
        int dx = other.getPosition().getX() - this.getPosition().getX();
        int dy = other.getPosition().getY() - this.getPosition().getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    private double distanceToPlant(int x, int y) {
        int dx = x - getPosition().getX();
        int dy = y - getPosition().getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
    /* ************************************** */


    /*Method for drawing a herbivore*/
    public void drawHerbivore(Graphics g){
        Position p = getPosition();
        g.drawImage(Simulation.herbivore_img, p.getX(), p.getY(), 32, 32, null);
    }


}
