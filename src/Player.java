import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends Rectangle{

	public int spd = 4;
	public int GAMEWIDTH = 480, GAMEHEIGHT = 480;
	public boolean right, up, down, left;
	
	public Player(int x, int y) {
		super(x, y, 32, 32);
	}
	
	public void tick() {
		if(right) {
			x += spd;	
			if(x > GAMEWIDTH + width) {
				x = 0 - width;
			}
		}else if(left) {
			x -= spd;
			if(x < 0 - width) {
				x = GAMEWIDTH + width;
			}
		}
		
		if(up) {
			y -= spd;
			if(y < 0 - height) {
				y = GAMEHEIGHT+ height;
			}
		}else if(down) {
			y += spd;
			if(y > GAMEHEIGHT+ height) {
				y = 0 - height;
			}
		}
	}
	
	public void render(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, width, height);
	}
	
}
