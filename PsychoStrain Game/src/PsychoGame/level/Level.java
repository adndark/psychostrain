// Level.java (Antes GameStage.java)
package PsychoGame.level;

import PsychoGame.Console;
import PsychoGame.Engine;
import PsychoGame.Hacker;
import PsychoGame.Map;
import PsychoGame.Weapon;
import PsychoGame.enemy.EnemyTank;
import PsychoGame.enemy.EnemySuperSpider;
import PsychoGame.enemy.EnemySpider;
import PsychoGame.enemy.EnemyRueda;
import PsychoGame.enemy.EnemyRobot;
import PsychoGame.enemy.EnemyMedusa;
import PsychoGame.enemy.EnemyClawRobot;
import PsychoGame.enemy.EnemyChainRobot;
import PsychoGame.enemy.Enemy;
import PsychoGame.gameobject.AnimatedObject;
import java.awt.image.BufferedImage;
import PsychoSystem.*;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimedes Diaz)
import java.awt.Graphics;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Observer;

public class Level implements Serializable {

    private Background background;
    private Map map;
    private Hacker hacker;
    private Weapon weapon;
    private ArrayList<Enemy> enemies;
    private Console cmd;
    private int lifes;
    private String mapName;
    private String backgroundName;

    public static Options staticOpt = new Options();

    public String getBackgroundName() {
        return backgroundName;
    }

    public void setBackgroundName(String backgroundName) {
        this.backgroundName = backgroundName;
    }

    public Level(final String back, final String mapName) {
        this.mapName = mapName;
        this.background = new Background("InfiniteBackground",
                GameImage.loadFromFile(back, "tiles"), 0, 0, 0, 768, 480);
        this.map = new Map(mapName, 30, 768, 480);
        this.enemies = transformList(FileLoader.loadEnemies(mapName));
        this.backgroundName = back;
    }

    public Level(final String mapName) {
        this.mapName = mapName;
        final BufferedImage[] bf = new BufferedImage[2];
        bf[0] = GameImage.loadImage("images/logo.png");
        bf[1] = GameImage.loadImage("images/logo.png");
        this.background = new Background("InfiniteBackground", bf, 0, 0, 0, 768, 480);
        this.map = new Map(mapName, 30, 768, 480, 0);
        this.enemies = transformList(FileLoader.loadEnemies(mapName));
        this.backgroundName = "";
    }

    public Level(final String mapName, final int difx, final String[] enem) {
        this.mapName = mapName;
        final BufferedImage[] bf = new BufferedImage[2];
        bf[0] = GameImage.loadImage("images/logo.png");
        bf[1] = GameImage.loadImage("images/logo.png");
        this.background = new Background("InfiniteBackground", bf, 0, 0, 0, 768, 480);
        this.map = new Map(mapName, 30, 768, 480, difx);
        this.enemies = transformArray(enem);
        this.backgroundName = "";
    }

    public Level(final String back, final String mapName, final int difx, final String[] enem) {
        this.mapName = mapName;
        this.background = new Background("InfiniteBackground",
                GameImage.loadFromFile(back, "tiles"), 0, 0, 0, 768, 480);
        this.map = new Map(mapName, 30, 768, 480, difx);
        this.enemies = transformArray(enem);
        this.backgroundName = back;
    }

    private ArrayList<Enemy> transformList(final ArrayList<String> lista) {
        ArrayList<Enemy> enemis = new ArrayList();
        if (lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                enemis.add(transformStringToEnemy(lista.get(i)));
            }
        }
        return enemis;
    }

    private ArrayList<Enemy> transformArray(final String[] array) {
        ArrayList<Enemy> enemis = new ArrayList();
        if (array != null) {
            for (int i = 0; i < array.length; i++) {
                enemis.add(transformStringToEnemy(array[i].substring(1)));
            }
        }
        return enemis;
    }

    private Enemy transformStringToEnemy(final String s) {
        Enemy e;
        System.out.println(s);
        int d = Engine.dificultad;
        int n = s.charAt(0) - '0';
        int x = Integer.parseInt(s.substring(4));
        int y = Integer.parseInt(s.substring(1, 4));
        System.out.println("Y: " + y);
        switch (n) {
            case 0:
                e = new EnemyRobot(x, y, 20 * d, 50 * d, 1000 / d, 10000 * d);
                break;
            case 1:
                e = new EnemyChainRobot(x, y, 25 * d, 60 * d, 1000 / d, 10000 * d);
                break;
            case 2:
                e = new EnemyClawRobot(x, y, 30 * d, 70 * d, 1000 / d, 10000 * d);
                break;
            case 3:
                e = new EnemyMedusa(x, y, 5 * d, 10 * d, 1000 / d, 2000 * d);
                break;
            case 4:
                e = new EnemyRueda(x, y, 5 * d, 15 * d, 1000 / d, 2000 * d);
                break;
            case 6:
                e = new EnemySuperSpider(x, y, 15 * d, 30 * d, 1000 / d, 15000 * d);
                break;
            case 7:
                e = new EnemyTank(x, y, 10 * d, 25 * d, 1000 / d, 3000 * d);
                break;
            default:
                e = new EnemySpider(x, y, 7 * d, 20 * d, 1000 / d, 5000 * d);
        }
        return e;
    }

    public void paintEnemies(Graphics g) {
        //Este ciclo es nuestro garbageCollector de enemigos.
        if (!enemies.isEmpty()) {
            for (int i = 0; i < enemies.size(); i++) { //recorremos la lista de enemigos.
                Enemy bad = enemies.get(i);
                if (bad.isAlive()) {
                    if (bad.isActiveOnScreen(map.getDifx())) {
                        try {
                            Engine.hpaux += bad.damageDeal(Engine.hacker);
                            g.drawImage(bad.getEnemySecuence(), bad.getScreenXPosition(), (int) bad.getYposition(), null);
                        } catch (Exception e) {
                            System.out.println("An enemy cannot be painted");
                        }
                    }
                } else {
                    enemies.remove(i);
                }
            }
        }
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public int getLifes() {
        return lifes;
    }

    public void setLifes(int lifes) {
        this.lifes = lifes;
    }

    public Background getBackground() {
        return background;
    }

    public Hacker getHacker() {
        return hacker;
    }

    public Map getMap() {
        return map;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setBackground(Background background) {
        this.background = background;
    }

    public void setHacker(Hacker hacker) {
        this.hacker = hacker;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    //Los siguientes 2 métodos, llaman al método de background.
    public void moveViewPortLeft(int n) {
        background.moveViewPortLeft(n);
    }

    public void moveViewPortRight(int n) {
        background.moveViewPortRight(n);
    }

    //Estos metodos son para el mapa...
    public int canGoLeft(AnimatedObject ao, int n) {
        return map.canGoLeft(ao, n);
    }

    public int canGoRight(AnimatedObject ao, int n) {
        return map.canGoRight(ao, n);
    }

    public int canGoUp(AnimatedObject ao, int n) {
        return map.canGoUp(ao, n);
    }

    public int canGoDown(AnimatedObject ao, int n) {
        return map.canGoDown(ao, n);
    }

    public void moveVisibleMatrixLeft(int dx) {
        map.moveVisibleMatrixLeft(dx);
    }

    public void moveVisibleMatrixRight(int dx) {
        map.moveVisibleMatrixRight(dx);
    }
    //Metodos de imagenes

    //Metodo para el background.
    public BufferedImage getBackgroundImageRealisation() {
        return background.getImageRealisation();
    }

    //Metodo para el Mapa...
    public BufferedImage getMapImage() {
        return map.getImage();
    }

    public boolean canGoUpBall(AnimatedObject ao) {
        return map.canGoUpBall(ao);
    }

    public int getDifX() {
        return map.getDifx();
    }

    public void setDifX(int x) {
        map.difx = x;
    }

    public void addObserver(Observer o) {
        map.addObserver(o);
    }

    public String[] getEnemyString() {
        String s[] = null;
        if (!enemies.isEmpty()) {
            s = new String[enemies.size()];
            for (int i = 0; i < enemies.size(); i++) { //recorremos la lista de enemigos.
                s[i] = enemies.get(i).toString();
                System.out.println(enemies.get(i).toString());
            }
        }
        return s;
    }

    public void setEnemiesList(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }
    
    public ArrayList<Enemy> getEnemies(){
        return this.enemies;
    }
}
