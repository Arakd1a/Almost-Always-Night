
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class Z_Dialogue1 {

    private final float LINE_SPACING = 30;
    private final float LINE_MARGIN = 20;
    private final float LINE_MAX_LENGTH = Game.WIDTH - 100;
    private final float READ_SPEED = 50;
    private final float WIDTH = 0;
    private final float HEIGHT = 100;
    private final float X = 0;
    private final float Y = Game.HEIGHT - HEIGHT;
    private final Color BACKGROUND_COLOR = new Color(Color.black);
    private final Color TEXT_COLOR = new Color(Color.white);
    int counter;
    int i = 0;
    int xmlI = 0;
    int lineNumber = 0;
    boolean singleLine;
    boolean doubleLine;
    private String fullString = "";
    private String firstLine = "";
    private String secondLine = "";
    private String thirdLine = "";
    char[] letters;
    String[] words;
    String tokenChar;
    int wordNumber = 0;
    AudioPlayer readBlip;
    String currentWord;
    int lengthOfLine;
    boolean waiting = false;
    Document document;
    ArrayList<String> dialogueTexts = new ArrayList<>();
    XmlReader xmlReader;
    UnicodeFont mainFont;

    public Z_Dialogue1() throws SlickException {
        xmlReader = new XmlReader();
        dialogueTexts = xmlReader.getAllDialogueText();
        readBlip = new AudioPlayer("readBlip.wav", -30);
        String fontPath = "main.ttf";
        mainFont = new UnicodeFont(fontPath, 34, false, false);
        mainFont.addAsciiGlyphs();
        mainFont.addGlyphs(400, 600);
        mainFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
        mainFont.loadGlyphs();
        singleLine = true;
        currentWord = "";
        lengthOfLine = 0;
    }

    public void render(Graphics g) throws SlickException {
       
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
       

        }
    }

