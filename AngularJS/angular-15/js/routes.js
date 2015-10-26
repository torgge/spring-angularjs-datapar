angular.module("app").config(function($routeProvider) {

  $routeProvider.when('/', {
    templateUrl : 'home.html',
    controller : 'HomeController',
    resolve: {
      "syncObject" : function(FirebaseService) {
        return FirebaseService.getPosts();
      }
    }
  }).when('/novo', {
    templateUrl : 'novo.html',
    controller : 'NovoController'
  });

});
