import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Timer;
import javax.swing.*;

public class Simulation extends JPanel implements ActionListener {
    public ArrayList<Herbivore> herbivore;
    public ArrayList<Predator> predator;
    public ArrayList<Plants> plants;
    public int kill_count_herbivore;
    public int kill_count_predator;
    public int herbivore_remain;
    public int predator_remain;

    public int x_pos;
    public int y_pos;

    public Image herbivore_img;
    public Image predator_img;
    public Image plant_img;
    public Image hunters_img;

    public Random random;

    public Timer simulation_time;

    Simulation(){
        images();

        simulation_time = new Timer(0, this);
        simulation_time.start();

        repaint();
    }

    private void init_start(){

    }



    public void paintComponent(Graphics g){                                                             // main graphic method
        super.paintComponent(g);
        draw_herbivore(g);
    }



    public void images(){
        herbivore_img = new ImageIcon("images/deer female calciumtrice (1).png").getImage();
        predator_img = new ImageIcon("images/wolf (1).png").getImage();
        hunters_img = new ImageIcon("images/pixel-grid-blueberries_2236497 (1)").getImage();
        plant_img = new ImageIcon("").getImage();
    }

    public void draw_herbivore(Graphics g){
        g.drawImage(herbivore_img, 350, 110, 32, 32, this);
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
