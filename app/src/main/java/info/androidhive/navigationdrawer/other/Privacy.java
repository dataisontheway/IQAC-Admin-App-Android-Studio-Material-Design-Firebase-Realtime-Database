package info.androidhive.navigationdrawer.other;

public class Privacy {

    String privacyId;
    String privacyDescription;

    public Privacy(){
    }

    public Privacy(String privacyId, String privacyDescription) {
        this.privacyId = privacyId;
        this.privacyDescription = privacyDescription;
    }

    public String getPrivacyId() {
        return privacyId;
    }

    public String getPrivacyDescription() {
        return privacyDescription;
    }

}
