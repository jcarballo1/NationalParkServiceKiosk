/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mypackage.nationalpark;

import java.util.Map;

/**
 *
 * @author jcarb
 */
public class Hours {
    private String name;
    private String des;
    private Map<String, String> stanHours;
    
    public Hours(String n, String d, Map<String, String> h){
        name = n;
        des = d;
        stanHours = h;
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
     * @return the stanHours
     */
    public Map<String, String> getStanHours() {
        return stanHours;
    }

    /**
     * @param stanHours the stanHours to set
     */
    public void setStanHours(Map<String, String> stanHours) {
        this.stanHours = stanHours;
    }
}
