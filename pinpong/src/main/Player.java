package main;

public class Player {
	GamePanel gp;
	public int points;
	public String name;
	public boolean winner;
	
	public Player(GamePanel gp, String name) {
		this.gp = gp;
		this.name = name;
		
		winner = false;
	}
	
	public void checkWinner() {
		if(points == 10) {
			winner = true;
		}
	}

}
