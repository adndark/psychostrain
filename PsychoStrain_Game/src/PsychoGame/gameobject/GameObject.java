package PsychoGame.gameobject;

import PsychoSystem.GameImage;
import java.awt.image.BufferedImage;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimedes Diaz)
public abstract class GameObject {

    // Coordenadas en donde será pintado en la pantalla.
    protected double xPosition;
    protected double yPosition;
    //Nombre de la imagen
    protected String imageName;
    protected BufferedImage image;
    //Imagen del objeto, en caso de ser inanimado, solo es una imagen pequeña
    //En caso de ser animado, es la imagen que contiene todas las subimagenes
    //de la animación del objeto.

    public GameObject() {
    }

    public GameObject(final String s) {
        xPosition = 0;
        yPosition = 0;
        imageName = s;
        image = GameImage.loadImage(imageName);
    }

    public GameObject(final String image, final double xPosition,
            final double yPosition) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.imageName = image;
        this.image = GameImage.loadImage(imageName);
    }

    public double getXposition() {
        return xPosition;
    }

    public double getYposition() {
        return yPosition;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setXposition(double x) {
        xPosition = x;
    }

    //Recibe como parámetro la posicion en el eje y
    public void setYposition(double y) {
        yPosition = y;
    }

    //Para settear la imagen solo se la un string e intentara cargala apartir de ese nombre.
    public void setImage(final String image) {
        imageName = image;
        this.image = GameImage.loadImage(imageName);
    }
}
