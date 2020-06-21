package PsychoMenu;

//Psysoft Team
import PsychoGame.Engine;
import PsychoSystem.FileLoader;
import PsychoSystem.Save;
import java.awt.Font;
import java.io.File;
import java.util.HashMap;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;

public class ProfilePanel extends javax.swing.JFrame {

    private HashMap<Integer, String> listaProfiles;
    private HashMap<Integer, String> listaProfilesAux;
    private HashMap<Integer, String> aux = new HashMap();

    public ProfilePanel() {
        initComponents();
        fillStuff();
        delBtn.setEnabled(false);
        loadBtn.setEnabled(false);
        getSaves();
        setCrazy();
    }

    public void setCrazy() {
        try {
            File file = new File(
                getClass()
                        .getClassLoader()
                        .getResource("resources/fonts/byte.ttf")
                        .getFile()
            );
            Font crazy = Font.createFont(Font.TRUETYPE_FONT, file);
            crazy = crazy.deriveFont(14f);
            delBtn.setFont(crazy);
            jButton1.setFont(crazy);
            jLabel1.setFont(crazy);
            jLabel2.setFont(crazy);
            loadBtn.setFont(crazy);
            newBtn.setFont(crazy);
            profileDelBtn.setFont(crazy);
            profileLbl.setFont(crazy);
            savedList.setFont(crazy);
            profileList.setFont(crazy);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public void fillStuff() {
        profileLbl.setText(Engine.selectedProfile);
        listaProfiles = FileLoader.getProfiles();
        listaProfilesAux = FileLoader.getProfiles();
        DefaultListModel a = new DefaultListModel();
        int control = 1;
        while (!listaProfilesAux.isEmpty()) {
            if (listaProfilesAux.containsKey(control)) {
                a.addElement(listaProfilesAux.get(control).replace("*", ""));
                listaProfilesAux.remove(control);
            }
            control++;
        }
        profileList.setModel(a);
        //listaProfilesAux = null;
    }

    public void getSaves() {
        if (!Engine.selectedProfile.equals("<No Profile Loaded>")) {
            DefaultListModel a = new DefaultListModel();
            File dir = new File("saved/" + Engine.selectedProfile);
            String[] children = dir.list();
            if (children == null) {
                delBtn.setEnabled(false);
                loadBtn.setEnabled(false);
            } else {
                for (int i = 0; i < children.length; i++) {
                    a.addElement("" + children[i]);
                }
                delBtn.setEnabled(true);
                loadBtn.setEnabled(true);
            }
            savedList.setModel(a);
        } else {
            delBtn.setEnabled(false);
            loadBtn.setEnabled(false);
            savedList.setEnabled(false);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        profileDelBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        profileList = new javax.swing.JList();
        newBtn = new javax.swing.JButton();
        profileLbl = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        savedList = new javax.swing.JList();
        jLabel2 = new javax.swing.JLabel();
        delBtn = new javax.swing.JButton();
        loadBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Load Profile");
        setLocationByPlatform(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(1, 1, 1));
        jPanel1.setForeground(new java.awt.Color(4, 255, 0));

        profileDelBtn.setBackground(new java.awt.Color(0, 0, 0));
        profileDelBtn.setFont(new java.awt.Font("Chiller", 1, 18)); // NOI18N
        profileDelBtn.setForeground(new java.awt.Color(0, 255, 0));
        profileDelBtn.setText("Eliminar");
        profileDelBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        profileDelBtn.setContentAreaFilled(false);
        profileDelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                profileDelBtnActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(null);

        profileList.setBackground(new java.awt.Color(51, 51, 51));
        profileList.setFont(new java.awt.Font("Chiller", 0, 20)); // NOI18N
        profileList.setForeground(new java.awt.Color(255, 255, 255));
        profileList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        profileList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                profileListValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(profileList);

        newBtn.setBackground(new java.awt.Color(0, 0, 0));
        newBtn.setFont(new java.awt.Font("Chiller", 1, 18)); // NOI18N
        newBtn.setForeground(new java.awt.Color(0, 255, 0));
        newBtn.setText("Nuevo");
        newBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        newBtn.setContentAreaFilled(false);
        newBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newBtnActionPerformed(evt);
            }
        });

        profileLbl.setBackground(new java.awt.Color(0, 0, 0));
        profileLbl.setFont(new java.awt.Font("Chiller", 0, 18)); // NOI18N
        profileLbl.setForeground(new java.awt.Color(255, 255, 255));
        profileLbl.setText("<error>");

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Chiller", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Perfil Actual:");

        jButton1.setBackground(new java.awt.Color(0, 0, 0));
        jButton1.setFont(new java.awt.Font("Chiller", 1, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(0, 255, 0));
        jButton1.setText("Salir");
        jButton1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        jButton1.setContentAreaFilled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        savedList.setBackground(new java.awt.Color(51, 51, 51));
        savedList.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        savedList.setForeground(new java.awt.Color(254, 254, 254));
        savedList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(savedList);

        jLabel2.setForeground(new java.awt.Color(254, 254, 254));
        jLabel2.setText("Juegos Guardados:");

        delBtn.setBackground(new java.awt.Color(0, 0, 0));
        delBtn.setFont(new java.awt.Font("Chiller", 1, 18)); // NOI18N
        delBtn.setForeground(new java.awt.Color(0, 255, 0));
        delBtn.setText("Borrar");
        delBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        delBtn.setContentAreaFilled(false);
        delBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delBtnActionPerformed(evt);
            }
        });

        loadBtn.setBackground(new java.awt.Color(0, 0, 0));
        loadBtn.setFont(new java.awt.Font("Chiller", 1, 18)); // NOI18N
        loadBtn.setForeground(new java.awt.Color(0, 255, 0));
        loadBtn.setText("Cargar");
        loadBtn.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 2, true));
        loadBtn.setContentAreaFilled(false);
        loadBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(profileLbl, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                        .addGap(83, 83, 83))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(delBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(loadBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(newBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(profileDelBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(profileLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(loadBtn)
                            .addComponent(delBtn))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newBtn)
                    .addComponent(profileDelBtn)
                    .addComponent(jButton1))
                .addContainerGap(47, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    static public boolean deleteDirectory(File path) {
        if (path.exists()) {
            File[] files = path.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                } else {
                    files[i].delete();
                }
            }
        }
        return (path.delete());
    }

    public void doLoad(String sav) {
        Save s = FileLoader.loadGame(
                "saved/" + Engine.selectedProfile + "/" + sav);
        Engine.staticOpt.loadConfig();
        Engine.loadGame(s);
        Menu.setControl(false);
        Menu.sound.stop();
    }

private void profileDelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_profileDelBtnActionPerformed
    int indx = profileList.getSelectedIndex();
    String val = "" + profileList.getSelectedValue();
    if (!val.equals("Default")) {
        DefaultListModel a = (DefaultListModel) profileList.getModel();
        int choice = JOptionPane.showConfirmDialog(this,
                "Seguro que quieres eliminar este perfil y todo su contenido?",
                "Eliminar Perfil...", JOptionPane.OK_CANCEL_OPTION);
        if (choice == 0) {
            File dir = new File("saved/" + a.getElementAt(indx));
            if (deleteDirectory(dir)) {
                a.remove(indx);
                listaProfiles.remove(val);
                JOptionPane.showMessageDialog(null, "Perfil Eliminado...");
            } else {
                a.remove(indx);
                listaProfiles.remove(val);
                JOptionPane.showMessageDialog(null, "Perfil Eliminado...");
            }
            if (a.getSize() == 0) {
                a.addElement("Default");
                File newdir = new File("saved/Default");
                newdir.mkdir();
            }
            profileList.setModel(a);
        }
    } else {
        JOptionPane.showMessageDialog(null,
                "El perfil \"Default\" no puede ser Eliminado...");
    }
    profileList.setSelectedIndex(0);
}//GEN-LAST:event_profileDelBtnActionPerformed

private void newBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newBtnActionPerformed
    String add = JOptionPane.showInputDialog(this, "Nombre del Perfil Nuevo?",
            null, 3);
    if (!(listaProfiles.containsValue(add))) {
        DefaultListModel a = (DefaultListModel) profileList.getModel();
        File newdir = new File("saved/" + add);
        newdir.mkdir();
        a.addElement(add);
        profileList.setModel(a);
    } else {
        JOptionPane
                .showMessageDialog(null, "El perfil " + add + " ya existe...");
    }
    profileList.setSelectedIndex(0);
}//GEN-LAST:event_newBtnActionPerformed

private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
    doExit();
}//GEN-LAST:event_formWindowClosing

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    this.dispose();
    doExit();
}//GEN-LAST:event_jButton1ActionPerformed

private void delBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delBtnActionPerformed
// TODO add your handling code here:
    int indx = savedList.getSelectedIndex();
    DefaultListModel a = (DefaultListModel) savedList.getModel();
    int choice = JOptionPane.showConfirmDialog(this,
            "Seguro que quieres borrar este Save?", "Borrar Juego Salvado...",
            JOptionPane.OK_CANCEL_OPTION);
    if (choice == 0) {
        File dir = new File("saved/" + Engine.selectedProfile + "/" + a
                .getElementAt(indx));
        if (dir.delete()) {
            JOptionPane.showMessageDialog(null,
                    "Perfil y Juegos Salvados borrados...");
            a.remove(indx);
        }
        if (a.getSize() == 0) {
            delBtn.setEnabled(false);
            loadBtn.setEnabled(false);
            savedList.setEnabled(false);
        }
        savedList.setModel(a);
    }
}//GEN-LAST:event_delBtnActionPerformed

private void loadBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadBtnActionPerformed
// TODO add your handling code here:
    String tmp = "" + savedList.getSelectedValue();
    if (savedList.getSelectedValue() != null) {
        this.hide();
        doLoad(tmp);
        this.dispose();
    }
}//GEN-LAST:event_loadBtnActionPerformed

private void profileListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_profileListValueChanged
// TODO add your handling code here:
    setSelectedProfile("" + profileList.getSelectedValue());
}//GEN-LAST:event_profileListValueChanged

    public void doExit() {
        DefaultListModel a = (DefaultListModel) profileList.getModel();
        for (int i = 0; i < a.getSize(); i++) {
            String tmp = "" + a.getElementAt(i);
            if (tmp.equals(Engine.selectedProfile)) {
                aux.put(i + 1, tmp + "*");
            } else {
                aux.put(i + 1, tmp);
            }
        }
        FileLoader.saveProfiles(aux);
    }

    public void setSelectedProfile(String s) {
        if (s != null) {
            Engine.selectedProfile = s;
            profileLbl.setText(Engine.selectedProfile);
            Menu.setProfileLbl(Engine.selectedProfile);
            getSaves();
        } else {
            Engine.selectedProfile = "Default";
            profileLbl.setText(Engine.selectedProfile);
            Menu.setProfileLbl(Engine.selectedProfile);
            getSaves();
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton delBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton loadBtn;
    private javax.swing.JButton newBtn;
    private javax.swing.JButton profileDelBtn;
    private javax.swing.JLabel profileLbl;
    private javax.swing.JList profileList;
    private javax.swing.JList savedList;
    // End of variables declaration//GEN-END:variables

}
