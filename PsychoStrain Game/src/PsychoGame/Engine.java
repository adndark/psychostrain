package PsychoGame;

import PsychoGame.hacker.Hacker;
import PsychoGame.console.Console;
import PsychoGame.handheld.Weapon;
import PsychoGame.level.Map;
import PsychoGame.level.Level;
import PsychoGame.enemy.Enemy;
import PsychoMenu.Menu;
import PsychoSystem.FileLoader;
import PsychoSystem.Options;
import PsychoSystem.Physics;
import PsychoSystem.Sound;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimedes Diaz)
public class Engine {

    public static Options staticOpt = new Options();
    public static Window window;
    public static Console cmd;
    public static Hacker hacker;
    public static Level level;
    private static Menu menu;
    public static Weapon weapon;
    public static int i, hpaux, lf, currentWeapon, salto, abajo, derecha, izquierda, openCmd, pause, dificultad, auxright, auxleft;
    public static boolean leftKey = false, rightKey = false, upKey = false, downKey =
            false, jumpKey = false;
    public static Thread hilo = new Thread();
    public static int teta = 0, l = 0, lvl = 1;
    public static String selectedProfile = "<No Profile Loaded>", soundName;
    public static Sound music;

    public static void loadGame(PsychoSystem.Save s) {
        System.out.println(s);
        dificultad = s.getDificultad();
        if (s.getBackName().equals("")) {
            level = new Level(s.getMapName(), s.getDifX(), s.getEnemiesList());
        } else {
            level = new Level(s.getBackName(), s.getMapName(), s.getDifX(), s
                    .getEnemiesList());
        }
        window = new Window();
        level.addObserver(window);
        hacker = new Hacker(selectedProfile, s.getPosY(), s.getEstado());
        cmd = new Console();
        cmd.setChallenge(s.getChallenge());
        lf = s.getLives();
        weapon = new Weapon();
        Map.nxtlvl = s.getCurrentLevel();
        weapon.setShotgun(s.getWeapons()[1]);
        weapon.setRifle(s.getWeapons()[2]);
        weapon.setGrandelauncher(s.getWeapons()[3]);
        weapon.setShotgunAmmo(s.getShotgunammo());
        weapon.setRifleAmmo(s.getRifleammo());
        weapon.setGranadeLauncherAmmo(s.getGranadelaucherammo());
        hpaux = s.getVida();
        window.addKeyListener(window);
        window.addMouseMotionListener(window);
        window.addMouseListener(window);
        window.setVisible(true);
        currentWeapon = 1;
        soundName = s.getSoundLevel();
        music = new Sound(soundName);
        music.play();
    }

    public static void loadMap(String map) {
        staticOpt.loadConfig();
        window = new Window();
        hacker = new Hacker(selectedProfile, 240, 0);
        cmd = new Console();
        weapon = new Weapon();
        dificultad = 2;
        level = new Level(map);
        level.addObserver(window);
        hpaux = 0;
        lf = 3;
        window.addKeyListener(window);
        window.addMouseMotionListener(window);
        window.addMouseListener(window);
        window.setVisible(true);
        currentWeapon = 1;
        music = new Sound("sounds/Tutorial.mid");
        soundName = "sounds/Tutorial.mid";
        music.play();
    }

    public static void loadLevel(String background, String mapPath,
            int HackerPosY) {
        staticOpt.loadConfig();
        window = new Window();
        hacker = new Hacker(selectedProfile, HackerPosY, 0);
        cmd = new Console();
        weapon = new Weapon();
        level = new Level(background, mapPath);
        level.addObserver(window);
        hpaux = 0;
        window.addKeyListener(window);
        window.addMouseMotionListener(window);
        window.addMouseListener(window);
        window.setVisible(true);
        currentWeapon = 1;
    }

    public static void setDefaults() {
        music = new Sound("sounds/Tutorial.mid");
        soundName = "sounds/Tutorial.mid";
        loadLevel("configurations/level/Ribbon.txt", "maps/Tutorial.map", 300);
        lf = 3;
        window.setFirstRun();
        music.play();
    }

    public static void loadLevel1() {
        if (music != null) {
            if (music.isPlaying()) {
                music.stop();
            }
            music = null;
        }
        music = new Sound("sounds/Level1.mid");
        soundName = "sounds/Level1.mid";
        loadLevel("configurations/level/bglevel1.txt", "maps/Level1.map", 330);
        music.play();
        Map.nxtlvl++;
    }

    public static void loadLevel2() {
        if (music != null) {
            if (music.isPlaying()) {
                music.stop();
            }
            music = null;
        }
        music = new Sound("sounds/Level2.mid");
        soundName = "sounds/Level2.mid";
        loadLevel("configurations/level/Ribbon.txt", "maps/Level2.map", 240);
        music.play();
        Map.nxtlvl++;

    }

    public static void loadLevel3() {
        if (music != null) {
            if (music.isPlaying()) {
                music.stop();
            }
            music = null;
        }
        music = new Sound("sounds/Level3.mid");
        soundName = "sounds/Level3.mid";
        loadLevel("configurations/level/Ribbon.txt", "maps/Level3.map", 240);
        music.play();
        Map.nxtlvl++;
    }

    public static void loadFinal() {
        if (music != null) {
            if (music.isPlaying()) {
                music.stop();
            }
            music = null;
        }
        music = new Sound("sounds/Final.mid");
        soundName = "sounds/Final.mid";
        loadLevel("configurations/level/Ribbon.txt", "maps/Final.map", 240);
        music.play();
        Map.nxtlvl++;
    }

    public static void loadLastProfile() {
        HashMap<Integer, String> listaProfiles = FileLoader.getProfiles();
        int control = 1;
        while (!listaProfiles.isEmpty()) {
            if (listaProfiles.containsKey(control)) {
                String tmp = listaProfiles.get(control);
                if (tmp.endsWith("*")) {
                    Engine.selectedProfile = (tmp.replace("*", ""));
                }
                listaProfiles.remove(control);
            }
            control++;
        }
    }

    @SuppressWarnings("static-access")
    //SUPER CHINGON METODO DE FLOW CONTROL para el menu, si lo cambian todo se rompe :(
    public static void runMenu() {

        int index = 0;
        menu = new Menu();
        menu.setVisible(true);
        System.out.println("Opening Menu");
        while (!menu.getShouldCloseMenu()) {
        }
        System.out.println("Closing Menu");
        menu.dispose();
    }

    public static void setNewCursor(String nombreImagenCursor) {
        //El toolkit es usado para crear cursores customizables.
        //El metodo regresa la imagen cargada como una Image.
        Image image = Toolkit.getDefaultToolkit()
                .createImage(nombreImagenCursor);
        Cursor transparentCursor = Toolkit.getDefaultToolkit()
                .createCustomCursor(image, new Point(12, 12), "invisibleCursor");
        window.setCursor(transparentCursor);
    }

    public static void setAim(Point a) {
        double posx = hacker.getXposition() + 32;
        double posy = hacker.getYposition() + 40;

        double x = a.x - posx, y = a.getY() - posy;
        double angulo = Math.atan2(y, x);

        teta = (int) (Math.toDegrees(angulo));
        if (a.getX() > posx) {
            hacker.setDirection(Hacker.Direction.FORWARD);
            if (teta <= -31) {
                //i=0;
                setI(0);
            } else if (teta > -31 && teta <= -17) {
                //i=1;
                setI(1);
            } else if (teta > -17 && teta <= -9) {
                //i=2;
                setI(2);
            } else if (teta > -9 && teta <= 6) {
                //i=3;
                setI(3);
            } else if (teta > 6 && teta <= 17) {
                //i=4;
                setI(4);
            } else if (teta > 17 && teta <= 34) {
                //i=5;
                setI(5);
            } else if (teta > 34) {
                //i=6;
                setI(6);
            }
        } else {
            hacker.setDirection(Hacker.Direction.BACKWARD);
            if (teta <= -135) {
                //i=7;
                setI(7);
            }
            if (teta < -135 && teta >= -162) {
                //i=8;
                setI(8);
            }
            if (teta < -162 && teta >= -174) {
                //i=9;
                setI(9);
            }
            if (teta < -174 || teta >= 167) {
                //i=10;
                setI(10);
            }
            if (teta < 167 && teta >= 158) {
                //i=11;
                setI(11);
            }
            if (teta < 158 && teta >= 146) {
                //i=12;
                setI(12);
            }
            if (teta < 146 && teta > 0) {
                //i=13;
                setI(13);
            }
        }
    }

    public static void enemyKill(Point a) {
        int xpk = (int) a.getX();
        int ypk = (int) a.getY();
        setAim(a);
        hacker.setFireImage(a);
        weapon.setAmmo(false);
        ArrayList<Enemy> enemies = level.getEnemies();
        if (!enemies.isEmpty()) {
            for (int j = 0; j < enemies.size(); j++) {
                Enemy bad = enemies.get(j);
                if (bad.isAlive()) {
                    if (bad.isActiveOnScreen(level.getDifX())) {
                        Rectangle r = bad.getBounds();
                        if (r.contains(a)) {
                            bad.setActualHP(bad.getActualHP() - (weapon
                                    .getWeaponPower()));
                        }
                    }
                }
            }
        }
    }

    public static void setStateHacker() {
        weapon.checkCursor();
        lifeMenosMenos();
        if (rightKey && !leftKey) {
            if (hacker.getState() != Hacker.State.DUCKING) {
                if (hacker.getDirection() == Hacker.Direction.FORWARD) {
                    if (!hacker.isJumping()) {
                        hacker.setState(Hacker.State.JUMPING);
                    } else if (hacker.getState() != Hacker.State.WALKING) {
                        hacker.setState(Hacker.State.WALKING);
                    }
                } else if (hacker.getDirection() == Hacker.Direction.BACKWARD) {
                    if (!hacker.isJumping()) {
                        hacker.setState(Hacker.State.JUMPING);
                    } else if (hacker.getState() != Hacker.State.WALKINGBACK) {
                        hacker.setState(Hacker.State.WALKINGBACK);
                    }
                }
            }
            auxright = level.canGoRight(hacker, 7);
            level.moveVisibleMatrixRight(auxright);
            if (auxright != 0) {
                level.moveViewPortRight(1);
            }
        }

        if (leftKey && !rightKey) {
            if (hacker.getState() != Hacker.State.DUCKING) {
                if (hacker.getDirection() == Hacker.Direction.BACKWARD) {
                    if (!hacker.isJumping()) {
                        hacker.setState(Hacker.State.JUMPING);
                    } else if (hacker.getState() != Hacker.State.WALKING) {
                        hacker.setState(Hacker.State.WALKING);
                    }
                } else if (hacker.getDirection() == Hacker.Direction.FORWARD) {
                    if (!hacker.isJumping()) {
                        hacker.setState(Hacker.State.JUMPING);
                    } else if (hacker.getState() != Hacker.State.WALKINGBACK) {
                        hacker.setState(Hacker.State.WALKINGBACK);
                    }
                }
            }
            auxleft = -level.canGoLeft(hacker, -6);
            level.moveVisibleMatrixLeft(auxleft);
            if (auxleft != 0) {
                level.moveViewPortLeft(1);
            }
        }

        if (jumpKey) {
            hacker.jump();

            //hacker.setState(Hacker.State.JUMPING);
        }

        if (downKey) {
            hacker.setState(Hacker.State.DUCKING);
        }

        if (upKey) {
            if (hacker.getState() == Hacker.State.DUCKING) {
                if (level.canGoUpBall(hacker)) {
                    hacker.setState(Hacker.State.WAITING);
                } else {
                    hacker.setState(Hacker.State.DUCKING);
                }

            } else {
                hacker.setState(Hacker.State.WAITING);
            }

        }

        if (!(downKey || rightKey || leftKey || jumpKey)) {
            if (hacker.getState() != Hacker.State.DUCKING) {

                hacker.setState(Hacker.State.WAITING);
            }
        }

    }

    public static void keyRefresh(int keyCode, boolean pressed) {
        System.out.println("Key pressed" + keyCode);
        if (keyCode == staticOpt.getSalto()) {
            jumpKey = pressed;
        } else if (keyCode == staticOpt.getAbajo()) {
            downKey = pressed;
        } else if (keyCode == staticOpt.getDerecha()) {
            rightKey = pressed;
        } else if (keyCode == staticOpt.getIzquierda()) {
            leftKey = pressed;
        } else if (keyCode == staticOpt.getCmd()) {
            cmd.show();
            cmd.setNotify(false);
            Engine.window.firstRun = false;
        } else if (keyCode == KeyEvent.VK_W) {
            upKey = pressed;
        } else if (keyCode == KeyEvent.VK_1) {
            currentWeapon = 1;
        } else if (keyCode == KeyEvent.VK_2) {
            if (weapon.getShotgun()) {
                currentWeapon = 2;
            }
        } else if (keyCode == KeyEvent.VK_3) {
            if (weapon.getRifle()) {
                currentWeapon = 3;
            }
        } else if (keyCode == KeyEvent.VK_4) {
            if (weapon.getGrandelauncher()) {
                currentWeapon = 4;
            }
        } else if (keyCode == staticOpt.getGuns()) {
            if (currentWeapon == 4) {
                currentWeapon = 1;
            } else {
                currentWeapon++;
            }
        }
    }

    public static void lifeMenosMenos() {
        if (hpaux >= 200) {
            lf--;
            setNanoContainers(lf);
            if (lf < 0) {
                gameOver();
            }
            hpaux = 0;
        }
    }

    public static void gameOver() {
        long time = System.currentTimeMillis();
        while (System.currentTimeMillis() - time < 5000) {
            window.setGame(true);
        }
        window.hide();
        window.dispose();
        runMenu();
    }

    public static int getI() {
        return i;
    }

    public static void setI(int aux) {
        i = aux;
    }

    public static int getNanoContainers() {
        return lf;
    }

    public static void setNanoContainers(int n) {
        lf = n;

    }

    public static void nextLevel(int i) {
        //if(aux > 5000 && b){
        window.show(false);
        window.dispose();
        System.out.println(i);
        switch (i) {
            case 1:
                loadLevel1();
                break;
            case 2:
                loadLevel2();
                break;
            case 3:
                loadLevel3();
                break;//siento que falta algo para que no se salga del rango de los 3
            //niveles, iba a poner un Engine.setNxtlvl(0); para que cada que llegara al nivel 3 y lo termine
            //regresara al tutorial, pero ps falta ver q onda con los niveles del usuario etc....
            }

    }

    public static void changeLevel(boolean a, int nextlevel) {
    }

    public static void setKeys() {
        leftKey = rightKey = upKey = downKey = jumpKey = false;
    }

    public static void main(String args[]) {
        System.out.println("Run menu");
        runMenu(); //hasta que el menu me deje seguir :D
        System.out.println("Menu ended");
        Engine.setDefaults();
        while (true) {
            setStateHacker();
            Physics.gravity(hacker);
            try {
                Thread.sleep(20);
            } catch (Exception e) {
                System.out.println("Exception while sleeping thread");
            }
        }
    }
}
