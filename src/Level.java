
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Level {

    ArrayList<Tile> tiles = new ArrayList<Tile>();
    int tileSize = 16;
    int[][] tileMap;
    int mapWidth;
    int mapHeight;
    Color none = new Color(0, 0, 0, 0);

    public Level(String levelName) throws SlickException {
        System.out.println("Building: ./res/levels/" + levelName + ".txt");
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader("./res/levels/" + levelName + ".txt"));
            mapWidth = Integer.parseInt(br.readLine());
            mapHeight = Integer.parseInt(br.readLine());
            tileMap = new int[mapHeight][mapWidth];
            String delimiter = " ";
            int sizeToken = 0;
            for (int row = 0; row < mapHeight; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delimiter);
                for (int col = 0; col < mapWidth; col++) {
                    tileMap[row][col] = Integer.parseInt(tokens[col]);
                    int currentToken = Integer.parseInt(tokens[col]);
                    tiles.add(new Tile(0 + col * tileSize, 0 + row * tileSize, currentToken));
                    sizeToken += tileSize;
                }
            }
            System.out.println("Loaded: ./res/levels/" + levelName + ".txt");
        } catch (IOException e) {
            Logger.getLogger(Level.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
            System.out.print("Could not read file:" + levelName);
        }

    }

    public void render(Graphics g) throws SlickException {

        for (int i = 0; i < tiles.size(); i++) {
            if (tiles.get(i).type == 1) {
                tiles.get(i).render(g);
            }
        }

    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

    }

    public int[][] getTileMap() {
        return tileMap;
    }

    public void getCol() {

    }

    public void getRow() {

    }

}
