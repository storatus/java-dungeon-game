import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;

public class Model extends PlayerModel {


	// Frame, Font and iconsize
	private int dHeight = 800;
	private int dWidth = 800;
	private int iconSize = 40;
	Font gameFont = new Font("Arial", Font.BOLD, 20);

	// The size of the tile
	private int tileSize;

	// Counting coins
	private int coinCount;

	// arrayList for Bots and Coins
	List<int[]> coinList = new ArrayList<int[]>();
	List<int[]> botList = new ArrayList<int[]>();
	private ArrayList<Rectangle> tiles = new ArrayList<Rectangle>();

	// States mainly to paint the view accordingly
	private boolean deadState = false;
	private boolean gameState = false;
	private boolean isPositioned = false;
	private boolean moveState = false;
	private boolean doorState = false;
	private boolean gameWon = false;
	private boolean seeScoreMenu = false;
	private boolean seeInstructionsMenu = false;

	// Number of items to be generated and the time running
	private int botNumber = 2;
	private int coinNumber = 12;
	private int time = 50;

	// Where the level starts
	private int currentLevel = 1;

	// These are coordinates to be used for moving the hero icon in the menus, 400,500, etc is used as points in the y coordinate
	private int menuState = 1;
	private int[] menuStates = { 400, 500, 600, 700 };
	private int[] menuDeadStates = { 270, 330 };

	// Showing the Room names in the levels accordingly
	private String[] roomNames = { "Entrance", "Kitchen", "Sewer", "Snake Pit" };

	// The time for placing the countdown within the game
	Timer countdownTimer = new Timer();

	// The word which is build to store in the textfile
	StringBuilder word = new StringBuilder();

	// The levels for the tile system are s
	// 1 - Wall
	// 2 - Closed Door
	// 3 - Opened Door

	private int[][] dungeon = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 2, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 2, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			};

	private int[][] dungeon2 = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
			{ 1, 0, 0, 1, 1, 0, 0, 0, 1, 1 },
			{ 1, 0, 0, 1, 1, 0, 0, 0, 1, 1 },
			{ 1, 0, 0, 1, 1, 0, 0, 0, 1, 1 },
			{ 1, 0, 0, 1, 1, 0, 0, 0, 2, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 2, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			};

	private int[][] dungeon3 = {
			{ 1, 1, 1, 2, 2, 2, 2, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 1, 1, 0, 1, 1, 0, 1 },
			{ 1, 0, 0, 1, 1, 0, 1, 1, 0, 1 },
			{ 1, 0, 0, 1, 1, 0, 1, 1, 0, 1 },
			{ 1, 0, 0, 1, 1, 0, 1, 1, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			};

	private int[][] dungeon4 = {
			{ 1, 1, 1, 2, 2, 2, 2, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 1, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 0, 1, 1, 1, 1, 1, 0, 1 },
			{ 1, 0, 0, 1, 1, 0, 1, 1, 0, 1 },
			{ 1, 0, 0, 1, 1, 0, 1, 1, 0, 1 },
			{ 1, 0, 0, 1, 1, 0, 1, 1, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			};

	//Getters and setters for manipulating the data from the controller

	public void setInstructionsMenu(boolean state) {
		seeInstructionsMenu = state;
	}

	public boolean getInstructionsMenu() {
		return seeInstructionsMenu;
	}

	public void setCountDown(int number) {
		this.time = number;
	}

	public StringBuilder getName() {
		return word;
	}

	// Build the name when the player has won the game
	public void buildName(char character, int code) {
		int length = this.getName().length();

		if (code == 8) {
			if (length > 0) {
				this.getName().deleteCharAt(length - 1);
			}
			return;
		}

		if (length >= 3) {
			return;
		}

		if (Character.isLetter(character)) {
			word.append(character);
		}
	}

	public void setScoreMenu(boolean state) {
		this.seeScoreMenu = state;
	}

	public boolean getScoreMenu() {
		return seeScoreMenu;
	}

	public void setGameWon(boolean state) {
		this.gameWon = state;
	}

	public boolean getGameWon() {
		return gameWon;
	}

	public int getIconSize() {
		return iconSize;
	}

	public Font getGameFont() {
		return gameFont;
	}

	public int getDimensionWidth() {
		return dWidth;
	}

	public int getDimensionHeight() {
		return dHeight;
	}

	public void setPosition(boolean state) {
		this.isPositioned = state;
	}

	public int getCoins() {
		return coinCount;
	}

	public void setCoinNumber(int number) {
		coinNumber = number;
	}

	public void setBotNumber(int number) {
		botNumber = number;
	}

	public void setCoin(int number) {
		this.coinCount = number;
	}

	// get the dungeon according to the current level
	public int[][] getDungeon() {

		if (currentLevel == 2) {
			return dungeon2;
		}

		if (currentLevel == 3) {
			return dungeon3;
		}

		if (currentLevel == 4) {
			return dungeon4;
		}

		return dungeon;

	}

	public int getTileSize() {
		return tileSize;
	}

	public boolean getDeadState() {
		return deadState;
	}

	public void setDeadState(boolean bool) {
		deadState = bool;
	}

	public boolean getGameState() {
		return gameState;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int level) {
		currentLevel = level;
	}

	public void setGameState(boolean bool) {
		gameState = bool;
	}

	public boolean getDoorState() {
		return doorState;
	}

	public void setDoorState(boolean state) {
		doorState = state;
	}

	public boolean getDoorNotification() {
		return doorState;
	}

	public void setDoorNotification(boolean state) {
		doorState = state;
	}

	public boolean getMoveState() {
		return moveState;
	}

	public void setMoveState(boolean state) {
		moveState = state;
	}

	public int getMenuState() {
		return menuState;
	}

	public int[] getMenuStates() {
		return menuStates;
	}

	public int[] getMenuDeadStates() {
		return menuDeadStates;
	}

	public void minusMenuState(int decreaseLimit) {
		if (menuState < decreaseLimit) {
			menuState++;
		}
	}

	public void plusMenuState() {
		if (menuState > 1) {
			menuState--;
		}
	}

	public ArrayList<Rectangle> getTiles() {
		return tiles;
	}

	public String getRoomName() {
		return roomNames[currentLevel - 1];
	}

	public int getTimer() {
		return time;
	}

	List<int[]> getCoinList() {
		return coinList;
	}

	List<int[]> getBotList() {
		return botList;
	}

	// generate all coins and bots randomly and store them in an array
	public void generateItems(List<int[]> itemList, int count, String name) {

		while (itemList.size() < count) {

			// Get random numbers in the x and y axis
			int randomX = ThreadLocalRandom.current().nextInt(0, dWidth + 1);
			int randomY = ThreadLocalRandom.current().nextInt(0, dHeight + 1);

			// Place the coordinates in a rectangle
			Rectangle itemRectangle = new Rectangle(randomX, randomY, 40, 40);
			boolean intersects = false;

			// Loop through all the rectangles that were stored in the tiles array
			// The tiles array contains only tiles that are restricted such as walls and doors
			// if an intersection was found out the intersects flag is set to true
			for (Rectangle rectangle : tiles) {
				if (rectangle.intersects(itemRectangle)) {
					intersects = true;
					break;
				}
			}

			// If there was an intersection loop again else store them in the corresponding array
			if (intersects) {
				generateItems(itemList, count, name);
			} else {

				if (name == "bots") {

					// randomNumber will be used for generating the image later
					int randomNumber = ThreadLocalRandom.current().nextInt(1, 4 + 1);
					itemList.add(new int[] { randomX, randomY, 1, 1, randomNumber });
				}

				if (name == "coins") {
					itemList.add(new int[] { randomX, randomY });
				}

			}

		}

	}

	// After I die clean all bot and coin list
	public void cleanItems() {

		if (botList.size() > 0) {
			botList.clear();
		}

		if (coinList.size() > 0) {
			coinList.clear();
		}

	}

	// I am dead - do things
	public void doDead() {

		setMoveState(false);
		setDeadState(true);

	}

	// Set move state to false when die
	public void doGameOver() {

		setMoveState(false);

	}

	// Position the player and when it intersects with a restricted tile (doors, walls)
	public void positionPlayer() {

		while (!isPositioned) {

			int randomX = ThreadLocalRandom.current().nextInt(0, dWidth + 1);
			int randomY = ThreadLocalRandom.current().nextInt(0, dHeight + 1);

			Rectangle itemRectangle = new Rectangle(randomX, randomY, 40, 40);
			boolean intersects = false;

			for (Rectangle rectangle : tiles) {
				if (rectangle.intersects(itemRectangle)) {
					intersects = true;
					break;
				}
			}

			if (intersects) {
				positionPlayer();
			} else {

				setHeroY(randomY);
				setHeroX(randomX);
				isPositioned = true;
			}

		}

	}

	// Prepare Image to provide to the controller
	public Image getImage(String name) {
		ImageIcon imageObj = new ImageIcon("images/" + name + ".png");
		Image image = imageObj.getImage();
		return image;
	}

	// Add restricted tile to the array
	public void setTile(Rectangle rectangle) {
		tiles.add(rectangle);
	}

	// Reverse doors again when switching level or dying
	public void setBackdoors() {
		dungeon = getDungeon();

		for (int row = 0; row < dungeon.length; row++) {
			for (int col = 0; col < dungeon[0].length; col++) {

				if (dungeon[row][col] == 3) {
					dungeon[row][col] = 2;
				}
			}
		}
	}

	// This is where I generate collisions and store them in the tile array
	public void generateCollisions() {

		// Clean tiles for new level
		tiles.clear();

		int tile = getTileSize();
		dungeon = getDungeon();

		for (int row = 0; row < dungeon.length; row++) {
			for (int col = 0; col < dungeon[0].length; col++) {

				int tileNumber = dungeon[row][col];

				// 1 is wall, 2 is door, 3 is open door
				if (tileNumber == 1 || tileNumber == 2 || tileNumber == 3) {
					tiles.add(new Rectangle(tile * col, tile * row, tile, tile));
				}
			}
		}
	}

	// Set the timer for inside the
	public void setTimer() {
		// source: goo.gl/9Txdbr
		countdownTimer.schedule(new TimerTask() {
			public void run() {
				time--;
				if (time == 0) {
					System.exit(0);
					countdownTimer.cancel();
				}

			}
		}, 0, 1000);

	}

	//Stop the timer once you won the game
	public void stopTimer() {
		countdownTimer.cancel();
	}

	// Initiate the items once you die or you switch the level
	public void initItems() {
		generateCollisions();
		setCoin(0);
		cleanItems();
		generateItems(coinList, coinNumber, "coins");
		generateItems(botList, botNumber, "bots");
		setBackdoors();
		setPosition(false);
		positionPlayer();
	}

	public Model() {

		super();
		// Set tile size and ini items
		this.tileSize = dWidth / dungeon.length;
		initItems();

	}

}
