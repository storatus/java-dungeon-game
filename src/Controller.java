import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.TimerTask;

import javax.swing.Timer;

import org.junit.jupiter.params.provider.EnumSource.Mode;

public class Controller implements ActionListener, KeyListener {

	Timer timer = new Timer(2, this);
	private Model model;
	private View view;

	private boolean isBotX = false;
	
	public Controller(Model model, View view) {

		this.model = model;
		this.view = view;
		timer.start();

		collideBots(3);
		moveBots(3);
		

		

	}
	

	public void doMovement(int key) {

		if (model.getDeadState()) {
			return;
		}
		
		if (model.getMoveState() == false) {
			model.setMoveState(true);
			model.setDoorNotification(false);
			
			//Here I set the timer 
			if(model.getTimer() == 50) {
				model.setTimer(); 
			}
			
		}

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
	
	public void doGameWonMovement(KeyEvent e){
		char character = e.getKeyChar();
		int keyCode = e.getKeyCode();
		model.buildName(character,keyCode);
		
		// set the properties value
		String name = model.getName().toString(); 
		String time = String.valueOf(model.getTimer()); 
		
		
		// To enter the name
		if(keyCode == 10 && model.getName().length() > 0) {
			
			try(PrintWriter output = new PrintWriter(new FileWriter("scores.txt",true))) 
			{				    
			
				String newLine = name+","+time;
			    output.printf("%s\r\n", newLine);
			    
			    
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		    System.exit(0);
			

		}
		
	}
	
	
	

	public void doMenuMovement(int key) {

		//Determine if dead or in main Menu
		int decreaseLimit = 4; 
		if(model.getDeadState()) {
			decreaseLimit = 2;
		}
		
		
		if (key == KeyEvent.VK_DOWN) {
			model.minusMenuState(decreaseLimit);
		}

		if (key == KeyEvent.VK_UP) {
			model.plusMenuState();
		}

		if (key == 10) {
			if(model.getScoreMenu()) {
				model.setScoreMenu(false);
				return; 
			}
			selectMenu(model.getMenuState());
		}
	}

	public void selectMenu(int state){
		
		if(!model.getDeadState()) {
			
			if (state == 1) {
				model.setGameState(true);
			}

			
			if (state == 3) {

				model.setScoreMenu(true); 
			
			}
			
			if (state == 4) {
				System.exit(0);
			}
		
		}else {
			if (state == 1) {
				renewGame();
			}

			if (state == 2) {
				System.exit(0);
			}
			
		}
		
	}
	
	public void renewGame() {
		
		model.initItems();
		model.setDeadState(false);	

	}
	
	
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

	
	public void openDoors(){
		
		for (Rectangle rectangle : model.getTiles()) {

			
				int tile = model.getTileSize();
				int xCoor = rectangle.x; 
				int yCoor = rectangle.y; 
				
				int col = yCoor /tile;
				int row = xCoor /tile; 
				
				int state = model.getDungeon()[col][row]; 
				
					if(model.getCoins() >= 3) {
						if(state == 2) {
							
							model.getDungeon()[col][row] = 3;
							model.getDungeon()[col][row] = 3;
						}
						
					}				
			}
		
		
	}
	
	
	
	public void hitDoor() {
		
		for (Rectangle rectangle : model.getTiles()) {

			if (model.getPlayer().intersects(rectangle)) {
				int tile = model.getTileSize();
				int xCoor = rectangle.x; 
				int yCoor = rectangle.y; 
				
				int col = yCoor /tile;
				int row = xCoor /tile; 
				
				int state = model.getDungeon()[col][row]; 
	
				if(state == 2) {
					model.setDoorNotification(true); 
					model.setMoveState(false);
				}
				
				if(state == 3) {
					
					// Here is where i change levels 
					
					//Notification that i changed levels
					
					if(model.getCurrentLevel() == 4) {
						model.setGameWon(true);
						model.stopTimer();
						model.doGameOver(); 
						break; 
					}
					
					
					int nextLevel = model.getCurrentLevel()+1;
					model.setCurrentLevel(nextLevel);
					model.initItems();
			
					break; 
				}
				
				
			}
		}	
	}

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

	public void moveBots(int time) {

		Timer timer = new Timer(time, new ActionListener() {
			int counter = 0;

			public void actionPerformed(ActionEvent e) {
				
				if(model.getMoveState()) {
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

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();

		
		
		if(model.getGameState() && !model.getDeadState() && !model.getGameWon()){
			doMovement(key);
		}else {
			
			if(model.getGameWon()) {
				doGameWonMovement(e); 
				return; 
			}
			
		
			
			doMenuMovement(key);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		model.setGoX(0);
		model.setGoY(0);
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (model.getGameState()) {
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
