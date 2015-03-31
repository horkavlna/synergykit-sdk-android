<p align="center" >
<img src="https://synergykit.blob.core.windows.net/synergykit/fbf107a82dceb49843f8de1fda4d9ea9.png" alt="SynergyKIT" title="SynergyKIT" width="33%">
</p>
Letsgood.com run Backend as a Service SynergyKit for fast and simple mobile/web/desktop applications products deployment. SynergyKit allows enterpreneurs implement an idea to project fast and low cost like Lean Startup, validates and runs product.

We know how hard can be to work with untried API, so we prepared SDKs for mostly used platforms. If you are looking for iOS SDK, you probably want https://github.com/Letsgood/synergykit-sdk-ios.

## High level Architecture
<p align="center" >
<img src="https://synergykit.blob.core.windows.net/synergykit/56a5f0b93ad04ca42265cfab4e1810fb.png" alt="SynergyKIT" title="SynergyKIT">
</p>

## Android Sample App uses SynergyKit Android SDK
Version 0.0.4:
- GET/POST requests examples
- Cloud code integration with Face++

Roadmap 0.0.5:
- Notifications
- Sign In
- Sign Up support for integration third party applications (Facebook, Google, Twitter, Github, LinkedIn, etc.)

How to use it?
- Open Android Studio
- Open existing project 
- That's all. Project uses Gradle.

## SynergyKit Android SDK
Version2.1.0:
- REST API wrapper (CRUD operations POST, PUT, GET, DELETE)
- OData filtering
- Uploading and downloading files and pictures from global CDN network
- Sending e-mails and push notifications via GCM
- Strong hashed passwords
- HTTPs security
- New REST API (2.1.0) interface supported
- Cloud code support
- Socket.IO 

Roadmap2.1.1:
- jCenter

How to use it?
- Open Android Studio
- Create / open your project
- Add repository to repositories in build.gradle of your project
```java
dependencies {
  jcenter()
  maven {
	  url  "http://dl.bintray.com/letsgood/maven"
  }
}
```
- Add dependency to your project/module build.gradle


```java
dependencies {
  compile 'synergykit-sdk-android:sdk:+'
}
```


### SynergyKit initialization
The initialization must be the first step of using SynergyKit Android SDK. Typically it's called from onCreate method of Application. If you don't know your application tenant or application key visit our https://synergykit.com website. Both of this are available there. 

#### Base initialization
```java

private static final String APPLICATION_TENANT = "synergykit-sample-app";
private static final String APPLICATION_KEY = "7cbb9eed-17dd-4f75-a7bd-c92f2f6faef9";

.
.
.

if(!SynergyKit.isInit()) {
    SynergyKit.init(APPLICATION_TENANT, APPLICATION_KEY);
}
```

#### Debug mode settings

You can also enable a debug mode. In debug mode SynergyKit Android SDK prints error messages and endpoint URI to console. 

```java
SynergyKit.setDebugModeEnabled(true);
```
An example code from Sample App:
```java

public class SampleAppApplication extends Application {

	private static final String APPLICATION_TENANT = "synergykit-sample-app";
	private static final String APPLICATION_KEY = "7cbb9eed-17dd-4f75-a7bd-c92f2f6faef9";
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		if(!SynergyKit.isInit()) {
			SynergyKit.init(APPLICATION_TENANT, APPLICATION_KEY);
	        SynergyKit.setDebugModeEnabled(true);
		}
	 }
 }
```


### Cache installation

The SynergyKit Android SDK provides Http response cache (HttpResponseCache). Http response cache caches all of your application's HTTP requests. This cache requires Android 4.0  or later.

You can install this cache with default cache dir size (10 MiB):
```java
SynergyKit.installCache(getApplicationContext());
```

Or you can install this cache with your own cache dir size:
```java
long cacheSize = 8 * 1024 * 1024; //8 MiB
SynergyKit.installCache(getApplicationContext(), cacheSize);
```

You can also flush installed cache:

```java
SynergyKit.flushCache();
```

### Records management
SynergyKit Android SDK provides CRUD methods to read or modify record(s). Every of this method has done and error callback. 

Done callback is called when everything was done without any error. It's called with Http Status Code and SynergyKitObject / SynergyKitObjects which may be retype to expected object type.

Error callback is called when the error occurred. It's called also with Http Status Code and SynergyKitError object.

#### `GET` Read record from collection
```java
private static final String COLLECTION = "demo_collection";
private static final String RECORD_ID = "494991d3-ecb8-4472-9c2a-1a4a1ed10946"; 
private static final Type OBJECT_TYPE = DemoObject.class;
private static final boolean PARALLEL_MODE = false;

.
.
.

SynergyKit.getRecord(COLLECTION,RECORD_ID,OBJECT_TYPE , new ResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKitObject object) {
		//Done callback		
		DemoObject object = (DemoObject) object;
		
	}
}, PARALLEL_MODE);
```
#### `GET` Read records from collection
```java
private static final String COLLECTION = "demo_collection";
private static final Type OBJECTS_TYPE = DemoObject[].class;
private static final boolean PARALLEL_MODE = false;

.
.
.

SynergyKit.getRecords(COLLECTION, OBJECTS_TYPE, new ResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKitObject[] object) {
		//Done callback		
		DemoObject[] object = (DemoObject[]) object;
		
	}
}, PARALLEL_MODE);
```

#### `POST` Create new record
```java

private static final String COLLECTION = "demo_collection";
private static final boolean PARALLEL_MODE = false;
DemoObject demoObject = new DemoObject();

.
.
.

SynergyKit.createRecord(COLLECTION, demoObject ,new ResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKitObject object) {
		// Done callback
		
		DemoObject demoObject = (DemoObject) object;
	}
}, PARALLEL_MODE);
```

#### `PUT` Update existing record
```java
private static final String COLLECTION = "demo_collection";
private static final boolean PARALLEL_MODE = false;
DemoObject demoObject = new DemoObject();
demoObject.setRecordId("15038c19-35d2-4b70-baa1-dcc8f36dbd33");




SynergyKIT.updateRecord(COLLECTION, demoObject ,new ResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKitObject object) {
		// Done callback
		
		DemoObject demoObject = (DemoObject) object;
	}
}, PARALLEL_MODE);
```

#### `PATCH` Patch existing record
```java
private static final String COLLECTION = "demo_collection";
DemoObject demoObject = new DemoObject();
demoObject.setRecordId("15038c19-35d2-4b70-baa1-dcc8f36dbd33");
private static final boolean PARALLEL_MODE = false;

.
.
.

SynergyKIT.patchRecord(COLLECTION, demoObject ,new ResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKitObject object) {
		// Done callback
		
		DemoObject demoObject = (DemoObject) object;
	}
}, PARALLEL_MODE);
```

#### `DELETE` Delete record
```java
private static final String COLLECTION = "demo_collection";
private static final String RECORD_ID = "15038c19-35d2-4b70-baa1-dcc8f36dbd33";
private static final boolean PARALLEL_MODE = false;

.
.
.

SynergyKit.deleteRecord(COLLECTION, RECORD_ID, new DeleteResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode) {
		// Done callback
		
	}
}, PARALLEL_MODE);
```

### Users management

SynergyKit Android SDK provides CRUD methods to read or modify users. Every of this method has done and error callback. 

Done callback is called when everything was done without any error. It's called with Http Status Code and SynergyKitUser / SynergyKitUsers.

Error callback is called when the error occurred. It's called also with Http Status Code and SynergyKitError object.


#### `GET` Read user from collection
```java
private static final String USER_ID = "494991d3-ecb8-4472-9c2a-1a4a1ed10946";
private static final boolean PARALLEL_MODE = false;

.
.
.

SynergyKit.getUser(USER_ID, DemoUser.class,new UserResponseListener() {
			
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKitUser user) {
		// Done callback
		
		DemoUser demoUser = (DemoUser) user;
		
	}
}, PARALLEL_MODE);
```
#### `GET` Read users from collection
```java
SynergyKit.getUsers(DemoUser[].class, new UsersResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKitUser[] users) {
		// Done callback
		
		DemoUser[] demoUsers = (DemoUser[]) users;
		
	}
}, true);
```

#### `POST` Create new user
```java
DemoUser demoUser = new DemoUser();

SynergyKit.createUser(demoUser, new UserResponseListener() {
		
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKitUser user) {
		// Done callback
		
		DemoUser demoUser = (DemoUser) user;
	}
}, true);
```

#### `PUT` Update existing user
```java
DemoUser demoUser = new DemoUser();

SynergyKit.updateUser(demoUser, new UserResponseListener() {
		
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKitUser user) {
		// Done callback
		
		DemoUser demoUser = (DemoUser) user;
	}
}, true);
```
#### `PATCH` Patch existing user
```java
DemoUser demoUser = new DemoUser();

SynergyKit.updateUser(demoUser, new UserResponseListener() {
		
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKitUser user) {
		// Done callback
		
		DemoUser demoUser = (DemoUser) user;
	}
}, true);
```

#### `DELETE` Delete user
```java
DemoUser demoUser = new DemoUser();
demoUser.set__id("494991d3-ecb8-4472-9c2a-1a4a1ed10946");

SynergyKit.deleteUser(demoUser, new DeleteResponseListener() {
		
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode) {
		// Done callback
		
	}
}, true);
```
### User authorization
SynergyKit Android SDK provides also authorization. You can register new user or login existing one.


#### `POST` Register new user
This method register new user. New user must have email and password. These parameters must be unique.
Register method has also done and error callback.


```java

DemoUser demoUser = new DemoUser();
demoUser.setEmail("demouser@synergykit.com");
demoUser.setPassword("mystrongpassword");

SynergyKit.registerUser(demoUser, new UserResponseListener() {
		
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKitUser user) {
		// Done callback
		
		DemoUser demoUser = (DemoUser) user;
	}
}, true);
```

#### `POST` Login user
This method login existing user.  User verification is provided by email and password. These parameters must be unique.

Login method has also done and error callback. 

```java
SynergyKit.loginUser(demoUser, new UserResponseListener() {
		
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKitUser user) {
		// Done callback
		
		DemoUser demoUser = (DemoUser) user;
	}
});
```



After login is automatically set session token to SynergyKit. It's necessery for next request authorization. But you can do it manually too.

```java
SynergyKit.setToken("ae10ecfdaa33658d563a2s55");
```


### Making requests with own URI (with OData filtering)
With OData you can filter, order and select data with REST Api endpoints. Web API supports the following OData query options:

#### $expand
Expands related entities inline.
#### $filter
Filters the results, based on a Boolean condition.
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

private static final String COLLECTION = "demo_collection";

.
.
.

SynergyKitUri uri =  new UriBuilder()
				.setResource(Resource.RESOURCE_DATA)
				.setCollection(COLLECTION)
				.setFilter(Filter.buildAttribute("age"), Filter.OPERATOR_EQUAL, 18)
				.setTop(20)
				.build();

/*
 * Set configuration object
 * 
 */

SynergyKitConfig config = new SynergyKITConfig();
config.setUri(uri);
config.setType(DemoObject[].class);
config.setParallelMode(false);


/*
 * Make request
 */

SynergyKit.getRecord(config, new ResponseListener() {
	
	@Override
	public void errorCallback(int statusCode, SynergyKitError errorObject) {
		// Error callback
		
	}
	
	@Override
	public void doneCallback(int statusCode, SynergyKitObject object) {
		// Done callback
		
		DemoObject demoObjects[] = (DemoObject[]) objects;
		
	}
});
```

### Making own requests

```java
private static final String COLLECTION = "demo_collection";
private static final boolean PARALLEL_MODE = false;

.
.
.

SynergyKit.synergylize(new SynergyKitRequest() {
	
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
		SynergyKitUri uri = new UriBuilder()
					.setResource(Resource.RESOURCE_DATA)
					.setCollection("demo_collection")
					.build();
		
		/*
		 * Make request
		 */
		SynergyKitResponse response = SynergyKitRequest.get(uri);
		
		/*
		 * Manage response to objects and store in response data holder
		 * ResponseDataHolder is a storage for errors & objects & status code, ...
		 */
		
		ResponseDataHolder responseDataHolder = manageResponseToObjects(response, DemoObject[].class);
		
		
		return responseDataHolder;
	}
}, PARALLEL_MODE);
```

###Socket
SynergyKit provides Socket.IO to make real-time communication. With this funcion you can develop dynamic applications like a chat. More information about Socket.IO is available here http://socket.io/ .

####Connect socket
To connect socket to SynergyKit server you must call SynergyKit.connect() method.

```java
SynergyKit.connectSocket();
```

If you need to listend states `connected`, `disconnected` and `reconnected` you can call connect method with listener.

```java
SynergyKit.connectSocket(new SocketStateListener() {
   @Override
   public void connected() {
      //this method is called when socket was connected
   }

   @Override
   public void disconnected() {
	 //this method is called when socket was disconnected
   }

   @Override
   public void reconnected() {
	 //this method is called when socket was reconnected
   }
});
```

#### Listen collection changes
SynergyKit provides listening changes of records in data collection. You can listen  `created`, `updated`, `patched`, `deleted`.  You can set / unset listener befor or after socket connection.

```java

/*
* If you wanna listen new posted records to collection demo_collection you can use this code.
*/

private static final String COLLECTION = "demo_collection";

.
.
.

SynergyKit.onSocket(Socket.MESSAGE_CREATED,COLLECTION,new SocketEventListener() {
	  @Override
      public void call(Object... args) {
		// this method is called when new record was created in collection demo_collection
             
      }

      @Override
      public void subscribed() {
		// this method is called when listener was subscribed and is ready to listen
      }

      @Override
      public void unsubscribed() {
		// this method is called when you call SynergyKit.offSocket(...); method and listener is unsubscribed 
      }

      @Override
      public void unauthorized() {
		// this method is called when listener was not authorized. Typically it's caused by wrong or invalid token.
      }
     });
```

If you don't want to listen collection changes anymore you can call SynergyKit.offSocket(...) method.
```java

/*
* If you wanna stop listen new posted records to collection demo_collection you can use this code.
*/


private static final String COLLECTION = "demo_collection";

.
.
.

SynergyKit.offSocket(Socket.MESSAGE_CREATED,COLLECTION);
```

#### Listen messages
You can also listen messages which are directly send to listeners and are not stored in SynergyKit.

```java

/*
* For example if you're developing chat and you wanna listen state typing you can use this code.
*/

private static final String EVENT_TYPING = "typing";

.
.
.

SynergyKit.onSocket(EVENT_TYPING,new SocketEventListener() {
	  @Override
      public void call(Object... args) {
		// this method is called when someone is typing - someone send emit typing.
        
        String data =((JSONObject) args[0]).toString();
        final Message message = GsonWrapper.getGson().fromJson(data, Message.class);     
        
      }

      @Override
      public void subscribed() {
		// this method is called when listener was subscribed and is ready to listen
      }

      @Override
      public void unsubscribed() {
		// this method is called when you call SynergyKit.offSocket(...); method and listener is unsubscribed 
      }

      @Override
      public void unauthorized() {
		// this method is called when listener was not authorized. Typically it's caused by wrong or invalid token.
      }
     });
```

#### Send message
You can send message which is directly send to listeners and is not stored in SynergyKit.

```java

/*
* For example if you're developing chat and you wanna send message about typing you can use this code.
*/

private static final String EVENT_TYPING = "typing";

.
.
.

Message message = new Message();
message.setText("TestUser is typing");

SynergyKit.emitViaSocket(EVENT_TYPING,message);
```


#### Disconnect socket
To disconnect socket use method disconnectSocket();
```java
SynergyKit.disconnectSocket();
```

###CloudCode
SynergyKit provides using cloudcode. You can simple write your code and invoke it by sdk.

<p align="center" >
<img src="https://synergykit.blob.core.windows.net/synergykit-sample-app/cloudcode.png" alt="SynergyKIT" title="SynergyKIT">
</p>

```java
/*
* Build your function uri
*/
SynergyKitUri uri = new UriBuilder()
                     .setResource(Resource.RESOURCE_FUNCTIONS)
                     .setFunctionId("face-recognition")
                     .build();

/*
* Create configuration object
*/
SynergyKitConfig config = new SynergyKitConfig();
config.setParallelMode(false);
config.setType(SynergyKitObject.class);
config.setUri(uri);


/*
* Create object which extedns from SynergyKitObject and contains your cloudcode function  parameters
*/

CloudeCodeParams params = new CloudCodeParams();

/*
* Invoke cloud code
*/
SynergyKit.invokeCloudCode(config,params,new ResponseListener() {
       @Override
       public void doneCallback(int statusCode, SynergyKitObject object) {
           
       }

       @Override
       public void errorCallback(int statusCode, SynergyKitError errorObject) {

       }
   });
```

## Author

<img src="http://letsgood.com/src/img/logo-letsgood.png" alt="SynergyKIT" title="SynergyKIT" width="10%"> 

Letsgood.com s.r.o., Prague, Heart of Europe

development@letsgood.com, http://letsgood.com/en



## License

SynergyKIT Android SDK is available under the Apache License, Version 2.0
