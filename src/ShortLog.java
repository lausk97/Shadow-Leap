import org.newdawn.slick.SlickException;

/**
 * The type Short log.
 */
public class ShortLog extends Log {
    private static final float SPEED = (float) 0.1;

    /**
     * Instantiates a new Short log.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param moveRight the move right
     * @throws SlickException the slick exception
     */
    public ShortLog(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc, x, y, moveRight);
        setSpeed(SPEED);
    }
}
