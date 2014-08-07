synergykit-android
==================

###SynergyKIT examples:


  **Attributes:**
  
  ```
	static final String TENANT = "example-tenant";	//Registered tenant on www.synergykit.com
	static final String APPLICATION_KEY = "01234567-890a-bcde-fghi-jklmnopqrstu";	//Unique key genereted on www.synergykit.com
	
	String collectionUrl = "example-collection"; //Unique collection URL 
	String recordId = "0123456789abcdefghijklmn"; //Unique record ID
	MyObject myObecjt = new MyObject(); //Some object to store/unstore
	Type type = MyObject.class; //Object type
	Type arrayType = MyObject[].class //Object type
  ```
	
**Initialization:**
  
  ```
  Synergykit.init(TENANT, APPLICATION_KEY);
  ```
  
  **Create Record:**
  
  ```
  Synergykit.createRecord(collectionUrl, myObecjt, new BaseResponseListener() {
  	
  	//Error callback is called when record WAS NOT created
  	//statusCode - HTTP response status code
  	//errorObject - contains error code and error message
  	@Override
  	public void errorCallback(int statusCode, SynergykitErrorObject errorObject) {
  		
  		
  	}
  	
  	//Done callback is called when record was successfully created
  	//statusCode - HTTP response status code
  	//baseObject - object which was created on SynergyKIT (! SynergykitBaseObject type !)
  	@Override
  	public void doneCallback(int statusCode, SynergykitBaseObject baseObject) {
  		MyObject object = (MyObject)baseObject; //Change data type
  		
  	}
  }, type);
  ```
  
  **Get record:**
  ```
  Synergykit.getRecord(collectionUrl, recordId, new BaseResponseListener() {
  	
  	//Error callback is called when record WAS NOT returned
  	//statusCode - HTTP response status code
  	//errorObject - contains error code and error message
  	@Override
  	public void errorCallback(int statusCode, SynergykitErrorObject errorObject) {
  		
  		
  	}
  	
  	//Done callback is called when record was successfully returned
  	//statusCode - HTTP response status code
  	//baseObject - object which was returned from SynergyKIT (! SynergykitBaseObject type !)
  	@Override
  	public void doneCallback(int statusCode, SynergykitBaseObject baseObject) {
  		MyObject object = (MyObject)baseObject; //Change data type
  		
  	}
  }, type);
  ```
  
  **Get all records:**
  ```
  Synergykit.getRecords(collectionUrl, new GetRecordsResponseListener() {
  	
  	//Error callback is called when records WERE NOT returned
  	//statusCode - HTTP response status code
  	//errorObject - contains error code and error message
  	@Override
  	public void errorCallback(int statusCode, SynergykitErrorObject errorObject) {
  		
  		
  	}
  	
  	//Done callback is called when records were successfully returned
  	//statusCode - HTTP response status code
  	//baseObject - array of objects which were created on SynergyKIT (! SynergykitBaseObject[] type !)
  	@Override
  	public void doneCallback(int statusCode, SynergykitBaseObject[] baseObject) {
  		MyObject object = (MyObject)baseObject[0]; //Change data type of first object
  		
  	}
  }, arrayType);
  ```
  
  **Update record:**
  ```
  Synergykit.updateRecord(collectionUrl, recordId, myObecjt, new BaseResponseListener() {
  	
  	//Error callback is called when record WAS NOT updated
  	//statusCode - HTTP response status code
  	//errorObject - contains error code and error message
  	@Override
  	public void errorCallback(int statusCode, SynergykitErrorObject errorObject) {
  		
  		
  	}
  	
  	//Done callback is called when record was successfully updated
  	//statusCode - HTTP response status code
  	//baseObject - objects which was updated on SynergyKIT (! SynergykitBaseObject type !)
  	@Override
  	public void doneCallback(int statusCode, SynergykitBaseObject baseObject) {
  		MyObject object = (MyObject)baseObject; //Change data type 
  		
  	}
  }, type);
  ```
  
  **Delete record:**
  ```
  Synergykit.deleteRecord(collectionUrl, recordId, new DeleteResponseListener() {
  	
  	//Error callback is called when record WAS NOT deleted
  	//statusCode - HTTP response status code
  	//errorObject - contains error code and error message
  	@Override
  	public void errorCallback(int statusCode, SynergykitErrorObject errorObject) {
  		
  		
  	}
  	
  	//Done callback is called when record was successfully deleted
  	//statusCode - HTTP response status code
  	@Override
  	public void doneCallback(int statusCode) {
  	
  		
  	}
  });
  ```
  
  **Customize request:**
  ```
  Synergykit.synergylize(new SynergylizeRequestAsyncTask() {
  	
  	//AsyncTask method which can be used for print results
  	@Override
  	protected void onPostExecute(Object object) {
  		
  		
  	}
  	
  	//AsyncTask method which can be used for multiple request
  	@Override
  	protected Object doInBackground(Void... params) {
  		
  		//Build own SynergyKIT URL
  		UrlBuilder urlBuilder = new UrlBuilder();
  		urlBuilder.setResource(UrlBuilder.RESOURCE_DATA)
  				   .setResourceUrl(collectionUrl);
  		
  		Url url = urlBuilder.build();
  		
  		//POST request
  		post(url, myObecjt, new BaseResponseListener() {
  			
  			//Error callback is called when record WAS NOT created
  			//statusCode - HTTP response status code
  			//errorObject - contains error code and error message
  			@Override
  			public void errorCallback(int statusCode, SynergykitErrorObject errorObject) {
  				
  				
  			}
  			
  			//Done callback is called when record was successfully created
  			//statusCode - HTTP response status code
  			//baseObject - object which was created on SynergyKIT (! SynergykitBaseObject type !)
  			@Override
  			public void doneCallback(int statusCode, SynergykitBaseObject baseObject) {
  				MyObject object = (MyObject)baseObject; //Change data type
  				
  			}
  		}, type);
  		
  		
  		//Build new own Synergykit Url
  		urlBuilder = new UrlBuilder();
  		urlBuilder.setResource(UrlBuilder.RESOURCE_DATA)
  				   .setResourceUrl(collectionUrl)
  				   .setResourceId(recordId);
  		
  		url = urlBuilder.build();
  		
  						
  		//DELETE request
  		delete(url,new DeleteResponseListener() {
  			
  			//Error callback is called when record WAS NOT deleted
  			//statusCode - HTTP response status code
  			//errorObject - contains error code and error message
  			@Override
  			public void errorCallback(int statusCode, SynergykitErrorObject errorObject) {
  				
  				
  			}
  			
  			//Done callback is called when record was successfully deleted
  			//statusCode - HTTP response status code
  			@Override
  			public void doneCallback(int statusCode) {
  			
  				
  			}});
  		
  		
  		return null;
  	}
  });
  ```
