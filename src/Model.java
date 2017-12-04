import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.ImageIcon;

public class Model extends PlayerModel{
	
	private int iconSize = 40;

	// Frame
	private int dHeight = 800;
	private int dWidth = 800;
		
	//tileSize
	private int tileSize;   
	
	//Counting coins
	private int coinCount = 0; 
	
	 List<int[]> coinList = new ArrayList<int[]>();
	 List<int[]> botList = new ArrayList<int[]>();
	 private ArrayList<Rectangle> tiles = new ArrayList<Rectangle>();

	// Other
	Font gameFont = new Font("Arial", Font.BOLD,20);	
	private boolean deadState = false;
	private boolean gameState = false;
	private boolean isPositioned = false;
	
	
	private int menuState = 1; 
	private int[] menuStates = {400,500,600};
	
		
	
	//	private int gameLevel;  
	
	private int[][] dungeon = {
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, 
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 }, 
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 2, 0, 0, 0, 0, 0, 0, 1, 1, 1 }, 
			{ 2, 0, 0, 0, 0, 0, 0, 1, 1, 1 }, 
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 3, 1 }, 
			{ 1, 0, 0, 0, 0, 0, 0, 0, 3, 1 }, 
			{ 1, 0, 0, 0, 0, 0, 0, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },			
	};
	
	
	
	public Model(){
		
		super(); 
		//Set tile size
		this.tileSize = dWidth/dungeon.length;  
		generateCollisions(); 
       
		// Set Items
		generateItems(coinList, 10, "coins");
		generateItems(botList, 5, "bots");
		positionPlayer(); 
		
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
	
	
	public int getCoins(){
		return coinCount; 
	}
	
	
	public void setCoin(int number){
		this.coinCount = number; 
	}
	
	public int[][] getDungeon() {
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
	
	public void setGameState(boolean bool) {
		gameState = bool;  
	}
	
	public int getMenuState() {
		return menuState;   
	}
	
	public int[] getMenuStates() {
		return menuStates;
	}
	
	
	public void minusMenuState() {
		if(menuState < 3) {
			menuState++;
		}
	}
	
	public void plusMenuState() {		
		if(menuState > 1) {
			menuState--;
		}
	}
	
	public void generateItems(List<int[]> itemList, int count, String name) {
		
		while(itemList.size() < count) {
			
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
			
			if(intersects){
				generateItems(itemList, count,name);
			}else {
				
				if(name == "bots") {
					//Get random number for bots
					int randomNumber = ThreadLocalRandom.current().nextInt(1, 4 + 1);
					itemList.add(new int[] {randomX,randomY,1,1, randomNumber});
				}
				
				if(name == "coins") {
					itemList.add(new int[] {randomX,randomY});
				}
				
				
			}
						
		}
		
	}
	
	public void positionPlayer() {
		
		while(!isPositioned) {
			
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
			
			if(intersects){
				positionPlayer();
			}else {
				setHeroY(randomY);
				setHeroY(randomX);
				isPositioned = true; 
			}
						
		}
		
	}
	
	

	
	public Image getImage(String name) {
		ImageIcon imageObj = new ImageIcon("images/"+name+".png");
		Image image = imageObj.getImage();
		return image;  
	}
	
	List<int[]> getCoinList(){
		return coinList; 
	}
	
	
	List<int[]> getBotList(){
		return botList; 
	}
	
	public void setTile(Rectangle rectangle) {
		tiles.add(rectangle); 
	}
	
	public void generateCollisions() {
		int tile = getTileSize();

		for (int row = 0; row < dungeon.length; row++) {
			for (int col = 0; col < dungeon[0].length; col++) {
								
				if(dungeon[row][col] >= 1 ) {
					tiles.add(new Rectangle(tile * col, tile * row, tile, tile));
				}
			}
		}
	}
	
	public ArrayList<Rectangle> getTiles() {
		return tiles; 
	}
	
}



