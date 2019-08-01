import org.newdawn.slick.SlickException;

/**
 * The type Bus.
 * @author Sii Kim Lau
 */
public class Bus extends Collider {
    /**
     * The constant SPEED.
     */
    private static final float SPEED = (float) 0.15;

    /**
     * Instantiates a new Bus.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param moveRight the move right
     * @throws SlickException the slick exception
     */
    public Bus(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc, x, y, moveRight);
        setSpeed(SPEED);
    }
}
