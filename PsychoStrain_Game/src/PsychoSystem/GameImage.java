package PsychoSystem;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import javax.imageio.ImageIO;

//  PsySoft Team 2008
//       (Manuel Espinoza, Alberto Zorrilla, Guillermo Leon y Arquimedes Diaz)
public class GameImage {

    public static BufferedImage loadImage(String nameImage) {
        try {
            InputStream inputStream = getInputStream(nameImage);
            BufferedImage bufferedImage = ImageIO.read(inputStream);
            return bufferedImage;
        } catch (IOException e) {
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
            InputStream inputStream = getInputStream(file);
            BufferedReader entry = new BufferedReader(new InputStreamReader(
                    inputStream));
            numImages = Integer.parseInt(data = entry.readLine());
            Loadedimages = new BufferedImage[numImages];
            while ((data = entry.readLine()) != null) {
                StringTokenizer st = new StringTokenizer(data);
                Loadedimages[idx++] = loadImage(
                        "resources/sprites/" + folder + "/" + st
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
            InputStream inputStream = getInputStream(file);
            BufferedReader entry = new BufferedReader(new InputStreamReader(
                    inputStream));
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
            ioe.printStackTrace();
        }
        return null;
    }

    private static InputStream getInputStream(String filepath) {
        return GameImage.class
                .getClassLoader()
                .getResourceAsStream(filepath);
    }
}
