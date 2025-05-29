public abstract class Animals {
    static protected int herbivorePoints;
    static protected int predatorPoints;
    private Position position;
    private boolean onRiver = false;
    private static final int MAX_X = 1100;
    private static final int MAX_Y = 1000;

    public static void addHerbivorePoint(int pointsToAdd) {
        herbivorePoints += pointsToAdd;
    }

    public static void addPredatorPoint(int pointsToAdd) {
        predatorPoints += pointsToAdd;
    }


    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public boolean isOnRiver() {
        return onRiver;
    }

    /*Not needed
    public void updateRiverStatus() {
        this.onRiver = River.isOnRiver(getPosition().getX(), getPosition().getY());
    }*/


    public void checkForBorder() {
        Position pos = getPosition();
        if (pos.getX() < 0) {
            pos.setX(0);
        } else if (pos.getX() >= MAX_X) {
            pos.setX(MAX_X - 1);
        }

        if (pos.getY() < 0) {
            pos.setY(0);
        } else if (pos.getY() >= MAX_Y) {
            pos.setY(MAX_Y - 1);
        }
    }

    //
    public Animals(int xPos, int yPos) {
        this.position = new Position(xPos, yPos);
    }

    /**
     * Animal 2D position
     */

    public static class Position {
        private int x;
        private int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
}