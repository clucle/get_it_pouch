package kr.edcan.getitpouch.models;

/**
 * Created by Junseok Oh on 2017-05-14.
 */

public class Event {
    private String title, url, date, link;

    public Event() {
    }

    public Event (String title, String date, String url, String link) {
        this.title = title;
        this.date = date;
        this.url = url;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getImageUrl() {
        return url;
    }

    public void setImageUrl(String imageUrl) {
        this.url = imageUrl;
    }

    public String getLink() {
        return link;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

}
