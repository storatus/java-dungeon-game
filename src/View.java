import javax.swing.*;
import java.util.List;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class View extends JPanel {

	// Instantiating controller from view
	private Controller controller;
	private Model model;


	// paintComponent is an awt/swing method which is the heart of the game
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		// If I am inside the game, if not I will go to the menu sections
		if (model.getGameState()) {

			// Draw components according to level with the corresponding information
			drawDungeon(g2d);
			drawDungeonInfo(g2d);
			drawItems(model.getCoinList(), model.getImage("gold"), g2d);
			drawBots(g2d);

			// If not dead also list conditions and notifications if you hit door, start the game or won the game
			if (!model.getDeadState()) {
				g2d.drawImage(model.getImage("hero"), model.getHeroX(), model.getHeroY(), this);

				if (!model.getMoveState()) {
					showConditionScreen(g2d, "You have to collect 3 coins", "Move to continue");
				}

				if (model.getDoorNotification()) {
					showConditionScreen(g2d, "You have to collect more than 3 coins", "To open this door");
				}

				if (model.getGameWon()) {
					showGameWonScreen(g2d);
				}

			} else {
				showDeadScreen(g2d);
			}

		} else {

			// Show main Menu if not the other menus are selected

			if (model.getScoreMenu()) {
				showScoreScreen(g2d);
				return;
			}

			if (model.getInstructionsMenu()) {
				showInstructionsScreen(g2d);
				return;
			}

			drawMenu(g2d);

		}

	}

	public void showDeadScreen(Graphics2D g2d) {

		// Draw Canvas
		g2d.setColor(Color.white);
		g2d.fillRect(100, 200, 600, 200);
		g2d.setColor(Color.black);
		g2d.drawRect(100, 200, 600, 200);

		// Draw Strings inside canvas
		g2d.setFont(new Font("SansSerif", Font.BOLD, 30));
		g2d.setColor(Color.black);
		g2d.drawString("You are dead", 300, 250);

		// Draw Buttons showed as images
		int menuState = model.getMenuState();
		int tokenState = model.getMenuDeadStates()[menuState - 1];
		g2d.drawImage(model.getImage("hero"), 250, tokenState, this);
		g2d.drawImage(model.getImage("menu-continue"), 300, 270, this);
		g2d.drawImage(model.getImage("menu-exit"), 300, 330, this);

	}

	public void showGameWonScreen(Graphics2D g2d) {

		// Draw Canvas
		g2d.setColor(Color.white);
		g2d.fillRect(100, 200, 600, 300);
		g2d.setColor(Color.black);
		g2d.drawRect(100, 200, 600, 300);

		// Draw Strings inside canvas
		g2d.setFont(new Font("SansSerif", Font.BOLD, 30));
		g2d.setColor(Color.black);
		g2d.drawString("Congratulations, You won !", 150, 250);
		g2d.drawString("Your time was: " + model.getTimer(), 150, 300);
		g2d.drawString("Write your name.", 150, 350);
		g2d.drawString("Name: " + model.getName(), 150, 400);

	}

	// This method shows when hero starts the game or hits the game
	public void showConditionScreen(Graphics2D g2d, String s, String s2) {

		// Draw Canvas
		g2d.setColor(Color.white);
		g2d.fillRect(100, 200, 600, 150);
		g2d.setColor(Color.black);
		g2d.drawRect(100, 200, 600, 150);

		// Draw String
		g2d.setFont(new Font("SansSerif", Font.BOLD, 20));
		g2d.setColor(Color.black);
		g2d.drawString(s, 250, 250);
		g2d.drawString(s2, 250, 270);

	}

	// Show score screen inside menu
	public void showScoreScreen(Graphics2D g2d) {

		// Draw String
		g2d.setFont(new Font("SansSerif", Font.BOLD, 25));
		g2d.setColor(Color.white);
		g2d.drawString("Press enter to go back.", 200, 50);
		g2d.drawString("Players who played", 200, 100);

		// Here I read all lines from the scores.txt file in order to display the scores
		// Scores.txt is used as a provisionally database
		BufferedReader fileInput;

		try {
			fileInput = new BufferedReader(new FileReader("scores.txt"));

			String line = null;
			int scoreCounter = 1;
			int newLineCounter = 150;

			while ((line = fileInput.readLine()) != null) {
				String scoreCounterString = String.valueOf(scoreCounter);
				String[] info = line.split(",");
				String name = info[0];
				String score = info[1] + " sec. ";

				String finalValue = scoreCounterString + ". " + name + " - " + score;

				if (scoreCounter < 10) {
					g2d.drawString(finalValue, 200, newLineCounter);

				}
				newLineCounter = newLineCounter + 50;
				scoreCounter++;

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void showInstructionsScreen(Graphics2D g2d) {

		// Draw String
		g2d.setFont(new Font("SansSerif", Font.BOLD, 25));
		g2d.setColor(Color.white);
		g2d.drawString("Press enter to go back.", 200, 50);
		g2d.drawString("The instructions", 200, 100);
		g2d.drawImage(model.getImage("instructions"), 0, 0, this);

	}

	public void drawBots(Graphics2D g2d) {

		//Draw the bots and get
		for (int i = 0; i < model.getBotList().size(); i++) {
			int posX = model.getBotList().get(i)[0];
			int posY = model.getBotList().get(i)[1];

			// Generate random bots from images
			int botNumber = model.getBotList().get(i)[4];
			Image icon = model.getImage("bot" + botNumber);
			g2d.drawImage(icon, posX, posY, this);

		}


	}

	public void drawDungeonInfo(Graphics2D g2d) {

		int currentLevel = model.getCurrentLevel();

		g2d.setStroke(new BasicStroke(10));
		g2d.setFont(model.getGameFont());
		g2d.setPaint(Color.white);
		g2d.drawString("Level: " + currentLevel, 50, 30);
		g2d.drawString("Coins: " + model.getCoins(), 150, 30);
		g2d.drawString("Room: " + model.getRoomName(), 250, 30);
		g2d.drawString("Time: " + model.getTimer(), 450, 30);
	}

	public void drawDungeon(Graphics2D g2d) {

		int tile = model.getTileSize();

		//GameDungeon determines which level should  be painted
		int[][] gameDungeon = model.getDungeon();

		for (int row = 0; row < gameDungeon.length; row++) {
			for (int col = 0; col < gameDungeon[0].length; col++) {

				Color tileColor;

				switch (gameDungeon[row][col]) {
				case 1:
					tileColor = new Color(34, 28, 53);
					break;
				case 2:
					tileColor = new Color(139, 69, 19);
					break;
				case 3:
					tileColor = new Color(0, 255, 255);
					break;
				case 4:
					tileColor = Color.BLUE;
					break;
				default:
					tileColor = Color.WHITE;
				}

				g2d.setColor(tileColor);
				g2d.fillRect(tile * col, tile * row, tile, tile);

				//Draw borders of tiles
				g2d.setColor(Color.BLACK);
				if (gameDungeon[row][col] != 1) {
					g2d.drawRect(tile * col, tile * row, tile, tile);
				}

			}
		}

	}

	//Draw main menu
	public void drawMenu(Graphics2D g2d) {

		int menuState = model.getMenuState();
		int tokenState = model.getMenuStates()[menuState - 1];
		Color darkBlue = new Color(34, 28, 53);
		setBackground(darkBlue);

		g2d.drawImage(model.getImage("hero"), 200, tokenState, this);

		g2d.drawImage(model.getImage("menu-main"), 200, 100, this);
		g2d.drawImage(model.getImage("menu-play"), 300, 400, this);
		g2d.drawImage(model.getImage("menu-instructions"), 300, 500, this);
		g2d.drawImage(model.getImage("menu-score"), 300, 600, this);
		g2d.drawImage(model.getImage("menu-exit"), 300, 700, this);

	}

	//Draw coins and bots
	public void drawItems(List<int[]> list, Image icon, Graphics2D g2d) {

		for (int i = 0; i < list.size(); i++) {
			int posX = list.get(i)[0];
			int posY = list.get(i)[1];
			g2d.drawImage(icon, posX, posY, this);

		}

	}

	// Set the dimensions of the window at the beginning
	public void setDimensions(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}

	public View() {

		// Initiate model and controller
		Model model = new Model();
		this.model = model;

		Controller controller = new Controller(model, this);
		this.controller = controller;

		setDimensions(model.getDimensionHeight(), model.getDimensionHeight());
		setFocusable(true);
		addKeyListener(controller);

		// Set frame
		JFrame frame = new JFrame("Dungeon Game");
		frame.getContentPane().add(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

}
