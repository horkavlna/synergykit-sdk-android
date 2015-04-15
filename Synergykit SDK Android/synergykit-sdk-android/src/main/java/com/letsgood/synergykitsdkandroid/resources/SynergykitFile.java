package com.letsgood.synergykitsdkandroid.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import com.google.gson.annotations.Expose;

import java.io.Serializable;

public class SynergykitFile extends SynergykitObject implements Serializable {
	
	/* Attributes */
    @Expose
	protected String path;
    @Expose
	protected String extension;
    @Expose
	protected long size;
    @Expose
	protected String fileName;
    @Expose
	protected String applicationUrl;

	/* Path getter */
	public String getPath() {
		return path;
	}

	/* Path getter */
	public void setPath(String path) {
		this.path = path;
	}

	/* Extension getter */
	public String getExtension() {
		return extension;
	}

	/* Extension setter */
	public void setExtension(String extension) {
		this.extension = extension;
	}

	/* Size getter */
	public long getSize() {
		return size;
	}

	/* Size setter */
	public void setSize(long size) {
		this.size = size;
	}

	/* File name getter */
	public String getFileName() {
		return fileName;
	}

	/* File name setter */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/* Application URL getter */
	public String getApplicationUrl() {
		return applicationUrl;
	}

	/* Application URL setter */
	public void setApplicationUrl(String applicationUrl) {
		this.applicationUrl = applicationUrl;
	}
}
