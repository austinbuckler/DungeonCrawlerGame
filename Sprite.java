import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 * Represents a single sprite.
 * @author Austin Buckler / 216517
 *
 */
public class Sprite {
	
	private int x;
	private int y;
	
	private final int spawnX;
	private final int spawnY;
	
	private final int width;
	private final int height;
	private final String imgName;
	private final SpriteType type;
	
	private final Rectangle hitbox;
	
	private boolean visible;
	
	public Sprite(int x, int y, int width, int height, String imgName, SpriteType type) {
		this.x = x;
		this.y = y;
		this.spawnX = x;
		this.spawnY = y;
		this.width = width;
		this.height = height;
		this.imgName = imgName;
		this.type = type;
		this.hitbox = new Rectangle();
		this.visible = true;
	}
	
	public void draw(Graphics g, Assign7 main) {
		if (!visible) {
			return;
		}
		Image img = main.getImage(main.getDocumentBase(), "img/" + imgName + ".png");
		g.drawImage(img, x, y, main);
	}
	
	public boolean collidesWithSprite(Sprite sprite) {
		getHitbox().setBounds(x, y, 30, 30);
		sprite.getHitbox().setBounds(sprite.getX(), sprite.getY(), 30, 30);
		return getHitbox().intersects(sprite.getHitbox()) && sprite.isVisible();
	}
	
	public SpriteType getType() {
		return type;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Rectangle getHitbox() {
		return hitbox;
	}

	public int getSpawnX() {
		return spawnX;
	}

	public int getSpawnY() {
		return spawnY;
	}

	public enum SpriteType {
		GOOD, ENEMY, WALL, PLAYER;
	}

}
