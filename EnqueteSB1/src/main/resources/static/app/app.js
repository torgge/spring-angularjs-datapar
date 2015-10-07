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


app.run(['$http', function($http){
		$http.defaults.headers.common.Authorization = '@#$%¨&';	
}]);


app.config( ['$stateProvider','$urlRouterProvider',
             function($stateProvider, $urlRouterProvider ) {

    $urlRouterProvider.otherwise('/home');

    $stateProvider

      	// HOME ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'app/view/home.html'
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


app.controller('DashController',['$scope','$state','$http',function ($scope,$state,$http){

	//Show sideNav - Working...
	//$('.button-collapse').sideNav('show');

	// Hide sideNav - Working...
	$scope.sobre = function(){
		$('.button-collapse').sideNav('hide');
		$state.go('sobre');
	}

	//esta funcao direcion para ulr especificas e
	//permite executar código antes de direcionar.
	//poderia ser usado para executar codigo customizado
	//de autorizacao do usuario
	$scope.direciona = function(url){
		$('.button-collapse').sideNav('hide');
		$state.go(url);
	}

}]);

app.controller('EnqueteController',['$scope','$http',function($scope,$http){

  $scope.enquete = [];

  $http.get('api/enquete/ativa')
  .success( function(data){
      $scope.enquete = data;
  });


  $scope.progresso='';
  $scope.atualizarProgresso = function(){
	  var marcada=0;
	  var i;
	  for (i=0;i<$scope.enquete.length;i++){
		  if ( $scope.enquete[i].resposta ){
			  marcada++;
		  }
	  };
	  
	  var estilo = marcada/$scope.enquete.length*100;
	  
	  $scope.progresso = estilo;
	  
	  return estilo.toString();
  };
  
  $scope.enviarEnquete = function() {
	  
	  var json = angular.fromJson( $scope.enquete );
	  
	  $http.post( '/api/enquete/gravar' ,json )
	  .then( function(response){
	  });
  };
  
  
}]);

app.controller('HistoricoController',['$scope','$http',function($scope,$http){
}]);

app.controller('GraficoController',['$scope','$http',function($scope,$http){
}]);


app.controller('EnqueteEnviarController',['$scope','$http',function($scope,$http){
}]);


app.controller('SobreController',['$scope','$http',function($scope,$http){
}]);
