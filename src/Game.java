import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends Canvas implements Runnable, KeyListener{
	
	public static int WIDTH = 480, HEIGHT = 480;
	public Player player; 
	
	public static void main(String[] args) {
		Game game = new Game();
		JFrame frame = new JFrame();
		
		frame.add(game);
		frame.setTitle("Mini Zelda");
		
		frame.pack();
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	
		new Thread(game).start();
		
	}
	
	public Game() {
		this.addKeyListener(this);
		this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		player = new Player(0,0);
	}
	
	public void tick() {
		player.tick();
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		
		if(bs == null) {
			this.createBufferStrategy(3);			
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(new Color(250, 250, 250));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		player.render(g);
		
		bs.show();
		
	}
	
	public void run() {
		
		while(true) {
			tick();
			render();
			try {
				Thread.sleep(1000/60);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}		
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			player.left = false;
			player.up = false;
			player.down= false;
			player.right = true;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			player.right = false;
			player.up = false;
			player.down= false;
			player.left = true;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			player.left = false;
			player.right = false;
			player.down= false;
			player.up = true;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
			player.left = false;
			player.up = false;
			player.right= false;
			player.down = true;
		}		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
