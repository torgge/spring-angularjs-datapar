'use strict';

var app = angular.module(
	'app',
	[
	 'ui.materialize',
	 'ui.router',
	 'ui.grid',
	 'ui.grid.pagination',
	 'ui.grid.cellNav',
	 'ngAnimate'
	] );


app.config( ['$stateProvider','$urlRouterProvider',
             function($stateProvider, $urlRouterProvider ) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider

        // HOME ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'app/view/home.html'
        })

		// MERCADERIA =================================
        .state('mercaderia', {
		        url: '/mercaderia',
		        templateUrl: 'app/view/mercaderia.html',
						controller:	'MercaderiaController'
        })

        // CATEGORIA =================================
        .state('categoria', {
		        url: '/categoria',
		        templateUrl: 'app/view/categoria.html',
						controller:	'CategoriaController'
        })

				// UNIDADE =================================
        .state('unidade', {
		        url: '/unidade',
		        templateUrl: 'app/view/unidade.html',
						controller:	'UnidadeController'
        })

				// SOBRE =================================
        .state('sobre', {
            url: '/sobre',
        templateUrl: 'app/view/sobre.html'
        });

}]);


app.controller('DashController',['$http',function ($http){

}]);


app.controller('UnidadeController',['$scope','$http','uiGridConstants', function ($scope,$http,uiGridConstants){

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



app.controller('CategoriaController',['$scope','$http','uiGridConstants', function ($scope,$http,uiGridConstants){

	$http.get('/api/categoria')
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


app.controller('MercaderiaController',['$scope','$http','uiGridConstants', function ($scope,$http,uiGridConstants){

	$http.get('/api/mercaderia')
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
