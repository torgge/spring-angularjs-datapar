
app.controller('EnqueteController',['$scope','$http',function($scope,$http){

  $scope.enquete = [];
  $scope.enqueteIdx  = undefined;

  $http.get('api/questionario.json')
  .success( function(data){
      $scope.enquete = data;
      $scope.enqueteIdx = 0
  });

  $scope.avancar=function(){
    if ( $scope.enqueteIdx < $scope.enquete.length-1  ){
      $scope.enqueteIdx++;
    }
  };

  $scope.voltar=function(){
    if ( $scope.enqueteIdx > 0 ){
      $scope.enqueteIdx--;
    }

  }
