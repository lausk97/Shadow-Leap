import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * The type World.
 *
 * @author Sii Kim Lau
 */
public class World {
    /**
     * The constant TILE_SIZE.
     */
    public static final int TILE_SIZE = 48;

    /**
     * The constant PLAYER_INIT_X.
     */
    public static final float PLAYER_INIT_X = 512;
    /**
     * The constant PLAYER_INIT_Y.
     */
    public static final float PLAYER_INIT_Y = 720;

	/**
	 * variables to check time for anything that requires event period
	 */
	private static int turtleCounterTime;
	private static int initExtraLifeTime;
	private static int extraLifeCounterTime;

	/**
	 * random time for extra life to appear on screen
	 */
	private static final int RANDOM_TIME_START = 25000;
	private static final int RANDOM_TIME_DIFF = 10000;
	private static int extraLifeRandomTime = getRandomTime();

	/**
	 * to check if there is an extra life on the screen
	 */
	private static boolean hasExtraLife = false;

	/**
	 * Finish hole constants
	 */
	private static final float FINISH_HOLE_INIT_X = 120;
	private static final float FINISH_HOLE_INIT_Y = 48;
	private static final float FINISH_HOLE_SEPARATION = 192;
	private static final float FINISH_HOLE_WIDTH = 96;
	private static final float FINISH_HOLE_HEIGHT = 48;

	/**
	 * Number of Finish Hole
	 */
	private static int numFinishHole = 0;

	private Player player;
	private static ExtraLife extralife;
	private static ArrayList<Integer> logIndex;
	private static ArrayList<Tile> tiles;
	private static ArrayList<Collider> colliders;
	private static ArrayList<Shifter> shifters;

    /**
     * Instantiates a new World.
     *
     * @param lvlFile the lvl file
     * @throws SlickException the slick exception
     * @throws IOException    the io exception
     */
    public World(String lvlFile) throws SlickException, IOException {
		turtleCounterTime = 0;
		initExtraLifeTime = 0;

		tiles = new ArrayList<>();
		colliders = new ArrayList<>();
		shifters = new ArrayList<>();
		logIndex = new ArrayList<>();

		player = new Player("assets/frog.png", PLAYER_INIT_X, PLAYER_INIT_Y);

		numFinishHole = 0;

		initFinishHole();

		FileHandler.initWorld(lvlFile);

		int count = 0;

		for (Shifter shifter : shifters) {
			if (shifter instanceof Log) {
				logIndex.add(count);
			}
			count++;
		}
	}

    /**
     * Update all the sprites.
     *
     * @param input the input
     * @param delta the delta
     * @throws SlickException the slick exception
     */
    public void update(Input input, int delta)
			throws SlickException {
		turtleCounterTime += delta;

		player.update(input, delta);

		// when the screen does not have any extra life
		if (!hasExtraLife) {
			initExtraLifeTime += delta;

			if (initExtraLifeTime > extraLifeRandomTime) {
				createExtraLife();
				initExtraLifeTime = 0;
				extraLifeCounterTime = 0;
			}

		}

		// Check the collision between player and extra life
		else {
			extraLifeCounterTime += delta;
			extralife.update(input, delta);
			player.contactSprite(extralife);
		}

		// Check for collision between player and shifters
		for (Shifter shifter : shifters) {
			shifter.update(input, delta);
			player.contactSprite(shifter);
		}

		// Check for collision between player and colliders
		for (Collider collider : colliders) {
			collider.update(input, delta);
			player.contactSprite(collider);
		}

		// Check for collisions between player and tiles
		for (Tile tile : tiles) {
			tile.update(input, delta);

			// Check for collisions between water and shifters that float on water
			if (tile.getName().equals("water")) {
				boolean safeToRide = false;
				for (Shifter shifter : shifters) {

					if (Shifter.isFloating()) {
						tile.contactSprite(shifter);

						if (tile.isCollided() && shifter.isCollided()) {
							tile.setCollided(false);
							shifter.setCollided(false);
							safeToRide = true;
							break;
						}

					}

				}

				if (!safeToRide) {
					player.contactSprite(tile);
				}

			} else {
                if (!(tile.getName().equals("grass"))) {
                    player.contactSprite(tile);
                }
			}
		}
	}


    /**
     * Render all the sprites in the game.
     *
     * @param g the g
     * @throws SlickException the slick exception
     */
    public void render(Graphics g) throws SlickException {
		// Draw all of the sprites in the game

		for (Tile tile : tiles) {
			tile.render();
		}

		for (Collider collider : colliders) {
			collider.render();
		}

		for (Shifter shifter: shifters) {
			shifter.render();
		}

		player.render();

		if (hasExtraLife) {
			extralife.render();
		}
	}

	/**
	 * Initialise all the Finish Holes
	 *
	 * @throws SlickException the slick exception
	 */
	private void initFinishHole() throws SlickException {
		for (float currPosX = FINISH_HOLE_INIT_X; (currPosX + FINISH_HOLE_WIDTH/2) < App.SCREEN_WIDTH;
			 currPosX += FINISH_HOLE_SEPARATION) {
			tiles.add(new FinishHole(currPosX, FINISH_HOLE_INIT_Y, FINISH_HOLE_WIDTH,
					FINISH_HOLE_HEIGHT, "finishhole", false));
			numFinishHole++;
		}
	}

	/**
	 * Get the index of a random log in the level
	 *
	 * @return the index of the log
	 */
	private static int getRandomLogIndex() {
		int random = new Random().nextInt(logIndex.size());

		return logIndex.get(random);
	}

	/**
	 * Get the random time for the extra life to appear
	 *
	 * @return the random time
	 */
	private static int getRandomTime() {
		int randomTime = new Random().nextInt(RANDOM_TIME_DIFF);

		return RANDOM_TIME_START + randomTime;
	}

	/**
	 * Create the extra life when reaches the random time
	 *
	 * @throws SlickException the slick exception
	 */
	private static void createExtraLife() throws SlickException {
		int index = getRandomLogIndex();

		extralife = new ExtraLife("assets/extralife.png",
								shifters.get(index).getX(),
								shifters.get(index).getY(),
								true, index);

		hasExtraLife = true;

		extraLifeRandomTime = getRandomTime();
	}

    /**
     * Gets tiles.
     *
     * @return the tiles
     */
    public static ArrayList<Tile> getTiles() {
		return tiles;
	}

    /**
     * Gets colliders.
     *
     * @return the colliders
     */
    public static ArrayList<Collider> getColliders() { return colliders; }

    /**
     * Gets shifters.
     *
     * @return the shifters
     */
    public static ArrayList<Shifter> getShifters() {
		return shifters;
	}

    /**
     * Gets counter time.
     *
     * @return the counter time
     */
    public static int getTurtleCounterTime() {
		return turtleCounterTime;
	}

    /**
     * Sets counter time.
     *
     * @param turtleCounterTime the counter time
     */
    public static void setTurtleCounterTime(int turtleCounterTime) {
		World.turtleCounterTime = turtleCounterTime;
	}

    /**
     * Gets extra life counter time.
     *
     * @return the extra life counter time
     */
    public static int getExtraLifeCounterTime() {
		return extraLifeCounterTime;
	}

    /**
     * Sets extra life counter time.
     *
     * @param extraLifeCounterTime the extra life counter time
     */
    public static void setExtraLifeCounterTime(int extraLifeCounterTime) {
		World.extraLifeCounterTime = extraLifeCounterTime;
	}

    /**
     * Sets has extra life.
     *
     * @param hasExtraLife the has extra life
     */
    public static void setHasExtraLife(boolean hasExtraLife) {
		World.hasExtraLife = hasExtraLife;
	}


    /**
     * Gets num finish hole.
     *
     * @return the num finish hole
     */
    public static int getNumFinishHole() {
		return numFinishHole;
	}
}
