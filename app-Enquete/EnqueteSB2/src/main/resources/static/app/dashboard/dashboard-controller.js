angular.module('app').controller('DashController',[
          '$scope','$state','$http','$rootScope',
 function ($scope,  $state , $http , $rootScope ){

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
		
		if ( $rootScope.username == undefined || $rootScope.username == '' ) {
			$state.go('login');
		}
		else
		{
			$state.go(url);
		}
	}

}]);
