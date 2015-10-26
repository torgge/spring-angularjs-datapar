angular.module("app").service("FirebaseService", function($firebaseArray, $firebaseObject) {
  var vm = this;

  vm.ref = new Firebase("https://devmediablog.firebaseio.com/");
  vm.posts = vm.ref.child('posts');
  vm.syncObject = $firebaseObject(vm.ref);

  this.add = function(post) {
    vm.posts.push(post);
  };

  this.getPosts = function() {
    return vm.syncObject;
  };

});
