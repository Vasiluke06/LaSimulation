import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Simulation {
    public ArrayList<Herbivore> herbivore;
    public ArrayList<Predator> predator;
    public ArrayList<Plants> plants;
    public int kill_count_herbivore;
    public int kill_count_predator;
    public int herbivore_remain;
    public int predator_remain;

    public int x_pos;
    public int y_pos;

    public Image herbivoreimage;
    public Image predatorimage;
    public Image plantimage;
    public Image huntersimage;

    public Random random;

    private void init_start(){

    }

    public void images(){

    }

    public void draw_herbivore(Graphics g){

    }

    public void draw_predator(Graphics g){

    }

    public void draw_plants(Graphics g){

    }

    private boolean victory_check(int herbivore_points, int predator_points, int pointsforvictory){
        return true;
    }
}
