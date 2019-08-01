import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import utilities.BoundingBox;

/**
 * The type Player.
 * @author Sii Kim Lau
 */
public class Player extends Sprite {
    /**
     * The constants of lives
     */
    private static final float LIVES_INIT_X = 24;
    private static final float LIVES_INIT_Y = 744;
    private static final int LIVES_SEPARATION = 32;

    private static final int NO_MOVEMENT = 0;
    private static int lives = 3;
    private Image livesImage = new Image("assets/lives.png");

    /**
     * check if the player is on shifter
     */
    private boolean onShifter = false;

    /**
     * check if it is a bulldozer
     */
    private boolean isBulldozer = false;

    /**
     * check if the move is safe
     */
    private boolean safe = false;
    private float speed;
    private boolean moveRight;

    /**
     * Instantiates a new Player.
     *
     * @param imageSrc the image src
     * @param x        the x
     * @param y        the y
     * @throws SlickException the slick exception
     */
    public Player(String imageSrc, float x, float y) throws SlickException {
        super(imageSrc, x, y);
    }

    /**
     * Update the player position
     *
     * @param input the input
     * @param delta the delta
     */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);

        move(input, delta);
    }

    /**
     * Render the player
     *
     * @throws SlickException the slick exception
     */
    @Override
    public void render() throws SlickException {
        super.render();

        for (int i = 0; i<lives; i++) {
            livesImage.drawCentered(LIVES_INIT_X + (i * LIVES_SEPARATION), LIVES_INIT_Y);
        }
    }

    /**
     * Other sprites contact with player
     *
     * @param other the other
     */
    @Override
    public void contactSprite(Sprite other) {
        super.contactSprite(other);

        if (this.isCollided() && other.isCollided()) {

            if (other instanceof ExtraLife) {
                lives++;
                World.setHasExtraLife(false);
            }

            else if (other instanceof Shifter) {
                onShifter = true;
                this.safe = true;
                this.speed = ((Shifter) other).getSpeed();
                this.moveRight = ((Shifter) other).isMoveRight();

                isBulldozer = (other instanceof Bulldozer);

                if (other instanceof Turtle && !Shifter.isFloating()) {
                    onShifter = false;
                    this.safe = false;
                }

                this.setCollided(false);
                other.setCollided(false);

            }

            else if (other instanceof Collider) {
                this.safe = false;
            }

            else if (other instanceof FinishHole) {
                ((FinishHole) other).setReached(true);
                other.setCollided(false);
                this.setOrigin();
            }


            if (lives > 0) {
                if (!this.safe) {
                    other.setCollided(false);
                    lives--;
                    this.setOrigin();
                } else {
                    if (checkWinLevel()) {
                        World.setHasExtraLife(false);
                        App.setFinishLevel(true);
                    }
                }
            }

        }
    }

    /**
     * Movement of player
     *
     * @param input the input
     * @param delta the delta
     */
    public void move(Input input, int delta) {

        moveWithShifter(delta);

        this.speed = NO_MOVEMENT;

        float nextMoveX;
        float nextMoveY;

        // update the position of the player if the new position is in the screen and is a valid move
        if (input.isKeyPressed(Input.KEY_RIGHT)) {
            nextMoveX = this.getX() + World.TILE_SIZE;
            nextMoveY = this.getY();

            if (!isBlocked(nextMoveX, nextMoveY) && isSafeOnWater(nextMoveX, nextMoveY)
                    && isValidMove(nextMoveX, nextMoveY)) {
                if ((nextMoveX) < (App.SCREEN_WIDTH - this.getWidth()/2)) {
                    this.setX(nextMoveX);
                }
            }

        } else if (input.isKeyPressed(Input.KEY_LEFT)) {
            nextMoveX = this.getX() - World.TILE_SIZE;
            nextMoveY = this.getY();

            if (!isBlocked(nextMoveX, nextMoveY) && isSafeOnWater(nextMoveX, nextMoveY)
                    && isValidMove(nextMoveX, nextMoveY)) {
                if ((nextMoveX) > this.getWidth()/2) {
                    this.setX(nextMoveX);
                }
            }

        } else if (input.isKeyPressed(Input.KEY_DOWN)) {
            nextMoveX = this.getX();
            nextMoveY = this.getY() + World.TILE_SIZE;

            if (!isBlocked(nextMoveX, nextMoveY) && isSafeOnWater(nextMoveX, nextMoveY)
                    && isValidMove(nextMoveX, nextMoveY)) {
                if ((nextMoveY) < (App.SCREEN_HEIGHT - this.getHeight()/2)) {
                    this.setY(nextMoveY);
                }
            }

        } else if (input.isKeyPressed(Input.KEY_UP) ) {
            nextMoveX = this.getX();
            nextMoveY = this.getY() - World.TILE_SIZE;

            if (!isBlocked(nextMoveX, nextMoveY) && isSafeOnWater(nextMoveX, nextMoveY)
                    && isValidMove(nextMoveX, nextMoveY)) {
                if ((nextMoveY) > this.getHeight()/2) {
                    this.setY(nextMoveY);
                }
            }

        }

    }

    /**
     * Move with Shifter
     *
     * @param delta the delta
     */
    private void moveWithShifter(int delta) {
        if (onShifter && safe) {
            isSafeOnWater(this.getX(), this.getY());

            if (this.moveRight) {
                // move to the right side with log and turtle
                if (!isBulldozer && ((this.getX() + this.getWidth() / 2 + this.speed * delta) < App.SCREEN_WIDTH)) {
                    this.setX(this.getX() + this.speed * delta);
                }
                // push to the right side by bulldozer
                else if (isBulldozer && ((this.getX() - this.getWidth() / 2 + this.speed * delta)
                                          < App.SCREEN_WIDTH)) {
                    this.setX(this.getX() + this.speed * delta);
                } else {

                    // push out of screen by bulldozer
                    if (isBulldozer) {
                        lives--;
                        this.setOrigin();
                    }

                    // player stays at the edge of the screen
                    else {
                        this.setX(this.getX());
                    }

                }
            } else {
                // move to the left side with log and turtle
                if (!isBulldozer && (this.getX() - this.getWidth() / 2 - this.speed * delta > 0)) {
                    this.setX(this.getX() - this.speed * delta);
                }
                // push to the left side by bulldozer
                else if (isBulldozer && (this.getX() + this.getWidth() / 2 - this.speed * delta) > 0) {
                    this.setX(this.getX() - this.speed * delta);
                } else {

                    // push out of screen by bulldozer
                    if (isBulldozer) {
                        lives--;
                        this.setOrigin();
                    }

                    // player stays at the edge of the screen
                    else {
                        this.setX(this.getX());
                    }

                }
            }
        }
    }

    /**
     * Check if there is a solid object blocking the next move
     *
     * @param x the next move position x
     * @param y the next move position y
     * @return the boolean
     */
    private boolean isBlocked(float x, float y) {
        BoundingBox checkBox = new BoundingBox(x, y, this.getWidth(), this.getHeight());

        for (Shifter shifter : World.getShifters()) {
            if (shifter instanceof Bulldozer) {

                if (checkBox.intersects(shifter.getBoundingBox())) {
                    return true;
                }

            }
        }

        for (Tile tile : World.getTiles()) {
            if (tile.getName().equals("tree")) {

                if (checkBox.intersects(tile.getBoundingBox())) {
                    return true;
                }

            }
        }

        return false;
    }

    /**
     * Check if the next move is landing in a safe zone
     *
     * @param x the next move position x
     * @param y the next move position y
     * @return the boolean
     */
    private boolean isSafeOnWater(float x, float y) {
        boolean safe = true;
        BoundingBox checkBox = new BoundingBox(x, y, this.getWidth(), this.getHeight());

        for (Tile tile : World.getTiles()) {
            if (tile.getName().equals("water")) {

                if (checkBox.intersects(tile.getBoundingBox())) {
                    safe = false;
                }

            }
        }

        for (Shifter shifter : World.getShifters()) {
            if (!(shifter instanceof Bulldozer)) {

                if (checkBox.intersects(shifter.getBoundingBox())) {
                    safe = true;
                }

            }

        }

        if (!safe) {
            lives--;
            this.setOrigin();
        }

        return safe;
    }

    /**
     * Check if the next move is a valid move
     *
     * @param x the next move position x
     * @param y the next move position y
     * @return the boolean
     */
    private boolean isValidMove(float x, float y) {
        BoundingBox checkBox = new BoundingBox(x, y, this.getWidth(), this.getHeight());

        for (Collider collider : World.getColliders()) {

            // lose a life when collided
            if (checkBox.intersects(collider.getBoundingBox())) {
                lives--;
                this.setOrigin();
                return false;
            }

        }

        for (Tile tile : World.getTiles()) {
            if (tile.getName().equals("finishhole") && tile.isReached()) {
                if (checkBox.intersects(tile.getBoundingBox())) {
                    lives--;
                    this.setOrigin();
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Check winning condition
     *
     * @return the boolean
     */
    private boolean checkWinLevel() {
        int totalReached = 0;

        for (Tile tile : World.getTiles()) {
            if (tile.getName().equals("finishhole") && tile.isReached()) {
                totalReached++;
            }
        }

        return totalReached == World.getNumFinishHole();
    }

    /**
     * Sets origin.
     */
    private void setOrigin() {
        this.setX(World.PLAYER_INIT_X);
        this.setY(World.PLAYER_INIT_Y);
        this.setCollided(false);
        onShifter = false;
        this.safe = true;

        if (lives == 0) {
            App.setEndGame(true);
        }
    }
}
