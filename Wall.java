
public class Wall extends Sprite {

	private final boolean vineWall;
	
	public Wall(int x, int y) {
		this(x, y, false);
	}
	
	public Wall(int x, int y, boolean vineWall) {
		super(x, y, 32, 32, vineWall ? "vinewall" : "wall", SpriteType.WALL);
		this.vineWall = vineWall;
	}

	public boolean isVineWall() {
		return vineWall;
	}

}
