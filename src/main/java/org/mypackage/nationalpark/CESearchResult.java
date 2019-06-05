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
public class CESearchResult {
    //alert
    private String title = "";
    private String category = "";
    private String description = "";
    private String url = "";
    
    //articles
    private String altText = "";
    private String listingDes = "";
    private String imageURL = "";
    
    //events
    private String email = "";
    private String contactName = "";
    private String contactPhone = "";
    private ArrayList<String> dates = new ArrayList<>();
    private String location = "";
    private String timeStart = "";
    private String timeEnd = "";
    private String fees = "";
    
    //news
    private String abs = ""; //abstract
    private String releaseDate = "";
    
    private String type = "";
    
    public CESearchResult(String t, String c, String d, String u){ //Alert
        title = t;
        category = c;
        description = d;
        url = u;
        type = "Alert";
    }
    
    public CESearchResult(String alt, String iURL, String l, String t, String u){ //Article
        altText = alt;
        imageURL = iURL;
        listingDes = l;
        title = t;
        url = u;
        type = "Article";
    }
    
    public CESearchResult(String e, String name, String phone, ArrayList<String> ds, String l, String tStart, String tEnd, String d, String t, String f, String u){ //Event
        email = e;
        contactName = name;
        contactPhone = phone; 
        dates = ds;
        location = l;
        timeStart = tStart;
        timeEnd = tEnd;
        description = d;
        title = t;
        fees = f;
        url = u;
        type = "Event";
    }
    
    public CESearchResult(String a, String rd, String t, String u, String iu, String blank){ //News
        abs = a;
        releaseDate = rd;
        title = t;
        url = u;
        imageURL = iu;
        type = "News";
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the contactName
     */
    public String getContactName() {
        return contactName;
    }

    /**
     * @param contactName the contactName to set
     */
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    /**
     * @return the contactPhone
     */
    public String getContactPhone() {
        return contactPhone;
    }

    /**
     * @param contactPhone the contactPhone to set
     */
    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }
    
    /**
     * @return the dates
     */
    public ArrayList<String> getDates() {
        return dates;
    }

    /**
     * @param dates the dates to set
     */
    public void setDates(ArrayList<String> dates) {
        this.dates = dates;
    }

    /**
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * @return the timeStart
     */
    public String getTimeStart() {
        return timeStart;
    }

    /**
     * @param timeStart the timeStart to set
     */
    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    /**
     * @return the timeEnd
     */
    public String getTimeEnd() {
        return timeEnd;
    }

    /**
     * @param timeEnd the timeEnd to set
     */
    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    /**
     * @return the abs
     */
    public String getAbs() {
        return abs;
    }

    /**
     * @param abs the abs to set
     */
    public void setAbs(String abs) {
        this.abs = abs;
    }

    /**
     * @return the releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * @param releaseDate the releaseDate to set
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
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

    /**
     * @return the altText
     */
    public String getAltText() {
        return altText;
    }

    /**
     * @param altText the altText to set
     */
    public void setAltText(String altText) {
        this.altText = altText;
    }

    /**
     * @return the listingDes
     */
    public String getListingDes() {
        return listingDes;
    }

    /**
     * @param listingDes the listingDes to set
     */
    public void setListingDes(String listingDes) {
        this.listingDes = listingDes;
    }

    /**
     * @return the imageURL
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * @param imageURL the imageURL to set
     */
    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
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

}
