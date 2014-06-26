import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Handles key related events.
 * @author Austin Buckler / 216517
 *
 */
public class GameKeyListener extends KeyAdapter {
	
	private final Assign7 main;
	private final Player player;

	public GameKeyListener(Assign7 main) {
		this.main = main;
		this.player = main.getPlayer();
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		super.keyPressed(e);
		
		if (main.isGameOver()) {
			return;
		}
		
		int lastX = player.getX();
		int lastY = player.getY();
		
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.setX(player.getX() - 5);
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.setX(player.getX() + 5);
		} else if (e.getKeyCode() == KeyEvent.VK_UP) {
			player.setY(player.getY() - 5);
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.setY(player.getY() + 5);
		}
		
		for (int i = 0; i < Assign7.getSprites().size(); i++) {
			
			Sprite sprite = Assign7.getSprites().get(i);
			
			if (player.collidesWithSprite(sprite)) {
				if (sprite instanceof Wall) {
					Wall wall = (Wall) sprite;
					if (!wall.isVineWall()) {
						player.setX(lastX);
						player.setY(lastY);
					}
				} else if (sprite instanceof Item) {
					Item item = (Item) sprite;
					main.getSoundCollect().play();
					int incrementAmount = item.getItemType() == Item.ItemType.CHEST ? 100 : 50;
					item.setVisible(false);
					player.setScore(player.getScore() + incrementAmount);
				} else if (sprite instanceof Enemy) {
					player.setHealth(player.getHealth() - 50);
					main.getSoundDamage().play();
				}
			}
			
			if (sprite instanceof Enemy) {
				((Enemy) sprite).move();
			}
			
		}
	}

	
	
}
