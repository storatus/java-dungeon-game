import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Random;

import javax.swing.Timer;

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

	public void doMenuMovement(int key) {

		//Determine if dead or in main Menu
		int decreaseLimit = 3; 
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
			selectMenu(model.getMenuState());
		}
	}

	public void selectMenu(int state) {
		
		if(!model.getDeadState()) {
			if (state == 1) {
				model.setGameState(true);
			}

			if (state == 3) {
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
	
	
	public void hitDoor() {
		
		for (Rectangle rectangle : model.getTiles()) {

			if (model.getPlayer().intersects(rectangle)) {
				int tile = model.getTileSize();
				int xCoor = rectangle.x; 
				int yCoor = rectangle.y; 
				
				int col = yCoor /tile;
				int row = xCoor /tile; 
				
				int state = model.getDungeon(1)[col][row]; 
	
				if(model.getCurrentLevel() == 1) {
					if(state == 3) {
						model.setCurrentLevel(2);
					}
				}
				
			
				
				
				
				
			}

		}
		
		
		
	
		
		
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

	public void frameBounce() {

		int bounceWidth = model.getDimensionWidth() - model.getIconSize();
		int bounceHeight = model.getDimensionHeight() - model.getIconSize();

		if (model.getHeroX() > bounceWidth) {
			model.setGoX(0);
			model.setHeroX(bounceWidth);
		}

		if (model.getHeroY() > bounceHeight) {
			model.setGoY(0);
			model.setHeroY(bounceHeight);
		}

		if (model.getHeroX() < 0) {
			model.setGoX(0);
			model.setHeroX(0);
		}

		if (model.getHeroY() < 0) {
			model.setGoY(0);
			model.setHeroY(0);
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
				model.setDeadState(true);
			}

		}
	}

	public void moveBots(int time) {

		Timer timer = new Timer(time, new ActionListener() {
			int counter = 0;

			public void actionPerformed(ActionEvent e) {

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
						;
						counter = 0;
					}
					counter++;

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

		if(model.getGameState() && !model.getDeadState()){
			doMovement(key);
		}else {
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
			frameBounce();
			hitCoin();
			hitBot();
			hitDoor();
			
			dungeonBounce();

			model.setHeroX(model.getHeroX() + model.getGoX());
			model.setHeroY(model.getHeroY() + model.getGoY());
		}
		
		view.repaint();

	}

}
