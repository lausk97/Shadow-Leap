import org.newdawn.slick.SlickException;

/**
 * The type Tile.
 * @author Sii Kim Lau
 */
public class Tile extends Sprite {
    private String name;
    private boolean reached = false;

    /**
     * Instantiates a new Tile.
     *
     * @param imageSrc the image src
     * @param x        the x
     * @param y        the y
     * @param name     the name
     * @throws SlickException the slick exception
     */
    public Tile(String imageSrc, float x, float y, String name) throws SlickException {
        super(imageSrc, x, y);
        this.name = name;
    }

    /**
     * Instantiates a new Tile.
     *
     * @param x       the x
     * @param y       the y
     * @param width   the width
     * @param height  the height
     * @param name    the name
     * @param reached the reached
     * @throws SlickException the slick exception
     */
    public Tile(float x, float y, float width, float height, String name, boolean reached) throws SlickException {
        super(x, y, width, height);
        this.name = name;
        this.reached = reached;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Is reached boolean.
     *
     * @return the boolean
     */
    public boolean isReached() {
        return reached;
    }

    /**
     * Sets reached.
     *
     * @param reached the reached
     */
    public void setReached(boolean reached) {
        this.reached = reached;
    }
}
