package simulation;

import simulation.core.Simulation;
import simulation.ui.Frame_Settings;
import javax.swing.*;

public class Main {
    public static Simulation simulation;

    public static void main(String[] args) {
       // args = new String[]{"C:\\Users\\kraev\\Documents\\Polit_Wroclawska\\Java\\settings.csv"};
        if (args.length > 0) {
            String csvPath = args[0];
            Frame_Settings frame_settings = new Frame_Settings(csvPath);
        } else {
            Frame_Settings frame_settings = new Frame_Settings();
        }
    }

    public static String n = "The World!";
}