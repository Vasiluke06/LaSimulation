import java.awt.*;

import java.awt.image.ImageObserver;

public class Herbivore extends Animals{

    public void next_move_herbivore(){
        next_move(); //Just move to another cordinates and check border of map + check for river

        //Next moves that are special for herbivore
    }

    public void new_herbivore(){

    }

    public void delete_herbivore(){

    }

    public void draw_herbivore(Graphics g){
        g.drawImage(Simulation.herbivore_img, Simulation.x_pos, Simulation.y_pos, 32, 32, null);
    }
}
