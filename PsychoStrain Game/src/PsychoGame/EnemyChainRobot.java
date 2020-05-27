package PsychoGame;

import PsychoSystem.Physics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public abstract class EnemyChainRobot extends Enemy {

    private int local1;

    public EnemyChainRobot(
            final int xPosition,
            final int yPosition,
            final int damagePerHit,
            final int hp,
            final int timeInactive,
            final int timeLoop) {
        super("EnemyChainRobot.txt",
                "enemy/ChainRobot",
                xPosition,
                yPosition,
                damagePerHit,
                hp,
                timeInactive,
                timeLoop);
        this.local1 = 0;
        setDirection(direction.BACKWARD);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getScreenXPosition(), (int) getYposition() + 2, 70, 60);
    }

    @Override
    public void enemyIntelligence() {

        if (timeToHit) {
            this.moveXposition(this.getVX());
            if (getVX() == 0) {
                jump();
            }
            if (this.getScreenXPosition() > Engine.hacker.getXposition()) {

                this.setVX(-6.0);

                if (this.getDirection() != direction.BACKWARD) {
                    setDirection(direction.BACKWARD);

                }
                if (isJumping()) {
                    if (this.getState() != enemyState.WALKING) {
                        setState(enemyState.WALKING);
                    }
                }
            }

            if (this.getScreenXPosition() < Engine.hacker.getXposition()) {
                this.setVX(6.0);

                if (this.getDirection() != direction.FORWARD) {
                    setDirection(direction.FORWARD);

                }

                if (isJumping()) {
                    if (this.getState() != enemyState.WALKING) {
                        setState(enemyState.WALKING);
                    }
                }

            }

        } else {/* this.setVX(4.0);
            aki el enemigo no hace naaaada, igual y se le puede poner algo para ke hga
            no hace nada durante 2 segundos, se puede configurar el tiempo de cuanto tiempo ataca y cuanto tiempo
            esta pasivo... aki se pondra el metodo de disparo o algo pasivo del enemigo
             */
            if (this.getState() != enemyState.ATTACKING) {
                setState(enemyState.ATTACKING);
            }
        }
    }

    @Override
    public BufferedImage getEnemySecuence() {
        Physics.gravity(this);
        setStateEnemy();

        if (this.getState() == enemyState.WAITING) {
            local1 = 0;
        }

        if (getDirection() == direction.FORWARD) {
            if (this.getState() == enemyState.WALKING) {
                if (local1 < 1 || local1 > 15) {
                    local1 = 1;
                }
                local1++;
            }

            if (this.getState() == enemyState.ATTACKING) {
                if (local1 < 17 || local1 > 20) {
                    local1 = 17;
                }
                local1++;
            }
            if (this.getState() == enemyState.JUMPING) {
                if (local1 < 52 || local1 > 59) {
                    local1 = 52;
                }
                local1++;
            }
        }
        if (getDirection() == direction.BACKWARD) {
            if (this.getState() == enemyState.WALKING) {
                if (local1 < 22 || local1 > 36) {
                    local1 = 22;
                }
                local1++;
            }

            if (this.getState() == enemyState.ATTACKING) {
                if (local1 < 37 || local1 > 41) {
                    local1 = 38;
                }
                local1++;
            }
            if (this.getState() == enemyState.JUMPING) {
                if (local1 < 43 || local1 > 50) {
                    local1 = 43;
                }
                local1++;
            }

        }
        return objectSecuence[local1];
    }

    @Override
    public String toString() {
        String s = ">1";
        if (this.getYposition() < 10) {
            s += "00";

        } else if (this.getYposition() >= 10 && this.getYposition() < 100) {
            s += "0";
        }
        s += (int) this.getYposition();
        s += (int) this.getXposition();
        return s;
    }

    private void jump() {
        if (this.getState() != enemyState.JUMPING) {
            setState(enemyState.JUMPING);
        }
        if (isJumping()) {
            setVY(-13);
        }
    }
}
