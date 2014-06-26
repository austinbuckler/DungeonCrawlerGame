import java.awt.Graphics;

/**
 * Holds static methods for a game level.
 * @author Austin Buckler / 216517
 *
 */
public class GameLevel {
	
	public static void populateLists(Graphics g, Assign7 main) {
		drawLevel(g, main, true);
		drawItems(g, main, true, false);
		drawBorder(g, main, true);
		drawEnemies(g, main, true, false);
		
		System.out.println("List: " + Assign7.getSprites().size());
	}
	
	public static void drawLevel(Graphics g, Assign7 main, boolean update) {
		drawWall(32, 64, g, main, update);
		drawWall(64, 64, g, main, update);
		drawWall(64, 96, g, main, update);
		drawWall(96, 96, g, main, update);
		drawWall(96, 128, g, main, update);
		drawWall(160, 32, g, main, update);
		drawWall(224, 32, g, main, update);
		drawWall(160, 96, g, main, update);
		drawWall(160, 128, g, main, update);
		
		for (int i = 32; i <= 224; i += 32) {
			drawWall(480, 96 + i, g, main, update);
		}
		
		drawWall(512, 192, g, main, update);
		for (int i = 96; i < 608; i += 32) {
			drawWall(512 + i, 192, g, main, update);
		}
		
		drawWall(224, 96, g, main, update);
		drawWall(224, 128, g, main, update);
		drawWall(224, 64, g, main, update);
		drawWall(224, 160, g, main, update);
		drawWall(256, 128, g, main, update);
		drawWall(288, 128, true, g, main, update);
		drawWall(320, 128, g, main, update);
		drawWall(352, 96, g, main, update);
		drawWall(352, 64, g, main, update);
		drawWall(352, 32, g, main, update);
		drawWall(480, 96, g, main, update);
		
		drawWall(384, 352, g, main, update);
		drawWall(352, 352, g, main, update);
		for (int i = 448; i <= 640; i+=32) {
			drawWall(i, 352, g, main, update);
		}
		//for (int i = 32; i <= 672; i+=32) {
		//	drawWall(0 + i, 352, g, main, update);
		//}
		
		for (int i = 32; i < 352; i+=32) {
			drawWall(0 + i, 352, g, main, update);
		}
		
		for (int i = 320; i < 512; i+=32) {
			drawWall(320, i, g, main, update);
		}
		
		drawVine(416, 352, g, main, update);
	}
	
	public static void drawItems(Graphics g, Assign7 main, boolean update, boolean redraw) {
		if (redraw) {
			for (int i = 0; i < Assign7.getSprites().size(); i++) {
				Sprite sprite = Assign7.getSprites().get(i);
				
				if (sprite instanceof Item) {
					Item it = (Item) sprite;
					it.draw(g, main);
				}
			}
		} else {
			drawItem(288, 32, Item.ItemType.CHEST, g, main, update);
			drawItem(576, 256, Item.ItemType.COIN, g, main, update);
			drawItem(576, 288, Item.ItemType.COIN, g, main, update);
			drawItem(576, 320, Item.ItemType.COIN, g, main, update);
			drawItem(544, 256, Item.ItemType.COIN, g, main, update);
			drawItem(544, 288, Item.ItemType.COIN, g, main, update);
			drawItem(544, 320, Item.ItemType.COIN, g, main, update);
			drawItem(608, 256, Item.ItemType.COIN, g, main, update);
			drawItem(608, 288, Item.ItemType.COIN, g, main, update);
			drawItem(608, 320, Item.ItemType.COIN, g, main, update);
			drawItem(672, 448, Item.ItemType.CHEST, g, main, update);
			drawItem(64, 416, Item.ItemType.CHEST, g, main, update);
			drawItem(96, 416, Item.ItemType.CHEST, g, main, update);
			drawItem(128, 416, Item.ItemType.CHEST, g, main, update);
		}
	}
	
	public static void drawEnemies(Graphics g, Assign7 main, boolean update, boolean redraw) {
		if (redraw) {
			for (int i = 0; i < Assign7.getSprites().size(); i++) {
				Sprite sprite = Assign7.getSprites().get(i);
				if (sprite instanceof Enemy) {
					Enemy en = (Enemy) sprite;
					en.draw(g, main);
				}
			}
		} else {
			drawEnemy(704, 416, 0, g, main, update);
			drawEnemy(576, 160, 1, g, main, update);
			drawEnemy(128, 192, 2, g, main, update);
			drawEnemy(256, 512, 2, g, main, update);
		}
	}
	
	public static void drawBorder(Graphics g, Assign7 main, boolean update) {
		
		for (int i = 0; i < 25; i++) {
			drawWall(32 * i, 0, g, main, update);
			drawWall(32 * i, Assign7.APPLET_HEIGHT - 32, g, main, update);
		}

		for (int i = 0; i < 19; i++) {
			drawWall(Assign7.APPLET_WIDTH - 32, 32 * i - 32, g, main, update);
			drawWall(0, 32 * i - 32, g, main, update);
		}
	}
	
	public static void drawWall(int x, int y, Graphics g, Assign7 main, boolean update) {
		drawWall(x, y, false, g, main, update);
	}
	
	public static void drawVine(int x, int y, Graphics g, Assign7 main, boolean update) {
		drawWall(x, y, true, g, main, update);
	}
	
	public static void drawWall(int x, int y, boolean vine, Graphics g, Assign7 main, boolean update) {
		Wall wall = new Wall(x, y, vine);
		wall.draw(g, main);
		if (update) {
			Assign7.getSprites().add(wall);
		}
	}
	
	public static void drawItem(int x, int y, Item.ItemType type, Graphics g, Assign7 main, boolean update) {
		Item item = new Item(x, y, type);
		item.draw(g, main);	
		if (update) {
			Assign7.getSprites().add(item);
		}
	}
	
	public static void drawEnemy(int x, int y, int index, Graphics g, Assign7 main, boolean update) {
		Enemy enemy = new Enemy(x, y, index);
		enemy.draw(g, main);
		if (update) {
			Assign7.getSprites().add(enemy);
		}
	}
	
}
