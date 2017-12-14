/**
 * Class. PlayerModel for
 * Multi-Player Adventure Game SWEngCW02
 *
 * @version 1.0
 * @created 15/11/2017
 * @gitCommit 1.12
 * @release 14/12/2017 Addressing  Functional
 *      Requirement for User stories SD01-SD17
 *
 */

import java.awt.Rectangle;

// This model class is an extension of Model

public class PlayerModel {

	// Hero position at the beginning
	private int heroX = 300, heroY = 300, goY = 0, goX = 0;
	private Rectangle playerPosition;

	// Getters and setters for the Hero positioning
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
