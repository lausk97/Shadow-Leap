import org.newdawn.slick.SlickException;

/**
 * The type Log.
 * @author Sii Kim Lau
 */
public class Log extends Shifter {
    /**
     * Instantiates a new Log.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param moveRight the move right
     * @throws SlickException the slick exception
     */
    public Log(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc, x, y, moveRight);
        setFloating(true);
    }
}
