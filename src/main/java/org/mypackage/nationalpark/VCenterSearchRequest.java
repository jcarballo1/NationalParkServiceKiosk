package org.mypackage.nationalpark;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import sun.misc.BASE64Encoder;
import org.json.*;

/**
 * VCenterSearchRequest
 *
 * @author Jennifer Carballo Process Visitor Center search query, opens
 * connection to NPS API call, parses returned JSON result, creates necessary
 * object with the information to be displayed later
 */
public class VCenterSearchRequest {

    private String jsonString;
    private ArrayList<VCenterSearchResult> results;
    private String[] keywords;
    private String desigs;
    private String states;

    public static class MyHostnameVerifier implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            // verification of hostname is switched off
            return true;
        }
    }

    /**
     * Processes search query and opens connections to api based on type
     * requested
     *
     * @param keywords
     * @param desigs
     * @param states
     * @param key
     * @return
     * @throws Exception
     */
    public ArrayList<VCenterSearchResult> process(String[] words, String ds, String sts, String key) throws Exception {
        results = new ArrayList<>();
        keywords = words;
        desigs = ds;
        states = sts;
        if (key.equals("")) {
            sendGetVC();
            sendGetCamp();
        } else if (key.equals("vc")) {
            sendGetVC();
        } else {
            sendGetCamp();
        }

        return results;
    }

    /**
     * Opens connection using api url
     *
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
    }

    /**
     * Creates url from base and adds destination codes, states codes, and
     * keywords
     *
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
     * calls to open connection through visitor center api url
     *
     * @throws Exception
     */
    public void sendGetVC() throws Exception {
        openConnection(createURL("https://developer.nps.gov/api/v1/visitorcenters?parkCode="));
        parseVCJSON();
    }

    /**
     * Calls to open connection through campground api url
     *
     * @throws Exception
     */
    public void sendGetCamp() throws Exception {
        openConnection(createURL("https://developer.nps.gov/api/v1/campgrounds?parkCode="));
        parseCampJSON();
    }

    /**
     * Parses visitor center json and creates and adds objects to arraylist
     *
     * @throws Exception
     */
    public void parseVCJSON() throws Exception {
        JSONObject mainObj = new JSONObject(jsonString);
        JSONArray array = mainObj.getJSONArray("data");
        boolean good = true;

        for (int i = 0; i < array.length(); i++) {
            ArrayList<Address> addies = new ArrayList<>();
            ArrayList<PhoneNumber> numbers = new ArrayList<>();
            ArrayList<String> emails = new ArrayList<>();
            ArrayList<Hours> hours = new ArrayList<>();

            JSONObject subObj = array.getJSONObject(i);
            JSONArray curr = null;
            JSONObject currObj = null;
            JSONObject con = null;
            int j = 0;

            try {
                curr = subObj.getJSONArray("addresses");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                for (j = 0; j < curr.length(); j++) {
                    currObj = curr.optJSONObject(j);
                    addies.add(new Address(currObj.getString("line1"), currObj.getString("line2"), currObj.getString("line3"), currObj.getString("city"),
                            currObj.getString("stateCode"), currObj.getString("postalCode"), currObj.getString("type")));
                }
            }

            try {
                con = subObj.getJSONObject("contacts");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                curr = con.getJSONArray("phoneNumbers");
                for (j = 0; j < curr.length(); j++) {
                    currObj = curr.optJSONObject(j);
                    numbers.add(new PhoneNumber(currObj.getString("phoneNumber"), currObj.getString("type")));
                }

                curr = con.getJSONArray("emailAddresses");
                for (j = 0; j < curr.length(); j++) {
                    currObj = curr.optJSONObject(j);
                    emails.add(currObj.getString("emailAddress"));
                }
            }

            good = true;
            try {
                curr = subObj.getJSONArray("operatingHours");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                if (curr.length() > 0) {
                    for (j = 0; j < 1; j++) {
                        currObj = curr.optJSONObject(j);
                        JSONObject hoursArray = currObj.getJSONObject("standardHours");
                        Map<String, String> stan = new HashMap<>();
                        Iterator<String> iter = hoursArray.keys();
                        while (iter.hasNext()) {
                            String key = iter.next();
                            String s1 = key.substring(0, 1).toUpperCase(); //capitalize first letter
                            String capKey = s1 + key.substring(1);
                            String val = hoursArray.getString(key);
                            stan.put(capKey, val);
                        }
                        hours.add(new Hours(currObj.getString("name"), currObj.getString("description"), stan));
                    }
                }
            }

            results.add(new VCenterSearchResult(subObj.getString("name"), subObj.getString("description"), subObj.getString("directionsInfo"),
                    subObj.getString("latLong"), numbers, emails, addies, hours, subObj.getString("url"), "Visitor Center"));
        }
    }

    /**
     * Parses through campground json and creates and adds objects to arraylist
     *
     * @throws Exception
     */
    public void parseCampJSON() throws Exception {
        JSONObject mainObj = new JSONObject(jsonString);
        JSONArray array = mainObj.getJSONArray("data");

        for (int i = 0; i < array.length(); i++) {
            ArrayList<Address> addies = new ArrayList<>();
            ArrayList<PhoneNumber> numbers = new ArrayList<>();
            ArrayList<String> emails = new ArrayList<>();
            ArrayList<Hours> hours = new ArrayList<>();
            String direct = "";
            String wheelchair = "";
            String ada = "";
            String toilets = "";
            String internet = "";
            String showers = "";
            String water = "";
            String fees = "";
            String weather = "";
            String regURL = "";
            String url = "";

            JSONObject subObj = array.getJSONObject(i);
            JSONArray curr = null;
            JSONObject currObj = null;
            JSONObject con = null;
            int j = 0;
            boolean good = true;

            try {
                curr = subObj.getJSONArray("addresses");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                for (j = 0; j < curr.length(); j++) {
                    currObj = curr.optJSONObject(j);
                    addies.add(new Address(currObj.getString("line1"), currObj.getString("line2"), currObj.getString("line3"), currObj.getString("city"),
                            currObj.getString("stateCode"), currObj.getString("postalCode"), currObj.getString("type"))); //PROBLEM HERE
                }
            }

            try {
                con = subObj.getJSONObject("contacts");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                curr = con.getJSONArray("phoneNumbers");
                for (j = 0; j < curr.length(); j++) {
                    currObj = curr.optJSONObject(j);
                    numbers.add(new PhoneNumber(currObj.getString("phoneNumber"), currObj.getString("type")));
                }

                curr = con.getJSONArray("emailAddresses");
                for (j = 0; j < curr.length(); j++) {
                    currObj = curr.optJSONObject(j);
                    emails.add(currObj.getString("emailAddress"));
                }
            }

            good = true;
            try {
                curr = subObj.getJSONArray("operatingHours");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                if (curr.length() > 0) {
                    for (j = 0; j < 1; j++) {
                        currObj = curr.optJSONObject(j);
                        JSONObject hoursArray = currObj.getJSONObject("standardHours");
                        Map<String, String> stan = new HashMap<>();
                        Iterator<String> iter = hoursArray.keys();
                        while (iter.hasNext()) {
                            String key = iter.next();
                            String val = hoursArray.getString(key);
                            stan.put(key, val);
                        }
                        hours.add(new Hours(currObj.getString("name"), currObj.getString("description"), stan));
                    }
                }
            }

            good = true;
            try {
                currObj = subObj.getJSONObject("accessibility");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                try {
                    wheelchair = currObj.getString("wheelchairaccess");
                } catch (Exception e) {
                }
                try {
                    ada = currObj.getString("adainfo");
                } catch (Exception e) {
                }
            }

            good = true;
            currObj = null;
            try {
                currObj = subObj.getJSONObject("amenities");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                try {
                    toilets = currObj.getString("toilets");
                } catch (Exception e) {
                }
                try {
                    internet = currObj.getString("internetConnectivity");
                } catch (Exception e) {
                }
                try {
                    showers = currObj.getString("showers");
                } catch (Exception e) {
                }
                try {
                    water = currObj.getString("potableWater");
                } catch (Exception e) {
                }
            }

            try {
                fees = subObj.getString("fees");
            } catch (Exception e) {
            }

            try {
                weather = subObj.getString("weatheroverview");
            } catch (Exception e) {
            }

            try {
                regURL = subObj.getString("regulationsurl");
            } catch (Exception e) {
            }

            try {
                direct = subObj.getString("directionsoverview");
            } catch (Exception e) {
            }

            results.add(new VCenterSearchResult(subObj.getString("name"), subObj.getString("description"), direct,
                    subObj.getString("latLong"), numbers, emails, addies, hours, url, wheelchair, ada, toilets,
                    internet, showers, water, fees, weather, regURL, "Campground"));
        }
    }
}
