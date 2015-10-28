angular.module('app').controller('TempoController',
		['$http','$scope',function($http,$scope){
	
	
	$scope.tempo = $http.get('/api/tempo/foz')
	.success( function(data){
		
		$scope.tempo = data;
		console.log(data);
		
	});
	
}]);