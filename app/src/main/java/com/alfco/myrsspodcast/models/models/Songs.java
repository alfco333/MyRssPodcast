package com.alfco.myrsspodcast.models.models;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by angel on 6/6/15.
 */
@Root(name = "item",strict = false)
public class Songs {

    @Element(name = "title")
    private String title;
    @Namespace
    @Element(name = "author")
    private String author;

    @Element(name = "guid")
    private String guid;
    @Element(name = "pubDate")
    private String pubDate;
    @Element(name = "duration")
    @Namespace
    private String duration;
    @Element(name = "image")
    private Image image;
    @Element(name="enclosure")
    private Url url;



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Url getUrl() {
        return url;
    }

    public void setUrl(Url url) {
        this.url = url;
    }
}
