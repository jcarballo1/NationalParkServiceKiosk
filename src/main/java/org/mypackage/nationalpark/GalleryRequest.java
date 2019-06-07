package org.mypackage.nationalpark;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import org.json.JSONArray;
import org.json.JSONObject;
import org.mypackage.nationalpark.MapResult;
import sun.misc.BASE64Encoder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jcarb
 */
public class GalleryRequest {

    private String jsonString;
    private ArrayList<Image> images = new ArrayList<>();

    public static class MyHostnameVerifier implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            // verification of hostname is switched off
            return true;
        }
    }

    public ArrayList<Image> sendGet(String desig) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/parks?parkCode=";
        baseURL += desig;
        baseURL += "&fields=images&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
        URL url = new URL(baseURL);
        String userName = "admin";
        String password = "admin";
        String authentication = "CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";

        HttpURLConnection connection = null;
        connection = (HttpsURLConnection) url.openConnection();
        ((HttpsURLConnection) connection).setHostnameVerifier(new MyHostnameVerifier());
        connection.setRequestProperty("Content-Type", "text/plain; charset=\"utf8\"");
        connection.setRequestMethod("GET");
        BASE64Encoder encoder = new BASE64Encoder();
        String encoded = encoder.encode((authentication).getBytes("UTF-8"));
        connection.setRequestProperty("Authorization", encoded);
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
        connection.setDoOutput(true);
        connection.connect();

        int responseCode = connection.getResponseCode();

        InputStream input = (InputStream) connection.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        jsonString = sb.toString();
        parseJSON();
        return images;
    }

    public void parseJSON() throws Exception {
        JSONObject mainObj = new JSONObject(jsonString);
        JSONArray array = mainObj.getJSONArray("data");
        String url = "";
        String cap = "";

        JSONObject obj = array.getJSONObject(0);
        JSONArray subArray = obj.getJSONArray("images");
        for (int i = 0; i < subArray.length(); i++) {
            JSONObject subObj = subArray.getJSONObject(i);
            try {
                url = subObj.getString("url");
            } catch (Exception e) {
            }

            try {
                cap = subObj.getString("caption");
            } catch (Exception e) {
            }

            images.add(new Image(url, cap));
        }
    }
}
