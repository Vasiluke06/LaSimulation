package simulation.core;

import simulation.entities.*;
import simulation.ui.Frame_Settings;

import simulation.ui.Frame_Simulation;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
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
    public static int numoftrees = 10;

    public static ArrayList<Herbivore> herbivore;
    public static ArrayList<Predator> predator;
    public static ArrayList<Plants> plants;
    public static ArrayList<Tree> tree;

    static int x_pos;
    static int y_pos;

    public static Image herbivoreImg;
    public static Image predatorImg;
    public static Image plantImg;
    public static Image huntersImg;
    public static Image wildfireImg;
    public static Image treeImg;
    public static Image spruceImg;
    public static int wildfire_timer;
    public static int hunters_timer;
    public static ArrayList<Image> treeImages = new ArrayList<>();


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

    Image loadImage(String path) {
        if (path == null) {
            throw new NullPointerException("Image path must not be null");
        }
        URL resource = getClass().getClassLoader().getResource(path);
        if (resource == null) {
            throw new IllegalArgumentException("Could not load image: " + path);
        }
        return new ImageIcon(resource).getImage();
    }

    private void init_start(){
        herbivore = new ArrayList<>();
        predator = new ArrayList<>();
        plants =  new ArrayList<>();
        tree = new ArrayList<>();

        Tree.initTree(numoftrees);
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
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Simulation.SCREEN_WIDTH = screenSize.width;
        Simulation.SCREEN_HEIGHT = screenSize.height;

        drawing(g);
    }

    private void images() {
        herbivoreImg = loadImage("images/deer.png");
        predatorImg = loadImage("images/wolf.png");
        huntersImg = loadImage("images/hunter.png");
        plantImg = loadImage("images/blueberries.png");
        wildfireImg = loadImage("images/wildfire.png");
        treeImg = loadImage("images/tree_1.png");
        spruceImg = loadImage("images/tree_2.png");
        treeImages.add(treeImg);
        treeImages.add(spruceImg);
    }

    public void drawing(Graphics g){
        River.createRiver(g);

        if (herbivore != null){
            for (Herbivore value : herbivore) {
                value.drawHerbivore(g);
            }
        }

        if (predator != null){
            for (Predator value : predator) {
                value.drawPredator(g);
            }
        }

        if (plants != null){
            for (Plants plant : plants) {
                plant.drawPlants(g);
            }
        }

        if (tree != null){
            for (Tree tree : tree) {
                tree.drawTree(g);
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
       System.out.println("Screen size: " + Simulation.SCREEN_WIDTH + "x" + Simulation.SCREEN_HEIGHT);

        if(herbivore != null){
            for(int i = 0; i < herbivore.size(); i++) {
                herbivore.get(i).nextMove();
            }
        }

        if(predator != null){
            for(int i = 0; i < predator.size(); i++) {
                predator.get(i).nextMove();
            }
        }

        Tree.spawn_new_tree();

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

        Frame_Simulation.instance.updatePointsDisplay();

        simulationTime.setDelay(1000/ Frame_Settings.speedofsimulation);


        repaint();

        if (victoryCheck()){
            new Result_Writer("Results.txt");

            simulationTime.stop();

            JOptionPane.showMessageDialog(this, "End of simulation");
            if (simulation.ui.Frame_Simulation.instance != null) {
                simulation.ui.Frame_Simulation.instance.dispose();
            }
        }
    }

    public static class Result_Writer{
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
                fw.write(Simulation.herbivore.size() + ",");
                fw.write(Simulation.predator.size() + ",");
                fw.write(Animals.herbivoresKilled + ",");
                fw.write(Animals.predatorsKilled + ",");
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
