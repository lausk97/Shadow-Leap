import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import javax.swing.*;

/**
 * The type Extra life.
 * @author Sii Kim Lau
 */
public class ExtraLife extends ActionSprites {
    private static final int EXTRA_LIFE_MOVE_TIME = 2000;
    private static final int EXTRA_LIFE_DISAPPEAR_TIME = 14000;

    private int count = 1;
    private int logIndex;

    /**
     * Instantiates a new Extra life.
     *
     * @param imageSrc  the image src
     * @param x         the x
     * @param y         the y
     * @param moveRight the move right
     * @param logIndex  the log index
     * @throws SlickException the slick exception
     */
    public ExtraLife(String imageSrc, float x, float y, boolean moveRight, int logIndex) throws SlickException {
        super(imageSrc, x, y, moveRight);
        this.logIndex = logIndex;
    }

    /**
     * Update the Extra life when it is on screen
     *
     * @param input the input
     * @param delta the time that has passed
     */
    @Override
    public void update(Input input, int delta) {
        super.update(input, delta);

        moveWithLog(delta);

        // movement of extra life
        if (World.getExtraLifeCounterTime() >= (EXTRA_LIFE_MOVE_TIME * count)) {
            move(delta);
            count++;
        }
    }

    /**
     * Render the Extra life
     *
     * @throws SlickException the slick exception
     */
    @Override
    public void render() throws SlickException {
        super.render();

        // make the extra life disappear from the screen
        if (World.getExtraLifeCounterTime() >= EXTRA_LIFE_DISAPPEAR_TIME) {
            World.setExtraLifeCounterTime(0);
            World.setHasExtraLife(false);
            count = 1;
        }

    }

    /**
     * Move with log.
     *
     * @param delta the delta
     */
    private void moveWithLog(int delta) {
        if (World.getShifters().get(logIndex).isMoveRight()) {

            if ((this.getX() - World.getShifters().get(logIndex).getWidth() / 2
                 + World.getShifters().get(logIndex).getSpeed() * delta) < App.SCREEN_WIDTH) {
                this.setX(this.getX() + World.getShifters().get(logIndex).getSpeed() * delta);
            } else {
                // appear from the other side of the screen
                this.setX(ORIGIN_LEFT - World.getShifters().get(logIndex).getWidth() / 2);
            }

        } else {

            if ((this.getX() + World.getShifters().get(logIndex).getWidth() / 2
                 - World.getShifters().get(logIndex).getSpeed() * delta) > 0) {
                this.setX(this.getX() - World.getShifters().get(logIndex).getSpeed() * delta);
            } else {
                // appear from the other side of the screen
                this.setX(ORIGIN_RIGHT + World.getShifters().get(logIndex).getWidth() / 2);
            }

        }
    }

    /**
     * Moving extra life to a certain point.
     *
     * @param delta the delta
     */
    public void move(int delta) {
        if (this.isMoveRight()) {
            if ((this.getX() + World.TILE_SIZE)
                  < (World.getShifters().get(logIndex).getX() + World.getShifters().get(logIndex).getWidth() / 2)) {
                this.setX(this.getX() + World.TILE_SIZE);
            } else {
                changeDirection();
                this.setX(this.getX() - World.TILE_SIZE);
            }
        } else {
            if ((this.getX() - World.TILE_SIZE)
                 > ((World.getShifters().get(logIndex).getX() - World.getShifters().get(logIndex).getWidth()/2))) {
                this.setX(this.getX() - World.TILE_SIZE);
            } else {
                changeDirection();
                this.setX(this.getX() + World.TILE_SIZE);
            }
        }
    }


    /**
     * Change direction.
     */
    private void changeDirection() {
        if (isMoveRight()) {
            setMoveRight(false);
        } else {
            setMoveRight(true);
        }
    }

}
