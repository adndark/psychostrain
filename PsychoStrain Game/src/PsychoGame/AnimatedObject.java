//AnimatedObject.java
package PsychoGame;

import PsychoSystem.GameImage;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimides Diaz)
public abstract class AnimatedObject extends GameObject {

    protected BufferedImage[] objectSecuence;
    protected int sprites;
            protected int SecuencePositionX;
    protected int[] SpritesPerImage;
    //Componentes de la velocidad (velocidad en "y" y en "x"
    protected double vy;
    protected double vx;
    protected boolean jumping = false;

    public AnimatedObject() {
        super();
        sprites = 1;
        vx = vy = 0;
    }

    public AnimatedObject(String Imagename, double Xposition, double Yposition, int sprites) {
        super(Imagename, Xposition, Yposition);
        this.sprites = sprites;
        vx = vy = 0;
    }

    public AnimatedObject(String name, String folder, double Xposition, double Yposition) {
        super(name, Xposition, Yposition);
        objectSecuence = GameImage.loadFromFile(name, folder);
        SpritesPerImage = GameImage.loadFromFileSprites(name);
        vx = vy = 0;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public BufferedImage getImageSecuence(int ImageNumber) {
        if (ImageNumber >= 0 && ImageNumber <= objectSecuence.length) {
            int Sprites = SpritesPerImage[ImageNumber];
            int SpriteWidth = (objectSecuence[ImageNumber].getWidth()) / Sprites;
            int x = (SecuencePositionX % Sprites) * SpriteWidth;
            SecuencePositionX++;
            return objectSecuence[ImageNumber].getSubimage(x, 0, SpriteWidth, objectSecuence[ImageNumber].getHeight());
        } else {
            System.out.println("error de getImageSecuence returning NULL");
            return null;
        }
    }

    public double getVX() {
        return vx;
    }

    public void setVX(double vx) {
        this.vx = vx;
    }

    public double getVY() {
        return vy;
    }

    public void setVY(double vy) {
        this.vy = vy;
    }

    //Recibe como parámetro cuanto se mueve en el eje x. Diferencial
    public void moveXposition(double dx) {
        int temp = 0;
        if (dx > 0) {
            temp = Engine.level.canGoRight(this, (int) dx);
        } else {
            temp = Engine.level.canGoLeft(this, (int) dx);
        }
        if (temp == 0) {
            setVX(0);
        }
        xPosition += temp;
    }

    //Recibe como parámetro cuanto se mueve en el eje y. Diferencial
    public void moveYposition(double dy) {
        int temp = 0;
        if (dy > 0) {
            temp = Engine.level.canGoDown(this, (int) dy);
        } else {
            temp = Engine.level.canGoUp(this, (int) dy);
        }
        if (temp == 0) {
            if (dy > 0) {
                setJumping(true);
            }
            setVY(0);
        } else {
            setJumping(false);
        }
        yPosition += temp;

    }

    public Rectangle getBounds() {
        return new Rectangle((int) getXposition(), (int) getYposition(), 35, 35);
    }
}
