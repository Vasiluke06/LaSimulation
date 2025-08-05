package simulation.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import simulation.core.Animals;
import simulation.core.Movable;
import simulation.core.Plants;
import simulation.core.Position;
import simulation.core.Simulation;

public class Herbivore extends Animals implements Movable<ArrayList<Plants>> {

    private final int STEP_SIZE = 10;
    private final int POINTS_FOR_REPRODUCTION = 6;
    private final int POINTS_FOR_EATING = 8;

    private static int eatingCooldown = Simulation.random.nextInt(61);
    private static int cooldown = 60;

    private boolean isFed = Math.random() < 0.5;
    private Herbivore mate;

    /**
     * Getters, setters, and constructors
     */
    public Herbivore() {
    }

    public Herbivore(boolean fed, int x, int y) {
        super(x, y);
        this.isFed = fed;
    }

    public boolean isFed() {
        return isFed;
    }

    public void setFed(boolean fed) {
        isFed = fed;
    }

    public Herbivore(int x, int y) {
        super(x, y);
    }

    public Herbivore getMate() {
        return mate;
    }

    /**
     * Implementation of Movable interface and movement logic
     */
    @Override
    public void nextMove(ArrayList<Plants> plant) {

        if (isFed) {
            if (mate == null) {
                findMate(Simulation.herbivore);
            }

            Herbivore currentMate = this.mate;

            if (currentMate != null && currentMate.getPosition() != null) {
                Position matePosition = mate.getPosition();
                this.setPosition(moveToward(matePosition, STEP_SIZE, this.getPosition()));
                checkForBorder(STEP_SIZE);
                herbivoreReproduction(mate);
            } else {
                this.setPosition(Move(STEP_SIZE));
            }

        } else if (eatingCooldown >= cooldown) {
            getToThePlant(plant);
        }
        if (eatingCooldown < cooldown && !isFed) {
            eatingCooldown++;
            this.setPosition(Move(STEP_SIZE));
        }
    }

    /*Herbivore-Herbivore interactions*/
    /**
     * Method used for finding the closest fed herbivore
     */
    private Position findMate(ArrayList<Herbivore> herbivores) {
        double closestDistance = Double.MAX_VALUE;

        for (Herbivore other : herbivores) {
            if (other != this && other.isFed()) {
                double dist = distanceTo(other, this.getPosition()); //
                if (dist < closestDistance) {
                    closestDistance = dist;
                    this.mate = other;
                }
            }
        }
        return (mate != null) ? mate.getPosition() : null;
    }

    /**
     * Method used for reproduction of herbivores
     */
    public void herbivoreReproduction(Herbivore mate) {
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

    public static void newHerbivore(int x, int y) {
        Simulation.herbivore.add(new Herbivore(x, y));
    }

    /* ************************************** */

 /*Herbivore-Plant interactions*/
    /**
     * Method used for finding the closest plant
     */
    private Position findFood() {
        Plants closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (Plants other : Simulation.plants) {
            double dist = distanceTo(this, other.getPosition());
            if (dist < closestDistance) {
                closestDistance = dist;
                closest = other;
            }
        }
        return (closest != null) ? closest.getPosition() : null;
    }

    /**
     * Method used for "eating" the target plant
     */
    public void getToThePlant(ArrayList<Plants> plantList) {
        Position target = findFood();
        if (target == null) {
            return;
        }

        int targetX = target.getX();
        int targetY = target.getY();
        Position myPos = getPosition();
        int myX = myPos.getX();
        int myY = myPos.getY();

        int dx = Integer.compare(targetX, myX) * STEP_SIZE;
        int dy = Integer.compare(targetY, myY) * STEP_SIZE;

        if (River.isOnRiver(myX, myY)) {
            dy += STEP_SIZE;
            dx /= 2;
            dy /= 2;
        }

        myPos.setX(myX + dx);
        myPos.setY(myY + dy);

        checkForBorder(STEP_SIZE);

        if (Math.abs(myX - targetX) < STEP_SIZE
                && Math.abs(myY - targetY) < STEP_SIZE) {

            myPos.setX(targetX);
            myPos.setY(targetY);
            plantList.removeIf(plant -> plant.getPosition().equals(target));
            setFed(true);
            Animals.addHerbivorePoint(POINTS_FOR_EATING);
        }
    }

    /* ************************************** */

 /*Method for drawing a herbivore*/
    public void drawHerbivore(Graphics g) {
        Position p = getPosition();
        g.drawImage(Simulation.herbivoreImg, p.getX(), p.getY(), SPRITE_SIZE, SPRITE_SIZE, null);
    }

    /**
     * Method used for initializing the herbivores
     */
    public static void initHerbivore(int numofherbivores) {
        for (int i = 0; i < numofherbivores; i++) {
            Position pos = new Position(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE), Simulation.random.nextInt(Simulation.SCREEN_HEIGHT - SPRITE_SIZE));

            for (; River.isOnRiver(pos.getX(), pos.getY());) {
                pos.setX(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE));
            }

            Herbivore.newHerbivore(pos.getX(), pos.getY());
        };
    }

    @Override
    public void drown() {
        Simulation.herbivore.remove(this);
        herbivoresKilled++;
    }

}
