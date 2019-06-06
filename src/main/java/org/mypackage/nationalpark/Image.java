/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.nationalpark;

/**
 *
 * @author jcarb
 */
public class Image {

    private String url;
    private String caption;
    private String cred;

    public Image(String u, String cap, String cre) {
        url = u;
        caption = cap;
        cred = cre;
    }
    
    public Image(String u, String cap){
        url = u;
        caption = cap;
        cred = "";
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
     * @return the caption
     */
    public String getCaption() {
        return caption;
    }

    /**
     * @param caption the caption to set
     */
    public void setCaption(String caption) {
        this.caption = caption;
    }

    /**
     * @return the cred
     */
    public String getCred() {
        return cred;
    }

    /**
     * @param cred the cred to set
     */
    public void setCred(String cred) {
        this.cred = cred;
    }

}
