'use strict';

angular.module('app').controller('CategoriaController',
				 ['$scope','$http','uiGridConstants', 'CategoriaService', 'ngFoobar',
         function ($scope , $http , uiGridConstants , CategoriaService , ngFoobar ){

	$scope.model={id:0,descricao:""};

 	$scope.refresh = function() {
		  CategoriaService.getCategorias()
		  .success( function (data) {
				 $scope.gridOptions.data = data;
		  });
	};

	$scope.refresh();



	$scope.gridOptions = {
	    enableFiltering: true,
	    enablePaginationControls: true,
	    enableGridMenu: false,
	    paginationPageSize: 10,
	    paginationPageSizes: [10, 20, 30],
	    columnDefs: [
	      { name: 'id'				, field:'id'				 , displayName:'Código'			, width: 80 , enableColumnMenu: false },

	      { name: 'descricao' , field:'descricao'  , displayName:'Descrición'	, enableColumnMenu: false },

				{ name: 'acao'       , field:'id.codigo' , displayName:'Ação',
				 	               cellTemplate:
				  '<div align="center"> ' +
				  '<a class=" waves-effect " ng-click="deletar( row.entity.id )" ng-controller="CategoriaController"><i class="material-icons">delete</i></a>'+
				  '</div>', width: 60, enableColumnMenu: false, enableSorting: false, enableFiltering:false
	      }

	    ]
	};

	$scope.deletar = function(id){
	    console.log(id);
		CategoriaService.deleteCategoria(id)
		.success(function (data) {
			$scope.refresh();
		})
		.error(function (data) {
			ngFoobar.show('error', 'Erro ao deletar categoria id:'+id );
			
		});
	};

	$scope.gravar = function(){
		CategoriaService.insertCategoria($scope.model)
			.success(function (data) {
			$scope.refresh();
		});
		
	};

	$scope.alterar = function(){
		CategoriaService.updateCategoria($scope.model)
			.success(function (data) {
			$scope.refresh();
		});
	};

}]);
