package simulation.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Objects;

import simulation.Main;
import simulation.core.Simulation;
import simulation.core.Animals;

public class Frame_Simulation extends JFrame implements ActionListener {
    JButton button_slowdown;
    JButton button_pause;
    JButton button_resume;
    JButton button_speedup;
    JButton button_off;

    ImageIcon button_slowdown_icon;
    ImageIcon button_pause_icon;
    ImageIcon button_resume_icon;
    ImageIcon button_speedup_icon;
    ImageIcon button_off_icon;

    public void updatePointsDisplay() {
        herbivorePointsLabel.setText("Herbivore Points: " + Animals.getHerbivorePoints());
        predatorPointsLabel.setText("Predator Points: " + Animals.getPredatorPoints());
    }

    private JLabel herbivorePointsLabel;
    private JLabel predatorPointsLabel;

    private int control_buttons_size = 45;

    public static Frame_Simulation instance;


    class MapPanel extends JPanel {
        public MapPanel() {
            setOpaque(true);
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            g.setColor(new Color(34, 139, 34));
            g.fillRect(0, 0, getWidth(), getHeight());
        }
    }

    Frame_Simulation(){
        Frame_Simulation.instance = this;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize);// sets JFrame to screen size

        //this.setVisible(true); //creating a frame
        this.setSize(screenSize.width, screenSize.height);
        this.setTitle("Forest Simulation");
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

        button_slowdown = new JButton(); //creating a button "Slowdown"
        button_pause = new JButton();
        button_resume = new JButton();
        button_speedup = new JButton();
        button_off = new JButton();

        button_slowdown_icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/slowdown_button.png")));
        button_pause_icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/pause_button.png")));
        button_resume_icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/resume_button.png")));
        button_speedup_icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/speedup_button.png")));
        button_off_icon = new ImageIcon(Objects.requireNonNull(getClass().getResource("/images/power_button.png")));


        mappanel.setBounds(0, 0, screenSize.width, screenSize.height);
        simulation.setBounds(0, 0, screenSize.width, screenSize.height);
        simulation.setOpaque(false);

        herbivorePointsLabel = new JLabel("Herbivore Points: 0");
        herbivorePointsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        herbivorePointsLabel.setForeground(Color.WHITE);
        herbivorePointsLabel.setBounds(20, 20, 250, 30);

        predatorPointsLabel = new JLabel("Predator Points: 0");
        predatorPointsLabel.setFont(new Font("Arial", Font.BOLD, 18));
        predatorPointsLabel.setForeground(Color.WHITE);
        predatorPointsLabel.setBounds(20, 60, 250, 30);

        /**
         * Simulation control buttons
         */
        button_off.setBounds(screenSize.width - 230, screenSize.height - 100, control_buttons_size, control_buttons_size);
        button_pause.setBounds(screenSize.width - 185,screenSize.height - 100, control_buttons_size, control_buttons_size);
        button_resume.setBounds(screenSize.width - 140,screenSize.height - 100, control_buttons_size, control_buttons_size);
        button_slowdown.setBounds(screenSize.width - 95, screenSize.height - 100, control_buttons_size, control_buttons_size);
        button_speedup.setBounds(screenSize.width - 50,screenSize.height - 100, control_buttons_size, control_buttons_size);

        button_slowdown.addActionListener(this);
        button_slowdown.setIcon(button_slowdown_icon);
        button_slowdown.setFocusable(false);

        button_pause.addActionListener(this);
        button_pause.setIcon(button_pause_icon);
        button_pause.setFocusable(false);

        button_resume.addActionListener(this);
        button_resume.setIcon(button_resume_icon);
        button_resume.setFocusable(false);

        button_speedup.addActionListener(this);
        button_speedup.setIcon(button_speedup_icon);
        button_speedup.setFocusable(false);

        button_off.addActionListener(this);
        button_off.setIcon(button_off_icon);
        button_off.setFocusable(false);

        JLayeredPane layers = new JLayeredPane();
        layers.setLayout(null);
        //layers.setPreferredSize(new Dimension(1100, 1000));

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true); // removes window borders and title bar
        layers.setPreferredSize(screenSize);

        layers.add(mappanel, Integer.valueOf(0));
        layers.add(simulation, Integer.valueOf(1));
        layers.add(button_slowdown, Integer.valueOf(3));
        layers.add(button_pause, Integer.valueOf(3));
        layers.add(button_resume, Integer.valueOf(3));
        layers.add(button_speedup, Integer.valueOf(3));
        layers.add(button_off, Integer.valueOf(3));
        layers.add(herbivorePointsLabel, Integer.valueOf(4));
        layers.add(predatorPointsLabel, Integer.valueOf(4));

        add(layers);
        pack();
        //this.add(Main.simulation);

        //this.add(new MapPanel());

        this.setVisible(true);

        URL iconURL = getClass().getResource("/images/icon.png");
        ImageIcon icon = new ImageIcon(iconURL);
        this.setIconImage(icon.getImage());

/*        button_slowdown.setToolTipText("Slow Down");
        button_pause.setToolTipText("Pause");
        button_resume.setToolTipText("Resume");
        button_speedup.setToolTipText("Speed Up");
*/


    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_slowdown){
            System.out.println("Click"); //actions after clicking the button

            if(Frame_Settings.speedofsimulation > 1) {
                Frame_Settings.speedofsimulation -= 5;
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

            Frame_Settings.speedofsimulation += 5;

            System.out.println(Frame_Settings.speedofsimulation);
        }

        if (e.getSource() == button_off){
            System.out.println("Off"); //actions after clicking the button

            this.dispose();

            Simulation.simulationTime.stop();

            System.exit(0);
    }
}
}