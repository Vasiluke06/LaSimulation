package simulation.ui;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Objects;

public class Frame_Settings extends JFrame implements ActionListener {
    public static int numofherbivore;
    public static int numofpredator;
    public static int numofplants;
    public static int chanceofwildfire;
    public static int chanceofdrowning;
    public static int chanceofhunters;
    public static int pointsforvictory;
    public static int speedofsimulation;
    private final int settingsHeight = 700;
    private final int settingsWidth = 600;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();


    JButton button_accept;
    JButton button_fast_insert_of_parameters;

    JTextField parameter_numofherbivore;
    JTextField parameter_numofpredator;
    JTextField parameter_numofplants;
    JTextField parameter_chanceofwildfire;
    JTextField parameter_chanceofdrowing;
    JTextField parameter_chanceofhunters;
    JTextField parameter_pointsforvictory;
    JTextField parameter_speedofsimulation;

    public Frame_Settings(){

        this.setLocation((screenSize.width - settingsWidth) / 2, (screenSize.height - settingsHeight) / 2);

        this.setSize(settingsWidth, settingsHeight);
        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        /*JLabel label = new JLabel(); //creating a label with text
        label.setText(Main.n);
        this.add(label);
        label.setBounds(0, 0, 550, 100);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);*/

        this.setLayout(null);

        JLabel label_numofherbivore = new JLabel(); //creating a label "Number of hebivore"
        label_numofherbivore.setText("Number of herbivore");
        label_numofherbivore.setBounds(25, 160, 200, 20);

        JLabel label_numofpredator = new JLabel(); //creating a label "Number of predator"
        label_numofpredator.setText("Number of predator");
        label_numofpredator.setBounds(25, 210, 200, 20);

        JLabel label_numofplants = new JLabel(); //creating a label "Number of plants"
        label_numofplants.setText("Number of plants");
        label_numofplants.setBounds(25, 260, 200, 20);

        JLabel label_chanceofwildfire = new JLabel(); //creating a label "Chance of wildfire"
        label_chanceofwildfire.setText("Chance of wildfire (% per 100 ticks)");
        label_chanceofwildfire.setBounds(25, 310, 200, 20);

        JLabel label_chanceofdrowning = new JLabel(); //creating a label "Chance of drowning in river"
        label_chanceofdrowning.setText("Chance of drowning in the river");
        label_chanceofdrowning.setBounds(25, 360, 200, 20);

        JLabel label_chanceofhunters = new JLabel(); //creating a label "Chance of hunters to appear"
        label_chanceofhunters.setText("Chance of hunters appearing (% per 100 ticks)");
        label_chanceofhunters.setBounds(25, 410, 300, 20);

        JLabel label_pointsforvictory = new JLabel(); //creating a label "Points for victory"
        label_pointsforvictory.setText("Points for victory");
        label_pointsforvictory.setBounds(25, 460, 200, 20);

        JLabel label_speedofsimulation = new JLabel(); //creating a label "Speed of simulation"
        label_speedofsimulation.setText("Speed of simulation (ticks per second)");
        label_speedofsimulation.setBounds(25, 510, 250, 20);

        ImageIcon titleIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/title.png")));
        JLabel titleLabel = new JLabel(titleIcon);
        titleLabel.setBounds(((settingsWidth - 558) / 2), 20, 558, 128);

        button_accept = new JButton(); //creating a button "Accept"
        button_accept.setBounds(settingsWidth / 5,600,125,50);
        this.add(button_accept);

        button_accept.addActionListener(this);
        button_accept.setText("Accept");
        button_accept.setFocusable(false);

        button_fast_insert_of_parameters = new JButton(); //creating a button "Recommended fast insert"
        button_fast_insert_of_parameters.setBounds(settingsWidth /2,600,125,50);
        this.add(button_fast_insert_of_parameters);
        button_fast_insert_of_parameters.addActionListener(this);
        button_fast_insert_of_parameters.setText("Preset");
        button_fast_insert_of_parameters.setFocusable(false);

        this.add(label_numofherbivore);
        this.add(label_numofpredator);
        this.add(label_numofplants);
        this.add(label_chanceofwildfire);
        this.add(label_chanceofdrowning);
        this.add(label_chanceofhunters);
        this.add(label_pointsforvictory);
        this.add(label_speedofsimulation);
        this.add(titleLabel);

        parameter_numofherbivore = new JTextField(); //creating a text field
        this.add(parameter_numofherbivore);
        parameter_numofherbivore.setBounds(350, 160, 100, 25);

        parameter_numofpredator = new JTextField(); //creating a text field
        this.add(parameter_numofpredator);
        parameter_numofpredator.setBounds(350, 210, 100, 25);

        parameter_numofplants = new JTextField(); //creating a text field
        this.add(parameter_numofplants);
        parameter_numofplants.setBounds(350, 260, 100, 25);

        parameter_chanceofwildfire = new JTextField(); //creating a text field
        this.add(parameter_chanceofwildfire);
        parameter_chanceofwildfire.setBounds(350, 310, 100, 25);

        parameter_chanceofdrowing = new JTextField(); //creating a text field
        this.add(parameter_chanceofdrowing);
        parameter_chanceofdrowing.setBounds(350, 360, 100, 25);

        parameter_chanceofhunters = new JTextField(); //creating a text field
        this.add(parameter_chanceofhunters);
        parameter_chanceofhunters.setBounds(350, 410, 100, 25);

        parameter_pointsforvictory = new JTextField(); //creating a text field
        this.add(parameter_pointsforvictory);
        parameter_pointsforvictory.setBounds(350, 460, 100, 25);

        parameter_speedofsimulation = new JTextField(); //creating a text field
        this.add(parameter_speedofsimulation);
        parameter_speedofsimulation.setBounds(350, 510, 100, 25);

        URL iconURL = getClass().getResource("/images/icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());

        this.setVisible(true); //creating a frame

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

            button_fast_insert_of_parameters.setEnabled(false);


            Frame_Simulation frame_simulation = new Frame_Simulation();

            dispose();
        }

        if (e.getSource() == button_fast_insert_of_parameters){
            System.out.println("Clickn"); //actions after clicking the button

            parameter_numofherbivore.setText("10");

            parameter_numofpredator.setText("1");

            parameter_numofplants.setText("15");;

            parameter_chanceofwildfire.setText("5");

            parameter_chanceofdrowing.setText("0");

            parameter_chanceofhunters.setText("10");

            parameter_speedofsimulation.setText("10");

            parameter_pointsforvictory.setText("1000");

            //button_fast_insert_of_parameters.setEnabled(false);

        }
    }
}