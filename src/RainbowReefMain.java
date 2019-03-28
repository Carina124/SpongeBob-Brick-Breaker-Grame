import sun.audio.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class RainbowReefMain extends JPanel {
    public static final int screenWidth = 1040;
    public static final int screenHeight = 1360;
    public static final int pipeSizeX = 160;
    public static BufferedImage background1;
    public static BufferedImage katch;
    public static BufferedImage spongeBob;
    public static BufferedImage gary;
    public static BufferedImage patrick8;
    public static BufferedImage block1;
    public static BufferedImage block2;
    public static BufferedImage block3;
    public static BufferedImage block4;
    public static BufferedImage blockSplit;
    public static BufferedImage KrakenBig;
    public static BufferedImage KrakenSmall;
    public static BufferedImage UnbreakableShort;
    public static BufferedImage UnbreakableLong;
    public static BufferedImage PowerUpHealth;
    public static int spongeBobLocationX = (screenWidth / 2) - (pipeSizeX / 2);
    public static int spongeBobLocationY = screenHeight - 200;
    public static int spongeBobSpeed = 5;
    public static int smallBlockWidth = 40;
    public static int smallBlockHeight = 40;
    public static int bigLegCount;
    public static int brickPoints;
    public static boolean checkLevelBoolean = false;
    public static Katch katch1;
    public static SpongeBob bob;
    public static Gary ghostGary;
    public static JPanel container = new JPanel();
    public static JFrame jf = new JFrame();
    public static GameEventObservable geobv;

    {
        try {
            background1 = ImageIO.read(new File("./res/house.jpg"));
            katch = ImageIO.read(new File("./res/Katch.png"));
            spongeBob = ImageIO.read(new File("./res/SpongeBob.png"));
            gary = ImageIO.read(new File("./res/Gary.png"));
            patrick8 = ImageIO.read(new File("./res/Tank8.png"));
            block1 = ImageIO.read(new File("./res/Block1.png"));
            block2 = ImageIO.read(new File("./res/Block6.png"));
            block3 = ImageIO.read(new File("./res/Block3.png"));
            block4 = ImageIO.read(new File("./res/Block4.png"));
            blockSplit = ImageIO.read(new File("./res/Block_split.png"));
            KrakenBig = ImageIO.read(new File("./res/Bigleg.png"));
            KrakenSmall = ImageIO.read(new File("./res/Bigleg_small.png"));
            UnbreakableShort = ImageIO.read(new File("./res/Wall.png"));
            UnbreakableLong = ImageIO.read(new File("./res/Block_solid.png"));
            PowerUpHealth = ImageIO.read(new File("./res/Block_life.png"));
        } catch (IOException e) {
            System.out.println("Image not loaded.");
        }
    }
    public RainbowReefMain() {
        this.geobv = new GameEventObservable();
    }

    public static void main(String[] args) {
        Thread x;
        RainbowReefMain trex = new RainbowReefMain();
        trex.init();
        playMusic("./res/Wrecking_Ball.wav");
        try {
            while (true) {
                trex.geobv.setChanged();
                trex.geobv.notifyObservers();
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(RainbowReefMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void init() {
        katch1 = new Katch(spongeBobLocationX + 200, spongeBobLocationY, spongeBobSpeed);
        bob = new SpongeBob(spongeBob, 200, 500, 1, 1, 3);
        ghostGary = new Gary(patrick8, -15000, -15000, 0, 0, 1);
        MapLayout.makeWeedMaps(MapLayout.weedMaps1);
        SpriteBricks.setCurrentLevel(1);
        container.setLayout(new OverlayLayout(container));
        KatchControl peeps = new KatchControl(katch1, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        container.add(this);
        container.add(katch1);
        jf.add(container);
        jf.addKeyListener(peeps);
        this.geobv.addObserver(katch1);
        this.geobv.addObserver(bob);
        jf.setTitle("Spongebobs RainbowReef!");
        jf.setSize(screenWidth, screenHeight);
        jf.setResizable(false);
        jf.setLocationRelativeTo(null);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background1, 0, 0, screenWidth, screenHeight, null);
        katch1.paintComponent(g2);
        bob.paintComponent(g2);
        ghostGary.paintComponent(g2);

        for (int i = 0; i < SpongeBobCollision.blocksList.size(); i++) {
            SpongeBobCollision.blocksList.get(i).paintComponent(g2);
        }

        if (bob.getLifeCount() >= 0) {
            for (int i = 0; i < bob.getLifeCount(); i++) {
                g2.drawImage(spongeBob, 50 + i * 50, screenHeight - 115, 40, 40, null);
            }
            if (bob.getLifeCount() == 0 && checkLevelBoolean == false) {
                g.setFont(new Font("Arial", Font.BOLD, 72));
                g2.drawString("GAME OVER!", 300, 680);
                RainbowReefMain.geobv.deleteObserver(ghostGary);
                SpongeBobCollision.blocksList.remove(ghostGary);
            }
            if (SpriteBricks.getCurrentLevel() == 3) {
                g.setFont(new Font("Arial", Font.BOLD, 72));
                g2.drawString("You Win!!", 300, 680);
                g2.drawString("Score: " + brickPoints, 300, 780);
                checkLevelBoolean = true;
            }
        }
        if (checkLevelBoolean == false) {
            g.setFont(new Font("Arial", Font.PLAIN, 48));
            g2.drawString("Score: " + brickPoints, screenWidth - 325, screenHeight - 75);
        }
    }
    private static void playMusic(String filePath) {
        InputStream music;
        try {
            music = new FileInputStream(new File(filePath));
            AudioStream audios = new AudioStream(music);
            AudioPlayer.player.start(audios);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
        }
    }
}
