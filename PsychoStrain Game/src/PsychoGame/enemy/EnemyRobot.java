package PsychoGame.enemy;

import PsychoGame.Engine;
import PsychoGame.enemy.Enemy;
import PsychoSystem.Physics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimides Diaz)
public class EnemyRobot extends Enemy {

    private int local1;

    public EnemyRobot(
            final int xPosition,
            final int yPosition,
            final int damagePerHit,
            final int hp,
            final int timeInactive,
            final int timeLoop) {
        super("EnemyRobot.txt",
                "enemy/Robot",
                xPosition,
                yPosition,
                damagePerHit,
                hp,
                timeInactive,
                timeLoop);
        local1 = 0;
        setDirection(direction.BACKWARD);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getScreenXPosition(), (int) getYposition() + 2, 70, 60);
    }

    private void jump() {
        if (this.getState() != enemyState.JUMPING) {
            setState(enemyState.JUMPING);
        }
        if (isJumping()) {
            setVY(-13);
        }
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
        } else {
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
                if (local1 < 1 || local1 > 9) {
                    local1 = 1;
                }
                local1++;
            }

            if (this.getState() == enemyState.ATTACKING) {
                if (local1 < 10 || local1 > 16) {
                    local1 = 10;
                }
                local1++;
            }
            if (this.getState() == enemyState.JUMPING) {
                if (local1 < 43 || local1 > 49) {
                    local1 = 43;
                }
                local1++;
            }

        }

        if (getDirection() == direction.BACKWARD) {
            if (this.getState() == enemyState.WALKING) {
                if (local1 < 18 || local1 > 26) {
                    local1 = 18;
                }
                local1++;
            }

            if (this.getState() == enemyState.ATTACKING) {
                if (local1 < 27 || local1 > 34) {
                    local1 = 27;
                }
                local1++;
            }
            if (this.getState() == enemyState.JUMPING) {
                if (local1 < 35 || local1 > 41) {
                    local1 = 35;
                }
                local1++;
            }

        }
        return objectSecuence[local1];
    }

    @Override
    public String toString() {
        String s = ">0";
        if (this.getYposition() < 10) {
            s += "00";
        } else if (this.getYposition() >= 10 && this.getYposition() < 100) {
            s += "0";
        }
        s += (int) this.getYposition();
        s += (int) this.getXposition();
        return s;
    }
}
