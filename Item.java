
public class Item extends Sprite {
	
	private final ItemType itemType;

	public Item(int x, int y, ItemType itemType) {
		super(x, y, 32, 32, itemType.name().toLowerCase(), SpriteType.GOOD);
		this.itemType = itemType;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public enum ItemType {
		CHEST, COIN;
	}
	
}
