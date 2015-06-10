package com.alfco.myrsspodcast.restclient.request;

import com.alfco.myrsspodcast.tools.Util;
import com.octo.android.robospice.request.springandroid.SpringAndroidSpiceRequest;

import org.springframework.web.client.RestTemplate;

/**
 * Created by angel on 6/6/15.
 */
public class RssFeedRequest extends SpringAndroidSpiceRequest<byte[]> {


    private String url;
    public RssFeedRequest(String url) {
        super(byte[].class);
        this.url=url;
        Util.le("Making request to: "+url);
    }

    @Override
    public byte[] loadDataFromNetwork() throws Exception {
        RestTemplate restTemplate=getRestTemplate();

        return restTemplate.getForObject(url, byte[].class);
    }


}
