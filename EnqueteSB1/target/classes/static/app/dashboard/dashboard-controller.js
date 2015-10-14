angular.module('app').controller('DashController',['$scope','$state','$http',function ($scope,$state,$http){

	//Show sideNav - Working...
	//$('.button-collapse').sideNav('show');

	// Hide sideNav - Working...
	$scope.sobre = function(){
		$('.button-collapse').sideNav('hide');
		$state.go('sobre');
	}

	//esta funcao direcion para ulr especificas e
	//permite executar código antes de direcionar.
	//poderia ser usado para executar codigo customizado
	//de autorizacao do usuario
	$scope.direciona = function(url){
		$('.button-collapse').sideNav('hide');
		$state.go(url);
	}

}]);
