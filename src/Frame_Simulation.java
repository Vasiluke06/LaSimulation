import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame_Simulation extends JFrame implements ActionListener {
    JButton button_slowdown;
    JButton button_pause;
    JButton button_resume;
    JButton button_fastup;

    ImageIcon button_slowdown_icon;
    ImageIcon button_pause_icon;
    ImageIcon button_resume_icon;
    ImageIcon button_fastup_icon;

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
        button_fastup = new JButton();

        button_slowdown_icon = new ImageIcon("images/slowdown_button.png");
        button_pause_icon = new ImageIcon("images/pause_button.png");
        button_resume_icon = new ImageIcon("images/resume_button.png");
        button_fastup_icon = new ImageIcon("images/speedup_button.png");






        //Simulation simulation = Main.simulation;

        mappanel.setBounds(0, 0, 1100, 1000);
        simulation.setBounds(0, 0, 1100, 1000);
        simulation.setOpaque(false);
        button_slowdown.setBounds(920,950,45,45);
        button_pause.setBounds(965,950,45,45);
        button_resume.setBounds(1010,950,45,45);
        button_fastup.setBounds(1055,950,45,45);

        button_slowdown.addActionListener(this);
        button_slowdown.setIcon(button_slowdown_icon);
        button_slowdown.setFocusable(false);

        button_pause.addActionListener(this);
        button_pause.setIcon(button_pause_icon);
        button_pause.setFocusable(false);

        button_resume.addActionListener(this);
        button_resume.setIcon(button_resume_icon);
        button_resume.setFocusable(false);

        button_fastup.addActionListener(this);
        button_fastup.setIcon(button_fastup_icon);
        button_fastup.setFocusable(false);

        JLayeredPane layers = new JLayeredPane();
        layers.setLayout(null);
        layers.setPreferredSize(new Dimension(1100, 1000));

        layers.add(mappanel, Integer.valueOf(0));
        layers.add(simulation, Integer.valueOf(1));
        layers.add(button_slowdown, Integer.valueOf(3));
        layers.add(button_pause, Integer.valueOf(3));
        layers.add(button_resume, Integer.valueOf(3));
        layers.add(button_fastup, Integer.valueOf(3));

        add(layers);
        pack();
        //this.add(Main.simulation);

        //this.add(new MapPanel());

        this.setVisible(true);





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

            Simulation.simulation_time.stop();
        }

        if (e.getSource() == button_resume){
            System.out.println("-" + Main.n); //actions after clicking the button

            Simulation.simulation_time.start();
        }

        if (e.getSource() == button_fastup){
            System.out.println("Click"); //actions after clicking the button

            Frame_Settings.speedofsimulation += 1;

            System.out.println(Frame_Settings.speedofsimulation);
        }
    }
}
