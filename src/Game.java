
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Game extends StateBasedGame {

    public static final String NAME = "Almost Always Night";
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;
    public static final boolean GAME_FULLSCREEN = true;
    public static final int MENU_STATE = 0;
    public static final int PLAY_STATE = 1;

    public Game(String GAME_NAME) {
        super(GAME_NAME);
        this.addState(new Menu(MENU_STATE));
        this.addState(new Play(PLAY_STATE));
    }

    public void initStatesList(GameContainer gc) throws SlickException {
        this.getState(MENU_STATE).init(gc, this);
        this.getState(PLAY_STATE).init(gc, this);
//      this.enterState(MENU_STATE);
        this.enterState(PLAY_STATE);
        
    }

    public static void main(String[] args) {
        AppGameContainer appgc;
        try {
            appgc = new AppGameContainer(new Game(NAME));
            appgc.setDisplayMode(WIDTH, HEIGHT, GAME_FULLSCREEN);
            appgc.start();
            appgc.setShowFPS(false);
            appgc.setVSync(true);
            appgc.setMouseGrabbed(true);
            appgc.setTargetFrameRate(60);
        } catch (SlickException e) {
            e.printStackTrace();
        }
        
       

    }
    
   public void enterState(String stateName){
       if(stateName == "PLAY_STATE"){
           this.enterState(PLAY_STATE);
       }
        
    }

}
