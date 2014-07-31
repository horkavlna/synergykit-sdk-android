package com.synergykit.android.exception;
/**
 * 
 * @author Pavel Stambrecht
 *
 */
public class NotInitializedException extends Exception {

	/* Constants */
	final private static String EXCEPTION = "SynergykitNotInitializedException";	
	final private static  long serialVersionUID = 6246708884259911758L;

	/*Constructor */
	public NotInitializedException(){
		super(EXCEPTION);
	}
	
}
