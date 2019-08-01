import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Collider.
 * @author Sii Kim Lau
 */
public class Collider extends ActionSprites {

    /**
     * Instantiates a new Collider.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param moveRight the move right
     * @throws SlickException the slick exception
     */
    public Collider(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc, x, y, moveRight);
    }

    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
        move(delta);
    }
}
