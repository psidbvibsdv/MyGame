package tile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.awt.Graphics2D;
import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {
    
GamePanel gp;
Tile[] tile;
int mapTileNum[][];

public TileManager(GamePanel gp){

    this.gp = gp;

    tile = new Tile[25];
    mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
    getTileImage();
    loadMap("/res/maps/map.txt");
}

public void getTileImage(){
    
    try {
        tile[0] = new Tile();
        tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall.png"));
        
        //left side of the circle bench
        tile[1] = new Tile();
        tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/circle1.png"));
        
        //right side of the circle bench
        tile[2] = new Tile();
        tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/circle2.png"));

        //left side of the road
        tile[3] = new Tile();
        tile[3].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/roadleft.png"));

        //right side of the road
        tile[4] = new Tile();
        tile[4].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/roadright.png"));

        //left side middle of the road
        tile[5] = new Tile();
        tile[5].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/roadmiddleleft.png"));

        //right side middle of the road
        tile[6] = new Tile();
        tile[6].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/roadmiddleright.png"));
    
        //plain road aka the roof of uni
        tile[7] = new Tile();
        tile[7].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/road.png"));

        //pipe
        tile[8] = new Tile();
        tile[8].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/pipe.png"));

        //grass
        tile[9] = new Tile();
        tile[9].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/grass.png"));

        tile[10] = new Tile();
        tile[10].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/vamkV.png"));

        tile[11] = new Tile();
        tile[11].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/vamkA.png"));

        tile[12] = new Tile();
        tile[12].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/vamkM.png"));

        tile[13] = new Tile();
        tile[13].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/vamkK.png"));

        tile[14] = new Tile();
        tile[14].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/glassbottom.png"));

        tile[15] = new Tile();
        tile[15].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/glasstop.png"));

        tile[16] = new Tile();
        tile[16].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/vamkWall.png"));

        tile[17] = new Tile();
        tile[17].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/glassSmall.png"));

        tile[18] = new Tile();
        tile[18].image = ImageIO.read(getClass().getResourceAsStream("/res/tiles/vamkorange.png"));

    } catch (IOException e) {
        e.printStackTrace();
    }

}

public void loadMap(String filePath){

try {
    
    InputStream is = getClass().getResourceAsStream(filePath);
    BufferedReader br = new BufferedReader(new InputStreamReader(is));

    int col = 0;
    int row = 0;

    while (col < gp.maxWorldCol && row < gp.maxWorldRow) {
        String line = br.readLine();
        
        while (col < gp.maxWorldCol) {
            
            String numbers[] = line.split(" ");
       
            int num = Integer.parseInt(numbers[col]);

            mapTileNum[col][row] = num;
            col++;
        }
        if (col == gp.maxWorldCol) {
            col = 0;
            row++;

        }
    
    }
    br.close();
} catch (Exception e) {

}

}

public void draw(Graphics2D g2){
    g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[1].image, 48, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[2].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[3].image, 48, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[4].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[5].image, 48, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[6].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[7].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[8].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[9].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[10].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[11].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[12].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[13].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[14].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[15].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[16].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[17].image, 96, 0, gp.tileSize, gp.tileSize, null);
    g2.drawImage(tile[18].image, 96, 0, gp.tileSize, gp.tileSize, null);

    int worldCol = 0;
int worldRow = 0; 

while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow){

    int tileNum = mapTileNum[worldCol][worldRow];

    int worldX = worldCol * gp.tileSize;
    int worldY = worldRow * gp.tileSize;
    int screenX = worldX - gp.player.worldX + gp.player.screenX;
    int screenY = worldY - gp.player.worldY + gp.player.screenY;

    if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && 
    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
        
        g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
  
    }

    worldCol++;
   

    if(worldCol == gp.maxWorldCol){
    worldCol = 0;
    
    worldRow++;
    
            }
        }


    }

}
