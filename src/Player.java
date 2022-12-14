import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Player extends Rectangle{

	public int spd = 4;
	public boolean right, up, down, left;

	public static boolean shoot = false;
	
	public static int curAnimation = 0;
	public static int curFrames, targetFrames = 10, frames = 0;
	
	public int dir = 1;
	
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	
	public Player(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void tick() {
		boolean moved = false;
		if(right && World.isFree(x+spd, y)) {
			moved = true;
			x += spd;
			dir = 1;
		}else if(left && World.isFree(x-spd, y)) {
			moved = true;
			x -= spd;
			dir = -1;
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
		}else {
			curAnimation = 0;
		}
		
		if(shoot) {
			frames++;
			if(frames == 10) {
				frames = 0;
				bullets.add(new Bullet(x, y, dir));
			}
		}
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).tick();
		}
		
	}
	
	public void render(Graphics g) {
//		g.setColor(Color.blue);
//		g.fillRect(x, y, width, height);
		g.drawImage(Spritesheet.player_front[curAnimation], x, y, 32, 32, null);
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
		
	}
	
}
