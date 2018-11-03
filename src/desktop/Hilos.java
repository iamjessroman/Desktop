package desktop;

import java.util.logging.Level;
import java.util.logging.Logger;
import javaxt.io.Image;
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
            image.saveAs("C:\\Users\\jessi\\Downloads\\Prueba" + i + ".jpg");
            System.out.println(i);
        } catch (JSONException ex) {
            Logger.getLogger(Hilos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
