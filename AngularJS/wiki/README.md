# AngularJS
A figura abaixo mostra a estruturação de uma aplicação angular. Sugere também um caminho para implementação da aplicação. 

<p align="center">
<img src="https://github.com/lyndontavares/spring-angularjs-datapar/blob/master/AngularJS/imgs/image_thumb_3DE5D87B.png" width="300">
</p>

Seguindo esta logica, podemos inferir o seguinte:

(I) Uma aplicação angular é uma instância de module, a qual precisamos configurar um nome e uma lista de dependências. Exemplo:

```js
angular.module('app',['ngRoute']);
```

(II) A configuração de rotas de uma aplicação se faz no módulo config. Exemplo.

```js
angular.module('app').config(function($routeProvider) {
		
		$routeProvider

			// rota para home 
			.when('/', {
				templateUrl : 'pages/home.html',
				controller  : 'mainController'
			})

			// rota para about
			.when('/about', {
				templateUrl : 'pages/about.html',
				controller  : 'aboutController'
			})

			// rota para contato
			.when('/contact', {
				templateUrl : 'pages/contact.html',
				controller  : 'contactController'
			});
	});
```

(III) A resposabilidade da camada de Controle fica definida em módulos Controllers. Exemplo:

```js
angular.module('app').('PessoaController',['$http','$scope',function($http,$scope){

	$scope.desenvolvedores = [
	      { id:1, nome:"Lyndon", email:"lyndon.tavares@fatapar.com.py" },
	      { id:2, nome:"George", email:"george.bonespirito@datapar.com.py"}
	]};
	
}]);
```

(IV) A resposabilildae da camada de View fica definida em templates html. 

```js
<body ng-controller="PessoaController">
    
    <h1>Desenvolvedores</h1>
    
    <table>
        <tr>
            <th>Nome</th><th>E-mail</th>
        </tr>
        
        <tr ng-repeat="desenv in desenvolvedores">
            <td>{{desenv.nome}}</td>
            <td>{{desenv.email}}</td>
        </tr>
    </table>

</body>    
```

(V) As diretivas do angular manipulam a DOM dinamicamente pemitindo ajustes nas views para facilitar o trabalho nesta camada.

Criando uma diretiva:

```js

‘use strict’;

angular.module(‘app.directives.customcontrols’, [])

.directive(‘loginControl’, function() {

return {

	template: ‘<h1>Meu primeiro control customizado!</h1>’;

};

});
```

Aplicando uma diretiva:

```html

<body>

<div>

	<h1>Custom Control Sample</h1>

	<login-control></login-control>

</div>

<script src=”app.js”></script><script>

<body>

```

Outro exemplo de diretira:

```js

angular.module('app').directive('helloWorld', function() {

  return {
      restrict: 'AECM',
      replace: 'true',
      template: '<h3>Hello World!!</h3>'
  };

});

```

Para criar seu elemento da directiva, no template, podemos utilizar 4 formas diferentes:

- A: via atributo
- C: via classe
- M: via comentário
- E: via elemento

##### A

```html
<div hello-world></div>
```

##### C

```html
<div class="hello-world"></div>
```

##### M

```html
<!-- directive:hello-world  -->
```

##### E

```html
<hello-world></hello-world>
```



(VI) O objeto $scope serve como cola para transportar o modelo entre view e controller de forma transparente.

```js
angular.module('app').('FaturaController',['$http','$scope','faturaService',function($http,$scope,faturaService){

	$scope.modelo_fatura = { 
			
		usuarioId  : '',
		dataFatura : '',
		planoFinanciacion : { id:'', descricao:''},
		moeda      :  { id:0 , descricao:''};
		tipoFatura :  { id:'', descricao:'',situacion:'' },
		entidade   :  { id:'', descricao:'',situacion:'' },
		vendedor   :  { id:'', descricao:'',situacion:'' },
		pedido     :  { id:'', dataPedido:'',situacion:'');
		
		itensFatura: [
			{
				id:0, 
				mercadoria:{ id:0,descricao:'' } 
				precoUnitarioMoedaGerencial:0.0,
				precoUnitarioMoedaContabil:0.0,
				quantidade: 0.0
			} 
		}],
			
		frete: { valorFrete:0.0, transportador:{id:0,nome:""} }, 

		situacion:''

	};
	
	$scope.criarNovaFatura = function(){
	
		//envia dados da fatura via REST/JSON
		faturaService.inserirfatura( $scope.modelo_fatura )
		.success( function( data ) {
		
			$scope.modelo_fatura = data; //recebe dados da nova fatura
		
		});
		
		
	};
	
}]);
```

(VII) Através de factory criamos factories,services e providers isolando camadas de códigos aumentando reuso dos mesmos  entre módulos da aplicação.

```js
'use strict';

angular.module('app')
    .factory('faturaService', ['$http', function($http) {

    var urlBase = 'api/fatura';
    var dataFactory = {};

    dataFactory.getFatura = function () {
        return $http.get(urlBase);
    };

    dataFactory.getFatura = function (id) {
        return $http.get(urlBase + '/' + id);
    };

    dataFactory.insertFatura = function (merc) {
        return $http.post(urlBase, merc);
    };

    dataFactory.updateFatura = function (merc) {
        return $http.put(urlBase + '/' + merc.ID, merc)
    };

    dataFactory.deleteFatura = function (id) {
        return $http.delete(urlBase + '/' + id);
    };

    return dataFactory;
}]);
```
Para saber mais: https://github.com/lyndontavares/traduz-ai/blob/master/angularjs/001-guia-definitivo-para-aprender-angularjs.md

# Aplicação angular
A aplicação abaixo exemplifica as principais diretivas do angular.

## ng-app
```html
<html ng-app="listaCarros">
    <head>
        <title></title>
        <script src="angular.min.js"></script>
        <script>
            var app = angular.module("listaCarros",[]);
        </script>
    </head>
</html>
``` 

## ng-controller

```html
<html ng-app="listaCarros">
    <head>
        <title></title>
        <script src="angular.min.js"></script>
        <script>
            var app = angular.module("listaCarros",[]);
            app.controller("listaCarrosCtrl",function($scope){ 
            });
        </script>
    </head>
<body ng-controller="listaCarrosCtrl">
</body>
```

## ng-repeat

```html
<html ng-app="listaCarros">
    <head>
        <title></title>
        <script src="angular.min.js"></script>
        <script>
            var app = angular.module("listaCarros",[]);
            app.controller("listaCarrosCtrl",function($scope){ 
                $scope.titulo = "Lista de Carros";
                $scope.carros = [{marca: "Fiat", modelo: "UNO"},
                                 {marca: "VW", modelo: "GOL"},
                                 {marca: "Peugeot", modelo: "207 Passion"}];
            });
        </script>
    </head>
<body ng-controller="listaCarrosCtrl">
    <h1>{{titulo}}</h1>
    <table>
        <tr>
            <th>Marca</th><th>Modelo</th>
        </tr>
        <tr ng-repeat="carro in carros">
            <td>{{carro.marca}}</td><td>{{carro.modelo}}</td>
        </tr>
    </table>
</body>
</html>
```

## ng-model

```html
<html ng-app="listaCarros">
    <head>
        <title></title>
        <script src="angular.min.js"></script>
        <script>
            var app = angular.module("listaCarros",[]);
            app.controller("listaCarrosCtrl",function($scope){ 
                $scope.titulo = "Lista de Carros";
                $scope.carros = [{marca: "Fiat", modelo: "UNO"},
                                 {marca: "VW", modelo: "GOL"},
                                 {marca: "Peugeot", modelo: "207 Passion"}];
                $scope.adicionarCarro = function(carro){
                    $scope.carros.push(angular.copy(carro));
                    delete $scope.carro;
                }
            });
        </script>
    </head>
<body ng-controller="listaCarrosCtrl">
    <h1>{{titulo}}</h1>
    <table>
        <tr>
            <th>Marca</th><th>Modelo</th>
        </tr>
        <tr ng-repeat="carro in carros">
            <td>{{carro.marca}}</td><td>{{carro.modelo}}</td>
        </tr>
    </table>
    <input type="text" ng-model="carro.marca" placeholder="Digite a marca do carro">
    <input type="text" ng-model="carro.modelo" placeholder="Digite o modelo do carro">
    <button ng-click="adicionarCarro(carro)">Adicionar Carro</button>
</body>
</html>
```

## ng-disable

```html
<html ng-app="listaCarros">
    <head>
        <title></title>
        <script src="angular.min.js"></script>
        <script>
            var app = angular.module("listaCarros",[]);
            app.controller("listaCarrosCtrl",function($scope){ 
                $scope.titulo = "Lista de Carros";
                $scope.carros = [{marca: "Fiat", modelo: "UNO"},
                                 {marca: "VW", modelo: "GOL"},
                                 {marca: "Peugeot", modelo: "207 Passion"}];
                $scope.adicionarCarro = function(carro){
                    $scope.carros.push(angular.copy(carro));
                    delete $scope.carro;
                }
            });
        </script>
    </head>
<body ng-controller="listaCarrosCtrl">
    <h1>{{titulo}}</h1>
    <table>
        <tr>
            <th>Marca</th><th>Modelo</th>
        </tr>
        <tr ng-repeat="carro in carros">
            <td>{{carro.marca}}</td><td>{{carro.modelo}}</td>
        </tr>
    </table>
    <input type="text" ng-model="carro.marca" placeholder="Digite a marca do carro">
    <input type="text" ng-model="carro.modelo" placeholder="Digite o modelo do carro">
    <button ng-click="adicionarCarro(carro)" ng-disable="!(carro.marca && carro.modelo)">
        Adicionar Carro
    </button>
</body>
</html>
```

## ng-class

```html
<html ng-app="listaCarros">
    <head>
        <title></title>
        <script src="angular.min.js"></script>
        <script>
            var app = angular.module("listaCarros",[]);
            app.controller("listaCarrosCtrl",function($scope){ 
                $scope.titulo = "Lista de Carros";
                $scope.carros = [{marca: "Fiat", modelo: "UNO"},
                                 {marca: "VW", modelo: "GOL"},
                                 {marca: "Peugeot", modelo: "207 Passion"}];
                $scope.adicionarCarro = function(carro){
                    $scope.carros.push(angular.copy(carro));
                    delete $scope.carro;
                }
            });
        </script>
        <style>
            .selecionado{
                font-weight: bold;
                color: red;
            }
        </style>
    </head>
<body ng-controller="listaCarrosCtrl">
    <h1>{{titulo}}</h1>
    <table>
        <tr>
            <th></th><th>Marca</th><th>Modelo</th>
        </tr>
        <tr ng-class="{selecionado: contato.selecionado}" ng-repeat="carro in carros">
            <td><input type="checkbox" ng-model="contato.selecionado"></td>
            <td>{{carro.marca}}</td>
            <td>{{carro.modelo}}</td>
        </tr>
    </table>
    <input type="text" ng-model="carro.marca" placeholder="Digite a marca do carro">
    <input type="text" ng-model="carro.modelo" placeholder="Digite o modelo do carro">
    <button ng-click="adicionarCarro(carro)" ng-disable="!(carro.marca && carro.modelo)">
        Adicionar Carro
    </button>
</body>
</html>
```

## ng-options

```html
<html ng-app="listaCarros">
    <head>
        <title></title>
        <script src="js/angular.min.js"></script>
        <script>
            var app = angular.module("listaCarros",[]);
            app.controller("listaCarrosCtrl",function($scope){ 
                $scope.titulo = "Lista de Carros";
                $scope.carros = [{marca: "Fiat", modelo: "UNO"},
                                 {marca: "VW", modelo: "GOL"},
                                 {marca: "Peugeot", modelo: "207 Passion"}];
                $scope.adicionarCarro = function(carro){
                    $scope.carros.push(angular.copy(carro));
                    delete $scope.carro;
                }
                $scope.motores = ["1.0","1.4","1.6", "2.0"];
            });
        </script>
        <style>
            .selecionado{
                font-weight: bold;
                color: red;
            }
        </style>
    </head>
<body ng-controller="listaCarrosCtrl">
    <h1>{{titulo}}</h1>
    <table>
        <tr>
            <th></th><th>Marca</th><th>Modelo</th><th>Motor</th>
        </tr>
        <tr ng-class="{selecionado: contato.selecionado}" ng-repeat="carro in carros">
            <td><input type="checkbox" ng-model="contato.selecionado"></td>
            <td>{{carro.marca}}</td>
            <td>{{carro.modelo}}</td>
            <td>{{carro.motor}}</td>
        </tr>
    </table>
    <input type="text" ng-model="carro.marca" placeholder="Digite a marca do carro">
    <input type="text" ng-model="carro.modelo" placeholder="Digite o modelo do carro">
    <select ng-model="carro.motor" ng-options="motor for motor in motores"></select>
    <button ng-click="adicionarCarro(carro)" ng-disable="!(carro.marca && carro.modelo)">
        Adicionar Carro
    </button>
</body>
</html>
```

## ng-show e ng-hide

```html
<html ng-app="listaCarros">
    <head>
        <title></title>
        <script src="js/angular.min.js"></script>
        <script>
            var app = angular.module("listaCarros",[]);
            app.controller("listaCarrosCtrl",function($scope){ 
                $scope.titulo = "Lista de Carros";
                $scope.carros = [{marca: "Fiat", modelo: "UNO"},
                                 {marca: "VW", modelo: "GOL"},
                                 {marca: "Peugeot", modelo: "207 Passion"}];
                $scope.adicionarCarro = function(carro){
                    $scope.carros.push(angular.copy(carro));
                    delete $scope.carro;
                }
                $scope.motores = ["1.0","1.4","1.6", "2.0"];
            });
        </script>
        <style>
            .selecionado{
                font-weight: bold;
                color: red;
            }
        </style>
    </head>
<body ng-controller="listaCarrosCtrl">
    <h1>{{titulo}}</h1>
    <table ng-show="carros.lenght>0">
        <tr>
            <th></th><th>Marca</th><th>Modelo</th><th>Motor</th>
        </tr>
        <tr ng-class="{selecionado: contato.selecionado}" ng-repeat="carro in carros">
            <td><input type="checkbox" ng-model="contato.selecionado"></td>
            <td>{{carro.marca}}</td>
            <td>{{carro.modelo}}</td>
            <td>{{carro.motor}}</td>
        </tr>
    </table>
    <input type="text" ng-model="carro.marca" placeholder="Digite a marca do carro">
    <input type="text" ng-model="carro.modelo" placeholder="Digite o modelo do carro">
    <select ng-model="carro.motor" ng-options="motor for motor in motores"></select>
    <button ng-click="adicionarCarro(carro)" ng-disable="!(carro.marca && carro.modelo)">
        Adicionar Carro
    </button>
</body>
</html>
```
