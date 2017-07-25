
import java.awt.Rectangle;
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Player extends Thing {
    Random r = new Random();
    Level level;
    Input input;
    int x = 64, y = 64, velX, velY, lastX, lastY;
    int gravity, terminalVel;
    boolean visible, movable, onGround, hitHead, canJump;
    Rectangle bounds, boundsTop, boundsBottom, boundsLeft, boundsRight;

    public Player(Level level) throws SlickException {
        this.level = level;
        input = new Input(Game.HEIGHT);
        width = 16;
        height = 16;
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        lastX = x;
        lastY = y;
        pollInput(gc);
        onGround = checkCollision();
        applyGravity();
    }

    public void render(Graphics g) throws SlickException {
        Color previous = new Color(g.getColor());
        g.fillRect(x, y, width, height);
        g.setColor(Color.red);
//      g.drawRect(boundsTop.x, boundsTop.y, boundsTop.width, boundsTop.height);
//      g.drawRect(boundsBottom.x, boundsBottom.y, boundsBottom.width, boundsBottom.height);
//      g.drawRect(boundsLeft.x, boundsLeft.y, boundsLeft.width, boundsLeft.height);
//      g.drawRect(boundsRight.x, boundsRight.y, boundsRight.width, boundsRight.height);
        g.setColor(previous);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void pollInput(GameContainer gc) {
        if (input.isKeyDown(Input.KEY_LSHIFT)) {
        }
        if (gc.getInput().isKeyDown(Input.KEY_SPACE)) {
            if (!hitHead) {
                y--;
            }
        }
        if (gc.getInput().isKeyDown(Input.KEY_D)) {
            x++;
        }
        if (gc.getInput().isKeyDown(Input.KEY_A)) {
            x--;
        }
    }

    public boolean checkCollision() {
        onGround = false;
        hitHead = false;
        bounds = getBounds();
        boundsTop = getBoundsTop();
        boundsBottom = getBoundsBottom();
        boundsLeft = getBoundsLeft();
        boundsRight = getBoundsRight();
        for (int i = 0; i < level.tiles.size(); i++) {
            if (boundsTop.intersects(level.tiles.get(i).getBounds())) {
                hitHead = true;
                 y = lastY;
                System.out.println("top");
            }
            if (boundsBottom.intersects(level.tiles.get(i).getBounds())) {
                onGround = true;
                y = lastY;
                System.out.println("bot");
            }
            if (boundsLeft.intersects(level.tiles.get(i).getBounds())) {
                x = lastX;
                System.out.println("left");
            }
            if (boundsRight.intersects(level.tiles.get(i).getBounds())) {
                System.out.println("right");
                x = lastX;
            }
        }
        return onGround;
    }

    public void applyGravity() {
        if (!onGround) {
              y++;
        }
    }

    public Rectangle getBoundsTop() {
        return new Rectangle(x, y, width, 1);
    }

    public Rectangle getBoundsBottom() {
        return new Rectangle(x, y+height, width, 1);
    }

    public Rectangle getBoundsLeft() {
        return new Rectangle(x, y, 1, height);
    }

    public Rectangle getBoundsRight() {
        return new Rectangle(x+width, y, 1, height);
    }
    
    
}
