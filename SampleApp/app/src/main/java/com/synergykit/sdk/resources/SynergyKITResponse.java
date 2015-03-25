package com.synergykit.sdk.resources;

import com.google.gson.annotations.Expose;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.Serializable;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKitResponse implements Serializable {
	
	/* Attributes */
    @Expose
	private int statusCode = 0;
    @Expose
	private BufferedReader bufferedReader = null;
    @Expose
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
