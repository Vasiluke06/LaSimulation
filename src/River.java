import java.awt.*;

public class River {
    private int x_river;
    private int width_of_river;
    public static void create_river(Graphics g){
        g.setColor(new Color(30, 144, 255));

        int x_river = 481;

        int width_of_river = 138;
        g.fillRect(x_river, 0, width_of_river, 1000);
    }

    public boolean onRiver(int x_pos, int y_pos){

        if (x_pos >= x_river && x_pos <= x_river + width_of_river && y_pos >= 0 && y_pos <= 1000){
            return true;
        } else {
            return false;
        }
    }


}



