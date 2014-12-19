<p align="center" >
<img src="https://synergykit.com/images/logo-synergykit.png" alt="SynergyKIT" title="SynergyKIT">
</p>

[![Version](https://img.shields.io/cocoapods/v/SynergyKIT-SDK-iOS.svg?style=flat)](http://cocoadocs.org/docsets/SynergyKIT-SDK-iOS)
[![License](https://img.shields.io/cocoapods/l/SynergyKIT-SDK-iOS.svg?style=flat)](http://cocoadocs.org/docsets/SynergyKIT-SDK-iOS)
[![Platform](https://img.shields.io/cocoapods/p/SynergyKIT-SDK-iOS.svg?style=flat)](http://cocoadocs.org/docsets/SynergyKIT-SDK-iOS)

## Installation

SynergyKIT-SDK-iOS is available through [CocoaPods](http://cocoapods.org). To install
it, simply add the following line to your Podfile:

    pod "SynergyKIT-SDK-iOS"

## Usage

To run the example project, clone the repo, and run `pod install` from the Example directory first.

### SynergyKIT inicialization
```objective-c
SKSynergy *synergyKIT = [SKSynergy sharedInstance];
synergyKIT.tenant = @"demo";
synergyKIT.applicationKey = @"114a5371-59c6-484f-a5de-5c810ee417dd";
synergyKIT.logUrlCall = YES;
synergyKIT.logRequestResponse = YES;
```

### Records management

#### `GET` Read record from collection
```objective-c
SKSynergy *synergyKIT = [SKSynergy sharedInstance];
[synergyKIT recordIn:@"collection-name" recordId:@"record-id" resultType:[DemoObject class] completion:^(BaseObject *result, NSError *error) {
    if (!error && result)
    {
        DemoObject *object = (DemoObject *)result;
    }
    else if (!error && !result)
    {
        // No result – no data read
    }
    else
    {
        // Error
    }
}];
```

#### `UPDATE` Update existing record
```objective-c
SKSynergy *synergyKIT = [SKSynergy sharedInstance];
[synergyKIT updateRecordIn:@"collection-name" record:@"record-id" resultType:[DemoObject class] completion:^(BaseObject *result, NSError *error) {
    if (!error && result)
    {
        DemoObject *object = (DemoObject *)result;
    }
    else
    {
        // Error
    }
}];
```

#### `POST` Create new record
```objective-c
SKSynergy *synergyKIT = [SKSynergy sharedInstance];
DemoObject *record = [DemoObject new];
record.text = @"String";
[synergyKIT createRecordIn:@"collection-name" record:record resultType:[DemoObject class] completion:^(BaseObject *result, NSError *error) {
    if (!error && result)
    {
        DemoObject *object = (DemoObject *)result;
    }
    else
    {
        // Error
    }
}];
```

#### `DELETE` Delete record
```objective-c
SKSynergy *synergyKIT = [SKSynergy sharedInstance];
[synergyKIT deleteRecordIn:@"collection-name" recordId:@"record-id" resultType:[BaseObject class] completion:^(id result, NSError *error) {
    if (!error)
    {
        // Success
    }
}];
```

### OData filtering
SynergyKIT uses OData protocol for content filtering. With `SKODataExpression` advanced filters can be easilly builded.
```objective-c
// Records with name equal to John and salary >= 5000
NSString *filter = [[[[[SKODataExpression new] filterField:@"name" operator:@"==" value:@"John"] filterAnd] filterField:@"salary" operator:@">=" value:[NSNumber numberWithInt:5000]] urlExtension];

// Five oldest records starting with Mo in field day.
NSString *filter = [[[[[SKODataExpression new] filterStartsWith:@"Mo" inField:@"day"] orderBy:@"date" direction:OrderByDirectionAsc] top:5] urlExtension];
```


### Advanced Cache Policy
`SynegyKIT-SDK-iOS` implements new advanced cache policy however `NSURLRequestCachePolicy` is supported too.

#### CacheElseLoad
Returns cached data if exists.
```objective-c
SKCache *cache = [[SKCache alloc] initWithType:SKCacheTypeCacheElseLoad];
[SKSynergy sharedInstance] recordIn:@"collection-name" recordId:@"record-id" resultType:[BaseObject class] cache:cache completion:^(BaseObject *result, NSError *error) {
…
```

#### CacheElseLoad with Expiration
Returns cached data if exists and is valid. Cached data will be invalidate after expiration.
```objective-c
SKCache *cache = [[SKCache alloc] initWithType:SKCacheTypeCacheElseLoad andExpiration:5*60];
[SKSynergy sharedInstance] recordIn:@"collection-name" recordId:@"record-id" resultType:[BaseObject class] cache:cache completion:^(BaseObject *result, NSError *error) {
…
```

#### LoadElseCache
Returns cached data if exists and internet connection is not available.
```objective-c
SKCache *cache = [[SKCache alloc] initWithType:SKCacheTypeLoadElseCache];
[SKSynergy sharedInstance] recordIn:@"collection-name" recordId:@"record-id" resultType:[BaseObject class] cache:cache completion:^(BaseObject *result, NSError *error) {
…
```

#### LoadElseCache with Expiration
Returns cached data if exists and internet connection is not available. Cached data will be invalidate after expiration.
```objective-c
SKCache *cache = [[SKCache alloc] initWithType:SKCacheTypeLoadElseCache andExpiration:5*60];
[SKSynergy sharedInstance] recordIn:@"collection-name" recordId:@"record-id" resultType:[BaseObject class] cache:cache completion:^(BaseObject *result, NSError *error) {
…
```

## Author

Letsgood.com s.r.o., development@letsgood.com

## License

SynergyKIT-SDK-iOS is available under the MIT license. See the LICENSE file for more info.
