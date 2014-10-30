package com.letsgood.synergykit.builders.errors;

public class Errors {
	
	//Unspecified error 
	public static final int SC_UNSPECIFIED_ERROR= -1;
	public static final String MSG_UNSPECIFIED_ERROR = "Unspecified error.";
	
	//Not initialized
	public static final int SC_SK_NOT_INITIALIZED = -2;
	public static final String MSG_SK_NOT_INITIALIZED = "SynergyKIT is NOT initialized.";
	
	//Uri not valid
	public static final int SC_URI_NOT_VALID = -3;
	public static final String MSG_URI_NOT_VALID= "URI in NOT valid.";
	
	//Object null
	public static final int SC_NO_OBJECT = -4;
	public static final String MSG_NO_OBJECT= "Sending object is NULL.";
	
	//Arguments must me set
	public static final int SC_NULL_ARGUMENTS_OR_EMPTY= -5;
	public static final String MSG_NULL_ARGUMENTS_OR_EMPTY= "Some of arguments is NULL or EMPTY.";
	
	//Order by overflow
	public static final String MSG_ORDRER_BY_OVERFLOW = "Maximum size of ORDER BY arguments is 12. You are over.";
	
	//Skip negative
	public static final String MSG_SKIP_NEGATIVE  = "Skip value must NOT be negative";
	
	//Top negative
	public static final String MSG_TOP_NEGATIVE  = "Top value must NOT be negative";
	
	//No listener
	public static final String MSG_NO_CALLBACK_LISTENER = "No callback listener was set.";
	
	//No config file
	public static final String MSG_NO_CONFIG = "SynergyKIT config file WAS NOT set";
	
	// No request
	public static final String MSG_NO_REQUEST = "SynergyKITRequest WAS NOT set.";

}
