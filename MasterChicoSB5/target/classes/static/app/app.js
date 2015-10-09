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




