package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import main.GamePanel;
import main.KeyHandler;

public class Wall extends Object{
	GamePanel gp;
	KeyHandler keyH;
	String side;
	
	public Wall(GamePanel gp, KeyHandler keyH, int xPosition, String side) {
		this.gp = gp;
		this.keyH = keyH;
		this.side = side;
		
		width = 20;
		height = 150;
		
		if(side.equals("right")) {
			x = xPosition - width;
		}else {
			x = xPosition;
		}
		
		defaultSettings();
	}
	
	public void defaultSettings(){
		speed = 10;
		y = gp.screenHeight/2 - height/2;
	}
	
	public void update() {
		if(side == "left") {
			if(keyH.upPressedLeft == true) {
				y -= speed;
			}
			else if(keyH.downPressedLeft == true) {
				y += speed;
			}
		}else {
			if(keyH.upPressedRight == true) {
				y -= speed;
			}
			else if(keyH.downPressedRight == true) {
				y += speed;
			}
		}
		if(y + height > gp.screenHeight) {
			y -= speed;
		}else if(y < 0) {
			y += speed;
		}
		
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.white);
		g2.fillRect(x, y, width, height);
	}
}
