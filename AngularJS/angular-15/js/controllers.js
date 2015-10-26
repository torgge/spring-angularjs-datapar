angular.module("app").controller("HomeController", ["$scope", "syncObject",
  function($scope, syncObject) {
    syncObject.$bindTo($scope, "data");
  }
]);

angular.module("app").controller("NovoController", ["$scope", "FirebaseService", "$timeout",
  function($scope, FirebaseService, $timeout) {

    var d   = new Date();
    var dtF = (d.getDate() < 10 ? "0" : "") + d.getDate() + "/" + (d.getMonth() + 1 < 10 ? "0" : "") + (d.getMonth() + 1) + "/" + d.getFullYear() + " " + d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();

    $scope.msg = "";
    $scope.title = "";
    $scope.body = "";
    $scope.author = "";
    $scope.dt = dtF;

    $scope.addMessage = function() {
      if ($scope.title && $scope.body) {
        FirebaseService.add({ title: $scope.title, body: $scope.body, author: $scope.author, date: $scope.dt });

        $scope.msg = "Salvo com Sucesso!";
        $timeout(function() {
          $scope.msg = "";
        }, 3000);

        $scope.title = "";
        $scope.body = "";
        $scope.author = "";
        $scope.dt = dtF;
      }
    };
  }
]);
