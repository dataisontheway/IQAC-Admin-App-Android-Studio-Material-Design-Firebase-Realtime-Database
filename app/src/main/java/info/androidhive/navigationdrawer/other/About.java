package info.androidhive.navigationdrawer.other;

public class About {

    String aboutId;
    String aboutDescription;

    public About(){
    }

    public About(String aboutId, String aboutDescription) {
        this.aboutId = aboutId;
        this.aboutDescription = aboutDescription;
    }

    public String getAboutId() {
        return aboutId;
    }

    public String getAboutDescription() {
        return aboutDescription;
    }

}
