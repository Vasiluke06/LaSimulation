import java.awt.*;

import static java.awt.geom.Point2D.distance;

public class Hunters {
    public static int hunters_counter;

    private static int x_pos;
    private static int y_pos;

    private static int in_which_direction;

    private static int hunters_speed = 15;

    public static Hunters hunter;


    public Hunters(int x_pos, int y_pos){
        this.x_pos = x_pos;

        this.y_pos = y_pos;
    }

    public static void create_hunter(){
        if (hunter == null) {
            in_which_direction = Simulation.random.nextInt(0, 2);
            if (in_which_direction == 0) {
                x_pos = Simulation.random.nextInt(1100);

                for (;x_pos >= (River.x_river - 64) && x_pos <= (River.x_river + River.width_of_river + 64);){
                    x_pos = Simulation.random.nextInt( 1036);
                }

                y_pos = 0;

                hunter = new Hunters(x_pos, y_pos);

                hunters_counter++;
            }
            if (in_which_direction == 1) {
                x_pos = 0;

                y_pos = Simulation.random.nextInt(936);

                hunter = new Hunters(x_pos, y_pos);

                hunters_counter++;
            }
        }
    }

    public static void move_hunter(){
        if (check_border_hunters() == false) {
            if (in_which_direction == 0) {
                    hunter.y_pos += hunters_speed;
            } else if (in_which_direction == 1) {
                if (River.onRiver(x_pos - 64, y_pos - 64) == true) {
                    hunter.x_pos += hunters_speed / 2;
                } else {
                    hunter.x_pos += hunters_speed;
                }
            }
            delete_hunters_entities();
        } else if (check_border_hunters() == true){
            delete_hunter();
        }
    }



    public static void delete_hunters_entities(){
        if (hunter != null){
            Simulation.herbivore.removeIf(h -> distance(hunter.x_pos, hunter.y_pos, h.getPosition().getX(), h.getPosition().getY()) <= 50);

            Simulation.predator.removeIf(p -> distance(hunter.x_pos, hunter.y_pos, p.getPosition().getX(), p.getPosition().getY()) <= 50);
        }
    }

    private static double distance(int x1, int y1, int x2, int y2) {
        return Math.hypot(x1 - x2, y1 - y2);
    }

    public static boolean check_border_hunters(){
        if (hunter.x_pos < 0 || hunter.x_pos > 1100 || hunter.y_pos < 0 || hunter.y_pos > 1000){
            return true;
        } else {
            return false;
        }
    }

    public static void delete_hunter(){
        hunter = null;
    }

    public void draw_hunter(Graphics g){
        g.drawImage(Simulation.hunters_img, x_pos, y_pos, 64, 64, null);
    }
}
