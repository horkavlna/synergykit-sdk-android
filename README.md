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
		DemoObject object = (DemoObject) object;
		
		//Done callback
		
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
		DemoObject[] demoObjects = (DemoObject[]) objects;
		
		//Done callback
	}
}, true);
```
