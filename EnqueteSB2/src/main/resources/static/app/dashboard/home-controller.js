angular.module('app')
.controller('HomeController',
	['$scope','$http','$rootScope','toastr','toastrConfig',
	 function($scope,$http,$root,toastr,toastrConfig){
	
	
	$scope.user = {nome:'',senha:''};
	
	$scope.autenticar = function(){
		
		var json = angular.fromJson( $scope.user );
		
		$http.post('autenticar',json)
		.success( function(data){
			
			$root.username = $scope.user.nome;
			$root.token = data;

			//openedToasts.push(
			//toastr.info(
			//		 'Bem-vindo!',
		    //			 'User:'+ $scope.user.nome
			//));



		})
		.error(function(data){
			
			console.log('error');
			
		});
		
		$scope.getLoginLout = function(){
			if( $root.username == undefined ){
				return 'Entrar';
			}else{
				return 'Sair';
			}
		}
		
	};
	
	$scope.abrirLogin = function(){
		$('#login').openModal();
	};
	$scope.abrirLogin();
	
	
}]);