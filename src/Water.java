import org.newdawn.slick.SlickException;

/**
 * The type Water.
 * @author Sii Kim Lau
 */
public class Water extends Tile {

    /**
     * Instantiates a new Water.
     *
     * @param imageSrc the image src
     * @param x        the x
     * @param y        the y
     * @param name     the name
     * @throws SlickException the slick exception
     */
    public Water(String imageSrc, float x, float y, String name) throws SlickException {
        super(imageSrc, x, y, name);
    }
}
