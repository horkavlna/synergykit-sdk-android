package com.letsgood.synergykitsdkandroid.resources;

/**
 * Created by Letsgood.com - Pavel Stambrecht on 21. 4. 2015.
 */
public class SynergykitCloudCode extends  SynergykitObject {

    /* Attributes */
    protected String cloudCodeName;

    /* New instance*/
    public static SynergykitCloudCode newInstance(String cloudCodeName){
        return new SynergykitCloudCode(cloudCodeName);
    }

    public SynergykitCloudCode(String cloudCodeName) {
        this.cloudCodeName = cloudCodeName;
    }

        /* Cloud code name getter */
    public String getCloudCodeName() {
        return cloudCodeName;
    }

    /* Cloud code name setter */
    public void setCloudCodeName(String cloudCodeName) {
        this.cloudCodeName = cloudCodeName;
    }
}
