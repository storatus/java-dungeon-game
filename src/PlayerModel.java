import java.awt.Rectangle;

public class PlayerModel {
	
	// Hero position
	private int heroX = 300, heroY = 300, goY = 0, goX = 0;
    private Rectangle playerPosition; 
     
	
	//** Hero and Go
	public int getHeroX() {
		return this.heroX; 
	}
	
	public void setHeroX(int number) {
		this.heroX = number; 
	}
	
	public int getHeroY() {
		return heroY; 
	}
	
	public void setHeroY(int number) {
		this.heroY = number; 
	}
	
	public int getGoX() {
		return goX; 
	}
	
	public void setGoX(int number) {
		this.goX = number; 
	}
	
	public int getGoY() {
		return goY; 
	}
	
	public void setGoY(int number) {
		this.goY = number; 
	}
	
	
	public void setPlayer(Rectangle rectangle) {
		 this.playerPosition = rectangle; 
	}
	
	public Rectangle getPlayer() {
		 return playerPosition; 
	}

}
