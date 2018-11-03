/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.xml.bind.DatatypeConverter;
import javaxt.io.Image;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/**
 *
 * @author jessi
 */
public class Images {

    public static void getImage(JSONObject object) throws FileNotFoundException, IOException, JSONException {
         
        String url=object.getString("data-uri");
        String[] parts = url.split("data:image/jpeg;base64,");

        for (int j = 1; j < parts.length; j++) {
            //convert base64 string to binary data
            byte[] data = DatatypeConverter.parseBase64Binary(parts[j]);
            String path = "test_image" + j + ".png";
            File file = new File(path);
            try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
                outputStream.write(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void cut(JSONObject object) throws JSONException {
        JSONArray features = (JSONArray) object.get("objects");
        for (int i = 0; i < features.length(); i++) {
            JSONObject temp = features.getJSONObject(i);
            if (temp.getString("type").equals("rect")) {
                Image image = new javaxt.io.Image("classes.jpg");
                image.crop(
                        temp.getInt("left"),
                        temp.getInt("top"),
                        temp.getInt("width"),
                        temp.getInt("height")
                );
                image.saveAs("C:\\Users\\jessi\\Downloads\\Prueba" + i + ".jpg");
            }

        }

    }
}
