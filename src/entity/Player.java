package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.IOException;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

import main.KeyHandler;
import main.GamePanel;
public class Player extends Entity{
    
    GamePanel gp;
    KeyHandler kh;

    public final int screenX;
    public final int screenY;
    public Player(GamePanel gp, KeyHandler kh){

        this.gp = gp;
        this.kh = kh;

        screenX = gp.screenWidth/2 - gp.tileSize/2;
        screenY = gp.screenHeight/2 - gp.tileSize/2;

        solidArea = new Rectangle(0, 0, 32, 32);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){

        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    }
    public void getPlayerImage(){

        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/playerpng/masha_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/playerpng/masha_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/playerpng/masha_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/playerpng/masha_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/playerpng/masha_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/playerpng/masha_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/playerpng/masha_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/playerpng/masha_right_2.png"));
        
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){

        if(kh.upPressed == true || kh.downPressed == true || kh.leftPressed == true || kh.rightPressed == true){
            if(kh.upPressed == true){
                direction = "up";
            }
            else if(kh.downPressed == true){
                direction = "down";
            }
            else if(kh.leftPressed == true){
                direction = "left";
            }
            else if(kh.rightPressed == true){
                direction = "right";
            }

            //Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            //if collision is false, player can move
            if (collisionOn == false) { 
                switch (direction) {
                    case "up":
                    worldY -= speed;
                        break;
                    
                    case "down":
                    worldY += speed;
                        break;
        
                    case "left":
                    worldX -= speed;
                        break;
        
                    case "right":
                    worldX += speed;
                        break;
                
                }
        
            }
            spriteCounter++;
            if (spriteCounter > 15){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if(spriteNum == 2){
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }  
        }
    }

    public void draw(Graphics2D g2){

       // g2.setColor(Color.white);

        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = null;

        switch(direction){
            case "up":
            if(spriteNum == 1){
            image = up1;
            }
            if(spriteNum == 2){
                image = up2;
                }
            break;
            case "down":
            if(spriteNum == 1){
                image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                    }
            break;
            case "left":
            if(spriteNum == 1){
                image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                    }
            break;
            case "right":
            if(spriteNum == 1){
                image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                    }
            break;
        }
        g2.drawImage(image, screenX, screenY, gp.tileSize,gp.tileSize, null);
    }
}
