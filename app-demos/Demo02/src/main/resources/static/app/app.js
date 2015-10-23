angular.module("app",[]);

angular.module("app").controller("MainController",function ( $scope, $http ){
	
	$scope.nome = "Lyndon";
	
	//$scope.produtos = [ 
	//                    {id:1,nome:"produto-1"},
	//                    {id:2,nome:"produto-2"}
	//                 ];
	
	$http.get("api/produtos")
	.success( function(data){
		
		$scope.produtos = data;
		
	} );
	
	
	
});