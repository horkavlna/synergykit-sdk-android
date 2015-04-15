package com.letsgood.synergykitsdkandroid.request;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */


import android.os.AsyncTask;

import com.letsgood.synergykitsdkandroid.builders.ResultObjectBuilder;
import com.letsgood.synergykitsdkandroid.builders.errors.Errors;
import com.letsgood.synergykitsdkandroid.requestmethods.Delete;
import com.letsgood.synergykitsdkandroid.requestmethods.Get;
import com.letsgood.synergykitsdkandroid.requestmethods.Patch;
import com.letsgood.synergykitsdkandroid.requestmethods.Post;
import com.letsgood.synergykitsdkandroid.requestmethods.PostFile;
import com.letsgood.synergykitsdkandroid.requestmethods.Put;
import com.letsgood.synergykitsdkandroid.resources.SynergykitError;
import com.letsgood.synergykitsdkandroid.resources.SynergykitObject;
import com.letsgood.synergykitsdkandroid.resources.SynergykitResponse;
import com.letsgood.synergykitsdkandroid.resources.SynergykitUri;

import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

public abstract class SynergykitRequest extends AsyncTask<Void, Void, Object> {

	/* Do in background */
	@Override
	protected abstract Object doInBackground(Void... params);

	/* On post execute */
	@Override
	protected abstract void onPostExecute(Object object);

	/* Request method GET */
	protected static SynergykitResponse get(SynergykitUri uri) {
		SynergykitResponse response = new SynergykitResponse();
		Get get = new Get(uri); // request method get

		response.setBufferedReader(get.execute());
		response.setStatusCode(get.getStatusCode());

		return response;
	}

	/* Request method GET */
	protected static SynergykitResponse getFile(SynergykitUri uri) {
		SynergykitResponse response = new SynergykitResponse();
		Get get = new Get(uri); // request method get

        get.setAuthorizationEnabled(false);
		response.setInputStream(get.halfExecute());
		response.setStatusCode(get.getStatusCode());

		return response;
	}
	
	/* Request method POST */
	protected static SynergykitResponse post(SynergykitUri uri, Object object) {
		SynergykitResponse response = new SynergykitResponse();
		Post post = new Post(uri, object);

		response.setBufferedReader(post.execute());
		response.setStatusCode(post.getStatusCode());

		return response;
	}


	
	/* File method POST */
	protected static SynergykitResponse postFile(SynergykitUri uri, byte[] data) {
		SynergykitResponse response = new SynergykitResponse();
		PostFile postFile = new PostFile(uri, data);

		response.setBufferedReader(postFile.execute());
		response.setStatusCode(postFile.getStatusCode());

		return response;
	}

	/* Request method PUT */
	protected static SynergykitResponse put(SynergykitUri uri, Object object) {
		SynergykitResponse response = new SynergykitResponse();
		Put put = new Put(uri, object);

		response.setBufferedReader(put.execute());
		response.setStatusCode(put.getStatusCode());

		return response;
	}

	/* Request method PUT */
	protected static SynergykitResponse delete(SynergykitUri uri) {
		SynergykitResponse response = new SynergykitResponse();
		Delete delete = new Delete(uri);

		response.setBufferedReader(delete.execute());
		response.setStatusCode(delete.getStatusCode());

		return response;
	}


    /* Request method PATCH */
    protected static SynergykitResponse patch(SynergykitUri uri, Object object) {
        SynergykitResponse response = new SynergykitResponse();
        Patch patch = new Patch(uri, object);

        response.setBufferedReader(patch.execute());
        response.setStatusCode(patch.getStatusCode());

        return response;
    }


	/* Manage response */
	protected ResponseDataHolder manageResponseToObject(
			SynergykitResponse response, Type type) {
		ResponseDataHolder dataHolder = new ResponseDataHolder();

		if (response == null
				|| response.getStatusCode() >= HttpStatus.SC_INTERNAL_SERVER_ERROR
				|| response.getStatusCode() <= Errors.SC_UNSPECIFIED_ERROR) {

			dataHolder.errorObject = ResultObjectBuilder.buildError(response.getStatusCode());
		} else if (response.getStatusCode() >= HttpStatus.SC_INTERNAL_SERVER_ERROR
				|| response.getStatusCode() <= Errors.SC_UNSPECIFIED_ERROR) {

			dataHolder.errorObject = ResultObjectBuilder.buildError(response
					.getStatusCode());

		} else if (response.getStatusCode() >= HttpStatus.SC_OK
				&& response.getStatusCode() < HttpStatus.SC_MULTIPLE_CHOICES) {

			dataHolder.statusCode = response.getStatusCode();

			if (response.getBufferedReader() != null)
				dataHolder.object = ResultObjectBuilder.buildObject(
						dataHolder.statusCode, response.getBufferedReader(),
						type);

		} else {

			dataHolder.statusCode = response.getStatusCode();
			dataHolder.errorObject = ResultObjectBuilder.buildError(
					dataHolder.statusCode, response.getBufferedReader());

		}

		return dataHolder;
	}

	/* Manage response */
	protected ResponseDataHolder manageResponseToObjects(SynergykitResponse response, Type type) {
		ResponseDataHolder dataHolder = new ResponseDataHolder();
		

		if (response == null
				|| response.getStatusCode() >= HttpStatus.SC_INTERNAL_SERVER_ERROR
				|| response.getStatusCode() <= Errors.SC_UNSPECIFIED_ERROR) {

			dataHolder.errorObject = ResultObjectBuilder.buildError(0);
		} else if (response.getStatusCode() >= HttpStatus.SC_INTERNAL_SERVER_ERROR
				|| response.getStatusCode() <= Errors.SC_UNSPECIFIED_ERROR) {

			dataHolder.errorObject = ResultObjectBuilder.buildError(response
					.getStatusCode());

		} else if (response.getStatusCode() >= HttpStatus.SC_OK
				&& response.getStatusCode() < HttpStatus.SC_MULTIPLE_CHOICES) {

			dataHolder.statusCode = response.getStatusCode();
			dataHolder.objects = ResultObjectBuilder.buildObjects(
					dataHolder.statusCode, response.getBufferedReader(), type);

		} else {

			dataHolder.statusCode = response.getStatusCode();
			dataHolder.errorObject = ResultObjectBuilder.buildError(
					dataHolder.statusCode, response.getBufferedReader());

		}

		return dataHolder;
	}

	// ----------------------------------------------------------------------------------
	protected class ResponseDataHolder {
		/* Attributes */
		public SynergykitError errorObject;
		public SynergykitObject object;
		public SynergykitObject[] objects;
		public byte[] data;
		public int statusCode;

		/* Constructor */
		public ResponseDataHolder() {
			statusCode = Errors.SC_UNSPECIFIED_ERROR;
			errorObject = null;
			object = null;
			objects = null;
			data = null;
		}
	}
}
