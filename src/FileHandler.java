import org.newdawn.slick.SlickException;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * The type File handler.
 * @author Sii Kim Lau
 */
public class FileHandler {

    /**
     * Initialise the world.
     *
     * @param lvlFile the lvl file
     * @throws IOException    the io exception
     * @throws SlickException the slick exception
     */
    public static void initWorld(String lvlFile)
            throws IOException, SlickException {

        File file = new File(lvlFile);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String text;

            while ((text = br.readLine()) != null) {
                String[] parts = text.split(",");
                String name = parts[0];
                float x = Float.parseFloat(parts[1]);
                float y = Float.parseFloat(parts[2]);

                switch (name) {
                    case "water": case "grass" : case "tree" :
                        addSprites(name, x, y);
                        break;
                    default:
                        addSprites(name, x, y, Boolean.parseBoolean(parts[3]));
                        break;
                }
            }
        }
    }

    /**
     * Add sprites based on the name and set its positions according to the file
     *
     * @param name the name
     * @param x    the x
     * @param y    the y
     * @throws SlickException the slick exception
     */
    private static void addSprites(String name, float x, float y)
            throws SlickException {
        switch (name) {
            case "water":
                World.getTiles().add(new Water("assets/water.png", x, y, name));
                break;
            case "grass":
                World.getTiles().add(new Grass("assets/grass.png", x, y, name));
                break;
            case "tree":
                World.getTiles().add(new Tree("assets/tree.png", x, y, name));
                break;
            default:
                break;
        }
    }

    /**
     * Add sprites based on the name and set its positions according to the file
     *
     * @param name      the name
     * @param x         the x
     * @param y         the y
     * @param moveRight the move right
     * @throws SlickException the slick exception
     */
    private static void addSprites(String name, float x, float y, boolean moveRight)
            throws SlickException {
        switch (name) {
            case "bus":
                World.getColliders().add(new Bus("assets/bus.png", x, y, moveRight));
                break;
            case "bike":
                World.getColliders().add(new Bike("assets/bike.png", x, y, moveRight));
                break;
            case "racecar":
                World.getColliders().add(new RaceCar("assets/racecar.png", x, y, moveRight));
                break;
            case "bulldozer":
                World.getShifters().add(new Bulldozer("assets/bulldozer.png", x, y, moveRight));
                break;
            case "log":
                World.getShifters().add(new ShortLog("assets/log.png", x, y, moveRight));
                break;
            case "longLog":
                World.getShifters().add(new LongLog("assets/longlog.png", x, y, moveRight));
                break;
            case "turtle":
                World.getShifters().add(new Turtle("assets/turtles.png", x, y, moveRight));
                break;
            default:
                break;
        }

    }
}
