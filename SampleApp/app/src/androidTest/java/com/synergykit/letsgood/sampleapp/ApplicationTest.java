package com.synergykit.letsgood.sampleapp;

import android.app.Application;
import android.test.ApplicationTestCase;

import junit.framework.TestCase;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {



    public ApplicationTest() {
        super(Application.class);

        TestCase uriBuilderTests= new UriBuilderTests();
        uriBuilderTests.run();


    }
}