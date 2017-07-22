
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Level {

    int tileSize = 16;
    int[][] tileMap;
    int mapWidth;
    int mapHeight;
    Color none = new Color(0,0,0,0);

    public Level(String levelName) throws SlickException {
        System.out.println("Building: ./res/levels/" + levelName + ".txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("./res/levels/" + levelName + ".txt"));
            mapWidth = Integer.parseInt(br.readLine());
            mapHeight = Integer.parseInt(br.readLine());
            tileMap = new int[mapHeight][mapWidth];
            String delimiter = " ";
            for (int row = 0; row < mapHeight; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delimiter);
                for (int col = 0; col < mapWidth; col++) {
                    tileMap[row][col] = Integer.parseInt(tokens[col]);
                }
            }
            System.out.println("\n");
            System.out.println("Loaded: ./res/levels/" + levelName + ".txt");
        } catch (IOException e) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            System.out.print("Could not read file:" + levelName);
        }
    }

    public void render(Graphics g) throws SlickException {
        for(int row =0; row<mapHeight; row++){
            for(int col=0; col<mapWidth; col++){
                int tile = tileMap[row][col];
                if(tile==0){
                    g.setColor(none);
                }if(tile==1){
                    g.setColor(Color.white);
                }
                g.fillRect(0 + col * tileSize, 0 + row * tileSize, tileSize, tileSize);
            }
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }

}
