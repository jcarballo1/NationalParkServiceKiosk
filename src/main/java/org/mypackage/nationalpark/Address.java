package org.mypackage.nationalpark;

/**
 * Address
 * @author Jennifer Carballo 
 * Object contains address lines 1-3, city, state,
 * postal code, and type of address
 */
public class Address {

    private String line1;
    private String line2;
    private String line3;
    private String city;
    private String state;
    private String zip;
    private String type;

    /**
     * Constructor of Address object
     *
     * @param lines 1-3
     * @param city
     * @param state
     * @param zipcode
     * @param type
     */
    public Address(String l1, String l2, String l3, String c, String s, String z, String t) {
        line1 = l1;
        line2 = l2;
        line3 = l3;
        city = c;
        state = s;
        zip = z;
        type = t;
    }

    /**
     * @return the line1
     */
    public String getLine1() {
        return line1;
    }

    /**
     * @param line1 the line1 to set
     */
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     * @return the line2
     */
    public String getLine2() {
        return line2;
    }

    /**
     * @param line2 the line2 to set
     */
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    /**
     * @return the line3
     */
    public String getLine3() {
        return line3;
    }

    /**
     * @param line3 the line3 to set
     */
    public void setLine3(String line3) {
        this.line3 = line3;
    }

    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the zip
     */
    public String getZip() {
        return zip;
    }

    /**
     * @param zip the zip to set
     */
    public void setZip(String zip) {
        this.zip = zip;
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
