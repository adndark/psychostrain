package PsychoSystem;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimedes Diaz)
public class GameImage {

    public static BufferedImage loadImage(String nameImage) {
        try {
            BufferedImage i = ImageIO.read(new File(nameImage));
            return i;
        } catch (Exception e) {
            System.out.print("Error loading method " + nameImage + "\n");
            System.out.print(e.getMessage());
            return null;
        }
    }

    public static BufferedImage[] loadFromFile(String file, String folder) {
        int numImages = 0;
        String data;
        BufferedImage[] Loadedimages;
        int idx = 0;
        try {
            BufferedReader entry = new BufferedReader(new FileReader(file));
            numImages = Integer.parseInt(data = entry.readLine());
            Loadedimages = new BufferedImage[numImages];
            while ((data = entry.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(data);
                Loadedimages[idx++] = loadImage("sprites/" + folder + "/" + st
                        .nextToken());
            }
            return Loadedimages;
        } catch (IOException ioe) {
            System.out.println("error de loadFromFile");
            System.out.println(ioe.getMessage());
        }
        return null;
    }

    public static int[] loadFromFileSprites(String file) {
        int numImages = 0;
        String data;
        int[] Sprites;
        int idx = 0;
        try {
            BufferedReader entry = new BufferedReader(new FileReader(file));
            numImages = Integer.parseInt(data = entry.readLine());
            Sprites = new int[numImages];

            while ((data = entry.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(data);
                st.nextToken();
                Sprites[idx++] = Integer.parseInt(st.nextToken());
            }
            return Sprites;
        } catch (IOException ioe) {
            System.out.println(
                    "Error loading images... loadFromFileSprites method.");
        }
        return null;
    }
}
