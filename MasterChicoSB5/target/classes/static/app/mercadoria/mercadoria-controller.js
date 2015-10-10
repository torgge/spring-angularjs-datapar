'use strict';

angular.module('app').controller('MercaderiaController',['$scope','$http','uiGridConstants', function ($scope,$http,uiGridConstants){

	$http.get('/api/mercaderia/geral')
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
						{ name: 'unidade'		, field:'unidade.descricao'	 , displayName:'Unidade'   ,  enableColumnMenu: false },
						{ name: 'categoria'	, field:'categoria.descricao'	 , displayName:'Categoria',  enableColumnMenu: false }
			    ]
	};

}]);
