import javax.swing.*;

public class Frame_Simulation extends JFrame {
    Frame_Simulation(){
        this.setVisible(true); //creating a frame
        this.setSize(550, 500);
        this.setTitle("LaSimulation (simulation)");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);

        JLabel label = new JLabel(); //creating a label with text
        label.setText("Simulation will be there\n Parameter = " + Frame_Settings.text1);
        this.add(label);
        label.setBounds(0, 0, 550, 100);
        label.setVerticalAlignment(JLabel.TOP);
        label.setHorizontalAlignment(JLabel.CENTER);
    }
}
