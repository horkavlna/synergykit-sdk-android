package com.letsgood.synergykit.resources;

import java.io.BufferedReader;

public class SynergyKITResponse {
	
	/* Attributes */
	private int statusCode = 0;
	private BufferedReader bufferedReader = null;
	
	/* Status code getter */
	public int getStatusCode() {
		return statusCode;
	}
	
	/* Status code setter*/
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	/* BuffedReader getter */
	public BufferedReader getBufferedReader() {
		return bufferedReader;
	}
	
	/* BufferedReader setter */
	public void setBufferedReader(BufferedReader bufferedReader) {
		this.bufferedReader = bufferedReader;
	}
	
	
	
}
