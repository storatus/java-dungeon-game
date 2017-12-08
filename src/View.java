import javax.swing.*;

import java.util.List;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

public class View extends JPanel {

	private Controller controller;
	private Model model;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;

		if (model.getGameState()) {

			drawDungeon(g2d);
			drawDungeonInfo(g2d);

			drawItems(model.getCoinList(), model.getImage("gold"), g2d);
			drawBots(g2d);

			
			
			
			if (!model.getDeadState()) {
				g2d.drawImage(model.getImage("hero"), model.getHeroX(), model.getHeroY(), this);

				if (!model.getMoveState()) {
					showConditionScreen(g2d, "You have to collect 10 coins", "Move to continue");
				}

				if (model.getDoorNotification()) {
					showConditionScreen(g2d, "You have to collect more than 10 coins", "To open this door");
				}
				
				
				
				
			} else {
				showDeadScreen(g2d);
			}

		} else {
			drawMenu(g2d);
		}

	}

	public void showDeadScreen(Graphics2D g2d) {

		// Draw Canvas
		g2d.setColor(Color.white);
		g2d.fillRect(100, 200, 600, 200);
		g2d.setColor(Color.black);
		g2d.drawRect(100, 200, 600, 200);

		// Draw String
		g2d.setFont(new Font("SansSerif", Font.BOLD, 30));
		g2d.setColor(Color.black);
		g2d.drawString("You are dead", 300, 250);

		// Draw Buttons
		int menuState = model.getMenuState();
		int tokenState = model.getMenuDeadStates()[menuState - 1];
		g2d.drawImage(model.getImage("hero"), 250, tokenState, this);

		g2d.drawImage(model.getImage("menu-continue"), 300, 270, this);
		g2d.drawImage(model.getImage("menu-exit"), 300, 330, this);

	}
	
	


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

	public void drawBots(Graphics2D g2d) {

		for (int i = 0; i < model.getBotList().size(); i++) {
			int posX = model.getBotList().get(i)[0];
			int posY = model.getBotList().get(i)[1];

			// Get different bots
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
		g2d.drawString("Level: "+currentLevel, 50, 30);
		g2d.drawString("Coins: " + model.getCoins(), 250, 30);

	}

	public void drawDungeon(Graphics2D g2d) {

		int tile = model.getTileSize();
		int[][] gameDungeon = model.getDungeon();



		for (int row = 0; row < gameDungeon.length; row++) {
			for (int col = 0; col < gameDungeon[0].length; col++) {
				Color color;
				switch (gameDungeon[row][col]) {
				case 1:
					color = new Color(34, 28, 53);
					break;
				case 2: 
					color = new Color(139, 69, 19);
					break;
				case 3:	
					color = new Color(0,255,255);
					break;
				case 4:	
					color = Color.BLUE;
					break;
				default:
					color = Color.WHITE;
				}

				g2d.setColor(color);
				g2d.fillRect(tile * col, tile * row, tile, tile);
				g2d.setColor(Color.BLACK);

				if (gameDungeon[row][col] != 1) {
					g2d.drawRect(tile * col, tile * row, tile, tile);
				}

			}
		}

	}

	public void drawMenu(Graphics2D g2d) {

		int menuState = model.getMenuState();
		int tokenState = model.getMenuStates()[menuState - 1];

		setBackground(Color.black);

		g2d.drawImage(model.getImage("hero"), 200, tokenState, this);
		g2d.drawImage(model.getImage("menu-main"), 200, 100, this);
		g2d.drawImage(model.getImage("menu-play"), 300, 400, this);
		g2d.drawImage(model.getImage("menu-continue"), 300, 500, this);
		g2d.drawImage(model.getImage("menu-exit"), 300, 600, this);

	}

	public void drawItems(List<int[]> list, Image icon, Graphics2D g2d) {

		for (int i = 0; i < list.size(); i++) {
			int posX = list.get(i)[0];
			int posY = list.get(i)[1];
			g2d.drawImage(icon, posX, posY, this);

		}

	}

	public void setDimensions(int width, int height) {
		setPreferredSize(new Dimension(width, height));
	}

	public View() {

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
