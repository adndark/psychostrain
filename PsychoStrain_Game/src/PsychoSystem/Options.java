package PsychoSystem;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import javax.swing.JOptionPane;
public class Options {
    //variables de instancia

    private HashMap<String, Integer> hashMap;
    private static boolean configLoaded = false;

    //Metodo secundario de loadConfigFromFile, este lo invoca y setea todas las instancias de opciones
    public void loadConfig() {
        if (!configLoaded) {
            hashMap = FileLoader.getConfig();
            configLoaded = true;
        }
    }

    public void loadConfig(String s) {
        if (!configLoaded) {
            hashMap = FileLoader.getConfig(s);
            configLoaded = true;
        }
    }

    public void saveConfig(String s) {
        if (configLoaded) {
            FileLoader.saveConfig(hashMap, s);
        }
    }

    public void saveConfig() {
        if (configLoaded) {
            FileLoader.saveConfig(hashMap);
        }
    }

    //Este metodo es el que carga las opciones default en caso de que todo falle
    public void loadDefaults() {
        configLoaded = true;
        hashMap.put("Disparo", 1);
        hashMap.put("Cmd", 112);
        hashMap.put("Derecha", 68);
        hashMap.put("Izquierda", 65);
        hashMap.put("Pausa", 10);
        hashMap.put("Salto", 32);
        hashMap.put("Up", 87);
        hashMap.put("Abajo", 83);
        hashMap.put("Guns", 81);
        saveConfig();
        reset();
    }

    //todos los getters y setters para hacer uso de las opciones, no sirven
    //hasta que la configuracion haya sido cargada
    public int getUp() {
        if (configLoaded) {
            return hashMap.get("Up");
        }
        return 1;
    }

    public int getDisparo() {
        if (configLoaded) {
            return hashMap.get("Disparo");
        }
        return 1;
    }

    public int getCmd() {
        if (configLoaded) {
            return hashMap.get("Cmd");
        }
        return 112;
    }

    public int getDerecha() {
        if (configLoaded) {
            return hashMap.get("Derecha");
        }
        return 68;
    }

    public int getIzquierda() {
        if (configLoaded) {
            return hashMap.get("Izquierda");
        }
        return 65;
    }

    public int getPausa() {
        if (configLoaded) {
            return hashMap.get("Pausa");
        }
        return 10;
    }

    public int getSalto() {
        if (configLoaded) {
            return hashMap.get("Salto");
        }
        return 32;
    }

    public int getAbajo() {
        if (configLoaded) {
            return hashMap.get("Abajo");
        }
        return 83;
    }

    public void setAbajo(int teclaAbajo) {
        if (configLoaded) {
            hashMap.put("Abajo", teclaAbajo);
        }
    }

    public void setUp(int teclaUp) {
        if (configLoaded) {
            hashMap.put("Up", teclaUp);
        }
    }

    public void setDisparo(int botonDisparo) {
        if (configLoaded) {
            hashMap.put("Disparo", botonDisparo);
        }
    }

    public void setCmd(int teclaCmd) {
        if (configLoaded) {
            hashMap.put("Cmd", teclaCmd);
        }
    }

    public void setDerecha(int teclaDerecha) {
        if (configLoaded) {
            hashMap.put("Derecha", teclaDerecha);
        }
    }

    public void setIzquierda(int teclaIzquierda) {
        if (configLoaded) {
            hashMap.put("Izquierda", teclaIzquierda);
        }
    }

    public void setPausa(int teclaPausa) {
        if (configLoaded) {
            hashMap.put("Pausa", teclaPausa);
        }
    }

    public void setSalto(int teclaSalto) {
        if (configLoaded) {
            hashMap.put("Salto", teclaSalto);
        }
    }

    public void setDificultad(int dificultad) {
        if (configLoaded) {
            hashMap.put("Dificultad", dificultad);
        }
    }

    public void setGuns(int guns) {
        if (configLoaded) {
            hashMap.put("Guns", guns);
        }
    }

    public int getGuns() {
        if (configLoaded) {
            return hashMap.get("Guns");
        }
        return java.awt.event.KeyEvent.VK_Q;
    }

    public void reset() {
        configLoaded = false;
    }

    public boolean contains(Object val) {
        if (hashMap.containsValue(val)) {
            JOptionPane.showMessageDialog(null, "La Tecla " + KeyEvent
                    .getKeyText((Integer) val) + " ya esta utilizada...");
            return true;
        } else {
            return false;
        }
    }

    //EOF :D
}
