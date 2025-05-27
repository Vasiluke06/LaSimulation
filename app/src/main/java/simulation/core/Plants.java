package simulation.core;

import java.awt.*;
import java.util.Timer;

public class Plants {
    private static int spawn_timer;
    private int x_pos;
    private int y_pos;

    public Plants(int x_pos, int y_pos){
        this.x_pos = x_pos;

        this.y_pos = y_pos;
    }

    public static void new_plant(int x_pos, int y_pos){
        Simulation.plants.add(new Plants(x_pos, y_pos));
    }

    public static void delete_plant(int x_pos_delete_plant, int y_pos_delete_plant){
        for (int i = 0; i < Simulation.plants.size(); i++){
            Plants plant = Simulation.plants.get(i);
            if (plant.x_pos == x_pos_delete_plant && plant.y_pos == y_pos_delete_plant) {
                Simulation.plants.remove(i);
                break;
            }
        }
    }

    public static void spawn_new_plant(){
        if (spawn_timer >= 100){
            int x_pos_spawn_new_plant = Simulation.random.nextInt(1100);

            int y_pos_spawn_new_plant = Simulation.random.nextInt(1000);

            new_plant(x_pos_spawn_new_plant, y_pos_spawn_new_plant);

            spawn_timer = 0;
        }

        spawn_timer++;
    }

    public void draw_plants(Graphics g){
        g.drawImage(Simulation.plant_img, x_pos, y_pos, 32, 32, null);
    }
}
