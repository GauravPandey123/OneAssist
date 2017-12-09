
package app.androidhive.info.realm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OfferDTO {

    @SerializedName("offerLocation")
    @Expose
    private String offerLocation;
    @SerializedName("offerValidity")
    @Expose
    private String offerValidity;
    @SerializedName("offerStoreName")
    @Expose
    private String offerStoreName;
    @SerializedName("offerPercent")
    @Expose
    private String offerPercent;
    @SerializedName("offerAddress")
    @Expose
    private String offerAddress;

    public String getOfferLocation() {
        return offerLocation;
    }

    public void setOfferLocation(String offerLocation) {
        this.offerLocation = offerLocation;
    }

    public String getOfferValidity() {
        return offerValidity;
    }

    public void setOfferValidity(String offerValidity) {
        this.offerValidity = offerValidity;
    }

    public String getOfferStoreName() {
        return offerStoreName;
    }

    public void setOfferStoreName(String offerStoreName) {
        this.offerStoreName = offerStoreName;
    }

    public String getOfferPercent() {
        return offerPercent;
    }

    public void setOfferPercent(String offerPercent) {
        this.offerPercent = offerPercent;
    }

    public String getOfferAddress() {
        return offerAddress;
    }

    public void setOfferAddress(String offerAddress) {
        this.offerAddress = offerAddress;
    }

}
