import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Timer;
import javax.swing.Timer;

public class Simulation implements ActionListener {
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

    public Timer simulation_time;

    Simulation(){
        images();

        simulation_time = new Timer(0, this);
        simulation_time.start();
    }

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

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
