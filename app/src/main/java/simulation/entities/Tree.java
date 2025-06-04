package simulation.entities;

import simulation.core.Plants;
import simulation.core.Position;
import simulation.core.Simulation;

import java.awt.*;

public class Tree {
    private static int spawnTimer;
    private Position position;
    private static final int SPRITE_SIZE = 72;
    private final Image treeImage;


        public Position getPosition() {
            return position;
        }

    public Tree() {
        this.treeImage = Simulation.treeImages.get(Simulation.random.nextInt(Simulation.treeImages.size()));
    }

    public Tree(int x, int y) {
        this.position = new Position(x, y);
        this.treeImage = Simulation.treeImages.get(Simulation.random.nextInt(Simulation.treeImages.size()));
    }

        public static void new_tree(int x, int y){
            Simulation.tree.add(new Tree(x,y));
        }


        public static void spawn_new_tree(){
            if (spawnTimer >= 150){
                Position pos = new Position(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE), Simulation.random.nextInt(Simulation.SCREEN_HEIGHT - SPRITE_SIZE));

                while (River.isOnRiver(pos.getX(), pos.getY())) {
                    pos.setX(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE));
                }

                new_tree(pos.getX(), pos.getY());

                spawnTimer = 0;
            }

            spawnTimer++;
        }

        public void drawTree(Graphics g) {
        Position p = getPosition();
        g.drawImage(treeImage, p.getX(), p.getY(), 64, 64, null);
        }

        /**
         * Method used for initializing the herbivores
         */

        public static void initTree(int numOfTrees) {
            for (int i = 0; i < numOfTrees; i++) {
                Position pos = new Position(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE),
                                    Simulation.random.nextInt(Simulation.SCREEN_HEIGHT - SPRITE_SIZE));
                while (River.isOnRiver(pos.getX(), pos.getY())) {
                    pos.setX(Simulation.random.nextInt(Simulation.SCREEN_WIDTH - SPRITE_SIZE));
                }

                new_tree(pos.getX(), pos.getY());
            }
        }
    }


