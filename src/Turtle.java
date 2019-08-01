import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Turtle.
 * @author Sii Kim Lau
 */
public class Turtle extends Shifter {
    private static final float SPEED = (float) 0.085;
    private static final int FLOATING_DURATION = 7000;
    private static final int SUBMERGED_DURATION = 2000;

    /**
     * Instantiates a new Turtle.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param moveRight the move right
     * @throws SlickException the slick exception
     */
    public Turtle(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc, x, y, moveRight);
        setSpeed(SPEED);
        setFloating(true);
    }

    /**
     * Update the turtle.
     *
     * @param input the input
     * @param delta the delta
     */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);

        if (isFloating() && World.getTurtleCounterTime() >= FLOATING_DURATION) {
            World.setTurtleCounterTime(0);
            setFloating(false);
        } else if (!(isFloating()) && World.getTurtleCounterTime() >= SUBMERGED_DURATION) {
            World.setTurtleCounterTime(0);
            setFloating(true);
        }

    }

    /**
     * Render the turtle.
     *
     * @throws SlickException the slick exception
     */
    @Override
    public void render() throws SlickException {
        if (isFloating()) {
            super.render();
        }
    }
}
