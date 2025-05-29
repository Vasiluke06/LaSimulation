import java.awt.*;
import java.util.Timer;

public class Plants {
    private static int spawn_timer;
    private int x_pos;
    private int y_pos;



    public int getXPos() {
        return x_pos;
    }
    public int getYPos() {
        return y_pos;
    }

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
        if (spawn_timer >= 10){
            int x_pos_spawn_new_plant = Simulation.random.nextInt(1068);

            int y_pos_spawn_new_plant = Simulation.random.nextInt(968);

            for (;x_pos_spawn_new_plant >= (River.x_river - 32) && x_pos_spawn_new_plant <= (River.x_river + River.width_of_river + 32);){
                x_pos_spawn_new_plant = Simulation.random.nextInt(1068);
            }

            new_plant(x_pos_spawn_new_plant, y_pos_spawn_new_plant);

            spawn_timer = 0;
        }


        spawn_timer++;
    }

    public static void init_plant(int numofplants){
        for (int i = 0; i < numofplants; i++){
            int x_pos_init_plant = Simulation.random.nextInt(1068);
            int y_pos_init_plant = Simulation.random.nextInt(968);

            for (;x_pos_init_plant >= (River.x_river - 32) && x_pos_init_plant <= (River.x_river + River.width_of_river + 32);){
                x_pos_init_plant = Simulation.random.nextInt(1068);
            }

            Plants.new_plant(x_pos_init_plant, y_pos_init_plant);
        };
    }
    public void draw_plants(Graphics g){
        g.drawImage(Simulation.plant_img, x_pos, y_pos, 32, 32, null);
    }
}
