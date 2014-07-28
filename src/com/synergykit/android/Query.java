package com.synergykit.android;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by tomas_000 on 27.2.14.
 */
public class Query {
	private String url;
	private Synergykit synergykit;

	public Query(Synergykit synergykit) {
		this.synergykit = synergykit;
		url = String.format(RESTHelper.BASE_URL, synergykit.getApplication());
	}

	public Query getUsers() {
		url += RESTHelper.RESOURCE_USERS;
		addApplicationKey();
		return this;
	}

	public Query getUsersNotification() {
		url += RESTHelper.RESOURCE_USERS_NOTIFICATION;
		addApplicationKey();
		return this;
	}

	public Query getUser(String id) {
		url += RESTHelper.RESOURCE_USERS + "/" + id;
		addApplicationKey();
		return this;
	}

	public Query getCollections() {
		url += RESTHelper.RESOURCE_COLLECTIONS;
		addApplicationKey();
		return this;
	}

	public Query getCollection(String collectionUrl) {
		url += RESTHelper.RESOURCE_COLLECTIONS + "/" + collectionUrl;
		addApplicationKey();
		return this;
	}

	public Query getData(String collection) {
		url += RESTHelper.RESOURCE_DATA + "/" + collection;
		addApplicationKey();
		return this;
	}

	public Query getData(String collection, String id) {
		url += RESTHelper.RESOURCE_DATA + "/" + collection + "/" + id;
		addApplicationKey();
		return this;
	}

	public Query filter(String filter) {
		try {
			url += "&$filter=" + URLEncoder.encode(filter, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		return this;
	}

	public Query select(String select) {
		try {
			url += "&$select=" + URLEncoder.encode(select, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		return this;
	}

	public Query inlinecount(String inlinecount) {
		try {
			url += "&$inlinecount=" + URLEncoder.encode(inlinecount, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		return this;
	}

	public Query top(int top) {
		url += "&$top=" + top;
		return this;
	}

	public Query skip(int skip) {
		url += "&$skip=" + skip;
		return this;
	}

	public Query orderby(String orderby) {
		try {
			url += "&$orderby=" + URLEncoder.encode(orderby, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			return null;
		}
		return this;
	}

	private Query addApplicationKey() {
		url += "?application=" + synergykit.getKey();
		return this;
	}

	public String getUrl() {
		return url;
	}
}
