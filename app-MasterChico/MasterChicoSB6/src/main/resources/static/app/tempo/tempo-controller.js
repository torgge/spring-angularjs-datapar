angular.module('app').controller('TempoController',
		['$http','$scope',function($http,$scope){

	var urlBase = 'http://localhost:5000';

	$scope.tempo = $http.get( urlBase+'/api/tempo/foz')
	.success( function(data){

		$scope.tempo = data;

		console.log(data);

	});

}]);
