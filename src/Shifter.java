import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Shifter.
 * @author Sii Kim Lau
 */
public class Shifter extends ActionSprites {
    private static boolean isFloating = false;

    /**
     * Instantiates a new Shifter.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param moveRight the move right
     * @throws SlickException the slick exception
     */
    public Shifter(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc, x, y, moveRight);
    }

    /**
     * Update the shifter.
     *
     * @param input the input
     * @param delta the delta
     */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
        move(delta);
    }

    /**
     * Is floating boolean.
     *
     * @return the boolean
     */
    public static boolean isFloating() {
        return isFloating;
    }

    /**
     * Sets floating.
     *
     * @param floating the floating
     */
    public static void setFloating(boolean floating) {
        isFloating = floating;
    }
}
