package com.letsgood.synergykitsdkandroid.resources;

import com.letsgood.synergykitsdkandroid.log.SynergykitLog;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 13. 4. 2015.
 */
public class SynergykitEndpoint {
    /* Constants */
    private static final String ENDPOINT = "Endpoint: ";

    /* Attributes */
    private String endpoint = new String();

    /* Constructor */
    public SynergykitEndpoint(String endpoint){
        this.endpoint = endpoint;
    }

    /* Uri getter */
    public String toString(){
        SynergykitLog.print(ENDPOINT + endpoint);
        return endpoint;
    }
}
