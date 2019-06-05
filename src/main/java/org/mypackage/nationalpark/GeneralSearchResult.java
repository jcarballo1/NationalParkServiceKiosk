/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.nationalpark;

import java.util.ArrayList;

/**
 *
 * @author jcarb
 */
public class GeneralSearchResult {
    private String name;
    private String coords;
    private String descrip;
    private String weather;
    private ArrayList<Address> adds;
    private ArrayList<PhoneNumber> numbers;
    private ArrayList<String> emails;
    private ArrayList<EntranceFee> fees;
    private ArrayList<EntrancePass> passes;
    private ArrayList<Image> images;
    private ArrayList<Hours> hours;
    private String url;

    public GeneralSearchResult(String n, String c, String d, String w, ArrayList<Address> a, ArrayList<PhoneNumber> num,
        ArrayList<String> e, ArrayList<EntranceFee> f, ArrayList<EntrancePass> p, ArrayList<Image> i, ArrayList<Hours> h, String u){
        name = n;
        coords = c;
        descrip = d;
        weather = w;
        adds = a;
        numbers = num;
        emails = e;
        fees = f;
        passes = p;
        images = i;
        hours = h;
        url = u;
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
     * @return the adds
     */
    public ArrayList<Address> getAdds() {
        return adds;
    }

    /**
     * @param adds the adds to set
     */
    public void setAdds(ArrayList<Address> adds) {
        this.adds = adds;
    }

    /**
     * @return the fees
     */
    public ArrayList<EntranceFee> getFees() {
        return fees;
    }

    /**
     * @param fees the fees to set
     */
    public void setFees(ArrayList<EntranceFee> fees) {
        this.fees = fees;
    }

    /**
     * @return the passes
     */
    public ArrayList<EntrancePass> getPasses() {
        return passes;
    }

    /**
     * @param passes the passes to set
     */
    public void setPasses(ArrayList<EntrancePass> passes) {
        this.passes = passes;
    }

    /**
     * @return the images
     */
    public ArrayList<Image> getImages() {
        return images;
    }

    /**
     * @param images the images to set
     */
    public void setImages(ArrayList<Image> images) {
        this.images = images;
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

   
}
