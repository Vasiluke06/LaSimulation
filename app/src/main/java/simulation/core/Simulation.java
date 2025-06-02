package simulation.core;

import simulation.entities.Herbivore;
import simulation.entities.Predator;
import simulation.ui.Frame_Settings;
import simulation.entities.River;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Timer;
import javax.swing.*;


public class Simulation extends JPanel implements ActionListener {


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

    public static int x_pos;
    public static int y_pos;

    public static Image herbivoreImg;
    public static Image predatorImg;
    public static Image plantImg;
    public Image huntersImg;

    public static Random random;

    public static Timer simulationTime;

    public Simulation(){
        images();

        x_pos = 350;

        y_pos = 110;

        random = new Random();

        init_start();

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



    public void paintComponent(Graphics g){                                                             // main graphic method
        super.paintComponent(g);
        drawing(g);
    }



    public void images() {
        herbivoreImg = loadImage("images/deer female calciumtrice (1).png");
        predatorImg = loadImage("images/wolf (1).png");
        //huntersImg = loadImage("images/hunters_icon.png"); // Replace with the actual file name
        plantImg = loadImage("images/pixel-grid-blueberries_2236497 (1).png");
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
        x_pos += 1;

        y_pos += 1; //just for test

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

        simulationTime.setDelay(1000/ Frame_Settings.speedofsimulation);

        //
        repaint();

        /*new Result_Writer("Results.txt");
        simulationTime.stop();
        JOptionPane.showMessageDialog(this, "End of simulation");*/

        if (victoryCheck()){
            new Result_Writer("Results.txt");

            simulationTime.stop();

            JOptionPane.showMessageDialog(this, "End of simulation");
        }
    }

    public class Result_Writer{
        Result_Writer(String filename){
            try{
                FileWriter fw = new FileWriter(filename, true);
                LocalDateTime datetime = LocalDateTime.now();
                fw.write("\nDate and time of simulation: " + datetime + "\n");
                fw.write("Points for victory: " + Frame_Settings.pointsforvictory + "\n");
                fw.write("Herbivore points: " + Herbivore.herbivorePoints + "\n");
                fw.write("Predator points: " + Predator.predatorPoints + "\n");
                fw.write("Herbivores remain: " + herbivore.size() + "\n");
                fw.write("Predators remain: " + predator.size() + "\n");
                fw.write("Herbivores killed during simulation: " + Animals.herbivoresKilled + "\n");
                fw.write("Predators killed during simulation: " + kill_count_predator + "\n");
                fw.write("Wildfires: " + wildfire_count + "\n");
                fw.write("Hunters: " + hunters_count + "\n");
                fw.close();
            } catch (IOException e){
                System.out.println("Error occured during writing results to the file");

                e.printStackTrace();
            }
        }
    }
}
