package PsychoMenu;

import PsychoGame.Engine;
import PsychoSystem.Sound;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimedes Diaz)
public class Menu extends javax.swing.JFrame {

    private boolean inGame = false;
    //inGame se va a usar cuando "esc" en vez de salir del juego vuelva a abrir el menu.
    private OptPanel options;
    //panel de opciones, voy a implementar una clase de opciones para modularizar
    private NewGame newgame;
    //panel de nuevo juego, este inicia el juego y manda variables como el nombre del hacker y la dificultad
    private static String optArray[];
    //odio los arrays, si descomento la linea que usa el array en startGame OMFG NULLPOINTEREXCEPTION
    private static boolean control;

    private boolean shouldCloseMenu;
    //este boolean es una maravilla, sirve para puro flow control, salud!
    public static Sound sound = new Sound("resources/sounds/Menu.mid");
    //
    private ProfilePanel profiles;
    private PMaps panelMapas;

    //Crea el menu, si la opcion no es "yes" dibuja en modo "windows"
    public Menu() {
        Engine.loadLastProfile();
        control = true;
        initComponents();
        drawLogo("resources/logo/Logo.png");
        setCrazy();
        sound.play();
        shouldCloseMenu = false;
    }

    //pone la fuente chingona
    public void setCrazy() {
        try {
            Font crazy = Font.createFont(Font.TRUETYPE_FONT, new File(
                    "resources/fonts/byte.ttf"));
            crazy = crazy.deriveFont(30f);
            newGameLbl.setFont(crazy);
            profilesLbl.setFont(crazy);
            optLbl.setFont(crazy);
            exitLbl.setFont(crazy);
            selectMap.setFont(crazy);
            newGameLbl.setForeground(Color.LIGHT_GRAY);
            profilesLbl.setForeground(Color.LIGHT_GRAY);
            optLbl.setForeground(Color.LIGHT_GRAY);
            exitLbl.setForeground(Color.LIGHT_GRAY);
            selectMap.setForeground(Color.LIGHT_GRAY);
            profileLbl.setText(Engine.selectedProfile);
        } catch (FontFormatException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    //dibuja el chingonsisimo logo
    private void drawLogo(String s) {
        ImageIcon icon = new ImageIcon(s);
        icon.setImage(icon.getImage().getScaledInstance(gameLogo.getWidth(),
                gameLogo.getHeight(), 0));
        gameLogo.setIcon(icon);
    }

    //FLOW CONTROL FOR THE FUCKING WIN, si borraramos esto, todo valdria madre :(
    public static boolean continueMain() {
        return control;
    }

    //Cambiar el estado de control, si es verdadero se queda en un ciclo el main del Engine  
    public static void setControl(boolean status) {
        control = status;
    }

    public synchronized void setShouldCloseMenu(boolean shouldCloseMenu) {
        this.shouldCloseMenu = shouldCloseMenu;
    }

    public synchronized boolean getShouldCloseMenu() {
        return this.shouldCloseMenu;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        exitLbl = new javax.swing.JLabel();
        gameLogo = new javax.swing.JLabel();
        optLbl = new javax.swing.JLabel();
        newGameLbl = new javax.swing.JLabel();
        profilesLbl = new javax.swing.JLabel();
        profile2Lbl = new javax.swing.JLabel();
        profileLbl = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        selectMap = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("The PsychoStrain");
        setBackground(new java.awt.Color(1, 1, 1));
        setBounds(new java.awt.Rectangle(340, 250, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel2.setBackground(new java.awt.Color(0, 0, 0));

        exitLbl.setBackground(new java.awt.Color(0, 0, 0));
        exitLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        exitLbl.setText("Salir");
        exitLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                exitLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitLblMouseExited(evt);
            }
        });

        optLbl.setBackground(new java.awt.Color(0, 0, 0));
        optLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        optLbl.setText("Opciones");
        optLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                optLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                optLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                optLblMouseExited(evt);
            }
        });

        newGameLbl.setBackground(new java.awt.Color(0, 0, 0));
        newGameLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        newGameLbl.setText("Juego Nuevo");
        newGameLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newGameLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                newGameLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                newGameLblMouseExited(evt);
            }
        });

        profilesLbl.setBackground(new java.awt.Color(0, 0, 0));
        profilesLbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        profilesLbl.setText("Perfiles");
        profilesLbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                profilesLblMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                profilesLblMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                profilesLblMouseExited(evt);
            }
        });

        profile2Lbl.setBackground(new java.awt.Color(0, 0, 0));
        profile2Lbl.setForeground(new java.awt.Color(255, 255, 255));
        profile2Lbl.setText("Perfil Actual:");

        profileLbl.setBackground(new java.awt.Color(0, 0, 0));
        profileLbl.setForeground(new java.awt.Color(255, 255, 255));
        profileLbl.setText("<error>");

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(11, 255, 0));
        jLabel2.setText("V1.0.1");

        selectMap.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        selectMap.setText("Mapas Extras");
        selectMap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                selectMapMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                selectMapMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                selectMapMouseExited(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(gameLogo, javax.swing.GroupLayout.DEFAULT_SIZE, 626, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(profile2Lbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profileLbl, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 409, Short.MAX_VALUE)
                .addComponent(jLabel2))
            .addComponent(profilesLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
            .addComponent(optLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
            .addComponent(newGameLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 648, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(selectMap, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(exitLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 628, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(gameLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(newGameLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(optLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(profilesLbl)
                .addGap(2, 2, 2)
                .addComponent(selectMap)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(exitLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 158, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(profile2Lbl)
                    .addComponent(profileLbl)
                    .addComponent(jLabel2)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

////////////////////// Menu Nuevo TEST /////////////////////////////////

private void optLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optLblMouseEntered
    optLbl.setForeground(Color.green);
    optLbl.setText(">> " + optLbl.getText() + " <<");
}//GEN-LAST:event_optLblMouseEntered

private void optLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optLblMouseExited
    optLbl.setForeground(Color.lightGray);
    optLbl.setText("Opciones");
}//GEN-LAST:event_optLblMouseExited

private void newGameLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newGameLblMouseEntered
    newGameLbl.setForeground(Color.green);
    newGameLbl.setText(">> " + newGameLbl.getText() + " <<");
}//GEN-LAST:event_newGameLblMouseEntered

private void newGameLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newGameLblMouseExited
    newGameLbl.setForeground(Color.lightGray);
    newGameLbl.setText("Juego Nuevo");
}//GEN-LAST:event_newGameLblMouseExited

private void optLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optLblMouseClicked
    options = new OptPanel(); //construye la form
    options.show();   //ensenia la form
}//GEN-LAST:event_optLblMouseClicked

private void newGameLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newGameLblMouseClicked
    newgame = new NewGame(this);
    newgame.show();
}//GEN-LAST:event_newGameLblMouseClicked

private void profilesLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilesLblMouseClicked
    profiles = new ProfilePanel();
    profiles.show();
}//GEN-LAST:event_profilesLblMouseClicked

private void exitLblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLblMouseClicked
    //System.exit(0); //kill de todo, podriamos poner que el usuario indique si no fue un error
    this.dispose();
}//GEN-LAST:event_exitLblMouseClicked

private void profilesLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilesLblMouseEntered
    profilesLbl.setForeground(Color.green);
    profilesLbl.setText(">> " + profilesLbl.getText() + " <<");
}//GEN-LAST:event_profilesLblMouseEntered

private void exitLblMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLblMouseEntered
    exitLbl.setForeground(Color.green);
    exitLbl.setText(">> " + exitLbl.getText() + " <<");
}//GEN-LAST:event_exitLblMouseEntered

private void profilesLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_profilesLblMouseExited
    profilesLbl.setForeground(Color.lightGray);
    profilesLbl.setText("Perfiles");
}//GEN-LAST:event_profilesLblMouseExited

private void exitLblMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitLblMouseExited
    exitLbl.setForeground(Color.lightGray);
    exitLbl.setText("Salir");
}//GEN-LAST:event_exitLblMouseExited

private void selectMapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMapMouseClicked
    panelMapas = new PMaps();
}//GEN-LAST:event_selectMapMouseClicked

private void selectMapMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMapMouseEntered
    selectMap.setForeground(Color.green);
    selectMap.setText(">> " + selectMap.getText() + " <<");
}//GEN-LAST:event_selectMapMouseEntered

private void selectMapMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_selectMapMouseExited
    selectMap.setForeground(Color.lightGray);
    selectMap.setText("Mapas Extras");
}//GEN-LAST:event_selectMapMouseExited

    @Override
    public void dispose() {
        sound.stop();
        super.dispose();
    }

    public static void setProfileLbl(String s) {
        profileLbl.setText(s);
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel exitLbl;
    private javax.swing.JLabel gameLogo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel newGameLbl;
    private javax.swing.JLabel optLbl;
    private javax.swing.JLabel profile2Lbl;
    private static javax.swing.JLabel profileLbl;
    private javax.swing.JLabel profilesLbl;
    private javax.swing.JLabel selectMap;
    // End of variables declaration//GEN-END:variables

}
