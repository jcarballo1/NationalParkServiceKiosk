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
public class EdSearchRequest {

    private ArrayList<EdSearchResult> results;
    private String jsonString;

    public static class MyHostnameVerifier implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            // verification of hostname is switched off
            return true;
        }
    }

    public ArrayList<EdSearchResult> process(String desigs, String states, String key) throws Exception {
        results = new ArrayList<>();
        if (key.equals("")) {
            sendLessonGet(desigs, states);
            sendPeopleGet(desigs, states);
            sendPlaceGet(desigs, states);
        } else if (key.equals("less")) {
            sendLessonGet(desigs, states);
        } else if (key.equals("peop")) {
            sendPeopleGet(desigs, states);
        } else if (key.equals("plac")) {
            sendPlaceGet(desigs, states);
        }
        return results;
    }

    public void sendLessonGet(String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/lessonplans?parkCode=";
        baseURL += desigs;
        baseURL += "&stateCode=" + states;
        baseURL += "&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
        URL url = new URL(baseURL);
        String userName = "admin";
        String password = "admin";
        String authentication = "CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";

        HttpURLConnection connection = null;
        connection = (HttpsURLConnection) url.openConnection();
        ((HttpsURLConnection) connection).setHostnameVerifier(new VCenterSearchRequest.MyHostnameVerifier());
        connection.setRequestProperty("Content-Type", "text/plain; charset=\"UTF-8\"");
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
        jsonString = jsonString.replaceAll("\'", "&#39");
        parseLessonJSON();
    }

    public void sendPeopleGet(String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/people?parkCode=";
        baseURL += desigs;
        baseURL += "&stateCode=" + states;
        baseURL += "&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
        URL url = new URL(baseURL);
        String userName = "admin";
        String password = "admin";
        String authentication = "CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";

        HttpURLConnection connection = null;
        connection = (HttpsURLConnection) url.openConnection();
        ((HttpsURLConnection) connection).setHostnameVerifier(new VCenterSearchRequest.MyHostnameVerifier());
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
        jsonString = jsonString.replaceAll("\'", "&#39");
        parsePeoplePlaceJSON("Person");
    }

    public void sendPlaceGet(String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/places?parkCode=";
        baseURL += desigs;
        baseURL += "&stateCode=" + states;
        baseURL += "&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
        URL url = new URL(baseURL);
        String userName = "admin";
        String password = "admin";
        String authentication = "CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";

        HttpURLConnection connection = null;
        connection = (HttpsURLConnection) url.openConnection();
        ((HttpsURLConnection) connection).setHostnameVerifier(new VCenterSearchRequest.MyHostnameVerifier());
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
        jsonString = jsonString.replaceAll("\'", "&#39");
        parsePeoplePlaceJSON("Place");
    }

    public void parseLessonJSON() throws Exception {
        JSONObject mainObj = new JSONObject(jsonString);
        JSONArray array = mainObj.getJSONArray("data");
        boolean good = true;

        for (int i = 0; i < array.length(); i++) {
            String title = "";
            String url = "";
            String object = "";
            String subject = "";

            JSONObject subObj = array.getJSONObject(i);
            JSONArray curr = null;
            JSONObject currObj = null;
            int j = 0;

            try {
                title = subObj.getString("title");
            } catch (Exception e) {
            }

            try {
                url = subObj.getString("url");
            } catch (Exception e) {
            }

            try {
                object = subObj.getString("questionobjective");
            } catch (Exception e) {
            }

            try {
                subject = subObj.getString("subject");
            } catch (Exception e) {
            }

            results.add(new EdSearchResult(title, url, object, subject));
        }
    }

    public void parsePeoplePlaceJSON(String key) throws Exception {
        JSONObject mainObj = new JSONObject(jsonString);
        JSONArray array = mainObj.getJSONArray("data");

        for (int i = 0; i < array.length(); i++) {
            String imageURL = "";
            String listing = "";
            String title = "";
            String url = "";

            JSONObject subObj = array.getJSONObject(i);
            JSONArray curr = null;
            JSONObject currObj = null;
            int j = 0;
            boolean good = true;

            try {
                currObj = subObj.getJSONObject("listingimage");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                imageURL = currObj.getString("url");
            }

            try {
                listing = subObj.getString("listingdescription");
            } catch (Exception e) {
            }

            try {
                title = subObj.getString("title");
            } catch (Exception e) {
            }

            try {
                url = subObj.getString("url");
            } catch (Exception e) {
            }

            results.add(new EdSearchResult(imageURL, listing, title, url, key));
        }
    }
}
