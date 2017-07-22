
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState {

    Player player;
    Level level;
    

    public Play(int State) {

    }

    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        level = new Level("level1");
        player = new Player(level);
       

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        level.render(g);
        player.render(g);


    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        level.update(gc, sbg, delta);
        player.update(gc, sbg, delta);

    }

    public int getID() {
        return 1;
    }
}
