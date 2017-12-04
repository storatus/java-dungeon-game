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
		
		if(model.getGameState()) {
			
			drawDungeon(g2d);
			drawDungeonInfo(g2d); 
			
			drawItems(model.getCoinList(), model.getImage("gold"), g2d); 		 
			drawBots(g2d); 
			 
			 if(!model.getDeadState()) {
				 g2d.drawImage(model.getImage("hero"), model.getHeroX(), model.getHeroY(), this);
			 }else {
//				 showDeadScreen(g2d); 
			 }
		
		}else {
			drawMenu(g2d); 
		}
		 
	}

	public void drawBots(Graphics2D g2d){
		
		for (int i = 0; i < model.getBotList().size(); i++) { 
			int posX = model.getBotList().get(i)[0];
			int posY = model.getBotList().get(i)[1];
			
			//Get different bots
			int botNumber = model.getBotList().get(i)[4];			
			Image icon = model.getImage("bot"+botNumber); 
			g2d.drawImage(icon, posX, posY, this);

		}
	}

	

	public void drawDungeonInfo(Graphics2D g2d) {
		
		g2d.setStroke(new BasicStroke(10));
		g2d.setFont(model.getGameFont());
		g2d.setPaint(Color.white);
		g2d.drawString("Level: 1", 50, 20);
		g2d.drawString("Coins: " + model.getCoins(), 150, 20);
	
	}

	
	
	public void drawDungeon(Graphics2D g2d) {

		int[][] dungeon = model.getDungeon();
		int tile = model.getTileSize();

		for (int row = 0; row < dungeon.length; row++) {
			for (int col = 0; col < dungeon[0].length; col++) {
				Color color;
				switch (dungeon[row][col]) {
				case 1:
					color = Color.BLACK;
					break;
				case 2: case 3:
					color = new Color(139, 69, 19);
					break;
				default:
					color = Color.WHITE;
				}

				g2d.setColor(color);
				g2d.fillRect(tile * col, tile * row, tile, tile);	
				g2d.setColor(Color.BLACK);

				if (dungeon[row][col] != 2) {
					g2d.drawRect(tile * col, tile * row, tile, tile);
				}

			}
		}

	}
	
	public void drawMenu(Graphics2D g2d) {
		
		int menuState = model.getMenuState(); 
		int tokenState =  model.getMenuStates()[menuState-1]; 
		
		
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
