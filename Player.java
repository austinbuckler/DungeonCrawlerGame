
/**
 * Represents the player.
 * @author Austin Buckler / 216517
 *
 */
public class Player extends Sprite {

	private int health = 100;
	
	private int score = 0;
	
	public Player() {
		super(32, 32, 32, 32, "player", SpriteType.PLAYER);
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

}
