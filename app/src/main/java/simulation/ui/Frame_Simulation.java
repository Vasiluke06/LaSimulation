package simulation.ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import simulation.Main;
import simulation.core.Simulation;

public class Frame_Simulation extends JFrame implements ActionListener {
    JButton button_slowdown;
    JButton button_pause;
    JButton button_resume;
    JButton button_speedup;

    ImageIcon button_slowdown_icon;
    ImageIcon button_pause_icon;
    ImageIcon button_resume_icon;
    ImageIcon button_speedup_icon;

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

            /*g.setColor(new Color(30, 144, 255));
            int riverWidth = 80;
            int x = getWidth() / 2 - riverWidth / 2;
            g.fillRect(x, 0, riverWidth, getHeight());*/
        }
    }
    Frame_Simulation(){
        Frame_Simulation.instance = this;

        //this.setVisible(true); //creating a frame
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

        button_slowdown = new JButton(); //creating a button "Slowdown"
        button_pause = new JButton();
        button_resume = new JButton();
        button_speedup = new JButton();

        button_slowdown_icon = new ImageIcon(getClass().getResource("/images/slowdown_button.png"));
        button_pause_icon = new ImageIcon(getClass().getResource("/images/pause_button.png"));
        button_resume_icon = new ImageIcon(getClass().getResource("/images/resume_button.png"));
        button_speedup_icon = new ImageIcon(getClass().getResource("/images/speedup_button.png"));



        //Simulation simulation = Main.simulation;

        mappanel.setBounds(0, 0, 1100, 864);
        simulation.setBounds(0, 0, 1100, 864);
        simulation.setOpaque(false);
        button_slowdown.setBounds(920,665,45,45);
        button_pause.setBounds(965,665,45,45);
        button_resume.setBounds(1010,665,45,45);
        button_speedup.setBounds(1055,665,45,45);

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

        JLayeredPane layers = new JLayeredPane();
        layers.setLayout(null);
        //layers.setPreferredSize(new Dimension(1100, 1000));
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize); // set JFrame to screen size
        this.setExtendedState(JFrame.MAXIMIZED_BOTH); // optional: maximize the window
        this.setUndecorated(true); // optional: remove window borders and title bar
        layers.setPreferredSize(screenSize);

        layers.add(mappanel, Integer.valueOf(0));
        layers.add(simulation, Integer.valueOf(1));
        layers.add(button_slowdown, Integer.valueOf(3));
        layers.add(button_pause, Integer.valueOf(3));
        layers.add(button_resume, Integer.valueOf(3));
        layers.add(button_speedup, Integer.valueOf(3));

        add(layers);
        pack();
        //this.add(Main.simulation);

        //this.add(new MapPanel());

        this.setVisible(true);

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
    }
}