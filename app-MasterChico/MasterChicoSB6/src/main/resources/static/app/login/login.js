'use strict';

angular.module('app').controller( 'LoginController', 
		['$rootScope','$scope','$http' , 'toastr','toastrConfig','$state', 
 function ($rootScope, $scope,  $http 	, toastr , toastrConfig, $state) {

    $scope.user = {login:'' , senha:''};

 
*/
	
    var openedToasts = [];
	
	$scope.entrar = function(){
 
		
		$http.post('autenticar' , $scope.user)
		.success( function(data){
			
			$rootScope.username = $scope.user.login;
			$rootScope.token = data;

			console.log(data);
			
			openedToasts.push(
			toastr.success(
					 'Bem-vindo!',''
			));
			
			$state.go('home');

		})
		.error(function(data){
		    $scope.user = {login:'' , senha:''};
			openedToasts.push(
						toastr.error(
							 'Bem-vindo!',
			    			 'User:'+ $scope.user.nome
						));
			console.log('error');
		});
		
	};
	
}]);