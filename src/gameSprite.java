import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;

public class gameSprite extends JComponent implements Observer
{
	
	protected int x;
	protected int y;
	protected BufferedImage img;
	protected Rectangle rec1 = new Rectangle();
	//    protected boolean collisionTruth = false;
	protected int collisionDirection = 0;
	protected int collisionDirection2 = 0;

	//    private final int r = 3; //speed the radius of the class
//    private int vx;
//    private int vy;
	
	public gameSprite() {
		this.x = 0;
		this.y = 0;
		this.img = null;
	}
	
	public gameSprite(int x, int y, BufferedImage img)
	{
		this.x = x;
		this.y = y;
		this.img = img;
		this.rec1.setSize(img.getWidth(), img.getHeight());
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public void update(Observable observable, Object o)
	{
		rec1.x = x;
		rec1.y = y;

		this.repaint();
		collisionDirection = 0;
		collisionDirection2 = 0;
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		g.drawImage(img, x, y, null);
	}
	
	@Override
	public String toString()
	{
		if (collisionDirection != 0)
		{
			return "true";
		}
		else
		{
			return "false";
		}
	}
}
