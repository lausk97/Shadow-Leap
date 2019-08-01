import org.newdawn.slick.SlickException;

/**
 * The type Bike.
 * @author Sii Kim Lau
 */
public class Bike extends Collider {
    private static final float SPEED = (float) 0.2;

    /**
     * Instantiates a new Bike.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param moveRight the move right
     * @throws SlickException the slick exception
     */
    public Bike(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc, x, y, moveRight);
        setMoveInScreen(true);
        setSpeed(SPEED);
    }
}
