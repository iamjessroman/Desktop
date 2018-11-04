package desktop;

import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaxt.io.Image;
import javaxt.utils.string;
import org.json.*;

/**
 *
 * @author jessi
 */
public class Hilos extends Thread {

    private JSONObject object;
    private int i;

    public Hilos(JSONObject msg, int i) {
        this.object = msg;
        this.i = i;
    }

    public void run() {

        try {
            Image image = new javaxt.io.Image("classes.jpg");
            image.crop(
                    object.getInt("left"),
                    object.getInt("top"),
                    object.getInt("width"),
                    object.getInt("height")
            );
            image.getImage();
            for (int j = 0; j < image.getHeight(); j++) {
                for (int k = 0; k < image.getWidth(); k++) {
                    int red = image.getColor(k, j).getRed();
                    int green = image.getColor(k, j).getGreen();
                    int blue = image.getColor(k, j).getBlue();
                    int avg = (red + green + blue) / 3;
                    int p = image.getBufferedImage().getRGB(k, j);
                    p = (avg << 24) | (avg << 16) | (avg << 8) | avg;
                    image.getBufferedImage().setRGB(k, j, p);

                }
            }

            for (int j = 0; j < image.getHeight(); j++) {
                for (int k = 0; k < image.getWidth(); k++) {
                    int red = image.getColor(k, j).getRed();
                    int green = image.getColor(k, j).getGreen();
                    int blue = image.getColor(k, j).getBlue();
                    int number = 112;
                    if (red > number && green > number && blue > number) {
                        image.setColor(k, j, Color.WHITE);
                    } else {
                        image.setColor(k, j, Color.BLACK);

                    }

                }

            }
            image.resize(100, 50);

            System.out.println(i);
        } catch (JSONException ex) {
            Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
