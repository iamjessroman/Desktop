/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desktop;

import java.io.*;
import java.util.*;
import org.apache.commons.io.FileUtils;
import org.json.*;

/**
 *
 * @author jessi
 */
public class ReadJson {

    public JSONObject jsonFileRead() throws JSONException, FileNotFoundException, IOException {
        String fileName = "classes.json";
        File file = new File(fileName);
        String content = FileUtils.readFileToString(file, "utf-8");
        JSONObject object = new JSONObject(content);
        return(object);
    }

}
