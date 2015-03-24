package com.synergykit.letsgood.sampleapp;

import com.synergykit.sdk.builders.UriBuilder;
import com.synergykit.sdk.builders.uri.Resource;
import com.synergykit.sdk.resources.SynergyKitUri;

import junit.framework.TestCase;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 18. 3. 2015.
 */
public class UriBuilderTests extends TestCase {

    /* Constants */
    private static final String BASE_URI ="https://synergykit-sample-app.api.synergykit.com/v2";

    private static final String FUNCTION_ID = "asdfjasdfkasdkjfkajsldfjasdf";

    private static final String RESULT_URI_FUNCTION = BASE_URI + "/functions/" + FUNCTION_ID;



    /* Test add */
    public void testFunctionUri() {
        SynergyKitUri uri = UriBuilder.newInstance()
                                .setResource(Resource.RESOURCE_FUNCTIONS)
                                .setFunctionId(FUNCTION_ID)
                                .build();

        assertTrue(RESULT_URI_FUNCTION.equals(uri.toString()));
    }

    @Override
    protected void runTest() throws Throwable {
        super.runTest();


        testFunctionUri();
    }
}
