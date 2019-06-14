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

/**
 * EdSearchRequest
 * @author Jennifer Carballo
 * Process Education search query, opens
 * connection to NPS API call, parses returned JSON result, creates necessary
 * object with the information to be displayed later
 */
public class EdSearchRequest {

    private ArrayList<EdSearchResult> results;
    private String jsonString;
    private String desigs;
    private String states;
    private String keywords[];

    public static class MyHostnameVerifier implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            // verification of hostname is switched off
            return true;
        }
    }

    /**
     * Processes search query and opens connections to api based on type
     * requested
     * @param keys
     * @param ds
     * @param sts
     * @param key
     * @return
     * @throws Exception 
     */
    public ArrayList<EdSearchResult> process(String[] keys, String ds, String sts, String key) throws Exception {
        results = new ArrayList<>();
        desigs = ds;
        states = sts;
        keywords = keys;
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
    
    /**
     * Opens connection using api url
     * @param url
     * @throws Exception 
     */
    public void openConnection(URL url) throws Exception {
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
    }

    /**
     * Creates url from base and adds destination codes, states codes, and keywords
     * @param baseURL
     * @return
     * @throws Exception 
     */
    public URL createURL(String baseURL) throws Exception {
        baseURL += desigs;
        baseURL += "&stateCode=" + states;
        
        if (!keywords[0].equals("")) {
            baseURL += "&q=";
            for (int i = 0; i < keywords.length; i++) {
                if (i == keywords.length - 1) {
                    baseURL += keywords[i];
                } else {
                    baseURL += keywords[i] + "%20";
                }
            }
        }
        
        baseURL += "&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
        URL url = new URL(baseURL);
        return url;
    }

    /**
     * passes in lesson plans url
     * @throws Exception 
     */
    public void sendLessonGet(String desigs, String states) throws Exception {
        openConnection(createURL("https://developer.nps.gov/api/v1/lessonplans?parkCode="));
        parseLessonJSON();
    }

    /**
     * passes in people url
     * @throws Exception 
     */
    public void sendPeopleGet(String desigs, String states) throws Exception {
        openConnection(createURL("https://developer.nps.gov/api/v1/people?parkCode="));
        parsePeoplePlaceJSON("Person");
    }

    /**
     * passes in places url
     * @throws Exception 
     */
    public void sendPlaceGet(String desigs, String states) throws Exception {
        openConnection(createURL("https://developer.nps.gov/api/v1/places?parkCode="));
        parsePeoplePlaceJSON("Place");
    }

    /**
     * parses lesson plans JSON and adds objects to arraylist
     * @throws Exception 
     */
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

    /**
     * parses people/place JSON and adds objects to arraylist
     * @throws Exception 
     */
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
