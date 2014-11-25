<p align="center" >
<img src="https://synergykit.com/images/logo-synergykit.png" alt="SynergyKIT" title="SynergyKIT">
</p>

## Usage

### SynergyKIT inicialization
```java
SynergyKIT.init("demo","114a5371-59c6-484f-a5de-5c810ee417dd");
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
