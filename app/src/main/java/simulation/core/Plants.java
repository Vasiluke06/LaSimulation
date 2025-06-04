package simulation.core;

import java.awt.*;
import simulation.entities.River;
import simulation.core.Simulation;
import simulation.ui.Frame_Simulation;

import java.util.Timer;

public class Plants {
    private static int spawnTimer;
    private Position position;
    private static final int SPRITE_SIZE = 32;

    public Position getPosition() {
        return position;
    }

    public Plants(int x, int y){
        this.position = new Position(x, y);
    }

    public Plants(Position pos){
        this.position.setX(pos.getX());
        this.position.setY(pos.getY());
    }

    public static void new_plant(int x, int y){
        Simulation.plants.add(new Plants(x,y));
    }

   /* public static void deletePlant(int x_pos_delete_plant, int y_pos_delete_plant){
        for (int i = 0; i < Simulation.plants.size(); i++){
            Plants plant = Simulation.plants.get(i);
            if (plant.position.getX() == x_pos_delete_plant && plant.position.getY() == y_pos_delete_plant) {
                Simulation.plants.remove(i);
                break;
            }
        }
    }
*/
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
     * Method used for initializing the herbivores
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
