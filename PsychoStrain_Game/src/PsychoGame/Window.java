package PsychoGame;

import PsychoSystem.GameImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimedes Diaz)
/**
 * Displays game.
 */
public class Window extends javax.swing.JFrame implements
        KeyListener,
        MouseListener,
        MouseMotionListener,
        Observer {

    /**
     * Window's title.
     */
    private String title;
    /**
     * Window's width.
     */
    private int width;
    /**
     * Window's height.
     */
    private int height;
    /**
     * BufferedImage for game components.
     */
    private BufferedImage lifeBar;
    private BufferedImage notifyIcon;
    private BufferedImage message;
    private BufferedImage gameOver;
    private BufferedImage[] weapons;
    /**
     * Is first game run.
     */
    public boolean firstRun;
    /**
     * Is running game.
     */
    private boolean game;

    public Window() {
        this.title = "PsychoStrain 1.1";
        this.width = 768;
        this.height = 510;
        this.setForeground(Color.BLACK);
        this.weapons = new BufferedImage[4];
        initComponents();
        setCrazy();
        // Originally createBufferStrategy used value 2
        createBufferStrategy(3);
        lifeBar = GameImage.loadImage("resources/sprites/misc/lifeBar.png");
        notifyIcon = GameImage.loadImage("resources/sprites/misc/notify.png");
        message = GameImage.loadImage("resources/sprites/misc/ifLol.png");
        gameOver = GameImage.loadImage("resources/sprites/misc/gameover.png");
        game = false;
        firstRun = false;
    }

    @Override
    public void paint(Graphics g) {
        refresh();
    }

    public void setFirstRun() {
        firstRun = true;
    }

    private void setCrazy() {
        try {
            Font crazy = Font.createFont(Font.TRUETYPE_FONT, new File(
                    "resources/fonts/byte.ttf"));
            crazy = crazy.deriveFont(20.0f);
            this.setFont(crazy);
        } catch (FontFormatException ex) {
            System.out.println("No se cargo la font");
        } catch (IOException ex) {
            System.out.println("No se cargo la font");
        }
    }

    private void refresh() {
        //System.out.println("Refreshing window");
        // Get BufferStrategy and draw components
        BufferStrategy p = getBufferStrategy();
        Graphics graphics = p.getDrawGraphics();
        if (!game) {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, 768, 700);
            graphics.drawImage(Engine.level.getBackgroundImageRealisation(), 0,
                    0, this);
            graphics.drawImage(Engine.hacker.getImage(), (int) Engine.hacker
                    .getXposition(),
                    (int) Engine.hacker.getYposition(), this);
            graphics.drawImage(Engine.level.getMapImage(), 0, 0, this);
            graphics.setColor(Color.GREEN);
            graphics.drawImage(Engine.weapon.weaponRefresh(), 0, 480, this);
            if (Engine.cmd.getNotify()) {
                graphics.drawImage(notifyIcon, 5, getHeight() - 40, this);
            }
            if (firstRun) {
                graphics.drawImage(message, 44, getHeight() - 86, this);
            }
            Engine.level.paintEnemies(graphics);
            nanoBotsRefresh(graphics);
        } else {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(0, 0, 768, 700);
            graphics.drawImage(gameOver, 0, 0, this);
        }
        graphics.dispose();
        p.show();
    }

    private void nanoBotsRefresh(Graphics g) {
        try {
            if (Engine.hpaux < 100) {
                g.setColor(Color.GREEN);
            } else if (Engine.hpaux >= 100 && Engine.hpaux < 170) {
                g.setColor(Color.YELLOW);
            } else {
                g.setColor(Color.RED);
            }
            g.fillRoundRect(550, 485, 200, 20, 5, 5);
            g.clearRect(550, 485, Engine.hpaux, 20);
            g.drawImage(lifeBar, 550, 485, this);
            g.setColor(Color.white);
            g.drawString("NanoBots", 400, 500);
            g.drawString("NanoContainers", 400, 540);
            g.setColor(Color.black);
            g.fillRect(650, 510, 30, 30);
            g.setColor(Color.white);
            g.drawString("" + Engine.getNanoContainers() + "", 650, 540);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    public void setGame(boolean game) {
        this.game = game;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle(title);
        setBackground(new java.awt.Color(0, 0, 0));
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(width, height));
        setName("window"); // NOI18N
        setResizable(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                formFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 656, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 538, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusLost
        Engine.setKeys();
    }//GEN-LAST:event_formFocusLost

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        Engine.keyRefresh(e.getKeyCode(), true);
        paint(null);
    }

    public void keyReleased(KeyEvent e) {
        Engine.keyRefresh(e.getKeyCode(), false);
        paint(null);
    }

    public void mouseDragged(MouseEvent e) {
        if (Engine.weapon.checkAmmo()) {
            Engine.enemyKill(e.getPoint());
        }
    }

    public void mouseMoved(MouseEvent e) {
        Engine.setAim(e.getPoint());

    }

    public void mouseClicked(MouseEvent e) {
        if (Engine.weapon.checkAmmo()) {
            Engine.enemyKill(e.getPoint());
        }
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void update(Observable o, Object arg) {
        if (o == Engine.level.getMap()) {
            this.repaint();
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
