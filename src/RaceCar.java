import org.newdawn.slick.SlickException;

/**
 * The type Race car.
 * @author Sii Kim Lau
 */
public class RaceCar extends Collider {
    private static final float SPEED = (float) 0.5;

    /**
     * Instantiates a new Race car.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param moveRight the move right
     * @throws SlickException the slick exception
     */
    public RaceCar(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc, x, y, moveRight);
        setSpeed(SPEED);
    }
}
