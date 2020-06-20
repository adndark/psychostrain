package PsychoSystem;

import PsychoGame.level.Map;
import PsychoGame.*;
import java.io.Serializable;

// Psysoft Team
public class Save implements Serializable {

    private int posY, difX, shotgunammo, rifleammo, granadelaucherammo, vida, estado, dificultad, lives, currentLevel;
    private String mapName, enemiesList[], backName, hackerName, soundLevel;
    private boolean weapons[], challenge;

    public Save() {
        weapons = new boolean[4];
        weapons[0] = Engine.weapon.getGun();
        weapons[1] = Engine.weapon.getShotgun();
        weapons[2] = Engine.weapon.getRifle();
        weapons[3] = Engine.weapon.getGrandelauncher();
        posY = (int) Engine.hacker.getYposition();
        difX = Engine.level.getDifX();
        shotgunammo = Engine.weapon.getShotgunAmmo();
        rifleammo = Engine.weapon.getRifleAmmo();
        granadelaucherammo = Engine.weapon.getGranadeLauncherAmmo();
        vida = Engine.hpaux;
        lives = Engine.getNanoContainers();
        dificultad = Engine.dificultad;
        mapName = Engine.level.getMapName();
        enemiesList = Engine.level.getEnemyString();
        backName = Engine.level.getBackgroundName();
        hackerName = Engine.hacker.getName();
        estado = Engine.hacker.getState().ordinal();
        challenge = Engine.cmd.getChallenge();
        currentLevel = Map.nxtlvl;
        soundLevel = Engine.soundName;
    }

    public String getSoundLevel() {
        return soundLevel;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public boolean getChallenge() {
        return challenge;
    }

    public int getLives() {
        return lives;
    }

    public int getDificultad() {
        return dificultad;
    }

    public String getBackName() {
        return backName;
    }

    public int getDifX() {
        return difX;
    }

    public int getEstado() {
        return estado;
    }

    public String[] getEnemiesList() {
        return enemiesList;
    }

    public int getGranadelaucherammo() {
        return granadelaucherammo;
    }

    public String getMapName() {
        return mapName;
    }

    public int getPosY() {
        return posY;
    }

    public int getRifleammo() {
        return rifleammo;
    }

    public int getShotgunammo() {
        return shotgunammo;
    }

    public int getVida() {
        return vida;
    }

    public boolean[] getWeapons() {
        return weapons;
    }

    public String getHackerName() {
        return hackerName;
    }

    @Override
    public String toString() {
        String s = "Game Saved: " + hackerName + "\n\n";
        s += "posy:" + posY + "\tdifx:" + difX + "\n";
        s +=
                "Armas activas: " + weapons[0] + " " + weapons[1] + " " + weapons[2] + " " + weapons[3] + "\n";
        s += "ShotgunAmmo:" + shotgunammo + "\n";
        s += "RifleAmmo:" + rifleammo + "\n";
        s += "granadelaucherAmmo:" + granadelaucherammo + "\n";
        s += "Estado: " + estado + "\n";
        s += "HP: " + vida + "\n";
        s += "Mapa: " + mapName + "\n";
        s += "Background: " + backName + "\n";
        return s;
    }
}
