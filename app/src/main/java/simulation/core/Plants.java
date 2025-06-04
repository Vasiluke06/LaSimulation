package simulation.core;

import java.awt.*;
import simulation.entities.River;

import java.util.Timer;

public class Plants {
    private static int spawnTimer;
    private Position position;
    private static final int SPRITE_SIZE = 32;

    public Plants(int x, int y){
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public static void new_plant(int x, int y){
        Simulation.plants.add(new Plants(x,y));
    }

    public static void spawn_new_plant(){
        if (spawnTimer >= 10){
            Position pos = new Position(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE), Simulation.random.nextInt(Simulation.SCREEN_HEIGHT - SPRITE_SIZE));

            for (; River.isOnRiver(pos.getX(), pos.getY()); ) {
                pos.setX(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE));
            }

            new_plant(pos.getX(), pos.getY());

            spawnTimer = 0;
        }

        spawnTimer++;
    }

    public void drawPlants(Graphics g){
        Position p = getPosition();
        g.drawImage(Simulation.plantImg, p.getX(), p.getY(), 32, 32, null);
    }

    /**
     * Method used for initializing the plant
     */

    public static void initPlant(int numofplants) {
        for (int i = 0; i < numofplants; i++) {
            Position pos = new Position(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE), Simulation.random.nextInt(Simulation.SCREEN_HEIGHT - SPRITE_SIZE));

            for (; River.isOnRiver(pos.getX(), pos.getY()); ) {
                pos.setX(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE));
            }

            Plants.new_plant(pos.getX(), pos.getY());
        }
    }
}
