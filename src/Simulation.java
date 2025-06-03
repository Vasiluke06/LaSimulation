import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Timer;
import javax.swing.*;

public class Simulation extends JPanel implements ActionListener {
    public static ArrayList<Herbivore> herbivore;
    public static ArrayList<Predator> predator;
    public static ArrayList<Plants> plants;
    public static int kill_count_herbivore;
    public int kill_count_predator;
    public int herbivore_remain;
    public int predator_remain;
    public int wildfire_count;
    public int hunters_count;

    public static int x_pos;
    public static int y_pos;

    public static Image herbivore_img;
    public static Image predator_img;
    public static Image plant_img;
    public static Image hunters_img;

    public static Random random;

    public static Timer simulation_time;

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
        Plants.init_plant(Frame_Settings.numofplants);

        for (int i = 0; i < Frame_Settings.numofherbivore; i++){
            int x_pos = Simulation.random.nextInt(1068);
            int y_pos = Simulation.random.nextInt(968);

            for (; x_pos >= (River.x_river - 32) &&
                    x_pos <= (River.x_river + River.width_of_river + 32); ) {
                x_pos = Simulation.random.nextInt(1068);
            }
            herbivore.add(new Herbivore(x_pos, y_pos));
        }

        for (int i = 0; i < Frame_Settings.numofpredator; i++){
            int x_pos = Simulation.random.nextInt(1068);
            int y_pos = Simulation.random.nextInt(968);

            for (; x_pos >= (River.x_river - 32) &&
                    x_pos <= (River.x_river + River.width_of_river + 32); ) {
                x_pos = Simulation.random.nextInt(1068);
            }
            predator.add(new Predator(x_pos, y_pos));
        }
        /*for (int i = 0; i < Frame_Settings.numofplants; i++){
            int x_pos = random.nextInt(1100);
            int y_pos = random.nextInt(1000);
            Plants.new_plant(x_pos, y_pos);
        };*/

        //River.create_river();

    }



    public void paintComponent(Graphics g){                                                             // main graphic method
        super.paintComponent(g);
        drawing(g);
    }



    public void images(){
        herbivore_img = new ImageIcon("images/deer female calciumtrice (1).png").getImage();
        predator_img = new ImageIcon("images/wolf (1).png").getImage();
        hunters_img = new ImageIcon("images/Hunter_card_render (3) (1) (1).png").getImage();
        plant_img = new ImageIcon("images/pixel-grid-blueberries_2236497 (1).png").getImage();
    }

    /*public void draw_herbivore(Graphics g){
        g.drawImage(herbivore_img, x_pos, y_pos, 32, 32, this);
    }*/

//    public void draw_predator(Graphics g){
///
//    }

    /*public void draw_plants(Graphics g){
        g.drawImage(plant_img, x_pos, y_pos, 32, 32, this);
    }*/



    public void drawing(Graphics g){
        River.create_river(g);

        if (herbivore != null){
            for (int i = 0; i < herbivore.size(); i++) {
                herbivore.get(i).draw_herbivore(g);
            }
        }

        if (predator != null){
            for (int i = 0; i < predator.size(); i++) {
                predator.get(i).drawPredator(g);
            }
        }

        if (plants != null){
            for (int i = 0; i < plants.size(); i++) {
                plants.get(i).draw_plants(g);
            }
        }

        if (Hunters.hunter != null){
            Hunters.hunter.draw_hunter(g);
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
            for(int i = 0; i < herbivore.size(); i++) {
                herbivore.get(i).nextMove();
            }
        }
        /* for (int i = 0; i < herbivore.size(); i++) {
                Herbivore h1 = herbivore.get(i);
                if (!h1.isFed()) continue;

                for (int j = i + 1; j < herbivore.size(); j++) {
                    Herbivore h2 = herbivore.get(j);
                    if (h2.isFed() &&
                            h1.getPosition().getX() == h2.getPosition().getX() &&
                            h1.getPosition().getY() == h2.getPosition().getY()) {

                        // Create new Herbivore at same location
                        Herbivore baby = new Herbivore(
                                h1.getPosition().getX(), h1.getPosition().getY()
                        );
                        herbivore.add(baby);

                        h1.setFed(false);
                        h2.setFed(false);
                        break;
                    }
                }
            }*/


        if(predator != null){
            for(int i = 0; i < predator.size(); i++){
                predator.get(i).nextMove();
            }
        }

        Plants.spawn_new_plant();

        if(Hunters.hunter == null){
            if (random.nextInt(100) < Frame_Settings.chanceofhunters){
                Hunters.create_hunter();
            }
        } else if (Hunters.hunter != null){
            Hunters.move_hunter();
        }

        simulation_time.setDelay(1000/Frame_Settings.speedofsimulation);

        //
        repaint();

        herbivore_remain = herbivore.size();

        predator_remain = predator.size();
        if (Herbivore.herbivorePoints > Frame_Settings.pointsforvictory || Predator.predatorPoints > Frame_Settings.pointsforvictory || herbivore_remain == 0 || predator_remain == 0){ //Checking if result writer works
            new Result_Writer("Results.csv");

            simulation_time.stop();

            JOptionPane.showMessageDialog(this, "End of simulation");
        }
    }

    public class Result_Writer{
        Result_Writer(String filename){
            try{
                File filename_check_first_row = new File(filename);

                boolean check_first_row_is_not_exist = false;

                if(!filename_check_first_row.exists() || filename_check_first_row.length() == 0) {
                    check_first_row_is_not_exist = true;
                }
                FileWriter fw = new FileWriter(filename, true);
                LocalDateTime datetime = LocalDateTime.now();
                if (check_first_row_is_not_exist == true){
                    fw.write("Date and time of simulation," + "Points for victory," + "Herbivore points," + "Predator points," + "Herbivores remain," + "Predators remain," + "Herbivores killed during simulation," + "Predators killed during simulation," + "Wildfires," + "Hunters\n");
                }
                fw.write(datetime + ",");
                fw.write(Frame_Settings.pointsforvictory + ",");
                fw.write(Herbivore.herbivorePoints + ",");
                fw.write(Predator.predatorPoints  + ",");
                fw.write(herbivore_remain + ",");
                fw.write(predator_remain + ",");
                fw.write(kill_count_herbivore + ",");
                fw.write(kill_count_predator + ",");
                fw.write(wildfire_count + ",");
                fw.write(Hunters.hunters_counter + "\n");
                fw.close();
            } catch (IOException e){
                System.out.println("Error occured during writing results to the file");

                e.printStackTrace();
            }
        }
    }
}