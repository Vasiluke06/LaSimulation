package simulation.core;

import simulation.entities.Herbivore;
import simulation.entities.Predator;
import simulation.ui.Frame_Settings;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;
//import java.util.Timer;
import javax.swing.*;

public class Simulation extends JPanel implements ActionListener {
    public static ArrayList<Herbivore> herbivore;
    public ArrayList<Predator> predator;
    public static ArrayList<Plants> plants;
    public int deathCountHerbivore;
    public int deathCountPredator;
    public int herbivoreRemain;
    public int predatorRemain;
    public int wildfire_count;
    public int hunters_count;

    public static int xPos;
    public static int yPos;

    public static Image herbivoreImg;
    public Image predatorImg;
    public static Image plantImg;
    public Image huntersImg;

    public static Random random;

    public static Timer simulationTime;
    //Changed constructor to public
    public Simulation(){
        images();

        xPos = 350;

        yPos = 110;

        random = new Random();

        initStart();

        simulationTime = new Timer(1000/Frame_Settings.speedofsimulation, this);
        simulationTime.start();


    }

    private void initStart(){
        herbivore = new ArrayList<>();
        predator = new ArrayList<>();
        plants =  new ArrayList<>();
        Plants.initPlant(Frame_Settings.numofplants);
        /*for (int i = 0; i < Frame_Settings.numofplants; i++){
            int x_pos = random.nextInt(1100);
            int y_pos = random.nextInt(1000);
            Plants.new_plant(x_pos, y_pos);
        };*/

        //River.createRiver();

    }



    public void paintComponent(Graphics g){                                                             // main graphic method
        super.paintComponent(g);
        drawing(g);
    }



    public void images(){
        herbivoreImg = new ImageIcon("images/deer female calciumtrice (1).png").getImage();
        predatorImg = new ImageIcon("images/wolf (1).png").getImage();
        huntersImg = new ImageIcon("").getImage();
        plantImg = new ImageIcon("images/pixel-grid-blueberries_2236497 (1).png").getImage();
    }

    /*public void drawHerbivore(Graphics g){
        g.drawImage(herbivoreImg, xPos, yPos, 32, 32, this);
    }*/

    public void drawPredator(Graphics g){

    }

    /*public void drawPlants(Graphics g){
        g.drawImage(plantImg, xPos, yPos, 32, 32, this);
    }*/



    public void drawing(Graphics g){
        if (herbivore != null){
            for (int i = 0; i < herbivore.size(); i++) {
                herbivore.get(i).drawHerbivore(g);
            }
        }

        if (plants != null){
            for (int i = 0; i < plants.size(); i++) {
                plants.get(i).drawPlants(g);
            }
        }

    }

    private boolean victoryCheck(int herbivorePoints, int predatorPoints, int pointsForVictory){
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        xPos += 1;

        yPos += 1; //just for test

        if(herbivore != null){
            for(int i = 0; i < herbivore.size(); i++){
                herbivore.get(i).nextMove();
            }
            for (int i = 0; i < herbivore.size(); i++) {
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
            }
        }

        if(predator != null){
            for(int i = 0; i < predator.size(); i++){
                predator.get(i).nextMove();
            }
        }

        Plants.spawn_new_plant();

        //
        repaint();
        if (Frame_Settings.speedofsimulation > 100){ //Checking if result writer works
            new ResultWriter("Results.txt");

            simulationTime.stop();

            JOptionPane.showMessageDialog(this, "End of simulation");
        }
    }

    public class ResultWriter{
        ResultWriter(String filename){
            try{
                FileWriter fw = new FileWriter(filename, true);
                LocalDateTime datetime = LocalDateTime.now();
                fw.write("\nDate and time of simulation: " + datetime + "\n");
                fw.write("Points for victory: " + Frame_Settings.pointsforvictory + "\n");
                fw.write("Herbivore points: " + "\n");
                fw.write("Predator points: " + "\n");
                fw.write("Herbivores remain: " + herbivoreRemain + "\n");
                fw.write("Predators remain: " + predatorRemain + "\n");
                fw.write("Herbivores killed during simulation: " + deathCountHerbivore + "\n");
                fw.write("Predators kills during simulation: " + deathCountPredator + "\n");
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
