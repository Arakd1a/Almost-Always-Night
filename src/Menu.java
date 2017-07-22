
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import org.newdawn.slick.*;

import org.newdawn.slick.state.*;

public class Menu extends BasicGameState {

    public final boolean IS_RAINING = true;
    public final int RAIN_DENSITY = 4;
    private BufferedImage spriteSheet = null;
    private BufferedImage background = null;
    private Image playerImage;
    private SpriteSheet playerSpriteSheet;
    private Animation playerFall;
    private Image backgroundImage;
    private Image buildingImage;
    private Image skylineImage;
    private boolean firstDraw = true;
    private float playerY = -2000;
    private float playerVelY = 0.5f;
    MenuText menuText;
    ArrayList<Rain> rainFront = new ArrayList<Rain>();
    ArrayList<Rain> rainBack = new ArrayList<Rain>();
    private AudioPlayer rainSound;
    private AudioPlayer titleMusic;
    Z_Dialogue1 dialogue;

    public Menu(int State) {

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        menuText = new MenuText();
        playerImage = new Image("playerfall.png");
        playerSpriteSheet = new SpriteSheet(playerImage, 48, 56);
        playerFall = new Animation();
        playerFall.setAutoUpdate(true);
        int tX = 0;
        int tY = 0;
        for (int row = 0; row < 1; row++) {
            tX = 0;
            for (int col = 0; col < 2; col++) {
                tX++;
                playerFall.addFrame(playerSpriteSheet.getSprite(tX, tY), 150);
               
            }
            tY++;
        }
//        rainSound = new AudioPlayer("rain.mp3", -25);
//        titleMusic = new AudioPlayer("titlecut.mp3", -10);
        backgroundImage = new Image("background.png");
        buildingImage = new Image("building.png");
        skylineImage = new Image("skyline.png");
        if (IS_RAINING) {
//        rainSound.loop();

            for (int i = 0; i < RAIN_DENSITY; i++) {
                rainFront.add(new Rain());
                rainBack.add(new Rain());
            }
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {

        g.drawImage(backgroundImage, 0, 0);
        for (int i = 0; i < rainBack.size(); i++) {
            rainBack.get(i).render(g);
        }
        g.drawImage(skylineImage, 0, 0);
        menuText.render(g);
        g.drawImage(buildingImage, 0, 0);
        playerFall.draw(110, playerY, 48 / 2, 56 / 2);

        for (int i = 0; i < rainFront.size(); i++) {
            rainFront.get(i).render(g);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        playerY += playerVelY;
        if (playerY < Game.HEIGHT) {

        }
        for (int i = 0; i < rainFront.size(); i++) {
            rainFront.get(i).update(gc, sbg, delta);
            rainBack.get(i).update(gc, sbg, delta);
        }
        if (firstDraw) {
           playMusic();
            firstDraw = false;
        }
        menuText.update(gc, sbg, delta);

       
    }

    public int getID() {
        return 0;
    }

    public BufferedImage getSpriteSheet() {
        return spriteSheet;
    }

    public void playMusic() {
//      titleMusic.loop();
    }

    public void stopMusic() {
//       titleMusic.stop();
    }

    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            System.exit(0);
        }

    }

}
