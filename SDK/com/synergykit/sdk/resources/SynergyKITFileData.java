package com.synergykit.sdk.resources;

/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

public class SynergyKITFileData extends SynergyKITObject{
	
	/* Attributes */
	protected String path;
	protected String extension;
	protected long size;
	protected String fileName;
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
