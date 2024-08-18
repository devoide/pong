package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {
	GamePanel gp;
	Font pointLeft, pointRight, winner;
	
	public UI(GamePanel gp) {
		this.gp = gp;
			
		
		pointLeft = new Font("Arial", Font.PLAIN, 40);
		pointRight = new Font("Arial", Font.PLAIN, 40);
		winner = new Font("Arial", Font.PLAIN, 40);
		
	}
	
	public void drawBorder(Graphics2D g2) {
		g2.setColor(Color.gray);
        int y = 15;
        for (int i = 0; i < 31; i++) {
            g2.fillRect(gp.screenWidth / 2, y, 10, 25);
            y += 40;
        }
	}
	
	public void draw(Graphics2D g2) {
		drawBorder(g2);

        g2.setFont(pointLeft);
        g2.setColor(Color.white);
        g2.drawString(Integer.toString(gp.player1.points), gp.screenWidth/4 - 20, 100);

        g2.setFont(pointRight);
        g2.setColor(Color.white);
        g2.drawString(Integer.toString(gp.player2.points), (gp.screenWidth*3)/4 - 20, 100);

        if (gp.player1.winner) {
            drawWinner(g2, gp.player1.name);
            gp.stop();
        } else if (gp.player2.winner) {
            drawWinner(g2, gp.player2.name);
            gp.stop();
        }
	}
	
	private void drawWinner(Graphics2D g2, String winnerName) {
        String text = winnerName + " wins";
        int textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        g2.setFont(winner);
        g2.setColor(Color.white);
        g2.drawString(text, gp.screenWidth / 2 - textLength / 2, gp.screenHeight / 2);
    }
}
