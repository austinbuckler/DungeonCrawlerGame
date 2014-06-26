import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Assignment 7 - Main class/Applet
 * 
 * @author Austin Buckler / 216517
 * 
 */
public class Assign7 extends Applet {
	
	public static final int APPLET_WIDTH = 800;
	public static final int APPLET_HEIGHT = 608;
	public static final int GOAL = 950;

	private Player player;
	private GameKeyListener keyListener;
	private GameAlarm alarm;
	
	private static final List<Sprite> sprites = new ArrayList<Sprite>();
	
	private boolean debug = false;

	/** Flashing Fix **/
	private Image image = null;
	private Graphics gfx = null;
	
	/** Sounds **/
	private AudioClip soundWin;
	private AudioClip soundLose;
	private AudioClip soundCollect;
	private AudioClip soundDamage;
	
	private boolean startup = false;
	private String message = null;
	private boolean gameOver = false;
	
	@Override
	public void init() {
		this.setBackground(new Color(113, 98, 133));
		this.setSize(new Dimension(APPLET_WIDTH, APPLET_HEIGHT));
		this.requestFocus();

		this.player = new Player();
		this.keyListener = new GameKeyListener(this);
		this.addKeyListener(keyListener);
		
		this.soundCollect = this.getAudioClip(getDocumentBase(), "snd/soundcollect.wav");
		this.soundWin = this.getAudioClip(getDocumentBase(), "snd/soundwin.wav");
		this.soundDamage = this.getAudioClip(getDocumentBase(), "snd/sounddamage.wav");
		this.soundLose = this.getAudioClip(getDocumentBase(), "snd/soundlose.wav"); // TODO
		
		this.alarm = new GameAlarm(46);
		this.alarm.createAlarm();
		
		this.startup = true;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if (startup) {
			GameLevel.populateLists(g, this);
			startup = false;
		}
		
		if (debug) {
			g.setColor(Color.WHITE);
			for (int i = 0 ; i < APPLET_WIDTH; i++) {
				g.drawLine(32, 32 * i, APPLET_WIDTH, 32 * i);
				g.setColor(Color.GREEN);
				g.drawString("" + 32 * i, 32 * i + 4, 45);
				g.setColor(Color.WHITE);
				for (int y = 0; y < APPLET_HEIGHT; y++) {
					g.drawLine(32 * y, 32, 32 * y, APPLET_HEIGHT);
					g.setColor(Color.GREEN);
					g.drawString("" + 32 * y, 32, 32 * y + 32);
					g.setColor(Color.WHITE);
				}
			}
		}
		
		int timeLeft = alarm.getTimeLeft();
		
		GameLevel.drawBorder(g, this, false);
		GameLevel.drawLevel(g, this, false);
		GameLevel.drawItems(g, this, false, true);
		GameLevel.drawEnemies(g, this, false, true);
		
		player.draw(g, this);
		
		this.message = "Time: " + alarm.getTimeLeft() + " Health: "
				+ player.getHealth() + " Score: " + player.getScore();
		
		if (timeLeft <= 0 || player.getHealth() <= 0) {
			message = "You lose!";
			soundLose.play();
			setBackground(new Color(255, 66, 57));
			showStatus(message);
			gameOver = true;
			return;
		} else {
			if (player.getScore() == GOAL) {
				message = "You win!";
				soundWin.play();
				setBackground(new Color(9, 178, 52));
				showStatus(message);
				gameOver = true;
				return;
			}
		}
		
		this.showStatus(message);
		repaint();
	}

	@Override
	public void update(Graphics g) {
		if (image == null) {
			image = createImage(getWidth(), getHeight());
			gfx = image.getGraphics();
		}

		gfx.setColor(getBackground());
		gfx.fillRect(0, 0, getWidth(), getHeight());

		gfx.setColor(getForeground());
		paint(gfx);

		g.drawImage(image, 0, 0, this);
	}
	
	public static int random(int maxRange) {
		return (int) Math.round((Math.random() * maxRange));
	}
	
	public Player getPlayer() {
		return player;
	}

	public static List<Sprite> getSprites() {
		return sprites;
	}

	public AudioClip getSoundWin() {
		return soundWin;
	}

	public AudioClip getSoundLose() {
		return soundLose;
	}

	public AudioClip getSoundCollect() {
		return soundCollect;
	}

	public AudioClip getSoundDamage() {
		return soundDamage;
	}

	public boolean isGameOver() {
		return gameOver;
	}

	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

	private static final long serialVersionUID = 1L;

}
