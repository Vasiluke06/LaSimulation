package simulation.entities;

import java.awt.*;

public class River {
    public static final int RIVER_X = 481;
    public static final int RIVER_WIDTH = 138;
    public static final int RIVER_Y_START = 0;
    public static final int RIVER_Y_END = 1000;

    /**
     * Method used for determining if the position is on the river
     */

    public static boolean isOnRiver(int x, int y) {
        return x >= RIVER_X && x <= (RIVER_X + RIVER_WIDTH) &&
                y >= RIVER_Y_START && y <= RIVER_Y_END;
    }

    /**
     * River drawing method
     */
    public static void createRiver(Graphics g) {
        g.setColor(new Color(30, 144, 255)); // Blue for river
        g.fillRect(RIVER_X, RIVER_Y_START, RIVER_WIDTH, RIVER_Y_END);
    }
}
