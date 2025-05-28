package simulation.core;

import java.awt.*;
import simulation.entities.River;
import java.util.Timer;

public class Plants {
    private static int spawnTimer;
    private int xPos;
    private int yPos;

    public int getXPos() {
        return xPos;
    }
    public int getYPos() {
        return yPos;
    }

    public Plants(int xPos, int yPos){
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public static void new_plant(int xPos, int yPos){
        Simulation.plants.add(new Plants(xPos, yPos));
    }

    public static void deletePlant(int x_pos_delete_plant, int y_pos_delete_plant){
        for (int i = 0; i < Simulation.plants.size(); i++){
            Plants plant = Simulation.plants.get(i);
            if (plant.xPos == x_pos_delete_plant && plant.yPos == y_pos_delete_plant) {
                Simulation.plants.remove(i);
                break;
            }
        }
    }

    public static void spawn_new_plant(){
        if (spawnTimer >= 10){
            int x_pos_spawn_new_plant = Simulation.random.nextInt(1068);

            int y_pos_spawn_new_plant = Simulation.random.nextInt(968);

            while (x_pos_spawn_new_plant >= River.RIVER_X - 32 &&
                    x_pos_spawn_new_plant <= River.RIVER_X + River.RIVER_WIDTH + 32) {
                x_pos_spawn_new_plant = Simulation.random.nextInt(1068);
            }

            new_plant(x_pos_spawn_new_plant, y_pos_spawn_new_plant);

            spawnTimer = 0;
        }

        spawnTimer++;
    }

    public static void initPlant(int numofplants){
        for (int i = 0; i < numofplants; i++){
            int x_pos_init_plant = Simulation.random.nextInt(1068);
            int y_pos_init_plant = Simulation.random.nextInt(968);

            for (; x_pos_init_plant >= (River.RIVER_X - 32) &&
                    x_pos_init_plant <= (River.RIVER_X + River.RIVER_WIDTH + 32); ) {
                x_pos_init_plant = Simulation.random.nextInt(1068);
            }

            Plants.new_plant(x_pos_init_plant, y_pos_init_plant);
        };
    }

    public void drawPlants(Graphics g){
        g.drawImage(Simulation.plantImg, xPos, yPos, 32, 32, null);
    }
}
