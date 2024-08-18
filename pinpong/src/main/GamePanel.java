package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
import objects.Ball;
import objects.Wall;

public class GamePanel extends JPanel implements Runnable {
	//variables
	//screen
	public final int screenWidth = 1000; //px
	public final int screenHeight = (int)(screenWidth * (0.65));
	final Dimension screenSize = new Dimension(screenWidth, screenHeight);
	
	int FPS = 100;
	
	//system
	Thread gameThread;
	KeyHandler keyH = new KeyHandler();
	public Sound sfx = new Sound();
	public Wall wall1 = new Wall(this,keyH,20,"left");
	public Wall wall2 = new Wall(this,keyH,(screenWidth - 20),"right");
	public Ball ball = new Ball(this);
	public Player player1 = new Player(this, "player1");
	public Player player2 = new Player(this, "player2");
	public UI ui = new UI(this);
	
	
	
	
	public GamePanel() {
		this.setPreferredSize(screenSize);
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	@Override
	public void run() {

		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while(gameThread != null) {
			currentTime = System.nanoTime();
			
			delta += (currentTime - lastTime) / drawInterval;
			
			lastTime = currentTime;
			
			if(delta >= 1) {
				update();
				repaint();
				delta--;
			}
			
		}
	}
	
	public void stop() {
		gameThread = null;
	}
	
	public void update() {
		wall1.update();
		wall2.update();
		ball.update();
		player1.checkWinner();
		player2.checkWinner();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		
		ball.draw(g2);
		wall1.draw(g2);
		wall2.draw(g2);
		ui.draw(g2);
		
		g2.dispose();
	}
	
	public void playSFX(int i) {
		sfx.setFile(i);
		sfx.play();
	}
}
