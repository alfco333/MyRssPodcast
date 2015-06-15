package com.alfco.myrsspodcast.models.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by angel on 6/14/15.
 */
@Root(strict = false)
public class Url {

    @Attribute(name = "url")
    private String url;
    @Attribute(name = "type")
    private String type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
