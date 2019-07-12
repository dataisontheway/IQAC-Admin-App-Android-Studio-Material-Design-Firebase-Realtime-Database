package info.androidhive.navigationdrawer.other;

public class News {

    String newsId;
    String newsTitle;
    String newsImageUrl;
    String newsDescription;

    public News(){

    }

    public News(String newsId, String newsTitle, String newsImageUrl, String newsDescription) {
        this.newsId = newsId;
        this.newsTitle = newsTitle;
        this.newsImageUrl = newsImageUrl;
        this.newsDescription = newsDescription;
    }

    public String getNewsId() {
        return newsId;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public String getNewsImageUrl() {
        return newsImageUrl;
    }

    public String getNewsDescription() {
        return newsDescription;
    }

}
