package simulation.entities;

import simulation.core.Simulation;


import java.awt.*;

public class River {
   /* public static final int RIVER_X = Frame_Simulation.WIDTH / 2;
    public static final int RIVER_WIDTH = Frame_Simulation.WIDTH / 5;
    public static final int RIVER_Y_START = 0;
    public static final int RIVER_Y_END = Frame_Simulation.HEIGHT;*/

    /**
     * Method used for determining if the position is on the river
     */
    public static boolean isOnRiver(int x, int y) {
        int riverX = Simulation.SCREEN_WIDTH / 2 - Simulation.SCREEN_WIDTH / 10;
        int riverWidth = Simulation.SCREEN_WIDTH / 6;
        int riverYStart = 0;
        int riverYEnd = Simulation.SCREEN_HEIGHT;

        return x >= riverX && x <= (riverX + riverWidth) &&
                y >= riverYStart && y <= riverYEnd;
    }

    /**
     * River drawing method
     */
    public static void createRiver(Graphics g) {
        Rectangle bounds = g.getClipBounds();
        int panelWidth = bounds.width;
        int panelHeight = bounds.height;

        int riverX = panelWidth / 2 - panelWidth / 10;
        int riverWidth = panelWidth / 6;
        int riverYStart = 0;
        int riverYEnd = panelHeight;

        g.setColor(new Color(30, 144, 255));
        g.fillRect(riverX, riverYStart, riverWidth, riverYEnd);
    }
}
