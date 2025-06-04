import java.awt.*;

public class Wildfire {
    public static int wildfire_count;

    private static int x_pos;

    private static int y_pos;

    private static int on_which_side;

    public static Wildfire wildfire;
    private static int i_wildfire = 0;



    public Wildfire(int x_pos, int y_pos){
        this.x_pos = x_pos;

        this.y_pos = y_pos;
    }

    public static void create_wildfire(){
        if (wildfire == null) {
            on_which_side = Simulation.random.nextInt(0, 2);
            if (on_which_side == 0) {
                x_pos = 0;

                y_pos = 900;

                wildfire = new Wildfire(x_pos, y_pos);

                wildfire_count++;
            }
            if (on_which_side == 1) {
                x_pos = River.x_river + River.width_of_river;

                y_pos = 900;

                wildfire = new Wildfire(x_pos, y_pos);

                wildfire_count++;
            }
        }
    }

    public static void move_wildfire(){
        if (wildfire != null) {
            for (; i_wildfire < 10;) {
                y_pos -= 100;

                delete_wildfire_entities();

                i_wildfire++;
                return;
            }
            if (i_wildfire == 10){
                delete_wildfire();

                i_wildfire = 0;
            }
        }
    }

    public static void delete_wildfire_entities(){
        if (wildfire == null) return;

        Rectangle wildfire_area = new Rectangle(x_pos, y_pos, 481, 100);

        Simulation.herbivore.removeIf(h -> wildfire_area.contains(h.getPosition().getX(), h.getPosition().getY()));

        Simulation.predator.removeIf(p -> wildfire_area.contains(p.getPosition().getX(), p.getPosition().getY()));

        Simulation.plants.removeIf(plnt -> wildfire_area.contains(plnt.getXPos(), plnt.getYPos()));
    }

    public static void delete_wildfire(){
        wildfire = null;
    }

    public static void draw_wildfire(Graphics g){
        g.drawImage(Simulation.wildfire_img, x_pos, y_pos, 481, 100, null);
    }
}





