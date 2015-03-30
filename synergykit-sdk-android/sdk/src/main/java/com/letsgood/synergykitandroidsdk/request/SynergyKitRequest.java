package com.letsgood.synergykitandroidsdk.request;



/*
 * Copyright 2014 Letsgood.com s.r.o.
 *
 */

import android.os.AsyncTask;

import com.letsgood.synergykitandroidsdk.builders.ResultObjectBuilder;
import com.letsgood.synergykitandroidsdk.builders.errors.Errors;
import com.letsgood.synergykitandroidsdk.requestmethods.Delete;
import com.letsgood.synergykitandroidsdk.requestmethods.Get;
import com.letsgood.synergykitandroidsdk.requestmethods.Patch;
import com.letsgood.synergykitandroidsdk.requestmethods.Post;
import com.letsgood.synergykitandroidsdk.requestmethods.PostFile;
import com.letsgood.synergykitandroidsdk.requestmethods.Put;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitError;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitObject;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitResponse;
import com.letsgood.synergykitandroidsdk.resources.SynergyKitUri;

import org.apache.http.HttpStatus;

import java.lang.reflect.Type;

public abstract class SynergyKitRequest extends AsyncTask<Void, Void, Object> {

	/* Do in background */
	@Override
	protected abstract Object doInBackground(Void... params);

	/* On post execute */
	@Override
	protected abstract void onPostExecute(Object object);

	/* Request method GET */
	protected static SynergyKitResponse get(SynergyKitUri uri) {
		SynergyKitResponse response = new SynergyKitResponse();
		Get get = new Get(uri); // request method get

		response.setBufferedReader(get.execute());
		response.setStatusCode(get.getStatusCode());

		return response;
	}

	/* Request method GET */
	protected static SynergyKitResponse getFile(SynergyKitUri uri) {
		SynergyKitResponse response = new SynergyKitResponse();
		Get get = new Get(uri); // request method get

        get.setAuthorizationEnabled(false);
		response.setInputStream(get.halfExecute());
		response.setStatusCode(get.getStatusCode());

		return response;
	}
	
	/* Request method POST */
	protected static SynergyKitResponse post(SynergyKitUri uri, Object object) {
		SynergyKitResponse response = new SynergyKitResponse();
		Post post = new Post(uri, object);

		response.setBufferedReader(post.execute());
		response.setStatusCode(post.getStatusCode());

		return response;
	}


	
	/* File method POST */
	protected static SynergyKitResponse postFile(SynergyKitUri uri, byte[] data) {
		SynergyKitResponse response = new SynergyKitResponse();
		PostFile postFile = new PostFile(uri, data);

		response.setBufferedReader(postFile.execute());
		response.setStatusCode(postFile.getStatusCode());

		return response;
	}

	/* Request method PUT */
	protected static SynergyKitResponse put(SynergyKitUri uri, Object object) {
		SynergyKitResponse response = new SynergyKitResponse();
		Put put = new Put(uri, object);

		response.setBufferedReader(put.execute());
		response.setStatusCode(put.getStatusCode());

		return response;
	}

	/* Request method PUT */
	protected static SynergyKitResponse delete(SynergyKitUri uri) {
		SynergyKitResponse response = new SynergyKitResponse();
		Delete delete = new Delete(uri);

		response.setBufferedReader(delete.execute());
		response.setStatusCode(delete.getStatusCode());

		return response;
	}


    /* Request method PATCH */
    protected static SynergyKitResponse patch(SynergyKitUri uri, Object object) {
        SynergyKitResponse response = new SynergyKitResponse();
        Patch patch = new Patch(uri, object);

        response.setBufferedReader(patch.execute());
        response.setStatusCode(patch.getStatusCode());

        return response;
    }


	/* Manage response */
	protected ResponseDataHolder manageResponseToObject(
			SynergyKitResponse response, Type type) {
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
	protected ResponseDataHolder manageResponseToObjects(SynergyKitResponse response, Type type) {
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
		public SynergyKitError errorObject;
		public SynergyKitObject object;
		public SynergyKitObject[] objects;
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
