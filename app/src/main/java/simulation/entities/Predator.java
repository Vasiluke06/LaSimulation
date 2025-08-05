package simulation.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import simulation.core.Animals;
import simulation.core.Movable;
import simulation.core.Position;
import simulation.core.Simulation;

public class Predator extends Animals implements Movable<ArrayList<Herbivore>> {

    private static final int STEP_SIZE = 12;
    private static final int POINTS_FOR_EATING = 15;
    private static int attackCooldown = 20;

    /* ************************************** */

 /*Constructors*/
    public Predator(int x, int y) {
        super(x, y);
    }

    /*Implementation of Movable interface and movement logic*/
    @Override
    public void nextMove(ArrayList<Herbivore> herbivores) {
        if (attackCooldown < 20) {
            attackCooldown++;
            this.setPosition(Move(STEP_SIZE));
        } else {
            //Pathfinding to the closest herbivore
            Position target = findClosestHerbivore(herbivores);
            if (target != null) {
                this.setPosition(moveToward(target, STEP_SIZE, this.getPosition()));
                checkForBorder(STEP_SIZE);
                if (attackCooldown >= 20
                        && Math.abs(this.getPosition().getX() - target.getX()) < STEP_SIZE
                        && Math.abs(this.getPosition().getY() - target.getY()) < STEP_SIZE) {

                    deleteHerbivorePredator(target, herbivores);
                    attackCooldown = 0;
                }
            }
            super.checkForBorder(STEP_SIZE);
            //River influence (downstream movement)
            if (isOnRiver()) {
                getPosition().setY(getPosition().getY() + STEP_SIZE);
            }
        }
    }

    /*Methods used for defining interactions between predator and herbivore*/
    /**
     * Method used for finding the closest herbivore
     */
    private Position findClosestHerbivore(ArrayList<Herbivore> herbivoreList) {
        Herbivore closest = null;
        double closestDistance = Double.MAX_VALUE;

        for (Herbivore h : herbivoreList) {
            double dist = distanceTo(h, this.getPosition());
            if (dist < closestDistance) {
                closestDistance = dist;
                closest = h;
            }
        }
        return (closest != null) ? closest.getPosition() : null;
    }

    /**
     * Method used for deleting the herbivore and earning points
     */
    public void deleteHerbivorePredator(Position target, ArrayList<Herbivore> herbivores) {
        int x = getPosition().getX();
        int y = getPosition().getY();
        int targetX = target.getX();
        int targetY = target.getY();

        if (Math.abs(x - targetX) < STEP_SIZE
                && Math.abs(y - targetY) < STEP_SIZE) {

            getPosition().setX(targetX);
            getPosition().setY(targetY);

            herbivores.removeIf(herbivore -> herbivore.getPosition().equals(target));
            herbivoresKilled++;
            Animals.addPredatorPoint(POINTS_FOR_EATING);
        }
    }

    /* ************************************** */
    /**
     * Method used for determining the range to the target
     */
    public static void newPredator(int x, int y) {
        Simulation.predator.add(new Predator(x, y));
    }

    public void drawPredator(Graphics g) {
        Position p = getPosition();
        g.drawImage(Simulation.predatorImg, p.getX(), p.getY(), SPRITE_SIZE, SPRITE_SIZE, null);
    }

    /**
     * Method used for initializing the predators
     */
    public static void initPredator(int numOfPredators) {
        for (int i = 0; i < numOfPredators; i++) {
            Position pos = new Position(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE), Simulation.random.nextInt(Simulation.SCREEN_HEIGHT - SPRITE_SIZE));

            for (; River.isOnRiver(pos.getX(), pos.getY());) {
                pos.setX(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE));
            }

            Predator.newPredator(pos.getX(), pos.getY());
        };
    }

    @Override
    public void drown() {
        Simulation.predator.remove(this);
        predatorsKilled++;
    }
}
