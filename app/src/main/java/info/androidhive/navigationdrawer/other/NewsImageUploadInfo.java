package info.androidhive.navigationdrawer.other;

public class NewsImageUploadInfo {

    public String imageName;
    public String imageURL;
    public String imageDescription;

    public NewsImageUploadInfo(){

    }

    public NewsImageUploadInfo(String imageName, String imageDescription, String imageURL) {
        this.imageName = imageName;
        this.imageURL = imageURL;
        this.imageDescription = imageDescription;
    }

    public String getImageName() {
        return imageName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getImageDescription() {
        return imageDescription;
    }


}
