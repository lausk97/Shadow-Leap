import org.newdawn.slick.Input;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;


/**
 * The type Sprite.
 * @author Sii Kim Lau
 */
public abstract class Sprite {

	private boolean collided = false;
	private Image imageSource;
	private float x, y, width, height;
	private BoundingBox boundingBox;

	/**
	 * Instantiates a new Sprite.
	 *
	 * @param imageSrc the image src
	 * @param x        the x
	 * @param y        the y
	 * @throws SlickException the slick exception
	 */
	public Sprite(String imageSrc, float x, float y) throws SlickException {
		// Constructor for sprites
		imageSource = new Image(imageSrc);
		this.x = x;
		this.y = y;
		boundingBox = new BoundingBox(imageSource, x, y);
	}

	/**
	 * Instantiates a new Sprite.
	 *
	 * @param x      the x
	 * @param y      the y
	 * @param width  the width
	 * @param height the height
	 * @throws SlickException the slick exception
	 */
	public Sprite(float x, float y, float width, float height) throws SlickException {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		boundingBox = new BoundingBox(x, y, width, height);
	}

	/**
	 * Update the bounding box.
	 *
	 * @param input the input
	 * @param delta the delta
	 */
	public void update(Input input, int delta) {
		// update location and boundingBox location
		getBoundingBox().setX(this.getX());
		getBoundingBox().setY(this.getY());
	}

	/**
	 * Render the sprite.
	 *
	 * @throws SlickException the slick exception
	 */
	public void render() throws SlickException {
		// draw the sprites
		this.imageSource.drawCentered(x, y);
	}

	/**
	 * Contact sprite.
	 *
	 * @param other the other
	 */
	public void contactSprite(Sprite other) {
		// check whether the sprites makes contact with each other.
        if (this.getBoundingBox().intersects(other.getBoundingBox())) {
			if (this.getY() == other.getY()) {
				collided = true;
				other.collided = true;
			}
		}
	}

	/**
	 * Gets x.
	 *
	 * @return the x
	 */
	public float getX() {
		return x;
	}

	/**
	 * Sets x.
	 *
	 * @param x the x
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Gets y.
	 *
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * Sets y.
	 *
	 * @param y the y
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Gets width.
	 *
	 * @return the width
	 */
	public float getWidth() { return this.imageSource.getWidth();}

	/**
	 * Gets height.
	 *
	 * @return the height
	 */
	public float getHeight() { return this.imageSource.getHeight();}

	/**
	 * Is collided boolean.
	 *
	 * @return the boolean
	 */
	public boolean isCollided() {
		return collided;
	}

	/**
	 * Sets collided.
	 *
	 * @param collided the collided
	 */
	public void setCollided(boolean collided) {
		this.collided = collided;
	}

	/**
	 * Gets bounding box.
	 *
	 * @return the bounding box
	 */
	public BoundingBox getBoundingBox() { return boundingBox; }
}
