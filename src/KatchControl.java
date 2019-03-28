import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;

public class KatchControl extends Observable implements KeyListener
{
	private final int right;
	private final int left;
	private Katch t1;
	
	public KatchControl(Katch t1, int left, int right)
	{
		this.t1 = t1;
		this.right = right;
		this.left = left;
	}
	
	@Override
	public void keyTyped(KeyEvent ke)
	{
	
	}
	
	@Override
	public void keyPressed(KeyEvent ke)
	{
		int keyPressed = ke.getKeyCode();
		if (keyPressed == right)
		{
			this.t1.toggleRightPressed();
		}
		if (keyPressed == left)
		{
			this.t1.toggleLeftPressed();
		}
	}
	
	@Override
	public void keyReleased(KeyEvent ke)
	{
		int keyReleased = ke.getKeyCode();
		if (keyReleased == right)
		{
			this.t1.unToggleRightPressed();
		}
		if (keyReleased == left)
		{
			this.t1.unToggleLeftPressed();
		}
	}
}
