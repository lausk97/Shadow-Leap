import org.newdawn.slick.SlickException;

/**
 * The type Grass.
 */
public class Grass extends Tile {
    /**
     * Instantiates a new Grass.
     *
     * @param imageSrc the image src
     * @param x        the x
     * @param y        the y
     * @param name     the name
     * @throws SlickException the slick exception
     */
    public Grass(String imageSrc, float x, float y, String name) throws SlickException {
        super(imageSrc, x, y, name);
    }
}
