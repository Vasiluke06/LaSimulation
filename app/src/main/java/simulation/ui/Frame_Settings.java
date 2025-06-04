package simulation.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.URL;
import java.util.Objects;
import java.io.IOException;

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
    ImageIcon button_accept_icon;
    JButton button_fast_insert_of_parameters;
    ImageIcon button_preset_icon;

    JTextField parameter_numofherbivore;
    JTextField parameter_numofpredator;
    JTextField parameter_numofplants;
    JTextField parameter_chanceofwildfire;
    JTextField parameter_chanceofdrowning;
    JTextField parameter_chanceofhunters;
    JTextField parameter_pointsforvictory;
    JTextField parameter_speedofsimulation;

    /*
        CSV file input
     */
    public Frame_Settings(String csvPath){
        this();
        loadSettingsFromCSV(csvPath);
    }

    protected void loadSettingsFromCSV(String path) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = reader.readLine(); // Read only the first line for simplicity
            reader.close();

            String[] values = line.split(",");
            if (values.length < 8) {
                throw new IllegalArgumentException("CSV must contain at least 8 values.");
            }

            parameter_numofherbivore.setText(values[0]);
            parameter_numofpredator.setText(values[1]);
            parameter_numofplants.setText(values[2]);
            parameter_chanceofwildfire.setText(values[3]);
            parameter_chanceofdrowning.setText(values[4]);
            parameter_chanceofhunters.setText(values[5]);
            parameter_pointsforvictory.setText(values[6]);
            parameter_speedofsimulation.setText(values[7]);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Failed to read CSV file: " + ex.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "CSV Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
        Default constructor
     */

    public Frame_Settings(){

        this.setLocation((screenSize.width - settingsWidth) / 2, (screenSize.height - settingsHeight) / 2);

        this.setSize(settingsWidth, settingsHeight);
        this.setTitle("Settings");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        /*
            Labels for input fields
         */

        //creating a label "Number of herbivores"
        JLabel label_numofherbivore = new JLabel();
        label_numofherbivore.setText("Number of herbivores");
        label_numofherbivore.setBounds(60, 160, 200, 20);
        //creating a label "Number of predators"
        JLabel label_numofpredator = new JLabel();
        label_numofpredator.setText("Number of predators");
        label_numofpredator.setBounds(60, 210, 200, 20);
        //creating a label "Number of plants"
        JLabel label_numofplants = new JLabel();
        label_numofplants.setText("Number of plants");
        label_numofplants.setBounds(60, 260, 200, 20);
        //creating a label "Chance of wildfire"
        JLabel label_chanceofwildfire = new JLabel();
        label_chanceofwildfire.setText("Chance of wildfire");
        label_chanceofwildfire.setBounds(60, 310, 200, 20);
        //creating a label "Chance of drowning in river"
        JLabel label_chanceofdrowning = new JLabel();
        label_chanceofdrowning.setText("Chance of drowning in the river");
        label_chanceofdrowning.setBounds(60, 360, 200, 20);
        //creating a label "Chance of hunters to appear"
        JLabel label_chanceofhunters = new JLabel();
        label_chanceofhunters.setText("Chance of hunters appearing");
        label_chanceofhunters.setBounds(60, 410, 200, 20);
        //creating a label "Points for victory"
        JLabel label_pointsforvictory = new JLabel();
        label_pointsforvictory.setText("Points for victory");
        label_pointsforvictory.setBounds(60, 460, 200, 20);
        //creating a label "Speed of simulation"
        JLabel label_speedofsimulation = new JLabel();
        label_speedofsimulation.setText("Simulation speed (tps)");
        label_speedofsimulation.setBounds(60, 510, 200, 20);
        //creating the title banner
        ImageIcon titleIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/title.png")));
        JLabel titleLabel = new JLabel(titleIcon);
        titleLabel.setBounds(((settingsWidth - 558) / 2), 20, 558, 128);

        //creating a button "Accept"
        button_accept = new JButton();
        button_accept_icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/accept_button.png")));
        button_accept.setBounds(settingsWidth - 265,575,125,50);
        button_accept.setIcon(button_accept_icon);
        this.add(button_accept);
        button_accept.addActionListener(this);
        button_accept.setFocusable(false);
        //creating a button "Preset"
        button_fast_insert_of_parameters = new JButton();
        button_preset_icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/preset_button.png")));
        button_fast_insert_of_parameters.setBounds(140,575,125,50);
        button_fast_insert_of_parameters.setIcon(button_preset_icon);
        this.add(button_fast_insert_of_parameters);
        button_fast_insert_of_parameters.addActionListener(this);
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

        /*
            Creating input fields
         */

        //Number of herbivores field
        parameter_numofherbivore = new JTextField();
        this.add(parameter_numofherbivore);
        parameter_numofherbivore.setBounds(400, 160, 100, 25);
        //Number of predators field
        parameter_numofpredator = new JTextField(); //creating a text field
        this.add(parameter_numofpredator);
        parameter_numofpredator.setBounds(400, 210, 100, 25);
        //Number of plants field
        parameter_numofplants = new JTextField(); //creating a text field
        this.add(parameter_numofplants);
        parameter_numofplants.setBounds(400, 260, 100, 25);
        //Chance of a wildfire field
        parameter_chanceofwildfire = new JTextField(); //creating a text field
        this.add(parameter_chanceofwildfire);
        parameter_chanceofwildfire.setBounds(400, 310, 100, 25);
        //Chance of drowning field
        parameter_chanceofdrowning = new JTextField(); //creating a text field
        this.add(parameter_chanceofdrowning);
        parameter_chanceofdrowning.setBounds(400, 360, 100, 25);
        //Chance of hunters field
        parameter_chanceofhunters = new JTextField(); //creating a text field
        this.add(parameter_chanceofhunters);
        parameter_chanceofhunters.setBounds(400, 410, 100, 25);
        //Points for victory field
        parameter_pointsforvictory = new JTextField(); //creating a text field
        this.add(parameter_pointsforvictory);
        parameter_pointsforvictory.setBounds(400, 460, 100, 25);
        //Simulation speed field
        parameter_speedofsimulation = new JTextField(); //creating a text field
        this.add(parameter_speedofsimulation);
        parameter_speedofsimulation.setBounds(400, 510, 100, 25);

        //Changing the icon of the settings window
        URL iconURL = getClass().getResource("/images/icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());

        this.setVisible(true); //creating a frame

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_accept) {

            try {
                numofherbivore = Integer.parseInt(parameter_numofherbivore.getText());
                if (numofherbivore < 1 || numofherbivore > 300) {
                    throw new IllegalArgumentException("Number of herbivores must be between 0 and 300.");
                }

                numofpredator = Integer.parseInt(parameter_numofpredator.getText());
                numofplants = Integer.parseInt(parameter_numofplants.getText());
                if (numofplants < 1 || numofplants > 300) {
                    throw new IllegalArgumentException("Number of plants must be between 0 and 300.");
                }

                chanceofwildfire = Integer.parseInt(parameter_chanceofwildfire.getText());
                if (chanceofwildfire < 0 || chanceofwildfire > 100) {
                    throw new IllegalArgumentException("Chance of wildfire must be between 0 and 100.");
                }

                chanceofdrowning = Integer.parseInt(parameter_chanceofdrowning.getText());
                if (chanceofdrowning < 0 || chanceofdrowning > 100) {
                    throw new IllegalArgumentException("Chance of drowning must be between 0 and 100.");
                }

                chanceofhunters = Integer.parseInt(parameter_chanceofhunters.getText());
                if (chanceofhunters < 0 || chanceofhunters > 100) {
                    throw new IllegalArgumentException("Chance of hunters must be between 0 and 100.");
                }

                speedofsimulation = Integer.parseInt(parameter_speedofsimulation.getText());
                if (speedofsimulation < 1 || speedofsimulation > 300) {
                    throw new IllegalArgumentException("Speed of simulation must be between 0 and 300.");
                }
                pointsforvictory = Integer.parseInt(parameter_pointsforvictory.getText());
                if (pointsforvictory < 0 || pointsforvictory > 10000) {
                    throw new IllegalArgumentException("Points forvictory must be between 0 and 10000.");
                }

                button_accept.setEnabled(false);
                button_fast_insert_of_parameters.setEnabled(false);

                Frame_Simulation frame_simulation = new Frame_Simulation();
                dispose();

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter only numbers in all fields.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage(), "Invalid Value", JOptionPane.ERROR_MESSAGE);
            }
        }


        if (e.getSource() == button_fast_insert_of_parameters){

            parameter_numofherbivore.setText("15");

            parameter_numofpredator.setText("5");

            parameter_numofplants.setText("30");;

            parameter_chanceofwildfire.setText("30");

            parameter_chanceofdrowning.setText("10");

            parameter_chanceofhunters.setText("30");

            parameter_pointsforvictory.setText("1000");

            parameter_speedofsimulation.setText("10");



            //button_fast_insert_of_parameters.setEnabled(false);

        }
    }
}