package org.mypackage.nationalpark;

import java.util.ArrayList;

/**
 * VCenterSearchResult
 * @author Jennifer Carballo
 * Object contains necessary information for visitor centers and campgrounds
 */
public class VCenterSearchResult {

    //both visitor canter and campground
    private String name;
    private String descrip;
    private String direct;
    private String coords;
    private ArrayList<PhoneNumber> numbers;
    private ArrayList<String> emails;
    private ArrayList<Address> addies;
    private ArrayList<Hours> hours;
    private String url;
    private String type;
    
    //campground
    private String wheelchair = "";
    private String ada = "";
    private String toilets = "";
    private String internet = "";
    private String showers = "";
    private String water = "";
    private String fees = "";
    private String weather = "";
    private String regulationURL = "";

    /**
     * Constructor for Visitor Center object
     * @param many 
     */
    public VCenterSearchResult(String n, String d, String di, String c, ArrayList<PhoneNumber> num, ArrayList<String> e, ArrayList<Address> a, ArrayList<Hours> h, String u, String ty) {
        name = n;
        descrip = d;
        direct = di;
        coords = c;
        numbers = num;
        emails = e;
        addies = a;
        hours = h;
        url = u;
        type = ty;

    }

    /**
     * Constructor for Campground object
     * @param many
     */
    public VCenterSearchResult(String n, String d, String di, String c, ArrayList<PhoneNumber> num, ArrayList<String> e, ArrayList<Address> a, ArrayList<Hours> h, String u,
            String wc, String ad, String t, String i, String s, String w, String f, String wea, String ru, String ty) {
        name = n;
        descrip = d;
        direct = di;
        coords = c;
        numbers = num;
        emails = e;
        addies = a;
        hours = h;
        url = u;
        wheelchair = wc;
        ada = ad;
        toilets = t;
        internet = i;
        showers = s;
        water = w;
        fees = f;
        weather = wea;
        regulationURL = ru;
        type = ty;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the descrip
     */
    public String getDescrip() {
        return descrip;
    }

    /**
     * @param descrip the descrip to set
     */
    public void setDescrip(String descrip) {
        this.descrip = descrip;
    }

    /**
     * @return the direct
     */
    public String getDirect() {
        return direct;
    }

    /**
     * @param direct the direct to set
     */
    public void setDirect(String direct) {
        this.direct = direct;
    }

    /**
     * @return the coords
     */
    public String getCoords() {
        return coords;
    }

    /**
     * @param coords the coords to set
     */
    public void setCoords(String coords) {
        this.coords = coords;
    }

    /**
     * @return the addies
     */
    public ArrayList<Address> getAddies() {
        return addies;
    }

    /**
     * @param addies the addies to set
     */
    public void setAddies(ArrayList<Address> addies) {
        this.addies = addies;
    }

    /**
     * @return the hours
     */
    public ArrayList<Hours> getHours() {
        return hours;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(ArrayList<Hours> hours) {
        this.hours = hours;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the numbers
     */
    public ArrayList<PhoneNumber> getNumbers() {
        return numbers;
    }

    /**
     * @param numbers the numbers to set
     */
    public void setNumbers(ArrayList<PhoneNumber> numbers) {
        this.numbers = numbers;
    }

    /**
     * @return the emails
     */
    public ArrayList<String> getEmails() {
        return emails;
    }

    /**
     * @param emails the emails to set
     */
    public void setEmails(ArrayList<String> emails) {
        this.emails = emails;
    }

    /**
     * @return the wheelchair
     */
    public String getWheelchair() {
        return wheelchair;
    }

    /**
     * @param wheelchair the wheelchair to set
     */
    public void setWheelchair(String wheelchair) {
        this.wheelchair = wheelchair;
    }

    /**
     * @return the ada
     */
    public String getAda() {
        return ada;
    }

    /**
     * @param ada the ada to set
     */
    public void setAda(String ada) {
        this.ada = ada;
    }

    /**
     * @return the toilets
     */
    public String getToilets() {
        return toilets;
    }

    /**
     * @param toilets the toilets to set
     */
    public void setToilets(String toilets) {
        this.toilets = toilets;
    }

    /**
     * @return the internet
     */
    public String getInternet() {
        return internet;
    }

    /**
     * @param internet the internet to set
     */
    public void setInternet(String internet) {
        this.internet = internet;
    }

    /**
     * @return the showers
     */
    public String getShowers() {
        return showers;
    }

    /**
     * @param showers the showers to set
     */
    public void setShowers(String showers) {
        this.showers = showers;
    }

    /**
     * @return the water
     */
    public String getWater() {
        return water;
    }

    /**
     * @param water the water to set
     */
    public void setWater(String water) {
        this.water = water;
    }

    /**
     * @return the fees
     */
    public String getFees() {
        return fees;
    }

    /**
     * @param fees the fees to set
     */
    public void setFees(String fees) {
        this.fees = fees;
    }

    /**
     * @return the weather
     */
    public String getWeather() {
        return weather;
    }

    /**
     * @param weather the weather to set
     */
    public void setWeather(String weather) {
        this.weather = weather;
    }

    /**
     * @return the regulationURL
     */
    public String getRegulationURL() {
        return regulationURL;
    }

    /**
     * @param regulationURL the regulationURL to set
     */
    public void setRegulationURL(String regulationURL) {
        this.regulationURL = regulationURL;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
