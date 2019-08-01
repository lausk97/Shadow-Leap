import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * The type Finish hole.
 * @author Sii Kim Lau
 */
public class FinishHole extends Tile {
    private Image playerImage = new Image("assets/frog.png");

    /**
     * Instantiates a new Finish hole.
     *
     * @param x       the x
     * @param y       the y
     * @param width   the width
     * @param height  the height
     * @param name    the name
     * @param reached the reached
     * @throws SlickException the slick exception
     */
    public FinishHole(float x, float y, float width, float height, String name, boolean reached) throws SlickException {
        super(x, y, width, height, name, reached);
    }

    /**
     * Render the Finish hole.
     */
    @Override
    public void render() {

        // when the player reaches the finish hole
        if (isReached()) {
            this.playerImage.drawCentered(getX(), getY());
        }

    }
}
