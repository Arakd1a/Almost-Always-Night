
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;

public class MenuText {

    public final String TITLE = "Almost Always Night";
    public final String PLAY = "Begin";
    public final String OPTIONS = "Options";
    public final String QUIT = "Quit";
    public final String CREDIT = "By Matt Barker";
    public final int OFFSET = 160;
    public int titleLength, playLength, optionsLength, quitLength, creditLength;
    public int selectedText;
    public Color playColor, optionsColor, quitColor;
    private final Color selectedColor = new Color(0, 124, 176);
    private final Color unselectedColor = new Color(Color.white);
    UnicodeFont mainFont;
    private AudioPlayer menuSelect, menuStart;

    public MenuText() throws SlickException {
//        menuSelect = new AudioPlayer("menuselect.wav", -10);
//        menuStart = new AudioPlayer("menustart.wav", -10);
        String fontPath = "main.ttf";
        mainFont = new UnicodeFont(fontPath, 38, false, false);
        mainFont.addAsciiGlyphs();
        mainFont.addGlyphs(400, 600);
        mainFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        mainFont.loadGlyphs();
        titleLength = mainFont.getWidth(TITLE);
        playLength = mainFont.getWidth(PLAY);
        optionsLength = mainFont.getWidth(OPTIONS);
        quitLength = mainFont.getWidth(QUIT);
        creditLength = mainFont.getWidth(CREDIT);
        playColor = new Color(Color.white);
        optionsColor = new Color(Color.white);
        quitColor = new Color(Color.white);
        selectedText = 1;
    }

    public void render(Graphics g) throws SlickException {
        mainFont.drawString((Game.WIDTH - titleLength) / 2 + OFFSET, 25, TITLE);
        mainFont.drawString((Game.WIDTH - playLength) / 2 + OFFSET, 100, PLAY, playColor);
        mainFont.drawString((Game.WIDTH - optionsLength) / 2 + OFFSET, 150, OPTIONS, optionsColor);
        mainFont.drawString((Game.WIDTH - quitLength) / 2 + OFFSET, 200, QUIT, quitColor);
        mainFont.drawString((Game.WIDTH - creditLength) / 2 + OFFSET, Game.HEIGHT - 25, CREDIT);
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        switch (selectedText) {
            case 1:
                playColor = selectedColor;
                optionsColor = unselectedColor;
                quitColor = unselectedColor;
                break;
            case 2:
                playColor = unselectedColor;
                optionsColor = selectedColor;
                quitColor = unselectedColor;
                break;
            case 3:
                playColor = unselectedColor;
                optionsColor = unselectedColor;
                quitColor = selectedColor;
                break;
        }
        if (gc.getInput().isKeyPressed(Input.KEY_W) & selectedText > 1) {
            selectedText--;
//            menuSelect.play();
        }
        if (gc.getInput().isKeyPressed(Input.KEY_S) & selectedText < 3) {
            selectedText++;
//            menuSelect.play();
        }
        if (gc.getInput().isKeyPressed(Input.KEY_SPACE) || gc.getInput().isKeyPressed(Input.KEY_ENTER)) {
//            menuStart.play();
            switch (selectedText) {
                case 1:
                    
                    
                  sbg.enterState(Game.PLAY_STATE);
                    break;
                case 2:
                    break;
                case 3:
                    System.exit(1);
                    break;
            }
        }
    }
}
