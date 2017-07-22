
import java.awt.Rectangle;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.state.StateBasedGame;

public class Player {
    Level level;
    private Image playerImage;
    private Image playerIdle;
    private SpriteSheet playerSpriteSheet;
    private Animation playerWalk;
    private float x, y;
    private float velX, velY;
    private boolean jumping;
    private boolean falling;
    private final int WIDTH = 48;
    private final int HEIGHT = 56;
    private final float WALKING_SPEED = 0.7f;
    private final float RUNNING_SPEED = 1.2f;
    private final float GRAVITY = 0.3f;
    private final float JUMPING_SPEED = 0.6f;
    private final float TERMINAL_VEL = 0.3f;
    Input input = new Input(Game.HEIGHT);
    private AudioPlayer playerJump;

    public Player(Level level) throws SlickException {
       
        y = Game.HEIGHT - HEIGHT - 25;
        playerImage = new Image("playerwalk.png");
        playerSpriteSheet = new SpriteSheet(playerImage, WIDTH, HEIGHT);
        playerIdle = new Image("playeridle.png");
        playerWalk = new Animation();
        playerWalk.setAutoUpdate(true);
        buildAnimations();
        playerJump = new AudioPlayer("playerjump.wav", -10);
    }

    public void render(Graphics g) throws SlickException {
        if (input.isKeyDown(Input.KEY_D)) {
            playerWalk.getCurrentFrame().getFlippedCopy(false, false).draw(x, y);

        } else if (input.isKeyDown(Input.KEY_A)) {
            playerWalk.getCurrentFrame().getFlippedCopy(true, false).draw(x, y);

        } else {
            playerIdle.draw(x, y);
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        playerWalk.update(delta);
        jumping = false;
        velX = WALKING_SPEED;

        if (input.isKeyDown(Input.KEY_LSHIFT)) {
            velX = RUNNING_SPEED;

        }

        if (gc.getInput().isKeyDown(Input.KEY_D)) {
            x += velX;

        }

        if (gc.getInput().isKeyDown(Input.KEY_A)) {
            x -= velX;

        }
        if(falling){
            y =- GRAVITY;
        }
        
//        int curCol =  level.getColTile(x);
//        int curRow = level.getRoyTile(y);

    }

    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, (int) WIDTH, (int) HEIGHT);
    }

    public void buildAnimations() {
        int tX = 0;
        int tY = 0;
        for (int row = 0; row < 1; row++) {
            tX = 0;
            for (int col = 0; col < 2; col++) {
                tX++;
                playerWalk.addFrame(playerSpriteSheet.getSprite(tX, tY), 150);
                
            }
            tY++;
        }
    }
}
