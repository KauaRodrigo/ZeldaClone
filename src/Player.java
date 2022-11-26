import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{

	public int spd = 4;
	public boolean right, up, down, left;
	
	public static int curAnimation = 0;
	public static int curFrames, targetFrames = 10;	
	
	public Player(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void tick() {
		boolean moved = false;
		if(right && World.isFree(x+spd, y)) {
			moved = true;
			x += spd;
		}else if(left && World.isFree(x-spd, y)) {
			moved = true;
			x -= spd;
		}		
		if(up && World.isFree(x, y-spd)) {
			moved = true;
			y -= spd;
		}else if(down && World.isFree(x, y+spd)) {
			moved = true;
			y += spd;
		}
		
		if(moved) {
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if(curAnimation == Spritesheet.player_front.length) {
					curAnimation = 0;
				}
			}
		}
		
		
	}
	
	public void render(Graphics g) {
//		g.setColor(Color.blue);
//		g.fillRect(x, y, width, height);
		g.drawImage(Spritesheet.player_front[curAnimation], x, y, 32, 32, null);
		
		
	}
	
}
