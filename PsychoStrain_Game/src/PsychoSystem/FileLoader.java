package PsychoSystem;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimedes Diaz)
import PsychoGame.Engine;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

public class FileLoader {

    private static final String[] configArray = {"Salto", "Up", "Derecha",
        "Izquierda", "Abajo", "Disparo", "Cmd", "Pausa", "Guns"};

    public static HashMap<String, Integer> getConfig() {
        HashMap<String, Integer> hashMap = new HashMap();
        int i = 0;
        String aux;
        InputStream inputStream = getInputStream("configurations/keyboard/defaultConfig.cfg");
        BufferedReader getCfgFile = new BufferedReader(new InputStreamReader(inputStream)); //gameConfig.cfg DUH
        try {
            while ((aux = getCfgFile.readLine()) != null) {
                if (!(aux.charAt(0) == '#' || aux.charAt(0) == ' ')) {
                    hashMap.put(configArray[i], Integer.parseInt(aux));
                    i++;
                }
            }
            getCfgFile.close();
        } catch (IOException ex) {
            System.out.println("Error when reading file");
            JOptionPane.showMessageDialog(null, ex);
        } catch (NullPointerException ex) {
            System.out.println("Error when reading file");
            JOptionPane.showMessageDialog(null,
                    "El archivo de configuracion esta mal formado\nSe cargaran los defaults para crear uno nuevo.");
            return hashMap;
        }
        return hashMap;
    }

    public static HashMap<String, Integer> getConfig(String s) {
        HashMap<String, Integer> hashMap = new HashMap();
        int i = 0;
        String aux;
        try {
            BufferedReader getCfgFile = new BufferedReader(new FileReader(s)); //gameConfig.cfg DUH
            try {
                while ((aux = getCfgFile.readLine()) != null) {
                    if (!(aux.charAt(0) == '#' || aux.charAt(0) == ' ')) {
                        hashMap.put(configArray[i], Integer.parseInt(aux));
                        i++;
                    }
                }
                getCfgFile.close();
            } catch (IOException ex) {
                System.out.println("Input error");
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, ex);
            } catch (NullPointerException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null,
                        "El archivo de configuracion esta mal formado\n"
                        + "Asegurate que tiene solo " + configArray.length + " opciones");
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Error when reading file, not found");
            JOptionPane.showMessageDialog(null, ex);
        }
        return hashMap;
    }

    public static int[][] getMap(String fileName) {
        String aux, acum = "", tmp;
        int x = 0, y = 0, num = 0;

        InputStream inputStream = getInputStream(fileName);
        BufferedReader getCfgFile = new BufferedReader(new InputStreamReader(
                inputStream));
        try {
            while ((aux = getCfgFile.readLine()) != null) {
                if (!(aux.charAt(0) == '#' || aux.charAt(0) == ' ' || aux
                        .charAt(0) == '>')) {
                    acum += aux + ".";
                    y++;
                }
            }
            getCfgFile.close();
        } catch (IOException | NullPointerException
                | StringIndexOutOfBoundsException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        //parte de creacion de matriz del metodo
        StringTokenizer st = new StringTokenizer(acum, ".");
        int[][] map = new int[(st.countTokens())][st.nextToken().length() / 2];
        aux = "";
        y = 0;
        while (st.hasMoreTokens()) {
            aux = st.nextToken();
            for (int i = 0; i < aux.length(); i += 2) {
                tmp = "" + aux.charAt(i) + "" + aux.charAt(i + 1);
                if (tmp.charAt(0) == '0') {
                    num = (tmp.charAt(1) - '0');
                } else {
                    num = Integer.parseInt(tmp);
                }
                map[y][x] = num;
                x++;
            }
            y++;
            x = 0;
        }
        //Este ciclo es encarga de duplicar la última linea visible.
        for (int i = 0; i < map[0].length; i++) {
            map[map.length - 1][i] = map[map.length - 2][i];
        }
        return map;
    }

    public static ArrayList<String> loadEnemies(String fileName) {
        ArrayList<String> enemies = new ArrayList();
        try {
            InputStream inputStream = getInputStream(fileName);
            BufferedReader fileIn = new BufferedReader(new InputStreamReader(
                    inputStream));
            String aux;
            while ((aux = fileIn.readLine()) != null) {
                if (aux.charAt(0) == '>') {
                    enemies.add(aux.substring(1));
                }
            }
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
        return enemies;
    }

    public static void saveConfig(HashMap<String, Integer> hashMap) {
        PrintWriter setCfgFile;
        try {
            setCfgFile = new PrintWriter(new FileWriter(
                    "configuration/keyboard/defaultConfig.cfg"));
            setCfgFile.println("# Auto-Generated Config File...");
            setCfgFile.println(
                    "# Se recomienda usar el sistema para modificar las opciones");
            setCfgFile.println("# Si se modifica a mano: \n#");
            setCfgFile.println(
                    "# Recuerda que las lineas que comienzan con # son comentarios\n#");
            setCfgFile.println(
                    "# El orden extricto de las opciones es la siguiente:");
            setCfgFile.println("#\t Tecla de Salto");
            setCfgFile.println(hashMap.get(configArray[0]));
            setCfgFile.println("#\t Tecla para Pararse");
            setCfgFile.println(hashMap.get(configArray[1]));//Integer.parseInt((String)hashMap.get(configArray[0]))
            setCfgFile.println("#\t Tecla de Derecha");
            setCfgFile.println(hashMap.get(configArray[2]));
            setCfgFile.println("#\t Tecla de Izquierda");
            setCfgFile.println(hashMap.get(configArray[3]));
            setCfgFile.println("#\t Tecla de Agachar");
            setCfgFile.println(hashMap.get(configArray[4]));
            setCfgFile.println("#\t Boton de Disparo");
            setCfgFile.println(hashMap.get(configArray[5]));
            setCfgFile.println("#\t Tecla para abrir Consola");
            setCfgFile.println(hashMap.get(configArray[6]));
            setCfgFile.println("#\t Tecla para Pausar el juego");
            setCfgFile.println(hashMap.get(configArray[7]));
            setCfgFile.println("#\t Tecla para Cambiar Arma");
            setCfgFile.println(hashMap.get(configArray[8]));
            setCfgFile.close();
            System.out.println("Se guardo la configuracion a gameConfig.cfg");
        } catch (IOException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public static void saveConfig(HashMap<String, Integer> hashMap, String s) {
        PrintWriter setCfgFile;
        try {
            setCfgFile = new PrintWriter(new FileWriter(s));
            setCfgFile.println("# Auto-Generated Config File...");
            setCfgFile.println(
                    "# Se recomienda usar el sistema para modificar las opciones");
            setCfgFile.println("# Si se modifica a mano: \n#");
            setCfgFile.println(
                    "# Recuerda que las lineas que comienzan con # son comentarios\n#");
            setCfgFile.println(
                    "# El orden extricto de las opciones es la siguiente:");
            setCfgFile.println("#\t Tecla de Salto");
            setCfgFile.println(hashMap.get(configArray[0]));
            setCfgFile.println("#\t Tecla para Pararse");
            setCfgFile.println(hashMap.get(configArray[1]));//Integer.parseInt((String)hashMap.get(configArray[0]))
            setCfgFile.println("#\t Tecla de Derecha");
            setCfgFile.println(hashMap.get(configArray[2]));
            setCfgFile.println("#\t Tecla de Izquierda");
            setCfgFile.println(hashMap.get(configArray[3]));
            setCfgFile.println("#\t Tecla de Agachar");
            setCfgFile.println(hashMap.get(configArray[4]));
            setCfgFile.println("#\t Boton de Disparo");
            setCfgFile.println(hashMap.get(configArray[5]));
            setCfgFile.println("#\t Tecla para abrir Consola");
            setCfgFile.println(hashMap.get(configArray[6]));
            setCfgFile.println("#\t Tecla para Pausar el juego");
            setCfgFile.println(hashMap.get(configArray[7]));
            setCfgFile.println("#\t Tecla para Cambiar Arma");
            setCfgFile.println(hashMap.get(configArray[8]));
            setCfgFile.close();
            System.out.println("Se guardo la configuracion a gameConfig.cfg");
        } catch (IOException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public static HashMap getTiles(String fileName) {
        HashMap<Integer, BufferedImage> hashMap = new HashMap();
        int i = 1;
        String aux;
        InputStream inputStream = getInputStream(fileName);
        BufferedReader getCfgFile = new BufferedReader(
                new InputStreamReader(inputStream)); //archivo tileList
        try {
            while ((aux = getCfgFile.readLine()) != null) {
                if (!(aux.charAt(0) == '#' || aux.charAt(0) == ' ')) {
                    hashMap.put(i, GameImage.loadImage(
                            "resources/tiles/" + aux));
                    i++;
                }
            }
            getCfgFile.close();
        } catch (IOException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        } catch (NullPointerException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,
                    "la lista de tiles esta mal formada\n");
        }
        return hashMap;
    }

    public static Object loadChallenge(String fileName) {
        Object obj;
        try {
            try (ObjectInputStream objRead =
                    new ObjectInputStream(
                            new FileInputStream(fileName))) {
                obj = objRead.readObject();
            }
            return obj;
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
            return null;
        }
    }

    public static void saveChallenge(String fileName, Object obj) {
        try {
            try (ObjectOutputStream saveObj =
                    new ObjectOutputStream(
                            new FileOutputStream(fileName))) {
                saveObj.writeObject(obj);
                saveObj.flush();
            }
        } catch (IOException e) {
            System.out.println("Error when saving challenge " + e);
        }
    }

    public static HashMap loadMails(String fileName) {
        HashMap<Integer, String> hashMap = new HashMap();
        int i = 0;
        String aux;
        InputStream inputStream = getInputStream(fileName);
        BufferedReader getCfgFile = new BufferedReader(new InputStreamReader(
                inputStream));
        try {
            while ((aux = getCfgFile.readLine()) != null) {
                if (!(aux.charAt(0) == '#' || aux.charAt(0) == ' ')) {
                    aux = aux.replace("$", "\n\t");
                    if (aux.contains("%")) {
                        String a = KeyEvent.getKeyText(Engine.staticOpt
                                .getIzquierda()) + "-->Izquierda" + "\n\t" + KeyEvent
                                        .getKeyText(Engine.staticOpt
                                                .getDerecha()) + "-->Derecha" + "\n\t"
                                + KeyEvent.getKeyText(Engine.staticOpt
                                        .getAbajo()) + "-->Ducking" + "\n\t" + KeyEvent
                                                .getKeyText(Engine.staticOpt.getUp()) + "-->Arriba" + "\n\t"
                                + KeyEvent.getKeyText(Engine.staticOpt
                                        .getSalto()) + "-->Salto" + "\n\t" + KeyEvent
                                                .getKeyText(Engine.staticOpt
                                                        .getCmd()) + "-->Consola";
                        
                        aux = aux.replace("%", "" + a);
                        
                    }
                    hashMap.put(i, aux);
                    i++;
                }
            }
            getCfgFile.close();
        } catch (IOException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        } catch (NullPointerException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null,
                    "El archivo de configuracion esta mal formado\nSe cargaran los defaults para crear uno nuevo.");
            return hashMap;
        }
        return hashMap;
    }

    /*
   public static HashMap loadMails(String fileName){
    HashMap<Integer,String> hashMap = new HashMap();
      int i=0; String aux;
      try {
        BufferedReader getCfgFile = new BufferedReader(new FileReader(fileName)); 
        try {
          while ((aux=getCfgFile.readLine()) != null) {
            if (!(aux.charAt(0) == '#' ||aux.charAt(0) == ' ')) {
              aux=aux.replace("$","\n\t");
              hashMap.put(i,aux); 
              i++;              
            }
          }
          getCfgFile.close();
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(null, ex);
        } catch (NullPointerException ex){
          JOptionPane.showMessageDialog(null,"El archivo de configuracion esta mal formado\nSe cargaran los defaults para crear uno nuevo.");
          return hashMap;
        }
      } catch (FileNotFoundException ex) {
        JOptionPane.showMessageDialog(null, "No se encontro el archivo de configuracion! ("+ex+")\nSe cargaran los defaults para crear uno nuevo.");
        return hashMap;
      }
      return hashMap;
   }*/
    public static void saveGame(String fileName) {
        PsychoSystem.Save s = new PsychoSystem.Save();
        try {
            try (ObjectOutputStream saveObj =
                    new ObjectOutputStream(
                            new FileOutputStream(fileName))) {
                saveObj.writeObject(s);
                saveObj.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public static PsychoSystem.Save loadGame(String fileName) {
        try {
            Save save;
            try (ObjectInputStream objRead =
                    new ObjectInputStream(
                            new FileInputStream(fileName))) {
                save = (Save) objRead.readObject();
            }
            return save;
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
            System.out.println("Error al cargar el archivo " + fileName);
            return null;
        }
    }

    public static HashMap getProfiles() {
        HashMap<Integer, String> hashMap = new HashMap();
        int i = 1;
        String aux;
        try {
            BufferedReader getCfgFile = new BufferedReader(new FileReader(
                    "saved/profileList"));
            try {
                while ((aux = getCfgFile.readLine()) != null) {
                    if (!(aux.charAt(0) == '#' || aux.charAt(0) == ' ')) {
                        hashMap.put(i, aux);
                        i++;
                    }
                }
                getCfgFile.close();
            } catch (IOException | NullPointerException ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(null, ex);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
        return hashMap;
    }

    public static void saveProfiles(HashMap<Integer, String> hashMap) {
        PrintWriter setCfgFile;
        int i = 1;
        try {
            setCfgFile = new PrintWriter(new FileWriter("saved/profileList"));
            setCfgFile.println(
                    "#\n# *Importante* NO MODIFICAR!\n# Para modificar profiles utiliza el menu del juego!\n#");
            while (!hashMap.isEmpty()) {
                if (hashMap.containsKey(i)) {
                    String aux = hashMap.get(i);
                    setCfgFile.println(aux);
                    hashMap.remove(i);
                }
                i++;
            }
            setCfgFile.close();
        } catch (IOException ex) {
            System.out.println(ex);
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    private static InputStream getInputStream(String fileName) {

        return FileLoader.class
                .getClassLoader()
                .getResourceAsStream(fileName);
    }
}
