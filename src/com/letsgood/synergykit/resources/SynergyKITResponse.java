package com.letsgood.synergykit.resources;

import java.io.BufferedReader;
import java.io.InputStream;

public class SynergyKITResponse {
	
	/* Attributes */
	private int statusCode = 0;
	private BufferedReader bufferedReader = null;
	private InputStream inputStream = null;;
	
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

	/* Input stream getter */
	public InputStream getInputStream() {
		return inputStream;
	}

	/* Input stream setter */
	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}
	
	
	
}
