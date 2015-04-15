package com.letsgood.sampleapp.model;

import com.google.gson.annotations.Expose;
import com.letsgood.synergykitsdkandroid.resources.SynergykitEmail;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 9. 4. 2015.
 */
public class DemoEmail extends SynergykitEmail {

    public static final String EMAIL_ID = "e-mail-example";

    /* Attributes */
    @Expose
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
