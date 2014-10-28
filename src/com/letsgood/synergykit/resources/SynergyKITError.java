package com.letsgood.synergykit.resources;

public class SynergyKITError {
	
	/* Attributes */
	private int statusCode;
    private String message;

    
    /* Constructor */
    public SynergyKITError(int statusCode, String message){
    	
    	this.statusCode=statusCode; //set status code
    	
    	//set message
    	if(message!=null)
    		this.message = message;
    	else
    		this.message=new String();    	
    }
    
    /* Constructor */
    public SynergyKITError(){
    	this(0, new String());
    }
    
    /* Status code getter */
    public int getStatusCode() {
        return statusCode;
    }

    /* Status code setter */
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
    /* Status code in string getter */
    public String getStatusCodeString(){
    	return Integer.toString(statusCode);
    }

    /* Message getter */
    public String getMessage() {
        return message;
    }

    /* Message setter */
    public void setMessage(String message) {
        this.message = message;
    }

    /* To String */
    public String toString(){
    	return "Status code: " + statusCode + ", Message: " + message;
    }
}
