/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;
import javaxt.io.Image;
import org.apache.commons.io.FileUtils;
import org.json.*;
import sun.misc.BASE64Encoder;

/**
 *
 * @author Jessica Roman
 */
public class GetJson {
    
    List<Image> imgs;
    Image img = null;

    public void get(String content, String path) throws IOException, JSONException {

        JSONObject json = new JSONObject(content);

        JSONArray objects = new JSONArray();

        objects = json.getJSONArray("objects");

        String substring = objects.getJSONObject(0).get("src").toString();
        String[] src = substring.split("data:image/jpeg;base64,");
        getImageParking(src[1], path);
        //System.out.println(json.toString());
        
        for (int i = 1; i < objects.length(); i++) {
            int x = objects.getJSONObject(i).getInt("left");
            int y = objects.getJSONObject(i).getInt("top");
            int width = objects.getJSONObject(i).getInt("width");
            int height = objects.getJSONObject(i).getInt("height");
            double scaleX=objects.getJSONObject(i).getDouble("scaleX");
            double scaleY=objects.getJSONObject(i).getDouble("scaleY");
                      
            img.copyRect(x, y, Math.round(width*(float) scaleX),Math.round(height*(float) scaleY)).saveAs(path + "\\" + (i) + ".jpg");
        }

    }

    public void getImageParking(String src, String dir) {

        byte[] data = DatatypeConverter.parseBase64Binary(src);
//        String path = dir + "\\" + (1) + ".jpg";
//        File file = new File(path);
//        try (OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
//            outputStream.write(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        img =new Image(data);
    }

    public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = "data:image/jpeg;base64," + encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }

}
