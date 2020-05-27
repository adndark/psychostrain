package PsychoGame;

import PsychoGame.gameobject.AnimatedObject;
import PsychoSystem.*;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Hacker extends AnimatedObject {

    public static enum Direction {
        BACKWARD, FORWARD
    };

    public static enum State {
        WAITING, WALKING, JUMPING, DUCKING, WALKINGBACK
    };

    private boolean isDucked;
    private BufferedImage[] hackerLegs;
    private Direction direction;
    private int actualHP;
    private int legsSecuencePositionX = 0;
    private int shot;
    private int totalHP;
    private int Xstart = 0;
    private State HackerState;
    private String name;

    public Hacker(final String name, final int posY, final int e) {
        super("SamusUpper.txt", "hacker", 340, posY);
        this.hackerLegs = GameImage.loadFromFile("SamusBottom.txt", "hacker");
        this.isDucked = false;
        this.name = name;
        this.totalHP = actualHP = 0;//tomaremos en cuenta 0 como maximo y 200 como minimo jejeje
        this.direction = Direction.FORWARD;
        this.shot = 0;
        setHackerState(e);
    }

    public void setState(State HackerState) {
        this.HackerState = HackerState;
    }

    public State getState() {
        return HackerState;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalHP() {
        return totalHP;
    }

    public int getActualHP() {
        return actualHP;
    }

    public void setTotalHP(int totalHP) {
        this.totalHP = totalHP;
    }

    public void setDirection(Direction direccion) {
        direction = direccion;
    }

    public Direction getDirection() {
        return direction;
    }

    //Metodo Junta las 2 imagenes del monito...
    public BufferedImage getImage() {
        checkState();
        if (HackerState == State.DUCKING) {
            return getImageSecuenceLegs();
        }
        final BufferedImage up = getActualImage();
        final BufferedImage down = getImageSecuenceLegs();
        final int width = Math.max(up.getWidth(), down.getWidth()); //Obtener cual imagen es la que mide mas de ancho
        final BufferedImage bufferedImage = new BufferedImage(width, (up.getHeight()
                + down.getHeight()) - 10, BufferedImage.TYPE_4BYTE_ABGR);
        final Graphics2D g = bufferedImage.createGraphics();
        g.drawImage(up, 0, 0, null); //Se dibuja la parte de arriba en la nueva imagen...
        g.drawImage(down, 33, up.getHeight() - 22, null); //Se dibuja la parte de abajo en la nueva imagen
        g.dispose(); //Graphics deja de referenciar a la imagen creada.
        g.finalize(); //Graphics se elimina ya que ha dejado de referenciar la imagen creada.
        up.flush(); //Se destruye la imagen temporal de la parte de arriba.
        down.flush(); //Se destruye la imagen temporal de la parte de abajo.
        return bufferedImage;
    }

    public void jump() {
        if (isJumping()) {
            if (isDucked) {
                // Brinca menos cuando esta agachado
                setVY(-15);
            } else {
                // Brinca mas parado
                setVY(-20);
            }
        }
    }

    @Override
    public Rectangle getBounds() {
        if (isDucked) {
            return new Rectangle((int) getXposition() + 31, (int) getYposition(), 25, 25);
        }
        if (this.direction == direction.FORWARD) {
            return new Rectangle((int) getXposition() + 48, (int) getYposition() + 27, 54, 84);
        }
        return new Rectangle((int) getXposition() + 20, (int) getYposition() + 27, 58, 84);
    }

    public void setActualHP(int hp) {
        this.actualHP = hp;
    }

    public boolean setFireImage(Point a) {
        if ((shot = shot % 20) % 2 == 0) {
            switch (Engine.getI()) {
                case 0:
                    Engine.setI(14);
                    break;
                case 1:
                    Engine.setI(15);
                    break;
                case 2:
                    Engine.setI(16);
                    break;
                case 3:
                    Engine.setI(17);
                    break;
                case 4:
                    Engine.setI(18);
                    break;
                case 5:
                    Engine.setI(19);
                    break;
                case 6:
                    Engine.setI(20);
                    break;
                case 7:
                    Engine.setI(21);
                    break;
                case 8:
                    Engine.setI(22);
                    break;
                case 9:
                    Engine.setI(23);
                    break;
                case 10:
                    Engine.setI(24);
                    break;
                case 11:
                    Engine.setI(25);
                    break;
                case 12:
                    Engine.setI(26);
                    break;
                case 13:
                    Engine.setI(27);
                    break;
            }
            return true;
        }
        return false;
    }

    private void checkState() {
        if (HackerState == State.DUCKING) {
            if (!isDucked) {
                setYposition(getYposition() + 83);
                isDucked = true;
            }
        } else {
            if (isDucked) {
                setYposition(getYposition() - 90);
                isDucked = false;
            }
        }
    }

    private void setHackerState(int e) {
        if (e == 3) {
            this.HackerState = State.DUCKING;
            this.isDucked = true;
        } else {
            this.HackerState = State.WAITING;
            this.isDucked = false;
        }
    }

    private BufferedImage getActualImage() {
        return getImageSecuence(Engine.getI());
    }

    private BufferedImage getImageSecuenceLegs() {

        System.out.println("Hacker state " + HackerState.toString());
        if (HackerState == HackerState.DUCKING) {
            if (direction == Direction.FORWARD) {
                if (!(Xstart >= 22 && Xstart < hackerLegs.length - 2)) {
                    Xstart = 22;
                }

                return hackerLegs[Xstart++];
            }
            if (direction == Direction.BACKWARD) {
                if (!(Xstart > 22 && Xstart < hackerLegs.length - 1)) {
                    Xstart = hackerLegs.length - 3;
                }
                return hackerLegs[Xstart--];
            }
        }

        if (direction == Direction.FORWARD) {
            if (HackerState == HackerState.WAITING) {
                return hackerLegs[0];
            }

            if (HackerState == HackerState.JUMPING) {

                return hackerLegs[29];

            }

            if (HackerState == HackerState.WALKING) {
                if (!(Xstart < 12)) {
                    Xstart = 2;
                }
                return hackerLegs[Xstart++];
            } else if (HackerState == HackerState.WALKINGBACK) {
                if (!(Xstart >= 2 && Xstart < 11)) {
                    Xstart = 11;
                }
                return hackerLegs[Xstart--];
            }
        }

        if (direction == Direction.BACKWARD) {
            if (HackerState == HackerState.WAITING) {
                return hackerLegs[1];
            }
            if (HackerState == HackerState.JUMPING) {
                return hackerLegs[30];
            }

            if (HackerState == HackerState.WALKING) {
                if (Xstart < 11 || Xstart > 20) {
                    Xstart = 12;
                }
                Xstart++;
            } else if (HackerState == HackerState.WALKINGBACK) {
                if (Xstart < 13 || Xstart > 22) {
                    Xstart = 21;
                }
                Xstart--;
            }
        }
        return hackerLegs[Xstart];
    }

}
