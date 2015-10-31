'use strict';

angular.module('app').controller('FornecedorController',
        ['$scope','$http','uiGridConstants', 'FornecedorService',
function ($scope,$http,uiGridConstants,FornecedorService){

	FornecedorService.getFornecedores()
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
      { name: 'nome' , field:'nome'  , displayName:'Nome'	, enableColumnMenu: false }
    ]
  };

}]);