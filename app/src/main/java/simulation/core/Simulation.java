package simulation.core;

import simulation.entities.*;
import simulation.ui.Frame_Settings;
import simulation.ui.Frame_Simulation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Timer;
import javax.swing.*;


public class Simulation extends JPanel implements ActionListener {

    public static int SCREEN_WIDTH;
    public static int SCREEN_HEIGHT;

    private Image loadImage(String path) {
        URL resource = getClass().getClassLoader().getResource(path);
        if (resource == null) {
            throw new RuntimeException("Image not found: " + path);
        }
        return new ImageIcon(resource).getImage();
    }


    public static ArrayList<Herbivore> herbivore;
    public static ArrayList<Predator> predator;
    public static ArrayList<Plants> plants;
    public int kill_count_herbivore;
    public int kill_count_predator;
    public int herbivore_remain;
    public int predator_remain;
    public int wildfire_count;
    public int hunters_count;
    public int wildfire_timer;
    public int hunters_timer;

    public static int x_pos;
    public static int y_pos;

    public static Image herbivoreImg;
    public static Image predatorImg;
    public static Image plantImg;
    public static Image huntersImg;
    public static Image wildfireImg;

    public static Random random;

    public static Timer simulationTime;

    public Simulation(){
        images();

        x_pos = 350;

        y_pos = 110;

        random = new Random();

        addNotify();

        simulationTime = new Timer(1000/Frame_Settings.speedofsimulation, this);
        simulationTime.start();
    }



    private void init_start(){
        herbivore = new ArrayList<>();
        predator = new ArrayList<>();
        plants =  new ArrayList<>();

        Plants.initPlant(Frame_Settings.numofplants);
        Herbivore.initHerbivore(Frame_Settings.numofherbivore);
        Predator.initPredator(Frame_Settings.numofpredator);

    }

    @Override
    public void addNotify() {
        super.addNotify();
        Simulation.SCREEN_WIDTH = getWidth();
        Simulation.SCREEN_HEIGHT = getHeight();

        SwingUtilities.invokeLater(() -> {
            init_start();
        });
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Store correct screen size at runtime
        Rectangle bounds = g.getClipBounds();
        Simulation.SCREEN_WIDTH = bounds.width;
        Simulation.SCREEN_HEIGHT = bounds.height;

        drawing(g);
    }



    public void images() {
        herbivoreImg = loadImage("images/deer female calciumtrice (1).png");
        predatorImg = loadImage("images/wolf (1).png");
        huntersImg = loadImage("images/hunters_icon.png");
        plantImg = loadImage("images/pixel-grid-blueberries_2236497 (1).png");
        wildfireImg = loadImage("images/wildfire.png");
    }



    /*public void draw_herbivore(Graphics g){
        g.drawImage(herbivoreImg, x_pos, y_pos, 32, 32, this);
    }

    public void draw_predator(Graphics g){

    }

    public void draw_plants(Graphics g){
        g.drawImage(plantImg, x_pos, y_pos, 32, 32, this);
    }*/



    public void drawing(Graphics g){
        River.createRiver(g);

        if (herbivore != null){
            for (int i = 0; i < herbivore.size(); i++) {
                herbivore.get(i).drawHerbivore(g);
            }
        }

        if (predator != null){
            for (int i = 0; i < predator.size(); i++) {
                predator.get(i).drawPredator(g);
            }
        }

        if (plants != null){
            for (int i = 0; i < plants.size(); i++) {
                plants.get(i).drawPlants(g);
            }
        }

        if (Hunters.hunter != null){
            Hunters.hunter.draw_hunter(g);
        }

        if (Wildfire.wildfire != null){
            Wildfire.wildfire.draw_wildfire(g);
        }



    }

    private boolean victoryCheck(){
        if (Herbivore.herbivorePoints >= Frame_Settings.pointsforvictory
                || Predator.predatorPoints >= Frame_Settings.pointsforvictory
                || herbivore.size() == 0 || predator.size() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //x_pos += 1;

        //y_pos += 1; //just for test

        if(herbivore != null){
            for(int i = 0; i < herbivore.size(); i++) {
                herbivore.get(i).nextMove();
            }
        }

        if(predator != null){
            for(int i = 0; i < predator.size(); i++){
                predator.get(i).nextMove();
            }
        }

        Plants.spawn_new_plant();

        if(Hunters.hunter == null){
            if (hunters_timer >= 100) {
                if (random.nextInt(100) < Frame_Settings.chanceofhunters) {
                    Hunters.create_hunter();
                }

                hunters_timer = 0;
            }
            hunters_timer++;
        } else if (Hunters.hunter != null){
            Hunters.move_hunter();
        }

        if(Wildfire.wildfire == null){
            if (wildfire_timer >= 100) {
                if (random.nextInt(100) < Frame_Settings.chanceofwildfire) {
                    Wildfire.create_wildfire();
                }
                wildfire_timer = 0;
            }
            wildfire_timer++;
        } else if (Wildfire.wildfire != null){
            Wildfire.move_wildfire();
        }

        //Frame_Simulation.instance.updatePointsDisplay();

        simulationTime.setDelay(1000/ Frame_Settings.speedofsimulation);

        //
        repaint();

        herbivore_remain = herbivore.size();

        predator_remain = predator.size();




        /*new Result_Writer("Results.txt");
        simulationTime.stop();
        JOptionPane.showMessageDialog(this, "End of simulation");*/

        if (victoryCheck()){
            new Result_Writer("Results.csv");

            simulationTime.stop();

            JOptionPane.showMessageDialog(this, "End of simulation");
            if (simulation.ui.Frame_Simulation.instance != null) {
                simulation.ui.Frame_Simulation.instance.dispose();
            }
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
                fw.write(Wildfire.wildfire_count + ",");
                fw.write(Hunters.hunters_counter + "\n");
                fw.close();
            } catch (IOException e){
                System.out.println("Error occured during writing results to the file");

                e.printStackTrace();
            }
        }
    }
}
