
import java.util.Random;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Rain extends GameObject {

    
    Color rainColor = new Color(208, 223, 244);
    Game game;
    Random r = new Random();
    float x, y;
    float rainSpeed = (float) 0.50;
    float rainWidth = 2;
    float rainHeight = 8;

    public Rain() {
        x = r.nextInt(Game.WIDTH);
        y = r.nextInt(Game.HEIGHT);
    }

    public void render(Graphics g) throws SlickException {
        g.setColor(rainColor);
        g.drawLine(x, y, x-10, y+15);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        y += rainSpeed;
        x -= rainSpeed /2 ;
        if (y >= Game.HEIGHT) {
            y = 0;
        }
        if (x <= 0) {
            x = Game.WIDTH;
        }
    }

}
