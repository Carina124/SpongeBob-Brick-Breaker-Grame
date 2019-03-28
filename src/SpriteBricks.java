import java.awt.image.BufferedImage;
import java.util.Observable;

public class SpriteBricks extends gameSprite {
    private int brickType;
    private int brickLifeCount;
    public static int currentLevel = 0;
    private boolean breakableTruth = true;
    private int blockScore;

    public SpriteBricks(int x, int y, BufferedImage img) {
        super(x, y, img);
        this.brickLifeCount = 1;
        this.blockScore = 0;
    }

    public void setBlockScore(int blockScore) {
        this.blockScore = blockScore;
    }

    public static int getCurrentLevel() {
        return currentLevel;
    }

    public static void setCurrentLevel(int currentLevel) {
        SpriteBricks.currentLevel = currentLevel;
    }

    public void setBrickType(int brickType) {
        this.brickType = brickType;
    }

    public void setBrickLifeCount(int brickLifeCount) {
        this.brickLifeCount = brickLifeCount;
    }

    public void setBreakableTruth(boolean breakableTruth) {
        this.breakableTruth = breakableTruth;
    }

    @Override
    public void update(Observable observable, Object o) {
        rec1.x = x;
        rec1.y = y;

        collisionDirection = SpongeBobCollision.spongeBobCollison(RainbowReefMain.bob, this);
        collisionHelper(collisionDirection, RainbowReefMain.bob);

        collisionDirection2 = SpongeBobCollision.ghostGaryCollision(RainbowReefMain.ghostGary, this);
        ghostCollisionHelper(collisionDirection2, RainbowReefMain.ghostGary);
    }

    private void collisionHelper(int collisionDirection, SpongeBob patty) {
        if (collisionDirection != 0) {
            brickLifeCount--;
        }
        if (collisionDirection != 0 && breakableTruth && brickLifeCount == 0) {

            RainbowReefMain.geobv.deleteObserver(this);
            SpongeBobCollision.blocksList.remove(this);
            RainbowReefMain.brickPoints += this.blockScore;
            if (this.brickType == 5 || this.brickType == 6) {
                RainbowReefMain.bigLegCount--;
                if (RainbowReefMain.bigLegCount == 0 && getCurrentLevel() == 1) {
                    MapLayout.deleteMap();
                    MapLayout.makeWeedMaps(MapLayout.weedMaps2);
                    setCurrentLevel(2);
                    RainbowReefMain.bob.setX(RainbowReefMain.katch1.getX());
                    RainbowReefMain.bob.setY(RainbowReefMain.screenHeight - 500);
                    RainbowReefMain.bob.setVy(0);
                    RainbowReefMain.geobv.deleteObserver(RainbowReefMain.ghostGary);
                    RainbowReefMain.ghostGary.setX(-1050);
                    RainbowReefMain.ghostGary.setY(-1050);
                }
                if (RainbowReefMain.bigLegCount == 0 && getCurrentLevel() == 2) {
                    MapLayout.deleteMap();
                    setCurrentLevel(3);
                    RainbowReefMain.bob.setX(-1500);
                    RainbowReefMain.bob.setY(-1500);
                    RainbowReefMain.bob.setVy(0);
                    RainbowReefMain.bob.setLifeCount(0);
                    RainbowReefMain.bob.setImg(RainbowReefMain.patrick8);
                    RainbowReefMain.geobv.deleteObserver(RainbowReefMain.bob);
                    RainbowReefMain.geobv.deleteObserver(RainbowReefMain.ghostGary);
                    RainbowReefMain.ghostGary.setX(-1050);
                    RainbowReefMain.ghostGary.setY(-1050);
                }
            }
            if (this.brickType == 10) {
                RainbowReefMain.geobv.addObserver(RainbowReefMain.ghostGary);
                RainbowReefMain.ghostGary.setImg(RainbowReefMain.gary);
                RainbowReefMain.ghostGary.setX(200);
                RainbowReefMain.ghostGary.setY(500);
                RainbowReefMain.ghostGary.setVx(1);
                RainbowReefMain.ghostGary.setVy(1);
                RainbowReefMain.ghostGary.setLifeCount(1);
            }
        }
        if (collisionDirection != 0 && this.brickType == 9) {
            patty.setLifeCount(patty.getLifeCount() + 1);
        }
    }

    private void ghostCollisionHelper(int collisionDirection2, Gary patty) {
        if (collisionDirection2 != 0) {
            RainbowReefMain.geobv.deleteObserver(this);
            SpongeBobCollision.blocksList.remove(this);
            RainbowReefMain.brickPoints += this.blockScore;
            if (this.brickType == 5 || this.brickType == 6) {
                RainbowReefMain.bigLegCount--;
                if (RainbowReefMain.bigLegCount == 0 && getCurrentLevel() == 1) {
                    MapLayout.deleteMap();
                    MapLayout.makeWeedMaps(MapLayout.weedMaps2);
                    setCurrentLevel(2);
                    RainbowReefMain.bob.setX(RainbowReefMain.katch1.getX());
                    RainbowReefMain.bob.setY(RainbowReefMain.screenHeight - 500);
                    RainbowReefMain.bob.setVy(0);
                    RainbowReefMain.geobv.deleteObserver(RainbowReefMain.ghostGary);
                    RainbowReefMain.ghostGary.setX(-1050);
                    RainbowReefMain.ghostGary.setY(-1050);
                }
                if (RainbowReefMain.bigLegCount == 0 && getCurrentLevel() == 2) {
                    MapLayout.deleteMap();
                    setCurrentLevel(3);
                    RainbowReefMain.bob.setX(-1500);
                    RainbowReefMain.bob.setY(-1500);
                    RainbowReefMain.bob.setVy(0);
                    RainbowReefMain.bob.setLifeCount(0);
                    RainbowReefMain.bob.setImg(RainbowReefMain.patrick8);
                    RainbowReefMain.geobv.deleteObserver(RainbowReefMain.bob);
                    RainbowReefMain.geobv.deleteObserver(RainbowReefMain.ghostGary);
                    RainbowReefMain.ghostGary.setX(-1050);
                    RainbowReefMain.ghostGary.setY(-1050);
                }
            }
        }
        if (collisionDirection2 != 0 && this.brickType == 9) {
            patty.setLifeCount(patty.getLifeCount() + 1);
        }
    }
}
