
import java.awt.Rectangle;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/* Abstract class for all things that need to be drawn
 * 
 */

/**
 *
 * @author Matt
 */
public abstract class Thing {
    
   int x, y, width, height;
   
    public Thing()throws SlickException {  }

    public abstract void render(Graphics g) throws SlickException;
    public abstract void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException;

 
    public Rectangle getBounds() {
        return new Rectangle(x,  y, width, height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    
}
