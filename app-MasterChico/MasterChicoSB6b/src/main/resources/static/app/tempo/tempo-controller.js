angular.module('app').controller('TempoController',
		['$http','$scope',function($http,$scope){

	var urlBase = 'http://localhost:5000';

	$tempo={temperatura:undefined,icon:'/image/10d.png'};

  /* Via Java
	$scope.tempo = $http.get( urlBase+'/api/tempo/foz')
	.success( function(data){

		$scope.tempo = data;

		console.log(data);

	});
  */

	//direto via ajax
	$scope.tempo = $http.get( 'http://api.openweathermap.org/data/2.5/weather?q=Foz do Iguacu&mode=json&units=metric&appid=bd82977b86bf27fb59a04b61b657fb6f')
	.success( function(data){

		$scope.tempo.temperatura = data.main.temp;
		$scope.tempo.icon = 'http://openweathermap.org/img/w/'+data.weather[0].icon+'.png';

		console.log(data);
		console.log($scope.tempo);

	});


}]);
