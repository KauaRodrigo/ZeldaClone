import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.List;

public class Inimigo extends Rectangle{

	public int spd = 2;
	public boolean right = true, up = false, down = false, left = false;

	public static boolean shoot = false;
	
	public static int curAnimation = 0;
	public static int curFrames, targetFrames = 20, frames = 0;
	
	public int dir = 1;
	
	public static List<Bullet> bullets = new ArrayList<Bullet>();
	
	public Inimigo(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void perseguirPlayer() {
		if(Game.player.y > y  && World.isFree(x, y+spd)) {
			
			y+=spd;
		}else if(Game.player.y < y  && World.isFree(x, y-spd)) {
			
			y-=spd;
		}
		if(Game.player.x > x && World.isFree(x+spd, y)) {
			
			x+=spd;			
		}else if(Game.player.x < x && World.isFree(x-spd, y)) {
			
			x-=spd;			
		}	
	}
	
	public void tick() {
		boolean moved = true;
		
		perseguirPlayer();		
		if(moved) {
			curFrames++;
			if(curFrames == targetFrames) {
				curFrames = 0;
				curAnimation++;
				if(curAnimation == Spritesheet.inimigo_front.length) {
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
		g.drawImage(Spritesheet.inimigo_front[curAnimation], x, y, 32, 32, null);
		
		for(int i = 0; i < bullets.size(); i++) {
			bullets.get(i).render(g);
		}
		
	}
	
}
