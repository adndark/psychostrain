package PsychoGame.level;
// Level.java (Antes GameStage.java)

import PsychoGame.Engine;
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
import java.util.stream.Collectors;
import java.util.List;

public class Level implements Serializable {

    private final Background background;
    private final Map map;
    private final String mapName;
    private List<Enemy> enemies;
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
        bf[0] = GameImage.loadImage("resources/images/logo.png");
        bf[1] = GameImage.loadImage("resources/images/logo.png");
        this.background = new Background("InfiniteBackground", bf, 0, 0, 0, 768,
                480);
        this.map = new Map(mapName, 30, 768, 480, 0);
        this.enemies = transformList(FileLoader.loadEnemies(mapName));
        this.backgroundName = "";
    }

    public Level(final String mapName, final int difx, final String[] enem) {
        this.mapName = mapName;
        final BufferedImage[] bf = new BufferedImage[2];
        bf[0] = GameImage.loadImage("resources/images/logo.png");
        bf[1] = GameImage.loadImage("resources/images/logo.png");
        this.background = new Background("InfiniteBackground", bf, 0, 0, 0, 768,
                480);
        this.map = new Map(mapName, 30, 768, 480, difx);
        this.enemies = transformArray(enem);
        this.backgroundName = "";
    }

    public Level(final String back, final String mapName, final int difx,
            final String[] enem) {
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
        ArrayList<Enemy> enemies = new ArrayList();
        if (array != null) {
            for (String array1 : array) {
                enemies.add(transformStringToEnemy(array1.substring(1)));
            }
        }
        return enemies;
    }

    private Enemy transformStringToEnemy(final String s) {
        Enemy enemy;
        System.out.println(s);
        int dificultad = Engine.dificultad;
        int enemyType = s.charAt(0) - '0';
        int xPosition = Integer.parseInt(s.substring(4));
        int yPosition = Integer.parseInt(s.substring(1, 4));
        System.out.println("X: " + xPosition);
        System.out.println("Y: " + yPosition);
        switch (enemyType) {
            case 0:
                enemy =
                        new EnemyRobot(xPosition, yPosition, 20 * dificultad,
                                50 * dificultad, 1000 / dificultad,
                                10000 * dificultad);
                break;
            case 1:
                enemy = new EnemyChainRobot(xPosition, yPosition,
                        25 * dificultad, 60 * dificultad, 1000 / dificultad,
                        10000 * dificultad);
                break;
            case 2:
                enemy =
                        new EnemyClawRobot(xPosition, yPosition, 30 * dificultad,
                                70 * dificultad, 1000 / dificultad,
                                10000 * dificultad);
                break;
            case 3:
                enemy = new EnemyMedusa(xPosition, yPosition, 5 * dificultad,
                        10 * dificultad, 1000 / dificultad, 2000 * dificultad);
                break;
            case 4:
                enemy = new EnemyRueda(xPosition, yPosition, 5 * dificultad,
                        15 * dificultad, 1000 / dificultad, 2000 * dificultad);
                break;
            case 6:
                enemy = new EnemySuperSpider(xPosition, yPosition,
                        15 * dificultad, 30 * dificultad, 1000 / dificultad,
                        15000 * dificultad);
                break;
            case 7:
                enemy = new EnemyTank(xPosition, yPosition, 10 * dificultad,
                        25 * dificultad, 1000 / dificultad, 3000 * dificultad);
                break;
            default:
                enemy = new EnemySpider(xPosition, yPosition, 7 * dificultad,
                        20 * dificultad, 1000 / dificultad, 5000 * dificultad);
        }
        return enemy;
    }

    public void paintEnemies(final Graphics g) {

        //Este ciclo es nuestro garbageCollector de enemigos.
        // Remueve enemigos que ya no estan vivos
        enemies = enemies.stream().filter(enemy -> enemy.isAlive()).collect(
                Collectors.toList());

        // Si la list esta vacia no hay enemigos que mostrar
        if (enemies.isEmpty()) {
            return;
        }
        enemies.stream().forEach(enemy -> { //recorremos la lista de enemigos.
            if (enemy.isActiveOnScreen(map.getDifx())) {
                try {
                    Engine.hpaux += enemy.damageDeal(Engine.hacker);
                    g.drawImage(enemy.getEnemySecuence(), enemy
                            .getScreenXPosition(), (int) enemy
                                    .getYposition(), null);
                } catch (Exception e) {
                    System.out.println("An enemy cannot be painted");
                }
            }

        });

    }

    public String getMapName() {
        return mapName;
    }

    public Map getMap() {
        return map;
    }

    //Los siguientes 2 métodos, llaman al método de background.
    public void moveViewPortLeft(final int n) {
        background.moveViewPortLeft(n);
    }

    public void moveViewPortRight(final int n) {
        background.moveViewPortRight(n);
    }

    //Estos metodos son para el mapa...
    public int canGoLeft(final AnimatedObject ao, final int n) {
        return map.canGoLeft(ao, n);
    }

    public int canGoRight(final AnimatedObject ao, final int n) {
        return map.canGoRight(ao, n);
    }

    public int canGoUp(final AnimatedObject ao, final int n) {
        return map.canGoUp(ao, n);
    }

    public int canGoDown(final AnimatedObject ao, final int n) {
        return map.canGoDown(ao, n);
    }

    public void moveVisibleMatrixLeft(final int dx) {
        map.moveVisibleMatrixLeft(dx);
    }

    public void moveVisibleMatrixRight(final int dx) {
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

    public boolean canGoUpBall(final AnimatedObject ao) {
        return map.canGoUpBall(ao);
    }

    public int getDifX() {
        return map.getDifx();
    }

    public void addObserver(final Observer o) {
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

    public List<Enemy> getEnemies() {
        return this.enemies;
    }
}
