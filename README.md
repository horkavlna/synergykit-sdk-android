<p align="center" >
<img src="https://synergykit.blob.core.windows.net/synergykit/56a5f0b93ad04ca42265cfab4e1810fb.png" alt="SynergyKIT" title="SynergyKIT" width="33%">
</p>
Letsgood.com run Backend as a Service SynergyKIT for fast and simple mobile/web/desktop applications products deployment. SynergyKIT allows enterpreneurs implement an idea to project fast and low cost like Lean Startup, validates and runs product.

We know how hard can be to work with untried API, so we prepared SDKs for mostly used platforms. If you are looking for iOS SDK, you probably want https://github.com/Letsgood/synergykit-sdk-ios.

## High level Architecture
<p align="center" >
<img src="https://synergykit.blob.core.windows.net/synergykit/56a5f0b93ad04ca42265cfab4e1810fb.png" alt="SynergyKIT" title="SynergyKIT">
</p>

## Android Sample App uses SynergyKIT SDK
Version 0.0.2:
- GET/POST requests examples
- Cloud code integration with Face++

Roadmap 0.0.3:
- Notifications
- Sign In
- Sign Up support for integration third party applications (Facebook, Google, Twitter, Github, LinkedIn, etc.)

How to use it?
- Open Android Studio
- Import project with destination folder SampleApp
- That's all. Project uses Gradle.

## SynergyKIT Android SDK
Version 0.0.2:
- REST API wrapper (CRUD operations POST, PUT, GET, DELETE)
- OData filtering
- Uploading and downloading files and pictures from global CDN network
- Sending e-mails and push notifications via GCM
- Strong hashed passwords
- HTTPs security

Version 0.0.3:
- Cloud code support

Roadmap 0.0.4:
- jCenter / Maven dependencies

How to use it?
- Open Android Studio
- Create new project/module
- Copy folder sdk/com to you project and add compile dependencies to your build.gradle script (Module: app)

```java
dependencies {
    compile 'com.facebook.android:facebook-android-sdk:+'
    compile 'com.android.support:support-v4:21.0.3'
    compile 'com.google.android.gms:play-services:+'
    compile 'com.google.code.gson:gson:2.3.1' //necessary
    compile 'com.android.support:appcompat-v7:21.0.3'
}
```
- Next version will be available via jCenter / Maven dependencies

### SynergyKIT initialization (onCreate in Application class)
```java
if(!SynergyKIT.isInit()) {
    SynergyKIT.init("synergykit-sample-app", "7cbb9eed-17dd-4f75-a7bd-c92f2f6faef9");
    SynergyKIT.setDebugModeEnabled(true); //TODO set FALSE !!
    SynergyKITLog.getInstance().setEnabled(true);
}
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
With OData you can filter, order and select data with REST Api endpoints. Web API supports the following OData query options:

#### $expand
Expands related entities inline.
#### $filter
Filters the results, based on a Boolean condition.
#### $inlinecount
Tells the server to include the total count of matching entities in the response. (Useful for server-side paging.)
#### $orderby
Sorts the results.
#### $select
Selects which properties to include in the response.
#### $skip
Skips the first n results.
#### $top
Returns only the first n the results.

More informations you can get on http://odata.org

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

## Author

<img src="http://letsgood.com/src/img/logo-letsgood.png" alt="SynergyKIT" title="SynergyKIT" width="10%"> 

Letsgood.com s.r.o., Prague, Heart of Europe

development@letsgood.com, http://letsgood.com/en



## License

SynergyKIT Android SDK is available under the Apache License, Version 2.0
