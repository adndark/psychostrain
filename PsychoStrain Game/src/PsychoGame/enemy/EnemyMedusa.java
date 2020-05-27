package PsychoGame.enemy;

import PsychoGame.Engine;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

/**
 *
 * @author Manuel
 */
public class EnemyMedusa extends Enemy {

    private int local1 = 0;

    public EnemyMedusa(
            final int xPosition,
            final int yPosition,
            final int damagePerHit,
            final int hp,
            final int timeInactive,
            final int timeLoop) {
        super("EnemyMedusa.txt",
                "enemy/Medusa",
                xPosition,
                yPosition,
                damagePerHit,
                hp,
                timeInactive,
                timeLoop);
        setDirection(direction.BACKWARD);
        local1 = 0;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle((int) getScreenXPosition(), (int) getYposition() + 2, 45, 52);
    }

    @Override
    public void enemyIntelligence() {

        if (this.getScreenXPosition() > Engine.hacker.getXposition()) {

            this.setVX(-6.0);
            this.moveXposition(this.getVX());

            if (this.getDirection() != direction.BACKWARD) {
                setDirection(direction.BACKWARD);

            }

        }

        if (this.getScreenXPosition() < Engine.hacker.getXposition()) {
            this.setVX(6.0);
            this.moveXposition(this.getVX());

            if (this.getDirection() != direction.FORWARD) {
                setDirection(direction.FORWARD);

            }
        }

    }

    @Override
    public BufferedImage getEnemySecuence() {
        setStateEnemy();
        if (getDirection() == direction.FORWARD) {
            if (local1 < 1 || local1 > 4) {
                local1 = 1;
            }
            local1++;
        }
        if (getDirection() == direction.BACKWARD) {
            if (local1 < 1 || local1 > 4) {
                local1 = 1;
            }
            local1++;
        }
        return objectSecuence[local1];
    }

    @Override
    public String toString() {
        String s = ">3";
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
