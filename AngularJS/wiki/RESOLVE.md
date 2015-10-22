Using Resolve In AngularJS Routes

In a previous post about testing I mentioned that route resolves can make authoring unit tests for a controller easier. Resolves can also help the user experience.

A resolve is a property you can attach to a route in both ngRoute and the more robust UI router. A resolve contains one or more promises that must resolve successfully before the route will change. This means you can wait for data to become available before showing a view, and simplify the initialization of the model inside a controller because the initial data is given to the controller instead of the controller needing to go out and fetch the data.

As an example, let’s use the following simple service which uses $q to simulate the async work required to fetch some data.

```js

app.factory("messageService", function($q){
    return {
        getMessage: function(){
            return $q.when("Hello World!");
        }
    };
});

```

And now the routing configuration that will use the service in a resolve.

```js

$routeProvider
    .when("/news", {
        templateUrl: "newsView.html",
        controller: "newsController",
        resolve: {
            message: function(messageService){
                return messageService.getMessage();
        }
    }
})

```


Resolve is a property on the routing configuration, and each property on resolve can be an injectable function (meaning it can ask for service dependencies). The function should return a promise.

When the promise completes successfully, the resolve property (message in this scenario) is available to inject into a controller function. In other words, all a controller needs to do to grab data gathered during resolve is to ask for the data using the same name as the resolve property (message).

```js

app.controller("newsController", function (message) {
    $scope.message = message;
});

```

You can work with multiple resolve properties. As an example, let’s introduce a 2nd service. Unlike the messageService, this service is a little bit slow.

```js

app.factory("greetingService", function($q, $timeout){
   return {
       getGreeting: function(){
           var deferred = $q.defer();
           $timeout(function(){
               deferred.resolve("Allo!");
           },2000);
           return deferred.promise;
       }
   }
});

```

Now the resolve in the routing configuration has two promise producing functions.

```js

.when("/news", {
    templateUrl: "newsView.html",
    controller: "newsController",
    resolve: {
        message: function(messageService){
            return messageService.getMessage();
        },
        greeting: function(greetingService){
            return greetingService.getGreeting();
        }
    }
})

```

And the associated controller can ask for both message and greeting.

```js

app.controller("newsController", function ($scope, message, greeting) {
    $scope.message = message;
    $scope.greeting = greeting;
});


```


Composing Resolve
Although there are benefits to moving code out of a controller, there are also drawbacks to having code inside the route definitions. For controllers that require a complicated setup I like to use a small service dedicated to providing resolve features for a controller. The service relies heavily on promise composition and might look like the following.

```js

app.factory("newsControllerInitialData", function(messageService, greetingService, $q) {
    return function() {
        var message = messageService.getMessage();
        var greeting = greetingService.getGreeting();
 
        return $q.all([message, greeting]).then(function(results){
            return {
                message: results[0],
                greeting: results[1]
            };
        });
    }
});

```

Not only is the code inside a service easier to test than the code inside a route definition, but the route definitions are also easier to read.

```js
.when("/news", {
    templateUrl: "newsView.html",
    controller: "newsController",
    resolve: {
        initialData: function(newsControllerInitialData){
            return newsControllerInitialData();
        }
    }
})

```


And the controller is also easy.

```js

app.controller("newsController", function ($scope, initialData) {
    $scope.message = initialData.message;
    $scope.greeting = initialData.greeting;
});

```

One of the keys to all of this working is $q.all, which is a beautiful way to compose promises and run requests in parallel.

Source: http://odetocode.com/blogs/scott/archive/2014/05/20/using-resolve-in-angularjs-routes.aspx
