'use strict';

angular.module(
	'app',
	[
	 'ui.materialize',
	 'ui.router',
	 'ui.grid',
	 'ui.grid.pagination',
	 'ui.grid.cellNav',
	 'ngAnimate'
	] );


angular.module('app').config( ['$stateProvider','$urlRouterProvider',
             function($stateProvider, $urlRouterProvider ) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider

        // HOME ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'app/dashboard/home.html'
        })

		// MERCADERIA =================================
        .state('mercaderia', {
		        url: '/mercaderia',
		        templateUrl: 'app/mercadoria/mercaderia.html',
						controller:	'MercaderiaController'
        })

        // CATEGORIA =================================
        .state('categoria', {
		        url: '/categoria',
		        templateUrl: 'app/categoria/categoria.html',
						controller:	'CategoriaController'
        })

		// UNIDADE =================================
        .state('unidade', {
		        url: '/unidade',
		        templateUrl: 'app/unidade/unidade.html',
						controller:	'UnidadeController'
        })

		// SOBRE =================================
        .state('sobre', {
            url: '/sobre',
        templateUrl: 'app/dashboard/sobre.html'
        });

}]);




