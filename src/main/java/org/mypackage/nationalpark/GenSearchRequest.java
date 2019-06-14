package org.mypackage.nationalpark;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import sun.misc.BASE64Encoder;
import org.json.*;

/**
 * GenSearchRequest
 *
 * @author Jennifer Carballo Process General Search search query, opens
 * connection to NPS API call, parses returned JSON result, creates necessary
 * object with the information to be displayed later
 */
public class GenSearchRequest {

    private String jsonString;
    private ArrayList<GeneralSearchResult> results = new ArrayList<>();

    public static class MyHostnameVerifier implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            // verification of hostname is switched off
            return true;
        }
    }

    /**
     * Opens connection using api url, stores resulting json, calls parsing
     * method
     *
     * @param desigs
     * @param states
     * @return array list of General Search Result objects
     * @throws Exception
     */
    public ArrayList<GeneralSearchResult> sendGetSingle(String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/parks?parkCode=";
        baseURL += desigs;
        baseURL += "&stateCode=" + states;
        baseURL += "&fields=addresses%2Ccontacts%2Centrancefees%2Centrancepasses%2Cimages%2Coperatinghours&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
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
        parseJSON();

        return results;
    }

    /**
     * parses JSON and adds objects to arraylist
     *
     * @throws Exception
     */
    public void parseJSON() throws Exception {
        JSONObject mainObj = new JSONObject(jsonString);
        JSONArray array = mainObj.getJSONArray("data");

        for (int i = 0; i < array.length(); i++) {
            ArrayList<Address> addies = new ArrayList<>();
            ArrayList<PhoneNumber> numbers = new ArrayList<>();
            ArrayList<String> emails = new ArrayList<>();
            ArrayList<EntranceFee> fees = new ArrayList<>();
            ArrayList<EntrancePass> passes = new ArrayList<>();
            ArrayList<Image> images = new ArrayList<>();
            ArrayList<Hours> hours = new ArrayList<>();

            JSONObject subObj = array.getJSONObject(i);
            JSONArray curr = null;
            JSONObject currObj;
            boolean good = true;
            int j;

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

            good = true;
            JSONObject con = null;
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
            }

            good = true;
            try {
                curr = con.getJSONArray("emailAddresses");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                for (j = 0; j < curr.length(); j++) {
                    currObj = curr.optJSONObject(j);
                    emails.add(currObj.getString("emailAddress"));
                }
            }

            good = true;
            try {
                curr = subObj.getJSONArray("entranceFees");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                for (j = 0; j < curr.length(); j++) {
                    currObj = curr.optJSONObject(j);
                    fees.add(new EntranceFee(currObj.getString("cost").substring(0, currObj.getString("cost").length() - 2), currObj.getString("description"), currObj.getString("title"))); //substring makes cost to two decimals
                }
            }

            good = true;
            try {
                curr = subObj.getJSONArray("entrancePasses");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                for (j = 0; j < curr.length(); j++) {
                    currObj = curr.optJSONObject(j);
                    passes.add(new EntrancePass(currObj.getString("cost").substring(0, currObj.getString("cost").length() - 2), currObj.getString("description"), currObj.getString("title"))); //substring makes cost to two decimals
                }
            }

            good = true;
            try {
                curr = subObj.getJSONArray("images");
            } catch (Exception e) {
                good = false;
            }
            if (good) {
                for (j = 0; j < curr.length(); j++) {
                    currObj = curr.optJSONObject(j);
                    images.add(new Image(currObj.getString("url"), currObj.getString("caption"), currObj.getString("credit")));
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

            results.add(new GeneralSearchResult(subObj.getString("fullName"), subObj.getString("latLong"), subObj.getString("description"),
                    subObj.getString("weatherInfo"), addies, numbers, emails, fees, passes, images, hours, subObj.getString("url")));
        }
    }
}
