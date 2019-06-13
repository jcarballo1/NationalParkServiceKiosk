package org.mypackage.nationalpark;

/**
 * EntrancePass
 * @author Jennifer Carballo
 * Object contains cost, description, and title of entrance pass
 */
public class EntrancePass {

    private String cost;
    private String des;
    private String title;

    public EntrancePass(String c, String d, String t) {
        cost = c;
        des = d;
        title = t;
    }

    /**
     * @return the cost
     */
    public String getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(String cost) {
        this.cost = cost;
    }

    /**
     * @return the des
     */
    public String getDes() {
        return des;
    }

    /**
     * @param des the des to set
     */
    public void setDes(String des) {
        this.des = des;
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

}
