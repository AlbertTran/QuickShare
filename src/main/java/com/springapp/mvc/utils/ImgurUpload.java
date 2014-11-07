package com.springapp.mvc.utils;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;



//Classe d'utilisation de l'api imgur afin d'obtenir une url à partir d'une image
//api de imgur utilisée afin de ne pas manipuler des url locales
public class ImgurUpload {

    private final static String IMGUR_API_URL = "https://api.imgur.com/3/image";
    private final static String IMGUR_CLIENT_ID = "d1e58300898371c";

    public static JSONObject getImgurResponse(BufferedImage image) throws Exception {
        URL url;
        url = new URL(IMGUR_API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        //conversion de l'image en base64 pour l'envoyer à l'API
        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArray);
        byte[] byteImage = byteArray.toByteArray();
        String dataImage = Base64.encode(byteImage);

        String data = URLEncoder.encode("image", "UTF-8") + "="
                + URLEncoder.encode(dataImage, "UTF-8");

        conn.setDoOutput(true);
        conn.setDoInput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Authorization", "Client-ID " + IMGUR_CLIENT_ID);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type",
                "application/x-www-form-urlencoded");

        conn.connect();
        StringBuilder stb = new StringBuilder();
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(data);
        wr.flush();

        // Get the response
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            stb.append(line).append("\n");
        }
        wr.close();
        rd.close();
        JSONObject json = new JSONObject (stb.toString());
        return json.optJSONObject("data");
    }
}
