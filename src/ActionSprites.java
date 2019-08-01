import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

/**
 * The type Action sprites.
 *
 * @author Sii Kim Lau
 */
public abstract class ActionSprites extends Sprite {
    /**
     * The constant ORIGIN_LEFT.
     */
    public static final int ORIGIN_LEFT = 0;
    /**
     * The constant ORIGIN_RIGHT.
     */
    public static final int ORIGIN_RIGHT = App.SCREEN_WIDTH;
    private boolean moveInScreen = false;
    private boolean moveRight;
    private float speed;

    /**
     * Instantiates a new Action sprites.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param moveRight the move right
     * @throws SlickException the slick exception
     */
    public ActionSprites(String imageSrc, float x, float y, boolean moveRight) throws SlickException {
        super(imageSrc, x, y);
        this.moveRight = moveRight;
    }

    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);
    }

    /**
     * Movement of all the sprites that moves
     *
     * @param delta the delta
     */
    public void move(int delta) {
        updateX(delta);
    }

    /**
     * Update x.
     *
     * @param delta the delta
     */
    public void updateX(int delta) {
        if (!this.isMoveRight()) {

            // for object that moves just in screen
            if (moveInScreen) {
                if ((this.getX() - this.getWidth() / 2 - delta * this.getSpeed()) > 0) {
                    this.setX(this.getX() - delta * this.getSpeed());
                } else {
                    this.setMoveRight(true);
                }
            } else {
                if ((this.getX() + this.getWidth() / 2 - delta * this.getSpeed()) > 0) {
                    this.setX(this.getX() - delta * this.getSpeed());
                } else {
                    // appear on the other side of the screen
                    this.setX(ORIGIN_RIGHT + this.getWidth() / 2);
                }
            }

        } else if (this.isMoveRight()) {

            if (moveInScreen) {
                if ((this.getX() + this.getWidth()/2 + delta * this.getSpeed()) < App.SCREEN_WIDTH) {
                    this.setX(this.getX() + delta * this.getSpeed());
                } else {
                    this.setMoveRight(false);
                }
            } else {
                 if ((this.getX() - this.getWidth() / 2 + delta * this.getSpeed()) < App.SCREEN_WIDTH) {
                     this.setX(this.getX() + delta * this.getSpeed());
                 } else {
                     this.setX(ORIGIN_LEFT - this.getWidth() / 2);
                 }
             }

        }
    }

    /**
     * Sets move in screen.
     *
     * @param moveInScreen the move in screen
     */
    public void setMoveInScreen(boolean moveInScreen) {
        this.moveInScreen = moveInScreen;
    }

    /**
     * Gets speed.
     *
     * @return the speed
     */
    public float getSpeed() {
        return speed;
    }

    /**
     * Sets speed.
     *
     * @param speed the speed
     */
    public void setSpeed(float speed) {
        this.speed = speed;
    }

    /**
     * Is move right boolean.
     *
     * @return the boolean
     */
    public boolean isMoveRight() {
        return moveRight;
    }

    /**
     * Sets move right.
     *
     * @param moveRight the move right
     */
    public void setMoveRight(boolean moveRight) {
        this.moveRight = moveRight;
    }

}
