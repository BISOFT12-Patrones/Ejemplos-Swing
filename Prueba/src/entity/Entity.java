package entity;

import java.awt.image.BufferedImage;

//Esta clase va a almacenar variables que se van usar para jugador, NPC, y bichos
public class Entity {

    public int worldX, worldY;
    public int speed;
    //Para cargar las imagenes que se utilizan
    public BufferedImage up1, up2, up3, down1, down2, down3, left1, left2, left3, right1, right2, right3;

    public String direction;
    //Estas dos variables son mias, para que se muestre una imagen cuando esten detenidos
    public String lastDirection = "down";
    public boolean movimiento = false;


    public int spriteCounter = 0;
    public int spriteNum = 1;
}
