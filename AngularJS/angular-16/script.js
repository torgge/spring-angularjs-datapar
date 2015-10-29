angular.module('app', [])

  .controller('ctrl',function($scope, $timeout, $sce) {
    $scope.immediate = { video: $sce.trustAsResourceUrl("sintel-trailer") };

    $timeout(function(){ //Simulate a request
      $scope.delayed = {
        pic: "Car-Club-1",
        video: $sce.trustAsResourceUrl("sintel-trailer")
      };
    },1000);

    $scope.getVideoUrl = function(video, ext) {
      return video && $sce.trustAsResourceUrl("http://corrupt-system.de/assets/media/sintel/" + video + ext);
    };
    
    $scope.showProps = function(id) {
      console.log(document.getElementById(id).getElementsByTagName('source'));
    };
  });
