package kr.edcan.getitpouch.models;

/**
 * Created by Junseok Oh on 2017-05-14.
 */

public class Event {
    private String title, content, imageUrl, eventId;

    public Event() {
    }

    public Event(String title, String content, String imageUrl, String eventId) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
