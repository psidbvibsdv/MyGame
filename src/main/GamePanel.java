package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import javax.swing.plaf.DimensionUIResource;

import entity.Player;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{
    //SCREEN SETTINGS 
    final int initialTileSize = 16; //16x16px tile  
    final int scale = 3;
    
    public final int tileSize = initialTileSize * scale; //48x48px tile // scaling our tile for an appropriate size in a large resolution
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol;    // 768px
    public final int screenHeight = tileSize * maxScreenRow;   // 576px

    //FPS
    int FPS = 60;

    TileManager tileM = new TileManager(this);
    KeyHandler kh = new KeyHandler();
    Thread gameThread;
    public collisionChecker cChecker = new collisionChecker(this);
    public Player player = new Player(this, kh);

    //WORLD PARAMETERS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxScreenRow;


    public GamePanel(){

        this.setPreferredSize(new DimensionUIResource(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    public void startGameThread(){

        gameThread = new Thread(this);
        gameThread.start();
    }   

    @Override
    public void run() {
        double drawInterval = 1000000000/FPS; // 0.01666 seconds
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        double delta = 0; 

        while(gameThread !=null){
          //  System.out.println("The game is running");

          currentTime = System.nanoTime();
		
          delta += (currentTime - lastTime) / drawInterval;
          timer += (currentTime - lastTime);
          lastTime = currentTime;
          
          if(delta >= 1) {
              update();
              repaint();
              delta--;
              drawCount++;
              
          }
          
          if(timer >= 1000000000) {
              System.out.println("FPS:" + drawCount);
              drawCount = 0;
              timer = 0;
          }
          
      }     
    }

    public void update(){
        player.update();
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
       tileM.draw(g2);
       player.draw(g2);
        
        g2.dispose();
    }

}
