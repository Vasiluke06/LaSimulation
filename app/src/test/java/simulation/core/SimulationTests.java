package simulation.core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import simulation.ui.Frame_Settings;

import java.io.FileNotFoundException;
import java.io.IOException;

public class SimulationTests {
    private Simulation simulation;

    @BeforeEach
    void setup() {
        Frame_Settings.speedofsimulation = 1;
        Frame_Settings.numofherbivore = 2;
        Frame_Settings.numofpredator = 1;
        Frame_Settings.numofplants = 1;
        Frame_Settings.pointsforvictory = 100;
        Frame_Settings.chanceofwildfire = 0;
        Frame_Settings.chanceofhunters = 0;
        simulation = new Simulation();
        Simulation.SCREEN_WIDTH = 800;
        Simulation.SCREEN_HEIGHT = 600;
    }

    @Test
    void loadImageThrowsNullPointerExceptionIfPathIsNull() {
        assertThrows(NullPointerException.class, () -> {
            simulation.loadImage(null);
        });
    }

    @Test
    void loadImageThrowsIllegalArgumentExceptionIfResourceIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            simulation.loadImage("images/wild.png");
        });
    }
}
