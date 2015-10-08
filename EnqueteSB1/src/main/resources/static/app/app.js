'use strict';

angular.module('app',[
	 'ui.materialize',
	 'ui.router',
	 'ui.grid',
	 'ui.grid.pagination',
	 'ui.grid.cellNav',
	 'ngAnimate'
]);


angular.module('app').run(['$http','$rootScope', function($http,$rootScope){
	$rootScope.username='';
	$rootScope.token='@#$%¨&';
	$http.defaults.headers.common.Authorization = $rootScope.token;
}]);



angular.module('app').config( ['$stateProvider','$urlRouterProvider',
             function($stateProvider, $urlRouterProvider ) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider

      	// HOME ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'app/view/home.html',
            controller:'HomeController'	
        })

				// ENQUETE =================================
        .state('enquete', {
		        url: '/enquete',
		        templateUrl: 'app/view/enquete.html',
						controller:	'EnqueteController',
        })

				// GRAFICO =================================
        .state('grafico', {
		        url: '/grafico',
		        templateUrl: 'app/view/grafico.html',
						controller:	'GraficoController'
        })

        // CATEGORIA =================================
        .state('historico', {
		        url: '/categoria',
		        templateUrl: 'app/view/historico.html',
						controller:	'HistoricoController'
        })

				// ENVIAR ENQUETE =================================
        .state('enviar', {
		        url: '/enviar',
		        templateUrl: 'app/view/enqueteenviar.html',
						controller:	'EnqueteEnviarController'
        })

				// SOBRE =================================
        .state('sobre', {
            url: '/sobre',
						templateUrl: 'app/view/sobre.html',
						controller:'SobreController'
        });

}]);








