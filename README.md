<p align="center" >
<img src="https://github.com/Letsgood/synergykit-sdk-android/blob/develop/logo-synergykit-basic.png" alt="SynergyKIT" title="SynergyKIT">
</p>

## Usage

### SynergyKIT initialization
```java
SynergyKIT.init("demo","114a5371-59c6-484f-a5de-5c810ee417dd");
```

### Cache installation
```java
SynergyKIT.installCache(getApplicationContext());
```

### Records management

#### `GET` Read record from collection
```java
SynergyKIT.getRecord("demo_collection","494991d3-ecb8-4472-9c2a-1a4a1ed10946",DemoObject.class , new ResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKITError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKITObject object) {
		//Done callback
		
		DemoObject object = (DemoObject) object;
		
	}
}, true);
```
#### `GET` Read records from collection
```java
SynergyKIT.getRecords("demo_collection", DemoObject[].class, new RecordsResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKITError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKITObject[] objects) {
		//Done callback
		
		DemoObject[] demoObjects = (DemoObject[]) objects;
		
	}
}, true);
```

#### `POST` Create new record
```java
SynergyKIT.createRecord("demo_collection", demoObject ,new ResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKITError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKITObject object) {
		// Done callback
		
		DemoObject demoObject = (DemoObject) object;
	}
}, true);
```

#### `PUT` Update existing record
```java
SynergyKIT.updateRecord("demo_collection", demoObject ,new ResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKITError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKITObject object) {
		// Done callback
		
		DemoObject demoObject = (DemoObject) object;
	}
}, true);
```

#### `DELETE` Delete record
```java
SynergyKIT.deleteRecord("demo_collection", "494991d3-ecb8-4472-9c2a-1a4a1ed10946", new DeleteResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKITError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode) {
		// Done callback
		
	}
}, true);
```

### Users management

#### `GET` Read user from collection
```java
SynergyKIT.getUser("494991d3-ecb8-4472-9c2a-1a4a1ed10946", DemoUser.class,new UserResponseListener() {
			
	@Override
	public void errorCallback(int statusCode, SynergyKITError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKITUser user) {
		// Done callback
		
		DemoUser demoUser = (DemoUser) user;
		
	}
}, true);
```
#### `GET` Read users from collection
```java
SynergyKIT.getUsers(DemoUser[].class, new UsersResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKITError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKITUser[] users) {
		// Done callback
		
		DemoUser[] demoUsers = (DemoUser[]) users;
		
	}
}, true);
```

#### `POST` Create new user
```java
SynergyKIT.createUser(demoUser, new UserResponseListener() {
		
	@Override
	public void errorCallback(int statusCode, SynergyKITError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKITUser user) {
		// Done callback
		
		DemoUser demoUser = (DemoUser) user;
	}
}, true);
```

#### `PUT` Update existing user
```java
SynergyKIT.updateUser(demoUser, new UserResponseListener() {
		
	@Override
	public void errorCallback(int statusCode, SynergyKITError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKITUser user) {
		// Done callback
		
		DemoUser demoUser = (DemoUser) user;
	}
}, true);
```

#### `DELETE` Delete user
```java
SynergyKIT.deleteUser("494991d3-ecb8-4472-9c2a-1a4a1ed10946", new DeleteResponseListener() {
		
	@Override
	public void errorCallback(int statusCode, SynergyKITError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode) {
		// Done callback
		
	}
}, true);
```
### User authorization

#### `POST` Register new user
```java
SynergyKIT.registerUser(demoUser, new UserResponseListener() {
		
	@Override
	public void errorCallback(int statusCode, SynergyKITError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKITUser user) {
		// Done callback
		
		DemoUser demoUser = (DemoUser) user;
	}
}, true);
```

#### `POST` Login user
```java
SynergyKIT.loginUser(demoUser, new UserResponseListener() {
		
	@Override
	public void errorCallback(int statusCode, SynergyKITError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKITUser user) {
		// Done callback
		
		DemoUser demoUser = (DemoUser) user;
	}
});
```
### Making requests with own URI (with OData filtering)
```java
/*
 * Build your own URI 
 * 
 * Example:  Top 20 records from collection demo_collection where attribute age equals 18
 */
SynergyKITUri uri =  new UriBuilder()
				.setResource(Resource.RESOURCE_DATA)
				.setCollection("demo_collection")
				.setFilter(Filter.buildAttribute("age"), Filter.OPERATOR_EQUAL, 18)
				.setTop(20)
				.build();

/*
 * Set configuration object
 * 
 */

SynergyKITConfig config = new SynergyKITConfig();
config.setUri(uri);
config.setType(DemoObject[].class);
config.setParallelMode(false);


/*
 * Make request
 */

SynergyKIT.getRecord(config, new ResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKITError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKITObject object) {
		// Done callback
		
		DemoObject demoObjects[] = (DemoObject[]) objects;
		
	}
});
```

### Making own requests

```java
SynergyKIT.synergylize(new SynergyKITRequest() {
	
	@Override
	protected void onPostExecute(Object object) {
		ResponseDataHolder responseDataHolder = (ResponseDataHolder) object;
		
		//Manage result stored in responseDataHolder
		
		
		
	}
	
	@Override
	protected Object doInBackground(Void... params) {

		/*
		 * Build own uri
		 */
		SynergyKITUri uri = new UriBuilder()
					.setResource(Resource.RESOURCE_DATA)
					.setCollection("demo_collection")
					.build();
		
		/*
		 * Make request
		 */
		SynergyKITResponse response = SynergyKITRequest.get(uri);
		
		/*
		 * Manage response to objects and store in response data holder
		 * ResponseDataHolder is a storage for errors & objects & status code, ...
		 */
		
		ResponseDataHolder responseDataHolder = manageResponseToObjects(response, DemoObject[].class);
		
		
		return responseDataHolder;
	}
}, true);
```
### Other

You can also use SynergyKIT Android SDK for:
- [ ] Uploading and downloading files and pictures
- [ ] Sending emails and notifications

## Author

Letsgood.com s.r.o., development@letsgood.com

## License

SynergyKIT Android SDK is available under the Apache License, Version 2.0