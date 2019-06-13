package org.mypackage.nationalpark;

/**
 * PhoneNumber
 * @author Jennifer Carballo
 * Object contains number and number type
 */
public class PhoneNumber {

    private String number;
    private String type;

    /**
     * Constructor for PhoneNumber object
     * @param n
     * @param t 
     */
    public PhoneNumber(String n, String t) {
        number = n;
        type = t;
    }

    /**
     * @return the number
     */
    public String getNumber() {
        return number;
    }

    /**
     * @param number the number to set
     */
    public void setNumber(String number) {
        this.number = number;
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
