package org.mypackage.nationalpark;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jcarb
 */
public class EdSearchResult {

    //Lesson Plans
    private String title = "";
    private String url = "";
    private String objective = "";
    private String subject = "";

    //People
    private String personName = "";
    private String imageURL = "";

    //Places
    private String listingDes = "";

    private String type = "";

    public EdSearchResult(String t, String u, String o, String s) { //Lesson Plans
        title = t;
        url = u;
        objective = o;
        subject = s;
        type = "Lesson Plan";
    }

    public EdSearchResult(String iu, String ld, String t, String u, String ty) { //People & Place
        imageURL = iu;
        listingDes = ld;
        title = t;
        url = u;
        type = ty;
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
     * @return the objective
     */
    public String getObjective() {
        return objective;
    }

    /**
     * @param objective the objective to set
     */
    public void setObjective(String objective) {
        this.objective = objective;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * @return the personName
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * @param personName the personName to set
     */
    public void setPersonName(String personName) {
        this.personName = personName;
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
