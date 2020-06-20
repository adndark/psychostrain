package PsychoGame.handheld;

import PsychoGame.Engine;
import PsychoSystem.GameImage;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimedes Diaz)
public class Weapon {

    private BufferedImage[] weapons;
    private BufferedImage weaponSelection;
    private boolean gun;
    private boolean rifle;
    private boolean shotgun;
    private boolean grandelauncher;
    private int rectPositionX;
    private int rectWidth;
    private int rectHeight;
    private int shotgunAmmo;
    private int rifleAmmo;
    private int granadeLauncherAmmo;
    private int maxshotgunAmmo;
    private int maxrifleAmmo;
    private int maxgranadelauncherAmmo;
    private int gunPower;
    private int shotgunPower;
    private int riflePower;
    private int granadeLaucherPower;

    public int getGranadeLauncherAmmo() {
        return granadeLauncherAmmo;
    }

    public void setGranadeLauncherAmmo(int granadeLauncherAmmo) {
        this.granadeLauncherAmmo = granadeLauncherAmmo;
    }

    public int getRifleAmmo() {
        return rifleAmmo;
    }

    public void setRifleAmmo(int rifleAmmo) {
        this.rifleAmmo = rifleAmmo;
    }

    public int getShotgunAmmo() {
        return shotgunAmmo;
    }

    public void setShotgunAmmo(int shotgunAmmo) {
        this.shotgunAmmo = shotgunAmmo;
    }

    public boolean[] getWeapons() {
        boolean w[] = new boolean[4];
        w[0] = getGun();
        w[1] = getShotgun();
        w[2] = getRifle();
        w[3] = getGrandelauncher();
        return w;
    }

    public Weapon() {
        this.weaponSelection = new BufferedImage(500, 150,
                BufferedImage.TYPE_4BYTE_ABGR);
        this.weapons = new BufferedImage[4];
        this.weapons[0] = GameImage.loadImage("sprites/weapons/gun0test.png");
        this.weapons[1] = GameImage
                .loadImage("sprites/weapons/shotgun0test.png");
        this.weapons[2] = GameImage.loadImage("sprites/weapons/rifle0test.png");
        this.weapons[3] = GameImage.loadImage(
                "sprites/weapons/granadelauncher0test.png");
        this.gun = true;
        this.rifle = false;
        this.shotgun = false;
        this.grandelauncher = false;

        this.shotgunAmmo = 50;
        this.rifleAmmo = 100;
        this.granadeLauncherAmmo = 50;
        this.maxshotgunAmmo = 50;
        this.maxrifleAmmo = 100;
        this.maxgranadelauncherAmmo = 50;
        this.gunPower = 1;
        this.shotgunPower = 3;
        this.riflePower = 5;
        this.granadeLaucherPower = 10;
    }

    public void setGrandelauncher(final boolean grandelauncher) {
        this.grandelauncher = grandelauncher;
    }

    public void setGun(final boolean gun) {
        this.gun = gun;
    }

    public void setRifle(final boolean rifle) {
        this.rifle = rifle;
    }

    public void setShotgun(final boolean shotgun) {
        this.shotgun = shotgun;
    }

    public boolean getGrandelauncher() {
        return grandelauncher;
    }

    public boolean getGun() {
        return gun;
    }

    public boolean getRifle() {
        return rifle;
    }

    public boolean getShotgun() {
        return shotgun;
    }

    public BufferedImage weaponRefresh() {

        Graphics2D g = weaponSelection.createGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 500, 150);
        g.setColor(Color.BLUE);
        if (getGun()) {
            g.fillRect(50, 20, 23, 21);
            g.drawImage(weapons[0], 50, 20, Engine.window);
        }
        if (getShotgun()) {

            g.fillRoundRect(100, 20, this.setRect(shotgunAmmo), 23, 5, 5);
            g.drawImage(weapons[1], 100, 20, Engine.window);
        }
        if (getRifle()) {
            g.fillRoundRect(200, 20, this.setRect3(rifleAmmo), 19, 5, 5);
            g.drawImage(weapons[2], 200, 20, Engine.window);
        }
        if (getGrandelauncher()) {
            g.fillRoundRect(300, 20, this.setRect2(granadeLauncherAmmo), 23, 5,
                    5);
            g.drawImage(weapons[3], 300, 20, Engine.window);
        }

        g.drawImage(weaponSelection, 0, 0, 500, 150, 0, 0, 500, 150, null);

        g.setColor(Color.GREEN);
        switch (Engine.currentWeapon) {

            case 1:
                if (getGun()) {
                    rectPositionX = 45;
                    rectWidth = 30;
                    rectHeight = 25;

                }
                break;
            case 2:
                if (getShotgun()) {
                    rectPositionX = 95;
                    rectWidth = 90;
                    rectHeight = 40;

                }
                break;
            case 3:
                if (getRifle()) {

                    rectPositionX = 195;
                    rectWidth = 100;
                    rectHeight = 45;

                }
                break;
            case 4:
                if (getGrandelauncher()) {
                    rectPositionX = 295;
                    rectWidth = 100;
                    rectHeight = 45;

                    break;
                }
        }

        g.drawRect(rectPositionX, 17, rectWidth, 30);

        return weaponSelection;
    }

    public void checkCursor() {
        switch (Engine.currentWeapon) {
            case 1:
                Engine.setNewCursor("sprites/sights/mira.png");
                break;
            case 2:
                if (getShotgun()) {
                    Engine.setNewCursor("sprites/sights/miraShotgun.png");
                }
                break;
            case 3:
                if (getRifle()) {
                    Engine.setNewCursor("sprites/sights/miraRifle.png");
                }
                break;
            case 4:
                if (getGrandelauncher()) {
                    Engine
                            .setNewCursor(
                                    "sprites/sights/miraGranadelauncher.png");
                }
                break;
        }
    }

    public void setAmmo(final boolean x) {

        //si el x es false que haga el -- y si es true que haga un ++
        int ammodif = 0;
        switch (Engine.currentWeapon) {

            case 2:
                ammodif = maxshotgunAmmo - shotgunAmmo;
                if (x) {
                    if (ammodif < 10) {
                        shotgunAmmo += ammodif;
                    } else {
                        shotgunAmmo += 10;
                    }
                } else {
                    shotgunAmmo--;
                    if (shotgunAmmo == 0) {
                        shotgunAmmo = 0;
                    }

                }

                break;
            case 3:
                ammodif = maxrifleAmmo - rifleAmmo;
                if (x) {
                    if (ammodif < 20) {
                        rifleAmmo += ammodif;
                    } else {
                        rifleAmmo += 20;
                    }
                } else {
                    rifleAmmo--;
                    if (rifleAmmo == 0) {
                        rifleAmmo = 0;
                    }
                }

                break;
            case 4:
                ammodif = maxgranadelauncherAmmo - granadeLauncherAmmo;
                if (x) {
                    if (ammodif < 10) {
                        granadeLauncherAmmo += ammodif;
                    } else {
                        granadeLauncherAmmo += 10;
                    }
                } else {
                    granadeLauncherAmmo--;
                    if (granadeLauncherAmmo == 0) {
                        granadeLauncherAmmo = 0;
                    }
                }

                break;
        }
    }

    public boolean checkAmmo() {
        switch (Engine.currentWeapon) {
            case 1://aqui no pongo nada porq se supone q la pistola tiene balas infinitas
                return true;
            // break;
            case 2:

                if (shotgunAmmo >= 0) {
                    return true;
                }
                return false;

            //  break;
            case 3:
                if (rifleAmmo >= 0) {
                    return true;
                }
                return false;

            //break;
            case 4:
                if (granadeLauncherAmmo >= 0) {
                    return true;
                }
                return false;
            //  break;
        }
        return false;
    }

    //para shotgun
    public int setRect(final int actualAmmo) {
        int i = 0;
        return i = (actualAmmo * 70) / maxshotgunAmmo;

    }

    //para granade
    public int setRect2(final int actualAmmo) {
        int i = 0;
        return i = (actualAmmo * 84) / maxgranadelauncherAmmo;
    }

    //para rifle
    public int setRect3(final int actualAmmo) {
        int i = 0;
        return i = (actualAmmo * 92) / maxrifleAmmo;
    }

    public int getWeaponPower() {
        switch (Engine.currentWeapon) {
            case 1:
                return gunPower;

            case 2:
                return shotgunPower;

            case 3:
                return riflePower;

            case 4:
                return granadeLaucherPower;

        }
        return 1;
    }

}
