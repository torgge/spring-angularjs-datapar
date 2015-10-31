## Sistema de Enquetes

Applicativo do curso de Spring/AngularJS na Datapar SA em Ciudad del Este, set/out/nov de 2015.

### (I) Objetivo

Construir uma aplicação web responsive com Spring Boot, AngularJS e Materialize utilizando os padrões RESTFul, WebSocket, SSE e JWT.

### (II) Diagrama de classe

<p align="center">
<img src="https://github.com/lyndontavares/spring-angularjs-datapar/blob/master/app-Enquete/EnqueteSB2/src/main/java/com/idomine/appquizzer/model/uml/diagrama5.png" width="600">
</p>

### (III) App Enquetes

Permitir que o grupo desenvolva e gerencie votações e questionários. O ponto central é o controle da votação. Este recurso usará a tecnologia de SSE (Server-Sent Events) para manter atualizado o placar da votação. 

### (IV) Papeis

Administrador: Criar e gerenciar votações e questinários.
Participante: Participar de votações e questionários.

### (V) Tela de preencimento de questionário

<p align="center">
<img src="https://camo.githubusercontent.com/5af12318c1633b6ec9a8564c01ebdae4953e2640/687474703a2f2f6c796e646f6e746176617265732e6769746875622e696f2f696d616765732f323031352d31302d30355f32322d33322d31342e706e67" width="600">
</p>


### (VI) SSE - Server-Sent Events

Web Sockets não é a única novidade na área de conectividade. Baseado em protocolo HTTP, outra boa novidade são os Server Sent Events (SSEs) que quer dizer, em tradução livre, eventos do lado do servidor. Ele cria um canal de comunicação simples e enxuto buscando por informações novas sempre que necessário. Trata-se sim do polling mencionado anteriormente, mas leve e enxuto, pois é controlado nativamente pelo navegador, substituindo linhas e mais linhas de JavaScripts e AJAXs.

Leia mais em: Programando em HTML5 http://www.devmedia.com.br/programando-em-html5/31040#ixzz3pgmsrlv4

Para usar essa tecnologia com o Spring Boot é necessário atualizar para o Spring MVC 4.2. Nossa app de enquetes já está atualizada.


#### (VI-a) Exemplo de implementação SSE em AngularJS

```js

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
	};

```

#### (VI-b) Exemplo de implementação do server com Spring

```java

	@RequestMapping("/event")
	public SseEmitter getRealTimeMessageAction( HttpServletRequest request) throws Throwable {
		final SseEmitter emitter = new SseEmitter();
		emitter.send( listaEnquetesAtivas() );
		emitter.complete();
		return emitter;
	}

```
