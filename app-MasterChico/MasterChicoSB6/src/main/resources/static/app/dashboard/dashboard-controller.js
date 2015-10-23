'use strict';

angular.module('app').controller('DashController',['$scope','$state','$http',function ($scope,$state,$http){

	//esta funcao direciona para ulr especifica.
	//permite executar código para fechar o sidebar antes de direcionar.
	//nessesário somente para o materialize.

	$scope.direciona = function(url){
		$('.button-collapse').sideNav('hide');
		$state.go(url);
	}

}]);
