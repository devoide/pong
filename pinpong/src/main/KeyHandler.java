package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	public boolean upPressedLeft, downPressedLeft, upPressedRight, downPressedRight;
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressedLeft = true;
		}
		if(code == KeyEvent.VK_S) {
			downPressedLeft = true;		
		}
		if(code == KeyEvent.VK_UP) {
			upPressedRight = true;
		}
		if(code == KeyEvent.VK_DOWN) {
			downPressedRight = true;
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		
		
		if(code == KeyEvent.VK_W) {
			upPressedLeft = false;
		}
		if(code == KeyEvent.VK_S) {
			downPressedLeft = false;		
		}
		if(code == KeyEvent.VK_UP) {
			upPressedRight = false;
		}
		if(code == KeyEvent.VK_DOWN) {
			downPressedRight = false;
		}
		
	}

}
