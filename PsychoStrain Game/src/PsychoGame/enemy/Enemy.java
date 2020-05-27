//Enemy.java
package PsychoGame.enemy;

import PsychoGame.Engine;
import PsychoGame.gameobject.AnimatedObject;
import PsychoSystem.GameImage;
import PsychoSystem.Physics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimedes Diaz)
public abstract class Enemy extends AnimatedObject {

    protected enum Direction {
        BACKWARD, FORWARD
    };

    protected enum State {
        WAITING, WALKING, JUMPING, DEAD, ATTACKING
    };
    protected Direction direction;
    protected State enemyState;
    protected long hittingTme;
    protected long inmunityTime;
    protected boolean timeToHit = true;
    protected boolean canBeHit = true;
    protected boolean activeOnScreen = true;
    protected boolean alive = true;
    protected int local = 0;
    protected int totalHP = 0;
    protected int actualHP = 0;
    protected int spriteActual = 0;
    protected int[] enemySpr;
    protected int screenXposition;
    protected int damagePerHit;
    protected int inactiveTime;
    protected int timeLoop;

    public Enemy(
            final String name,
            final String folder,
            final int xPosition,
            final int yPosition,
            final int damagePerHit,
            final int hp,
            final int timeInactive,
            final int timeLoop) {

        super(name, folder, xPosition, yPosition);
        this.screenXposition = xPosition;
        this.totalHP = actualHP = hp;
        this.direction = Direction.BACKWARD;
        this.enemyState = State.WAITING;
        this.objectSecuence = GameImage.loadFromFile(name, folder);
        this.alive = timeToHit = canBeHit = true;
        this.hittingTme = System.currentTimeMillis();
        this.inmunityTime = 1;
        this.damagePerHit = damagePerHit;
        this.inactiveTime = timeInactive;
        this.timeLoop = timeLoop;
        isActiveOnScreen();
    }

    public void setScreenXPosition(int x) {
        screenXposition = (int) getXposition() - x;
    }

    public int getScreenXPosition() {
        return screenXposition;
    }

    public State getState() {
        return enemyState;
    }

    public void setState(State d) {
        this.enemyState = d;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction d) {
        direction = d;
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

    /*
    Este metodo sera el setter del ActualHP pero, este metodo tiene que evaluar
    si el personaje principal sigue vivo, al evaluar si su actual HP sigue vivo.
    si sigue vivo, regresa un true si no un false.
     */
    public void setActualHP(final int hp) {
        if (hp < 0) {
            actualHP = 0;
            alive = false;
        } //Para que no rebase el limite de totalHP...
        else if (hp > getTotalHP()) {
            actualHP = getTotalHP();
            alive = true;
        } //de cualquier otra forma, se le asigna la nueva variable HP...
        else {
            actualHP = hp;
            alive = true;
        }
    }

    //Dependiendo si el wey puede ser pintado en la pantalla es lo que regresa true/false
    public boolean isActiveOnScreen(final int difx) {
        setScreenXPosition(difx);
        isActiveOnScreen();
        return getActiveOnScreen();
    }

    public boolean isAlive() {
        return alive;
    }

    public boolean getActiveOnScreen() {
        return activeOnScreen;
    }

    public void setStateEnemy() {
        if (activeOnScreen) {
            updateHittingTime();
            TimeToHit();
            enemyIntelligence();
        }
    }

    public BufferedImage getEnemySecuence() {
        Physics.gravity(this);
        setStateEnemy();
        return objectSecuence[local++ % objectSecuence.length];
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getScreenXPosition() + 4, (int) getYposition() + 2, 70, 45);
    }

    public int damageDeal(final AnimatedObject ao) {
        final Rectangle r = ao.getBounds();
        if (r.intersects(this.getBounds()) && canBeHit) {
            canBeHit = false;
            inmunityTime = System.currentTimeMillis();
            hittingTme = System.currentTimeMillis();

            if (r.getMaxX() > this.getBounds().getMinX()) {
                int auxleft = -Engine.level.canGoLeft(Engine.hacker, -20);
                Engine.level.moveVisibleMatrixLeft(auxleft);
                if (auxleft != 0) {
                    Engine.level.moveViewPortLeft(4);
                }

            }
            if (r.getMinX() < this.getBounds().getMaxX()) {
                int auxright = Engine.level.canGoRight(Engine.hacker, 20);
                Engine.level.moveVisibleMatrixRight(auxright);
                if (auxright != 0) {
                    Engine.level.moveViewPortRight(4);
                }

            }
            return damagePerHit;
        }
        return 0;
    }

    public int damagePerHit() {
        return damagePerHit;
    }

    public boolean TimeToHit() {
        if ((System.currentTimeMillis() - hittingTme) < inactiveTime) {
            return timeToHit = false;
        } else {
            return timeToHit = true;
        }
    }

    public void updateHittingTime() {
        if ((System.currentTimeMillis() - hittingTme > timeLoop)) {
            hittingTme = System.currentTimeMillis();
        }
        if (System.currentTimeMillis() - inmunityTime > 1500) {
            canBeHit = true;
        }
    }

    @Override
    public String toString() {
        return "";
    }

    private void isActiveOnScreen() {
        if (getScreenXPosition() + 70 < 0 || getScreenXPosition() > 768) {
            activeOnScreen = false;
        } else {
            activeOnScreen = true;
        }
    }

    public void enemyIntelligence() {
        if (timeToHit) {
            this.moveXposition(this.getVX());
            if (getVX() == 0) {
                jump();
            }
            if (this.getScreenXPosition() > Engine.hacker.getXposition()) {

                this.setVX(-6.0);
            } else {

                this.setVX(6.0);
            }

        } else {
            /* this.setVX(4.0);
                aki el enemigo no hace naaaada, igual y se le puede poner algo para ke hga
                no hace nada durante 2 segundos, se puede configurar el tiempo de cuanto tiempo ataca y cuanto tiempo
                esta pasivo... aki se pondra el metodo de disparo o algo pasivo del enemigo
             */
            // jump();
        }
    }

    private void jump() {
        if (isJumping()) {
            setVY(-13);
        }
    }

}
