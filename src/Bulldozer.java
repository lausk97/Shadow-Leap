import org.newdawn.slick.SlickException;

/**
 * The type Bulldozer.
 * @author Sii Kim Lau
 */
public class Bulldozer extends Shifter {
    private static final float SPEED = (float) 0.07;

    /**
     * Instantiates a new Bulldozer.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param moveRight the move right
     * @throws SlickException the slick exception
     */
    public Bulldozer(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc, x, y, moveRight);
        setSpeed(SPEED);
    }
}
