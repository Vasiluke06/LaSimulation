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
    public static ArrayList<Plants> plants;
    public int kill_count_herbivore;
    public int kill_count_predator;
    public int herbivore_remain;
    public int predator_remain;

    public static int x_pos;
    public static int y_pos;

    public static Image herbivore_img;
    public Image predator_img;
    public static Image plant_img;
    public Image hunters_img;

    public static Random random;

    public Timer simulation_time;

    Simulation(){
        images();

        x_pos = 350;

        y_pos = 110;

        random = new Random();

        init_start();

        simulation_time = new Timer(1000/Frame_Settings.speedofsimulation, this);
        simulation_time.start();


    }

    private void init_start(){
        herbivore = new ArrayList<>();
        predator = new ArrayList<>();
        plants =  new ArrayList<>();
        for (int i = 0; i < Frame_Settings.numofplants; i++){
            int x_pos = random.nextInt(1100);
            int y_pos = random.nextInt(1000);
            Plants.new_plant(x_pos, y_pos);
        };

        //River.create_river();

    }



    public void paintComponent(Graphics g){                                                             // main graphic method
        super.paintComponent(g);
        drawing(g);
    }



    public void images(){
        herbivore_img = new ImageIcon("images/deer female calciumtrice (1).png").getImage();
        predator_img = new ImageIcon("images/wolf (1).png").getImage();
        hunters_img = new ImageIcon("").getImage();
        plant_img = new ImageIcon("images/pixel-grid-blueberries_2236497 (1).png").getImage();
    }

    /*public void draw_herbivore(Graphics g){
        g.drawImage(herbivore_img, x_pos, y_pos, 32, 32, this);
    }*/

    public void draw_predator(Graphics g){

    }

    /*public void draw_plants(Graphics g){
        g.drawImage(plant_img, x_pos, y_pos, 32, 32, this);
    }*/



    public void drawing(Graphics g){
        if (herbivore != null){
            for (int i = 0; i < herbivore.size(); i++) {
                herbivore.get(i).draw_herbivore(g);
            }
        }

        if (plants != null){
            for (int i = 0; i < plants.size(); i++) {
                plants.get(i).draw_plants(g);
            }
        }

    }

    private boolean victory_check(int herbivore_points, int predator_points, int pointsforvictory){
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        x_pos += 1;

        y_pos += 1; //just for test

        if(herbivore != null){
            for(int i = 0; i < herbivore.size(); i++){
                herbivore.get(i).next_move_herbivore();
            }
        }

        if(predator != null){
            for(int i = 0; i < predator.size(); i++){
                predator.get(i).next_move_predator();
            }
        }

        Plants.spawn_new_plant();

        //
        repaint();
    }
}
