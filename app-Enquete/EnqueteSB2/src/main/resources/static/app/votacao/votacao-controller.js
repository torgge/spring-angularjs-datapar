'use strict';
angular.module('app').controller( 'VotacaoController',[
        '$http','$scope','VotacaoService', 'EnqueteService',
function($http,$scope, VotacaoService, EnqueteService){
	
	//model
	$scope.model = {
			enquete:{id:'',descricao:'',situacao:''},
			pergunta:{id:'',descricao:'',situacao:''},
			opcoes:[],
			participantes: [{id:'',nome:'',resposta:''}]
	};
	
	//selecionar enquete ativa
	EnqueteService.getAtiva()
	.sucess( function(data){
		$scope.model = data;
	});
	
	//selecionar questão
	
	//configurar votacao
	
	//iniciar votacao
	
	//pausar votacao
	
	//cancelar votacao
	
	//confirmar votacao

	//enviar mensagem
	
	//bloquear chat
	
	//liberar chat
	
	//mostrar resultado
	
	
	
	$scope.msg="*";

	$scope.addMsg = function(event) {
		$scope.$apply(function () {
			console.log(event.data);
			$scope.msg = event.data;			
		});
	};

	$scope.openMsg = function(event) {
		console.log('open>'+event);
	};
	
	$scope.stop = function(){
		if ( $scope.eventSource != undefined ){
			$scope.msg='';
			$scope.eventSource.close();
		}	
	};
	
	$scope.start = function(){
		$scope.eventSource = new EventSource("api/event");

		$scope.eventSource.addEventListener("message",$scope.addMsg,false);  

		$scope.eventSource.addEventListener("open",$scope.openMsg,false);  
		
		/*$scope.eventSource.onmessage = function(event) {
			$scope.$apply(function () {
					console.log(event.data);
					$scope.msg = event.data;			
			});
		};*/	
	};
	
	
	
	
	
	
	
}]);