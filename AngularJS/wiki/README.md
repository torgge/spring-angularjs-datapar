# AngularJS

<img src="https://github.com/lyndontavares/spring-angularjs-datapar/blob/master/AngularJS/imgs/image_thumb_3DE5D87B.png" width="300">


# Aplicação angular
O resumo abaixo mostra as tags mais usuais e os componentes angular.

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
