 // var source = new EventSource('api/event');
 // source.onmessage = function(e){
	//  document.getElementById('xxx').innerHTML = e.data;
	  //console.log('oi');
//  };



angular.module('app').controller('EnqueteController',['$scope','$http',
                                                      function($scope,$http){

  $scope.enquete = [];

  $http.get('api/enquete/ativa')
  .success( function(data){
      $scope.enquete = data;
  });


  $scope.progresso='';
  $scope.atualizarProgresso = function(){
	  var marcada=0;
	  var i;
	  for (i=0;i<$scope.enquete.length;i++){
		  if ( $scope.enquete[i].resposta ){
			  marcada++;
		  }
	  };
	  
	  var estilo = marcada/$scope.enquete.length*100;
	  
	  $scope.progresso = estilo;
	  
	  return estilo.toString();
  };
  
  $scope.enviarEnquete = function() {
	  
	  var json = angular.fromJson( $scope.enquete );
	  
	  console.log(json);
	  
	  $http.post( '/api/enquete/gravar' ,json )
	  .then( function(response){
	  });
  };
  
 

 
  
  
  
}]);

