'use strict';

angular.module('app').controller('CategoriaController',
				 ['$scope','$http','uiGridConstants', 'CategoriaService',
         function ($scope , $http , uiGridConstants , CategoriaService ){


  $scope.model={};

  CategoriaService.getCategorias()
	.success(function (data) {
		 $scope.gridOptions.data = data;
  });

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
			  '<div class="ui-grid-cell-contents pull-right"> ' +
			  '<a class=" waves-effect " ng-click="delCategoria( row.entity.id )"><i class="material-icons">delete</i></a>'+
			  '</div>', width: 70, enableColumnMenu: false, enableSorting: false, enableFiltering:false
      }

    ]
  };

	$scope.deletar = function(id){
	  CategoriaService.deletetCategoria(id)
		.success(function (data) {
			 //gravou
	  });
	};


	$scope.gravar = function(){
	  CategoriaService.insertCategoria($scope.model)
		.success(function (data) {
			 //gravou
	  });
	};

	$scope.alterar = function(){
	  CategoriaService.updateCategoria($scope.model)
		.success(function (data) {
			 //gravou
	  });
	};

}]);
