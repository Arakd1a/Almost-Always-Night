
import java.awt.Rectangle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Tile extends Thing {

    int type;
    int width = 16;
    int height = 16;

    public Tile(int x, int y, int type) throws SlickException {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void render(Graphics g) throws SlickException {
        if (type != 0) { //If its not a blank space render.
            g.drawRect(x, y, width, height);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }

    public Rectangle getBounds() {
        if (type != 0) { //If its not a blank space colide.
            return new Rectangle((int) x, (int) y, (int) width, (int) height);
        } else {
            return new Rectangle(0, 0, 0, 0);
        }

    }

}
