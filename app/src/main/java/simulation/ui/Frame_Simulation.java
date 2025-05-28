package simulation.ui;

import javax.swing.*;
import java.awt.*;

import simulation.Main;
import simulation.core.Simulation;
import simulation.entities.River;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame_Simulation extends JFrame implements ActionListener {
        JButton button_slowdown;
        JButton button_pause;
        JButton button_resume;
        JButton button_speedup;
    class MapPanel extends JPanel {
        public MapPanel() {
            setOpaque(true);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            //Painting the background
            g.setColor(new Color(34, 139, 34));
            g.fillRect(0, 0, getWidth(), getHeight());

            //Painting the river
            River.createRiver(g);
        }
    }
    Frame_Simulation(){
        this.setVisible(true); //creating a frame
        this.setSize(1100, 1000);
        this.setTitle("LaSimulation (simulation)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        /*JLabel label = new JLabel(); //creating a label with text
        label.setText("Simulation will be there\n Parameter = " + Frame_Settings.text1);

        this.add(label);
        label.setBounds(0, 0, 550, 100);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);*/

        MapPanel mappanel = new MapPanel();

        Simulation simulation = new Simulation();

        //Simulation simulation = Main.simulation;

        mappanel.setBounds(0, 0, 1100, 1000);
        simulation.setBounds(0, 0, 1100, 1000);
        simulation.setOpaque(false);

        button_slowdown = new JButton(); //creating a button "Slowdown
        button_pause = new JButton();
        button_resume = new JButton();
        button_speedup = new JButton();

        button_slowdown.setBounds(920,950,45,45);
        button_pause.setBounds(965,950,45,45);
        button_resume.setBounds(1010,950,45,45);
        button_speedup.setBounds(1055,950,45,45);

        button_slowdown.addActionListener(this);
        button_slowdown.setText("S");
        button_slowdown.setFocusable(false);

        button_pause.addActionListener(this);
        button_pause.setText("P");
        button_pause.setFocusable(false);

        button_resume.addActionListener(this);
        button_resume.setText("R");
        button_resume.setFocusable(false);

        button_speedup.addActionListener(this);
        button_speedup.setText("F");
        button_speedup.setFocusable(false);

        JLayeredPane layers = new JLayeredPane();
        layers.setLayout(null);
        layers.setPreferredSize(new Dimension(1100, 1000));

        layers.add(mappanel, Integer.valueOf(0));
        layers.add(simulation, Integer.valueOf(1));

        layers.add(button_slowdown, Integer.valueOf(3));
        layers.add(button_pause, Integer.valueOf(3));
        layers.add(button_resume, Integer.valueOf(3));
        layers.add(button_speedup, Integer.valueOf(3));

        add(layers);
        pack();


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_slowdown){
            System.out.println("Click"); //actions after clicking the button

            if(Frame_Settings.speedofsimulation > 10) {
                Frame_Settings.speedofsimulation -= 1;
            }

            System.out.println(Frame_Settings.speedofsimulation);
        }

        if (e.getSource() == button_pause){
            System.out.println(Main.n); //actions after clicking the button

            Simulation.simulationTime.stop();
        }

        if (e.getSource() == button_resume){
            System.out.println("-" + Main.n); //actions after clicking the button

            Simulation.simulationTime.start();
        }

        if (e.getSource() == button_speedup){
            System.out.println("Click"); //actions after clicking the button

            Frame_Settings.speedofsimulation += 1;

            System.out.println(Frame_Settings.speedofsimulation);
        }
        //this.add(Main.simulation);

        //this.add(new MapPanel());

        this.setVisible(true);





    }
}