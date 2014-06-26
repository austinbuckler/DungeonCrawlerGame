
/**
 * Represents an enemy.
 * @author Austin Buckler / 216517
 *
 */
public class Enemy extends Sprite {
	
	private static final String[] images = { "enemy1", "enemy2", "enemy3" };
	private final int MOVE_AMOUNT = 5;

	public Enemy(int x, int y, int index) {
		super(x, y, 32, 32, images[index], SpriteType.ENEMY);
	}
	
	public void move() {
		this.setX(Assign7.random(1) == 0 ? getX() + Assign7.random(MOVE_AMOUNT)
				: getX() - Assign7.random(MOVE_AMOUNT));
		this.setY(Assign7.random(1) == 0 ? getY() + Assign7.random(MOVE_AMOUNT)
				: getY() - Assign7.random(MOVE_AMOUNT));
		for (int i = 0; i < Assign7.getSprites().size(); i++) {
			Sprite sprite = Assign7.getSprites().get(i);
			if (this.collidesWithSprite(sprite)) {
				if (sprite instanceof Wall) {
					Wall wall = (Wall) sprite;
					if (!wall.isVineWall()) {
						setX(getSpawnX());
						setY(getSpawnY());
					}
				}
			}
		}
	}

}
