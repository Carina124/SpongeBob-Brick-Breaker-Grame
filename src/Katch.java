import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JComponent;

public class Katch extends JComponent implements Observer {
    public Rectangle rec1 = new Rectangle();
    private int x;
    private int y;
    private int vx;
    private BufferedImage img;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean collisionTruth = false;
    private int katchWidth = rec1.width;
    private int katchHeight = rec1.height;

    public Katch(int x, int y, int vx) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.img = RainbowReefMain.katch;
        this.rec1.setSize(img.getWidth(), img.getHeight());
    }

    public void toggleRightPressed() {
        this.RightPressed = true;
    }

    public void toggleLeftPressed() {
        this.LeftPressed = true;
    }

    public void unToggleRightPressed() {
        this.RightPressed = false;
    }

    public void unToggleLeftPressed() {
        this.LeftPressed = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(img, x, y, null);
        System.out.println(this.toString());
    }

    @Override
    public void update(Observable o, Object o1) {
        this.rec1.x = this.x;
        this.rec1.y = this.y;

        if (this.LeftPressed) {
            this.moveBackwards();
        }
        if (this.RightPressed) {
            this.moveForwards();
        }
        SpongeBobCollision.spongeBobCollisonWithKatch(RainbowReefMain.bob);
        SpongeBobCollision.garyCollisionWithKatch(RainbowReefMain.ghostGary);
        this.repaint();
    }

    private void moveBackwards() {
        x -= vx;
        checkBorder();
    }

    private void moveForwards() {
        x += vx;
        checkBorder();
    }

    private void checkBorder() {
        if (x < 40) {
            x = 40;
        }
        if (x >= RainbowReefMain.screenWidth - RainbowReefMain.pipeSizeX - 10 - 40) {
            x = RainbowReefMain.screenWidth - RainbowReefMain.pipeSizeX - 10 - 40;
        }
    }

    @Override
    public String toString() {
        if (collisionTruth) {
            return "true";
        } else {
            return "false";
        }
    }
}
