import org.newdawn.slick.SlickException;

/**
 * The type Long log.
 * @author Sii Kim Lau
 */
public class LongLog extends Log {
    private static final float SPEED = (float) 0.07;

    /**
     * Instantiates a new Long log.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param moveRight the move right
     * @throws SlickException the slick exception
     */
    public LongLog(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc, x, y, moveRight);
        setSpeed(SPEED);
    }
}
