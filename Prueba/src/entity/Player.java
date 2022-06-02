package entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.security.Key;

public class Player extends Entity{

    GamePanel gp;
    KeyHandler KeyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.KeyH = keyH;

        screenX = gp.screenWidth/2 - (gp.titleSize / 2);
        screenY = gp.screenHeight/2 - (gp.titleSize / 2);

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        //Posicion inicial del personaje
        worldX = gp.titleSize * 23;
        worldY = gp.titleSize * 26;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("../player/ArribaDe.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("../player/ArribaIz.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("../player/ArribaCen.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("../player/abajoDe.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("../player/abajoIz.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("../player/abajoCen.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("../player/IzquierdaDe.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("../player/IzquierdaIz.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("../player/IzquierdaCen.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("../player/DerechaDe.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("../player/DerechaIz.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("../player/DerechaCen.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        //Para actualizar la posicion del jugador
        //Solo se va a cambiar si se presiona una tecla
        if (KeyH.upPressed == true || KeyH.downPressed == true || KeyH.leftPressed == true || KeyH.rightPressed == true) {
            if (KeyH.upPressed == true) {
                direction = "up";
                worldY -= speed;
            } else if (KeyH.downPressed == true) {
                direction = "down";
                worldY += speed;
            } else if (KeyH.leftPressed == true) {
                direction = "left";
                worldX -= speed;
            } else if (KeyH.rightPressed == true) {
                direction = "right";
                worldX += speed;
            }
            lastDirection = direction;
//Esto es para creear la animacion de que camina, esto se ejecuta 60 veces por segundo
            spriteCounter++;
            if (spriteCounter > 15) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            movimiento = true;
        } else {
            movimiento = false;
        }


    }

    public void draw(Graphics2D g2) {
      /*  //Dar color
        g2.setColor(Color.white);

        //Para crear un objeto
        g2.fillRect(x, y, gp.titleSize, gp.titleSize);*/
        BufferedImage image = null;

        if (movimiento) {
            switch (direction) {
                case "up":

                    if (spriteNum == 1) {
                        image = up1;
                    }
                    if (spriteNum == 2) {
                        image = up2;
                    }
                    break;
                case "down":

                    if (spriteNum == 1) {
                        image = down1;
                    }
                    if (spriteNum == 2) {
                        image = down2;
                    }
                    break;
                case "left":

                    if (spriteNum == 1) {
                        image = left1;
                    }
                    if (spriteNum == 2) {
                        image = left2;
                    }
                    break;
                case "right":

                    if (spriteNum == 1) {
                        image = right1;
                    }
                    if (spriteNum == 2) {
                        image = right2;
                    }
                    break;
            }
        } else {
            switch (lastDirection) {
                case "up":
                    image = up3;
                    break;
                case "down":
                    image = down3;
                    break;
                case "left":
                    image = left3;
                    break;
                case "right":
                    image = right3;
                    break;
            }
        }
        g2.drawImage(image, screenX, screenY, gp.titleSize, gp.titleSize, null); //Null para el observer

    }


}
