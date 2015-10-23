'use strict';

angular.module('app').controller('UnidadeController',['$scope','$http','uiGridConstants', function ($scope,$http,uiGridConstants){

	$http.get('/api/unidade')
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
      { name: 'descricao' , field:'descricao'  , displayName:'Descrición'	, enableColumnMenu: false }
    ]
  };

}]);

