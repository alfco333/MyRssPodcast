package com.alfco.myrsspodcast.models.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by angel on 6/8/15.
 */
@Root(strict = false)
public class Image {
    @Attribute(name = "href")
    @Namespace
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
