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

    public void get(String content, String path, int Threads) throws IOException, JSONException {

        JSONObject json = new JSONObject(content);

        JSONArray objects = new JSONArray();

        objects = json.getJSONArray("objects");

        String substring = objects.getJSONObject(0).get("src").toString();
        String[] src = substring.split("data:image/jpeg;base64,");
        getImageParking(src[1], path);
        //System.out.println(json.toString());

        ClassExecutorsClassifiers cef = new ClassExecutorsClassifiers();
        cef.RUN(objects, Threads, img);
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
        img = new Image(data);
    }
}
