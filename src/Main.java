import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

class Main extends JPanel implements ActionListener, KeyListener {

	Timer timer = new Timer(2, this);

	// Hero position
	private int dx = 300, dy = 300, goDy = 0, goDx = 0;

	// Icon (20x20)
	private Image heroImage;
	private Image coinImage;
	private Image botImage;
	private int iconSize = 40;

	// Frame
	private int dWidth = 800;
	private int dHeight = 800;
	
	
	//tileSize
	private int tileSize;   
	
	//Counting coins
	private int coinCount = 0; 
	
	 List<int[]> coinList = new ArrayList<int[]>();
	 List<int[]> botList = new ArrayList<int[]>();

	// Other
	Font gameFont = new Font("Arial", Font.BOLD,20);
	
	private boolean notifyDoor = false; 
	private boolean isDead = false;

	//	private int gameLevel;  
	
	
	private int[][] maze = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 }, 
			{ 2, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 2, 0, 0, 0, 0, 0, 0, 1, 1, 1 }, 
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 }, 
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 3, 1 }, 
			{ 1, 0, 0, 0, 0, 0, 0, 0, 3, 1 }, 
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },			
	};


	

	
	public Main() {
		timer.start();

		
		tileSize = dWidth/maze.length;   
		generateItems(coinList, 10);
		generateItems(botList, 8);
		// Instantiate JPanel and methods
		addKeyListener(this);
		setFocusable(true);
		setPreferredSize(new Dimension(dWidth, dHeight));
		moveBots(100); 
		

	}
	

	
	public void generateItems(List<int[]> itemList, int count) {
		
		while(itemList.size() < count) {
			int randomX = ThreadLocalRandom.current().nextInt(0, dWidth + 1);
			int randomY = ThreadLocalRandom.current().nextInt(0, dHeight + 1);
						
			int tileX = (randomX-20) / tileSize;
			int tileY = (randomY-20) / tileSize;
			
			
			int pos = maze[tileY][tileX];
			
			if(pos == 1 || pos == 2 || pos == 3) {
				generateItems(itemList, count); 
			}else {
				itemList.add(new int[] { randomX, randomY}); 	
			}
						
		}
		
	}
	
	

	
	


	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		ImageIcon imageObj = new ImageIcon("images/hero.png");
		heroImage = imageObj.getImage();
		Graphics2D g2d = (Graphics2D) g;
		paintDungeon(g2d);

		if(!isDead) {
			g2d.drawImage(heroImage, dx, dy, this);
		}
		
		
		g2d.setStroke(new BasicStroke(10));
		g2d.setFont(gameFont);

		g2d.setPaint(Color.white);

		g2d.drawString("Level: 1", 50, 20);
		g2d.drawString("X: " + dx, 150, 20);
		g2d.drawString("Y: " + dy, 250, 20);
		g2d.drawString("Coins: " + coinCount, 350, 20);
		

		if(notifyDoor) {
			g2d.setPaint(Color.black);
			g2d.drawString("You have to collect at least 5 coins", 200, 200);
		}
		
		if(isDead) {
			g2d.setPaint(Color.black);
			g2d.drawString("YOU DIED!", 200, 200);
		}
		
		
		if(coinCount > 5) {
			for (int row = 0; row < maze.length; row++) {
				for (int col = 0; col < maze[0].length; col++) {
					
					if(maze[row][col] == 3) {
						maze[row][col] = 0; 
					}	
					
			}
		}

		}
	}

	
	public void getCoin() {
		for (int i = 0; i < coinList.size(); i++) {
			int posX = coinList.get(i)[0]; 
			int posY = coinList.get(i)[1];
			
			
			if ((dx > posX-20 && dx < posX+20) && dy > posY-20 && dy < posY+20) {
				coinList.remove(i);
				coinCount++; 
			}
			
		 }
	}
	
	public void getBot() {
		for (int i = 0; i < botList.size(); i++) {
			int posX = botList.get(i)[0]; 
			int posY = botList.get(i)[1];
			
			
			if ((dx > posX-20 && dx < posX+20) && dy > posY-20 && dy < posY+20) {
				
				isDead = true;  
			}
			
		 }
	}

	
	public void dungeonBounce() {
		if (goDx == -1) {
			int tileX = (dx) / tileSize;
			int tileY = (dy + 40) / tileSize;
			int pos = maze[tileY][tileX];

			if (pos == 1 || pos == 2 || pos == 3) {
				goDx = 0;
				dx = dx + 5;
			}
		}

		if (goDx == 1) {
			int tileX = (dx + 40) / tileSize;
			int tileY = (dy + 40) / tileSize;
			int pos = maze[tileY][tileX];

			if (pos == 1 || pos == 2 || pos == 3) {
				goDx = 0;
				dx = dx - 5;
				if(pos == 3) {
					notifyDoor = true; 
				}
			}
			
		}

		if (goDy == 1) {
			int tileX = (dx + 40) / tileSize;
			int tileY = (dy + 40) / tileSize;
			int pos = maze[tileY][tileX];

			if (pos == 1 || pos == 2 || pos == 3) {
				goDy = 0;
				dy = dy - 10;
				
			}
			
		}

		if (goDy == -1) {
			int tileX = (dx + 40) / tileSize;
			int tileY = (dy) / tileSize;
			int pos = maze[tileY][tileX];

			if (pos == 1 || pos == 2 || pos == 3) {
				goDy = 0;
				dy = dy + 10;
			}
		
		}
	} 
	
	
	public void moveBots(int time) {
	
		
	    Timer timer = new Timer(time, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	    
	        	for (int i = 0; i < botList.size(); i++) {
	            	int randomX = ThreadLocalRandom.current().nextInt(-20, 20 + 1);
	    			int posX = botList.get(i)[0]; 
	    			botList.get(i)[0] = posX +randomX;
	    		 }
	        	
	         for (int i = 0; i < botList.size(); i++) {
		    		int randomY = ThreadLocalRandom.current().nextInt(-20, 20 + 1);
	    			int posY = botList.get(i)[1];
	    			botList.get(i)[1] = posY +randomY;
	    		 }
	        	
	        	
	        }
	    });
	    timer.start();
	}
	

	
	
	public void actionPerformed(ActionEvent e) {
		
		
		
		jframeBounce();
		getCoin();
		getBot();
		dungeonBounce();


		

		dx = dx + goDx;
		dy = dy + goDy;
		repaint();

	}

	public void paintDungeon(Graphics2D g) {

		// draw the maze

		for (int row = 0; row < maze.length; row++) {
			for (int col = 0; col < maze[0].length; col++) {
				Color color;
				switch (maze[row][col]) {
				case 1:
					color = Color.BLACK;
					break;
				case 2: case 3:
					color = new Color(139,69,19);
					break;
				default:
					color = Color.WHITE;
				}
				g.setColor(color);
				g.fillRect(tileSize * col, tileSize * row, tileSize, tileSize);
				g.setColor(Color.BLACK);
				
				if(maze[row][col] != 2) {
					g.drawRect(tileSize * col, tileSize * row, tileSize, tileSize);
				}
				
			}
		}
		
		
		
		for (int i = 0; i < coinList.size(); i++) {
			int posX = coinList.get(i)[0]; 
			int posY = coinList.get(i)[1];
			ImageIcon coinObj = new ImageIcon("images/gold.png");
			
			
			
			
//			Do Images
//			System.out.println(getClass()); 			
//			ImageIcon coinObj = new ImageIcon(getClass().getResource("/images/gold.png"));
			
			
			
			
			
			coinImage = coinObj.getImage();
			g.drawImage(coinImage, posX, posY, this);

		 }


		
		for (int i = 0; i < botList.size(); i++) {
			int posX = botList.get(i)[0]; 
			int posY = botList.get(i)[1];
			ImageIcon botObj = new ImageIcon("images/bot.png");
			botImage = botObj.getImage();
			g.drawImage(botImage, posX, posY, this);

		 }


	}

	public void doMovement(int key) {

		if (key == KeyEvent.VK_DOWN) {
			goDy = 1;
			goDx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			goDy = -1;
			goDx = 0;
		}
		if (key == KeyEvent.VK_LEFT) {
			goDy = 0;
			goDx = -1;
		}
		if (key == KeyEvent.VK_RIGHT) {
			goDy = 0;
			goDx = 1;
		}

	}

	public void jframeBounce() {

		int bounceWidth = dWidth - iconSize;
		int bounceHeight = dHeight - iconSize;

		if (dx > bounceWidth) {
			goDx = 0;
			dx = bounceWidth;
		}

		if (dy > bounceHeight) {
			goDy = 0;
			dy = bounceHeight;
		}

		// blocking bounced for JFrame - left
		if (dx < 0) {
			goDx = 0;
			dx = 0;
		}
		if (dy < 0) {
			goDy = 0;
			dy = 0;
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
		goDx = 0;
		goDy = 0;
	}

	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		if(key == 10) {
			notifyDoor = false; 
		}
		
		
		doMovement(key);

	}

	public static void main(String[] s) {

		// Why Am I having twice setBackgroundblack ? It is when I start
		JFrame frame = new JFrame();

		frame.getContentPane().add(new Main());
		frame.setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
