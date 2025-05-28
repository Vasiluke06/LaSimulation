package simulation.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame_Settings extends JFrame implements ActionListener {
    JButton button_accept;

    JTextField parameter_numofherbivore;
    JTextField parameter_numofpredator;
    JTextField parameter_numofplants;
    JTextField parameter_chanceofwildfire;

    JTextField parameter_chanceofdrowing;
    JTextField parameter_chanceofhunters;
    JTextField parameter_pointsforvictory;
    JTextField parameter_speedofsimulation;

    public static int numofherbivore;
    public static int numofpredator;
    public static int numofplants;
    public static int chanceofwildfire;
    public static int chanceofdrowning;
    public static int chanceofhunters;
    public static int pointsforvictory;
    public static int speedofsimulation;
    //Made constructor public
    public Frame_Settings(){

        this.setVisible(true); //creating a frame
        this.setSize(450, 560);
        this.setTitle("LaSimulation (settings)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        /*JLabel label = new JLabel(); //creating a label with text
        label.setText(Main.n);
        this.add(label);
        label.setBounds(0, 0, 550, 100);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);*/

        JLabel label_numofherbivore = new JLabel(); //creating a label "Number of hebivore"
        label_numofherbivore.setText("Number of herbivore");
        label_numofherbivore.setBounds(25, 50, 200, 20);

        JLabel label_numofpredator = new JLabel(); //creating a label "Number of predator"
        label_numofpredator.setText("Number of predator");
        label_numofpredator.setBounds(25, 100, 200, 20);

        JLabel label_numofplants = new JLabel(); //creating a label "Number of plants"
        label_numofplants.setText("Number of plants");
        label_numofplants.setBounds(25, 150, 200, 20);

        JLabel label_chanceofwildfire = new JLabel(); //creating a label "Chance of wildfire"
        label_chanceofwildfire.setText("Chance of wildfire");
        label_chanceofwildfire.setBounds(25, 200, 200, 20);

        JLabel label_chanceofdrowning = new JLabel(); //creating a label "Chance of drowning in river"
        label_chanceofdrowning.setText("Chance of drowning in the river");
        label_chanceofdrowning.setBounds(25, 250, 200, 20);

        JLabel label_chanceofhunters = new JLabel(); //creating a label "Chance of hunters to appear"
        label_chanceofhunters.setText("Chance of hunters appearing");
        label_chanceofhunters.setBounds(25, 300, 200, 20);

        JLabel label_pointsforvictory = new JLabel(); //creating a label "Points for victory"
        label_pointsforvictory.setText("Points for victory");
        label_pointsforvictory.setBounds(25, 350, 200, 20);

        JLabel label_speedofsimulation = new JLabel(); //creating a label "Speed of simulation"
        label_speedofsimulation.setText("Speed of simulation (tps)");
        label_speedofsimulation.setBounds(25, 400, 200, 20);

        button_accept = new JButton(); //creating a button "Accept"
        button_accept.setBounds(150,450,125,50);
        this.add(button_accept);
        this.setLayout(null);
        button_accept.addActionListener(this);
        button_accept.setText("Accept");
        button_accept.setFocusable(false);

        this.add(label_numofherbivore);
        this.add(label_numofpredator);
        this.add(label_numofplants);
        this.add(label_chanceofwildfire);
        this.add(label_chanceofdrowning);
        this.add(label_chanceofhunters);
        this.add(label_pointsforvictory);
        this.add(label_speedofsimulation);

        parameter_numofherbivore = new JTextField(); //creating a text field
        this.add(parameter_numofherbivore);
        parameter_numofherbivore.setBounds(300, 50, 100, 25);

        parameter_numofpredator = new JTextField(); //creating a text field
        this.add(parameter_numofpredator);
        parameter_numofpredator.setBounds(300, 100, 100, 25);

        parameter_numofplants = new JTextField(); //creating a text field
        this.add(parameter_numofplants);
        parameter_numofplants.setBounds(300, 150, 100, 25);

        parameter_chanceofwildfire = new JTextField(); //creating a text field
        this.add(parameter_chanceofwildfire);
        parameter_chanceofwildfire.setBounds(300, 200, 100, 25);

        parameter_chanceofdrowing = new JTextField(); //creating a text field
        this.add(parameter_chanceofdrowing);
        parameter_chanceofdrowing.setBounds(300, 250, 100, 25);

        parameter_chanceofhunters = new JTextField(); //creating a text field
        this.add(parameter_chanceofhunters);
        parameter_chanceofhunters.setBounds(300, 300, 100, 25);

        parameter_pointsforvictory = new JTextField(); //creating a text field
        this.add(parameter_pointsforvictory);
        parameter_pointsforvictory.setBounds(300, 350, 100, 25);

        parameter_speedofsimulation = new JTextField(); //creating a text field
        this.add(parameter_speedofsimulation);
        parameter_speedofsimulation.setBounds(300, 400, 100, 25);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_accept){
            System.out.println("Click"); //actions after clicking the button

            numofherbivore = Integer.parseInt(parameter_numofherbivore.getText());
            System.out.println(numofherbivore);

            numofpredator = Integer.parseInt(parameter_numofpredator.getText());
            System.out.println(numofpredator);

            numofplants = Integer.parseInt(parameter_numofplants.getText());
            System.out.println(numofplants);

            chanceofwildfire = Integer.parseInt(parameter_chanceofwildfire.getText());
            System.out.println(chanceofwildfire);

            chanceofdrowning = Integer.parseInt(parameter_chanceofdrowing.getText());
            System.out.println(chanceofdrowning);

            chanceofhunters = Integer.parseInt(parameter_chanceofhunters.getText());
            System.out.println(chanceofhunters);

            speedofsimulation = Integer.parseInt(parameter_speedofsimulation.getText());
            System.out.println(speedofsimulation);

            pointsforvictory = Integer.parseInt(parameter_pointsforvictory.getText());
            System.out.println(pointsforvictory);
            button_accept.setEnabled(false);
            Frame_Simulation frame_simulation = new Frame_Simulation();
        }

    }
}