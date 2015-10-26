angular.module('app').config( ['$stateProvider','$urlRouterProvider','toastrConfig',
             function($stateProvider, $urlRouterProvider ,toastrConfig) {

	
	
	angular.extend(toastrConfig, {
	    allowHtml: false,
	    closeButton: false,
	    closeHtml: '<button>&times;</button>',
	    extendedTimeOut: 1000,
	    iconClasses: {
	      error: 'toast-error',
	      info: 'toast-info',
	      success: 'toast-success',
	      warning: 'toast-warning'
	    },  
	    messageClass: 'toast-message',
	    onHidden: null,
	    onShown: null,
	    onTap: null,
	    progressBar: false,
	    tapToDismiss: true,
	    templates: {
	      toast: 'directives/toast/toast.html',
	      progressbar: 'directives/progressbar/progressbar.html'
	    },
	    timeOut: 5000,
	    titleClass: 'toast-title',
	    toastClass: 'toast'
	  });
	
	
	
	
	
	
	
	
    $urlRouterProvider.otherwise('/home');

    $stateProvider

      	// HOME ========================================
        .state('home', {
            url: '/home',
            templateUrl: 'app/dashboard/home.html',
            controller:'HomeController'
            
			//resolve: { pageTitle: 'Home' }//exemplo de resolve. a constante pageTitle é avaliada e pode ser injeta no controller.
			
			//There are also optional 'onEnter' and 'onExit' callbacks 
			//that get called when a state becomes active and inactive 
			//respectively. The callbacks also have access to all the 
			//resolved dependencies.
			
			//onEnter: function(pageTitle){
			//    if(pageTitle){ 
			//    	//... do something ...
			//    }
			//},
			
			//onExit: function(pageTitle){
			//    if(pageTitle){ 
			//    	//... do something ... 
			//    }
		    //}

        })

		// ENQUETE =================================
        .state('enquete', {
		        url: '/enquete',
		        templateUrl: 'app/enquete/enquete.html',
				controller:	'EnqueteController'
        })

		// GRAFICO =================================
        .state('grafico', {
		        url: '/grafico',
		        templateUrl: 'app/grafico/grafico.html',
				controller:	'GraficoController'
        })

        // CATEGORIA =================================
        .state('historico', {
		        url: '/categoria',
		        templateUrl: 'app/historico/historico.html',
				controller:	'HistoricoController'
        })

        // AVATAR =================================
        .state('avatar', {
		        url: '/avatar',
		        templateUrl: 'app/avatar/avatar.html',
				controller:	'AvatarController'
        })

		// SOBRE =================================
        .state('sobre', {
            url: '/sobre',
						templateUrl: 'app/dashboard/sobre.html',
						controller:'SobreController'
        })
        
		// LOGIN =================================
        .state('login', {
            url: '/login',
						templateUrl: 'app/login/login.html',
						controller:'LoginController'
        })
        
		// VOTACAO =================================
        .state('votacao', {
            url: '/votacao',
						templateUrl: 'app/votacao/votacao.html',
						controller:'VotacaoController'
        })

        .state('timer', {
            url: '/timer',
						templateUrl: 'app/timer/timer.html',
						controller:'TimerController'
        });
 

}]);








