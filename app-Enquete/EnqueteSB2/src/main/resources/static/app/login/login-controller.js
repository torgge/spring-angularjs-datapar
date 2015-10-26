'use strict';

angular.module('app').controller( 'LoginController', 
		['$rootScope','$scope','$http' , 'toastr','toastrConfig', 
 function ($rootScope, $scope,  $http 	, toastr , toastrConfig) {

    $scope.user = {login:'' , senha:''};

/*	$http.post('autenticar',$scope.user)
	.success( function(data){
	})
	.error( function(erro){
		$rootScope.username='';
		$scope.user={login:'',senha:''};
		$rootScope.token='';
		$http.defaults.headers.common.Authorization = $rootScope.token;
	});
*/
	
    var openedToasts = [];
	
	$scope.entrar = function(){
		
		//var json = angular.fromJson( $scope.user );
		
		$http.post('autenticar' , $scope.user)
		.success( function(data){
			
			$rootScope.username = $scope.user.login;
			$rootScope.token = data;

			openedToasts.push(
			toastr.success(
					 'Bem-vindo!',''
			));

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

