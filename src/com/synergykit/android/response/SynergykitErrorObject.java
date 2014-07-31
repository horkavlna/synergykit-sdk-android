package com.synergykit.android.response;

/**
 * 
 * @author Pavel Stambrecht & tomas_000
 *
 */
public class SynergykitErrorObject {
    private String status;
    private String message;

    /* Status getter */
    public String getStatus() {
        return status;
    }

    /* Status sette */
    public void setStatus(String status) {
        this.status = status;
    }

    /* Message getter */
    public String getMessage() {
        return message;
    }

    /* Message setter */
    public void setMessage(String message) {
        this.message = message;
    }

}

