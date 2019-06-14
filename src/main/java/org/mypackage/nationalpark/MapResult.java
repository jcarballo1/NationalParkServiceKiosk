package org.mypackage.nationalpark;

/**
 * MapResult
 * @author Jennifer Carballo
 * Object contains name, latitude, and longitude of requested destination
 */
public class MapResult {
    private String name;
    private String latitude;
    private String longitude;

    /**
     * Constructor of Map Result object; 
     * splits passed in string coordinates into long and lat
     * @param n
     * @param coords 
     */
    public MapResult(String n, String coords) {
        name = n;
        String[] array = coords.split(", ");
        if(array.length > 1){
            latitude = array[0].substring(4);
            longitude = array[1].substring(5);
        } else{
            latitude = "";
            longitude = "";
        }
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
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
}
