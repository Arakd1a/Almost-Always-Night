
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

public class Z_Dialogue {

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

    public Z_Dialogue() throws SlickException {
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
        mainFont.drawString(LINE_MARGIN + 15, LINE_SPACING, firstLine, Color.white);
        mainFont.drawString(LINE_MARGIN, LINE_SPACING + 30, secondLine, Color.white);
        mainFont.drawString(LINE_MARGIN, LINE_SPACING + 30 + 30, thirdLine, Color.white);

        g.drawLine(LINE_MARGIN, 200, lengthOfLine, 200);

        mainFont.drawString(LINE_MARGIN, 250, Integer.toString(lengthOfLine), Color.white);
        if (waiting) {
            mainFont.drawString(20, 200 + 30 + 30, "Press any key", Color.white);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        gc.setShowFPS(false);
   

        counter += delta;
        fullString = dialogueTexts.get(xmlI);
        letters = fullString.toCharArray();
        words = fullString.split("\\s+");
        

        

        if (waiting) {
            if (gc.getInput().isKeyPressed(Input.KEY_SPACE)) {
                firstLine = "";
                secondLine = "";
                thirdLine = "";
                lineNumber = 0;
                wordNumber = 0;
                if (i < letters.length) {
                    waiting = false;
                    return;
                }
                xmlI++;
                i = 0;
                fullString = dialogueTexts.get(xmlI);
                waiting = false;
            }
        }
        if (!waiting) {
            
    

            System.out.println("Currennt word is: " + words[wordNumber]);
            System.out.println("Line length before: " + lengthOfLine + " of a total of " + LINE_MAX_LENGTH);
            System.out.println("Line length after: " + lengthOfLine + " of a total of " + LINE_MAX_LENGTH);

            switch (lineNumber) {
                case 0:
                    lengthOfLine = mainFont.getWidth(firstLine) + mainFont.getWidth(words[wordNumber]);
                    System.out.println(words[wordNumber]);
                    break;
                case 1:
                    lengthOfLine = mainFont.getWidth(secondLine) + mainFont.getWidth(words[wordNumber]);
                    System.out.println(words[wordNumber]);
                    break;
                case 2:
                    lengthOfLine = mainFont.getWidth(thirdLine) + mainFont.getWidth(words[wordNumber]);
                    System.out.println(words[wordNumber]);
                    break;
            }
            

            
            if (counter >= READ_SPEED & i < letters.length) {
                if (lengthOfLine >= LINE_MAX_LENGTH && lineNumber == 2 || i == letters.length - 1) {
                    waiting = true;
                }
                if (lengthOfLine >= LINE_MAX_LENGTH && lineNumber == 1) {
                    lineNumber = 2;
                }
                if (lengthOfLine >= LINE_MAX_LENGTH && lineNumber == 0) {
                    lineNumber = 1;
                }
                switch (lineNumber) {
                    case 0:
                        firstLine += letters[i];
                        break;
                    case 1:
                        secondLine += letters[i];
                        break;
                    case 2:
                        thirdLine += letters[i];
                        break;
                }
                readBlip.play();
                                            tokenChar = String.valueOf(letters[i]);
        if (tokenChar.matches("\\s+")) {
            if (wordNumber < words.length) {
                wordNumber++;
            }
        }

                currentWord = words[wordNumber];
                i++;
                counter = 0;
                System.out.print(lengthOfLine);
            }
        }
    }
}
