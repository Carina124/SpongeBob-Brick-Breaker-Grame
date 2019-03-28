import java.awt.*;
import java.util.ArrayList;

public class SpongeBobCollision {
    private static int rec1Left;
    private static int rec1Right;
    private static int spongeBobLeft;
    private static int spongeBobRight;
    private static int rec1Top;
    private static int rec1Bottom;
    private static int spongeBobTop;
    private static int spongeBobBottom;
    private static int deltaLeft;
    private static int deltaRight;
    private static int deltaTop;
    private static int deltaBottom;
    private static int deltaHorizontal;
    private static int deltaVertical;
    private static int deltaDirection;
    public static ArrayList<SpriteBricks> blocksList = new ArrayList<>();

    public static int spongeBobCollison(SpongeBob bob, SpriteBricks brick1) {
        Rectangle pattyRec = bob.rec1;
        Rectangle rec1 = brick1.rec1;

        if (pattyRec.intersects(rec1)) {
            rec1Left = rec1.x;
            rec1Right = rec1.x + rec1.width;
            spongeBobLeft = pattyRec.x;
            spongeBobRight = pattyRec.x + pattyRec.width;
            rec1Top = rec1.y;
            rec1Bottom = rec1.y + rec1.height;
            spongeBobTop = pattyRec.y;
            spongeBobBottom = pattyRec.y + pattyRec.height;

            deltaLeft = (rec1Right - spongeBobLeft) * (rec1Right - spongeBobLeft);
            deltaRight = (rec1Left - spongeBobRight) * (rec1Left - spongeBobRight);
            deltaTop = (rec1Top - spongeBobBottom) * (rec1Top - spongeBobBottom);
            deltaBottom = (rec1Bottom - spongeBobTop) * (rec1Bottom - spongeBobTop);

            deltaHorizontal = (deltaLeft < deltaRight) ? deltaLeft : deltaRight;
            deltaVertical = (deltaTop < deltaBottom) ? deltaTop : deltaBottom;
            deltaDirection = (deltaHorizontal < deltaVertical) ? 1 : -1;

            RainbowReefMain.bob.collisionDirection = deltaDirection;
            return deltaDirection;
        }
        return 0;
    }

    public static void spongeBobCollisonWithKatch(SpongeBob bob) {
        Rectangle rec1 = bob.rec1;
        Rectangle rec2 = RainbowReefMain.katch1.rec1;
        int x1 = rec1.x + rec1.width / 2;
        int x2 = rec2.x;

        if (rec1.intersects(rec2)) {
            int pipeWidth = RainbowReefMain.pipeSizeX;
            double currentVX = bob.getVx();
            if (x1 > x2 && x1 < (x2 + (pipeWidth / 2))) {
                if (bob.getVx() > 0) {
                    bob.setVx(-currentVX);
                }
                bob.setX(bob.getX() - 20);
                bob.setVy(-bob.getVy() - 0.1);
                bob.setY(bob.getY() - 20);
            }
            if ((x1 >= x2 + (pipeWidth / 2)) && (x1 < (x2 + pipeWidth))) {
                if (bob.getVx() < 0) {
                    bob.setVx(-currentVX);
                }
                bob.setX(bob.getX() + 20);
                bob.setVy(-bob.getVy() - 0.1);
                bob.setY(bob.getY() - 20);
            }
        }
    }

    public static int ghostGaryCollision(Gary gary, SpriteBricks brick1) {
        Rectangle gPattyRec = gary.rec1;
        Rectangle rec1 = brick1.rec1;

        if (gPattyRec.intersects(rec1)) {
            RainbowReefMain.ghostGary.collisionDirection = 0;
            return 100;
        }
        return 0;
    }

    public static void garyCollisionWithKatch(Gary patRick) {
        Rectangle rec1 = RainbowReefMain.ghostGary.rec1;
        Rectangle rec2 = RainbowReefMain.katch1.rec1;
        int x1 = rec1.x + rec1.width / 2;
        int x2 = rec2.x;

        if (rec1.intersects(rec2)) {
            int pipeWidth = RainbowReefMain.pipeSizeX;
            double currentVX = patRick.getVx();
            if (x1 > x2 && x1 < (x2 + (pipeWidth / 2))) {
                if (patRick.getVx() > 0) {
                    patRick.setVx(-currentVX);
                }
                patRick.setX(patRick.getX() - 20);
                patRick.setVy(-patRick.getVy() - 0.1);
                patRick.setY(patRick.getY() - 20);
            }
            if ((x1 >= x2 + (pipeWidth / 2)) && (x1 < (x2 + pipeWidth))) {
                if (patRick.getVx() < 0) {
                    patRick.setVx(-currentVX);
                }
                patRick.setX(patRick.getX() + 20);
                patRick.setVy(-patRick.getVy() - 0.1);
                patRick.setY(patRick.getY() - 20);
            }
        }
    }

}
