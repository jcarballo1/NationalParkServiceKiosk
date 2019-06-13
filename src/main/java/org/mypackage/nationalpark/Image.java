package org.mypackage.nationalpark;

/**
 * Image
 * @author Jennifer Carballo
 * Object contains url, caption, and credit
 */
public class Image {

    private String url = "";
    private String caption = "";
    private String cred = "";

    /**
     * Constructor for Image with credit passed in
     * @param u
     * @param cap
     * @param cre 
     */
    public Image(String u, String cap, String cre) {
        url = u;
        caption = cap;
        cred = cre;
    }

    /**
     * Constructor for Image without credit passed in
     * @param u
     * @param cap 
     */
    public Image(String u, String cap) {
        url = u;
        caption = cap;
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
