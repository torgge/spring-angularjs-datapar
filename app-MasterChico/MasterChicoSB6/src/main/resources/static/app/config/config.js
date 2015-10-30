
'use strict'

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
        .state('mercadoria', {
		        url: '/mercadoria',
		        templateUrl: 'app/mercadoria/mercadoria.html',
						controller:	'MercadoriaController'
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

        // GRAFICO =================================
        .state('pedido', {
		        url: '/pedido',
		        templateUrl: 'app/pedido/pedido.html',
				controller:	'PedidoController'
        })

        // GRAFICO =================================
        .state('fornecedor', {
		        url: '/fornecedor',
		        templateUrl: 'app/fornecedor/fornecedor.html',
				controller:	'FornecedorController'
        })

        // GRAFICO =================================
        .state('grafico', {
		        url: '/grafico',
		        templateUrl: 'app/grafico/grafico.html',
				controller:	'GraficoController'
        })


		// SOBRE =================================
        .state('sobre', {
            url: '/sobre',
        templateUrl: 'app/dashboard/sobre.html'
        });

}]);