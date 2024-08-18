package objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;

import main.GamePanel;

public class Ball extends Object{
	GamePanel gp;
	
	public Ball(GamePanel gp) {
		this.gp = gp;
		
		radius = 20;
		angle = Math.toRadians(130);
		
		defaultSettings();
	}
	
	
	public void defaultSettings(){
		x = gp.screenWidth/2 - radius;
		y = 0;
		speed = 10;
		maxBounceAngle = Math.toRadians(60);
		System.out.println(gp.wall1.y);
	}
	
	public void update() {
		x += Math.cos(angle) * speed;
		y -= Math.sin(angle) * speed;
		
		checkCollision();
		
		if(x < 0) { //hits left wall
			angle = Math.toRadians(50);
			defaultSettings();
			gp.wall1.defaultSettings();
			gp.wall2.defaultSettings();
			gp.player2.points += 1;
		}else if(x + radius > gp.screenWidth){ //hits right wall
			angle = Math.toRadians(130);
			defaultSettings();
			gp.wall1.defaultSettings();
			gp.wall2.defaultSettings();
			gp.player1.points += 1;
		}
		
		if(y < 0) { //hits top wall
			y = 0;
			angle = (Math.PI * 2) - angle;
		}else if(y + radius > gp.screenHeight) { //hits bottom wall
			y = gp.screenHeight - radius;
			angle = (Math.PI * 2) - angle;
		}
		
	}
	
	public void checkCollision() {
		boolean collisionOn = false;
		//right and left side rectangle
		if (x <= gp.wall1.x + gp.wall1.width && y >= gp.wall1.y && y <= gp.wall1.y + gp.wall1.height) {
            final int relativeIntersectY = (int) ((gp.wall1.y + (gp.wall1.height / 2)) - y);
            final double normalizedRelativeIntersectY = (double) relativeIntersectY / (gp.wall1.height / 2);
            final double bounceAngle = normalizedRelativeIntersectY * maxBounceAngle;

            x = gp.wall1.x + gp.wall1.width;
            angle = Math.PI - angle + bounceAngle;
            
            collisionOn = true;

        } else if (x + radius >= gp.wall2.x && y >= gp.wall2.y && y <= gp.wall2.y + gp.wall2.height) {
            final int relativeIntersectY = (int) ((gp.wall2.y + (gp.wall2.height / 2)) - y);
            final double normalizedRelativeIntersectY = (double) relativeIntersectY / (gp.wall2.height / 2);
            final double bounceAngle = normalizedRelativeIntersectY * maxBounceAngle;

            x = gp.wall2.x - radius;
            angle = Math.PI - angle + bounceAngle;
            
            collisionOn = true;
        }
		
		//top rectangle
		if (y + radius >= gp.wall1.y && y - radius <= gp.wall1.y + gp.wall1.height && x >= gp.wall1.x && x <= gp.wall1.x + gp.wall1.width) {
            angle = -angle; 
            collisionOn = true;
        } else if (y + radius >= gp.wall2.y && y - radius <= gp.wall2.y + gp.wall2.height && x >= gp.wall2.x && x <= gp.wall2.x + gp.wall2.width) {
            angle = -angle;
            collisionOn = true;
        }
		
		if(collisionOn) {
			gp.playSFX(0);
		}
	}
	
	public void draw(Graphics2D g2) {
		g2.setColor(Color.white);
		Ellipse2D.Double circle = new Ellipse2D.Double(x, y, radius, radius);
		g2.fill(circle);
	}
}
