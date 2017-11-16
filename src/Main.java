import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.Image;

class Main extends JPanel implements KeyListener {


	private int dx = 100;
	private int dy = 100;

	
	private int heroStep = 20;
	private Image heroImage;

	public Main() {

		// Instantiate JPanel and methods
		addKeyListener(this);
		setPreferredSize(new Dimension(800, 800));
		setBackground(Color.BLACK);

	}

	// I do not know what it does but seems to be essential
	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

	public void paintComponent(Graphics g) {

		ImageIcon imageObj = new ImageIcon("images/pacman.jpg");
		heroImage = imageObj.getImage();

		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(heroImage, dx, dy, this);

		g2d.setStroke(new BasicStroke(2));
		g2d.setPaint(Color.white);

		// Loop Lines
		generateDungeon(g2d);

		// between points x1,y1,x2,y2

		// between points x1,y1,x2,y2
		g2d.drawRect(120, 20, 100, 100);

	}

	public void generateDungeon(Graphics2D g2d) {

		g2d.drawLine(60, 200, 60, 100);
		g2d.drawLine(100, 0, 100, 100);
		g2d.drawLine(140, 200, 140, 100);

	}

	public void doMovement(char key) {

		// Stop the move on out boundaries - set 20 for the icon size
		int maxX = (getWidth() - 20);
		int maxY = (getHeight() - 20);


		// left
		if (key == 'a') {
			if (dx == 0) {
				return;
			}
			dx = dx - heroStep;
		}

		// right
		if (key == 'd') {
			if (dx >= maxX) {
				return;
			}
			dx = dx + heroStep;
		}

		// up
		if (key == 'w') {
			 if (dy == 0) {
			 return;
			 }
			dy = dy - heroStep;
		}

		// down
		if (key == 's') {
			if (dy >= maxY) {
				return;
			}
			dy = dy + 20;
		}

		System.out.println("dx: " + dx);
		System.out.println("dy: " + dy);

	}

	// Nothing
	public void keyTyped(KeyEvent e) {
	}

	// Nothing
	public void keyReleased(KeyEvent e) {
	}

	public void keyPressed(KeyEvent e) {

		char key = e.getKeyChar();

		System.out.println(key);

		doMovement(key);
		repaint();

	}

	public static void main(String[] s) {

		// Why Am I having twice setBackgroundblack ? It is when I start
		JFrame frame = new JFrame();
		frame.getContentPane().add(new Main());
		frame.setBackground(Color.black);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
