/**
 * Project 2 for SWEN20003: Object Oriented Software Development 2018
 * by Eleanor McMurtry, University of Melbourne
 */

import org.newdawn.slick.*;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main class for the game.
 * Handles initialisation, input and rendering.
 * Modified by Sii Kim Lau, 890511
 */
public class App extends BasicGame {
    /**
     * screen width, in pixels
     */
    public static final int SCREEN_WIDTH = 1024;
    /**
     * screen height, in pixels
     */
    public static final int SCREEN_HEIGHT = 768;
    private static boolean endGame = false;
    private static boolean finishLevel = false;
    private int level = 0;

    private World world;

    /**
     * Instantiates a new App.
     */
    public App() {
        super("Shadow Leap");
    }

    @Override
    public void init(GameContainer gc)
            throws SlickException {
        try {
            world = new World("assets/levels/" + Integer.toString(level) + ".lvl");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** Update the game state for a frame.
     * @param gc The Slick game container object.
     * @param delta Time passed since last frame (milliseconds).
     */
    @Override
    public void update(GameContainer gc, int delta)
            throws SlickException {
        // Get data about the current input (keyboard state).
        Input input = gc.getInput();
        world.update(input, delta);

        // When the user wins a level
        if (finishLevel) {
            level++;

            // run the next level
            try {
                world = new World("assets/levels/" + Integer.toString(level) + ".lvl");
            } catch (FileNotFoundException e) {
                endGame = true;
            } catch (IOException e) {
                e.printStackTrace();
            }

            finishLevel = false;
        }

        // when the player either wins the game or lose the game
        if (endGame) {
            gc.exit();
        }

        // when escape key is pressed, exit the game
        if (input.isKeyPressed(Input.KEY_ESCAPE)) {
            gc.exit();
        }
    }

    /** Render the entire screen, so it reflects the current game state.
     * @param gc The Slick game container object.
     * @param g The Slick graphics object, used for drawing.
     */
    public void render(GameContainer gc, Graphics g)
            throws SlickException {

        world.render(g);
    }

    /**
     * Start-up method. Creates the game and runs it.
     *
     * @param args Command-line arguments (ignored).
     * @throws SlickException the slick exception
     */
    public static void main(String[] args)
            throws SlickException {
        AppGameContainer app = new AppGameContainer(new App());
        app.setShowFPS(true);
        app.setDisplayMode(SCREEN_WIDTH, SCREEN_HEIGHT, false);
        app.start();
    }

    /**
     * Sets end game.
     *
     * @param endGame the end game
     */
    public static void setEndGame(boolean endGame) {
        App.endGame = endGame;
    }

    /**
     * Sets finish level.
     *
     * @param finishLevel the finish level
     */
    public static void setFinishLevel(boolean finishLevel) {
        App.finishLevel = finishLevel;
    }
}