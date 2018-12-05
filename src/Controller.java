import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;

public class Controller implements ActionListener, KeyListener {

	// The timer for setting the movement algorithm
	Timer timer = new Timer(1, this);

	// model and view
	private Model model;
	private View view;

	// For changing positions
	private boolean isBotX = false;

	// Constructor for initiating the model and the view
	public Controller(Model model, View view) {

		this.model = model;
		this.view = view;
		timer.start();

		// 3 as a time constraints for how bots collide and move
		collideBots(3);
		moveBots(3);

	}

	// Do movement within the game
	public void doMovement(int key) {

		// If i am dead, I cannot move
		if (model.getDeadState()) {
			return;
		}

		// When the game starts and I move for the first time the entry notification gets away
		if (model.getMoveState() == false) {
			model.setMoveState(true);
			model.setDoorNotification(false);

			// Here I set the timer once I start
			if (model.getTimer() == 50) {
				model.setTimer();
			}

		}

		// setGo is bound with the timer.
		// If it is 0 the player wont move to x or y
		// If 1, the player will move accordingly
		if (key == KeyEvent.VK_DOWN) {
			model.setGoX(0);
			model.setGoY(1);
		}

		if (key == KeyEvent.VK_UP) {
			model.setGoX(0);
			model.setGoY(-1);
		}

		if (key == KeyEvent.VK_LEFT) {
			model.setGoX(-1);
			model.setGoY(0);
		}

		if (key == KeyEvent.VK_RIGHT) {
			model.setGoX(1);
			model.setGoY(0);
		}

	}

	// Do movement and writing once I won the game
	// This mainly indicates how to write the name
	public void doGameWonMovement(KeyEvent e) {

		// I I build the name within the Swing library
		char character = e.getKeyChar();
		int keyCode = e.getKeyCode();
		model.buildName(character, keyCode);

		// Show the time and written name in Swing
		String name = model.getName().toString();
		String time = String.valueOf(model.getTimer());

		// If the name is entered, play enter and it is stored in the scores.txt
		if (keyCode == 10 && model.getName().length() > 0) {

			try (PrintWriter output = new PrintWriter(new FileWriter("scores.txt", true))) {

				String newLine = name + "," + time;
				output.printf("%s\r\n", newLine);

			} catch (IOException e1) {
				e1.printStackTrace();
			}
			System.exit(0);

		}

	}

	// Do movements within the menu
	public void doMenuMovement(int key) {

		// Determine if dead or in main Menu
		int decreaseLimit = 4;
		if (model.getDeadState()) {
			decreaseLimit = 2;
		}

		// Go up and down in the menu
		if (key == KeyEvent.VK_DOWN) {
			model.minusMenuState(decreaseLimit);
		}

		if (key == KeyEvent.VK_UP) {
			model.plusMenuState();
		}

		// 10 is "enter" as keyCode
		if (key == 10) {

			if (model.getScoreMenu()) {
				model.setScoreMenu(false);
				return;
			}

			if (model.getInstructionsMenu()) {
				model.setInstructionsMenu(false);
				return;
			}

			selectMenu(model.getMenuState());
		}
	}

	// Selecting accordingly the menu
	public void selectMenu(int state) {

		// If not dead be in main menu
		// Else select in death menu

		if (!model.getDeadState()) {

			if (state == 1) {
				model.setGameState(true);
			}

			if (state == 2) {
				model.setInstructionsMenu(true);
			}

			if (state == 3) {
				model.setScoreMenu(true);
			}

			if (state == 4) {
				System.exit(0);
			}

		} else {
			if (state == 1) {
				renewGame();
			}

			if (state == 2) {
				System.exit(0);
			}

		}

	}

	// Renew the game once I go a level up
	public void renewGame() {

		model.initItems();
		model.setDeadState(false);

	}

	// If I bounce as a player against the walls. The walls are stored in the tiles array.
	// -5 is to bounce back
	public void dungeonBounce() {

		for (Rectangle rectangle : model.getTiles()) {

			if (model.getPlayer().intersects(rectangle)) {
				if (model.getGoX() == 1) {
					model.setGoX(0);
					model.setHeroX(model.getHeroX() - 5);
				}

				if (model.getGoX() == -1) {
					model.setGoX(0);
					model.setHeroX(model.getHeroX() + 5);
				}
				if (model.getGoY() == 1) {
					model.setGoY(0);
					model.setHeroY(model.getHeroY() - 5);
				}

				if (model.getGoY() == -1) {
					model.setGoY(0);
					model.setHeroY(model.getHeroY() + 5);
				}

			}

		}

	}

	// If the player has hit enough coins. the doors will open
	public void openDoors() {

		for (Rectangle rectangle : model.getTiles()) {

			int tile = model.getTileSize();
			int xCoor = rectangle.x;
			int yCoor = rectangle.y;

			int col = yCoor / tile;
			int row = xCoor / tile;

			// The state can be 1,2,3 (Wall,Door, opened door)

			int state = model.getDungeon()[col][row];

			// We are looking for state 2 because this represents the door
			if (model.getCoins() >= 3) {
				if (state == 2) {

					model.getDungeon()[col][row] = 3;
					model.getDungeon()[col][row] = 3;
				}

			}
		}

	}

	// If I hit the door
	public void hitDoor() {

		for (Rectangle rectangle : model.getTiles()) {

			if (model.getPlayer().intersects(rectangle)) {
				int tile = model.getTileSize();
				int xCoor = rectangle.x;
				int yCoor = rectangle.y;

				int col = yCoor / tile;
				int row = xCoor / tile;

				int state = model.getDungeon()[col][row];

				// If state is 2, door is still not opened
				if (state == 2) {
					model.setDoorNotification(true);
					model.setMoveState(false);
				}

				// If state is 3 (opened door) switch the level
				if (state == 3) {
					// Here is where i change levels
					// Notification that i changed levels

					if (model.getCurrentLevel() == 4) {
						model.setGameWon(true);
						model.stopTimer();
						model.doGameOver();
						break;
					}

					int nextLevel = model.getCurrentLevel() + 1;
					model.setCurrentLevel(nextLevel);
					model.initItems();

					break;
				}

			}
		}
	}

	// If I hit the coin
	// store the coin in the array list
	public void hitCoin() {

		List<int[]> list = model.getCoinList();

		for (int i = 0; i < list.size(); i++) {
			int posX = list.get(i)[0];
			int posY = list.get(i)[1];
			Rectangle coinPosition = new Rectangle(posX, posY, 40, 40);

			if (model.getPlayer().intersects(coinPosition)) {
				list.remove(i);
				model.setCoin(model.getCoins() + 1);
			}

		}
	}

	// If I hit a bot I die
	public void hitBot() {

		List<int[]> list = model.getBotList();

		for (int i = 0; i < list.size(); i++) {
			int posX = list.get(i)[0];
			int posY = list.get(i)[1];
			Rectangle botPosition = new Rectangle(posX, posY, 40, 40);

			if (model.getPlayer().intersects(botPosition)) {
				model.doDead();
			}

		}
	}

	// Move the Bots
	public void moveBots(int time) {

		// Set the timer which has the same functionality as moving the hero
		// ActrionPerformed is repetitive due to the timer
		// Every time 3 nanoseconds actionePerformed is repeated so the moving of the bots and the hero is smooth
		Timer timer = new Timer(time, new ActionListener() {
			int counter = 0;

			public void actionPerformed(ActionEvent e) {

				if (model.getMoveState()) {
					for (int i = 0; i < model.getBotList().size(); i++) {
						int posX = model.getBotList().get(i)[0];
						int posY = model.getBotList().get(i)[1];
						int goX = model.getBotList().get(i)[2];
						int goY = model.getBotList().get(i)[3];

						if (isBotX) {
							model.getBotList().get(i)[1] = posY + goY;
						} else {
							model.getBotList().get(i)[0] = posX + goX;
						}
						if (counter > 500) {
							Random random = new Random();
							isBotX = random.nextBoolean();
							counter = 0;
						}
						counter++;

					}
				}

			}
		});
		timer.start();

	}

	// Do the same as moveBots(), but now apply it to collisions
	public void collideBots(int time) {

		Timer timer = new Timer(time, new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				for (int i = 0; i < model.getBotList().size(); i++) {
					int posX = model.getBotList().get(i)[0];
					int posY = model.getBotList().get(i)[1];

					Rectangle botRectangle = new Rectangle(posX, posY, 40, 40);
					boolean intersects = false;

					for (Rectangle rectangle : model.getTiles()) {
						if (botRectangle.intersects(rectangle)) {
							intersects = true;
						}
					}

					if (intersects) {
						if (isBotX) {
							model.getBotList().get(i)[3] = -1 * model.getBotList().get(i)[3];
						} else {
							model.getBotList().get(i)[2] = -1 * model.getBotList().get(i)[2];
						}

					}
				}
			}
		});
		timer.start();
	}

	// Detect once the key is pressed
	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		// If game is won or dead, do movement
		if (model.getGameState() && !model.getDeadState() && !model.getGameWon()) {
			doMovement(key);
		} else {

			// if the game is won
			if (model.getGameWon()) {
				doGameWonMovement(e);
				return;
			}

			// In main menu
			doMenuMovement(key);
		}

	}

	// Detect key released
	@Override
	public void keyReleased(KeyEvent e) {
		model.setGoX(0);
		model.setGoY(0);
	}


	@Override
	public void keyTyped(KeyEvent e) {}

	// actionPerformed is the Heart of the game. It detects all the changes.
	@Override
	public void actionPerformed(ActionEvent e) {

		// gameState is if you are in the game or not
		if (model.getGameState()) {
			// If you are in the game do all detections
			model.setPlayer(new Rectangle(model.getHeroX(), model.getHeroY(), 40, 40));
			hitCoin();
			hitBot();
			hitDoor();
			openDoors();
			dungeonBounce();

			model.setHeroX(model.getHeroX() + model.getGoX());
			model.setHeroY(model.getHeroY() + model.getGoY());
		}

		view.repaint();

	}

}
