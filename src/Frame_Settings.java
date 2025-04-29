import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Frame_Settings extends JFrame implements ActionListener {
    JButton button_accept;

    JTextField parameter;

    public static String text1;
    Frame_Settings(){

        this.setVisible(true); //creating a frame
        this.setSize(550, 500);
        this.setTitle("LaSimulation (settings)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JLabel label = new JLabel(); //creating a label with text
        label.setText(Main.n);
        this.add(label);
        label.setBounds(0, 0, 550, 100);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
        button_accept = new JButton(); //creating a button "Accept"
        button_accept.setBounds(200,365,125,50);
        this.add(button_accept);
        this.setLayout(null);
        button_accept.addActionListener(this);
        button_accept.setText("Accept");
        button_accept.setFocusable(false);

        parameter = new JTextField(); //creating a text field
        this.add(parameter);
        parameter.setBounds(215, 250, 100, 30);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button_accept){
            System.out.println("Click"); //actions after clicking the button

            text1 = parameter.getText();
            System.out.println(text1);
            button_accept.setEnabled(false);
            Frame_Simulation frame_simulation = new Frame_Simulation();
        }

    }
}
