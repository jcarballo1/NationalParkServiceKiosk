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


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jcarb
 */
public class VCenterSearchRequest {

    private String jsonString;
    private BufferedWriter inFile;
    private ArrayList<VCenterSearchResult> results;

    public static class MyHostnameVerifier implements HostnameVerifier {

        public boolean verify(String hostname, SSLSession session) {
            // verification of hostname is switched off
            return true;
        }
    }

    public ArrayList<VCenterSearchResult> process(String[] keywords, String desigs, String states, String key) throws Exception {
        results = new ArrayList<>();
        if (key.equals("both")) {
            sendGetVCWithKeyword(keywords, desigs, states);
            sendGetCampWithKeyword(keywords, desigs, states);
        } else if (key.equals("vc")) {
            sendGetVCWithKeyword(keywords, desigs, states);
        } else {
            sendGetCampWithKeyword(keywords, desigs, states);
        }

        return results;
    }

    public void sendGetVCWithKeyword(String[] keywords, String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/visitorcenters?parkCode=";
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

        baseURL += "&fields=addresses%2Ccontacts%2Coperatinghours";
        baseURL += "&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
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

//        inFile = new BufferedWriter(new FileWriter("C:\\Users\\jcarb\\Documents\\NetBeansProjects\\NationalParkServiceKiosk\\output.txt"));;
//        inFile.write("Sending 'GET' request to URL : " + url.toString());
//        inFile.write("\nResponse Code : " + responseCode);
//        inFile.write("\n");
//        inFile.write(connection.getContentType());
        InputStream input = (InputStream) connection.getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, "UTF-8"), 8);
        StringBuilder sb = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        jsonString = sb.toString();
        parseVCJSON();
    }

    public void sendGetCampWithKeyword(String[] keywords, String desigs, String states) throws Exception {
        String baseURL = "https://developer.nps.gov/api/v1/campgrounds?parkCode=";
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

        baseURL += "&fields=addresses%2Ccontacts%2Coperatinghours";
        baseURL += "&api_key=CAYHsEFFEaczB1PMOxrLh5GQjtumjbZpdRsZE8Xm";
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
        parseCampJSON();
    }

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

            JSONObject con = subObj.getJSONObject("contacts");
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

            results.add(new VCenterSearchResult(subObj.getString("name"), subObj.getString("description"), subObj.getString("directionsInfo"),
                    subObj.getString("latLong"), numbers, emails, addies, hours, subObj.getString("url"), "Visitor Center"));
        }
    }

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

            JSONObject con = subObj.getJSONObject("contacts");
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
