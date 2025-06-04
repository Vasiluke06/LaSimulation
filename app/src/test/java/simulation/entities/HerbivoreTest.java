package simulation.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import simulation.core.Position;
import simulation.core.Simulation;
import simulation.ui.Frame_Settings;

public class HerbivoreTest {

    private Simulation simulation;
    private Herbivore h1;
    private Herbivore h2;

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
    public void findMateAssignsMate() {
        //Assign
        h1 = new Herbivore(true, 100, 100);
        h2 = new Herbivore(true, 200, 200);
        simulation.herbivore.clear();
        simulation.herbivore.add(h1);
        simulation.herbivore.add(h2);
        //Act
        h1.nextMove();
        //Assert
        assertEquals(h2.getPosition(), h1.getMate().getPosition());
    }

    @Test
    public void herbivoreReproductionAddsHerbivore() {
        //Assign
        Position expected = new Position(200, 200);
        h1 = new Herbivore(true, 100, 100);
        h2 = new Herbivore(true, 200, 200);
        simulation.herbivore.clear();
        simulation.herbivore.add(h1);
        simulation.herbivore.add(h2);
        //Act
        h1.nextMove();
        Position actual = simulation.herbivore.get(simulation.herbivore.size() - 1).getPosition();
        //Assert
        assertEquals(expected.getX(), actual.getX());
        assertEquals(expected.getY(), actual.getY());
    }
}
