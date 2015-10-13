angular.module('app')
.controller('HomeController',['$scope','$http','$rootScope',function($scope,$http,$root){
	
	
	$scope.user = {nome:'',senha:''};
	
	$scope.autenticar = function(){
		
		var json = angular.fromJson( $scope.user );
		
		$http.post('autenticar',json)
		.success( function(data){
			
			$root.username = $scope.user.nome;
			$root.token = data;
			
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
	
	
}]);
